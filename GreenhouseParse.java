
// import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
// import java.io.*;
// import java.util.Arrays;

public class GreenhouseParse extends HTMLEditorKit.ParserCallback {

    // Method State
    private boolean isValidIndividualTag = false;
    private boolean isValidGroupTag = false;
    private boolean isValidApplyNowButtonTag = false;
    private boolean isValidPositionTitleTag = false;
    private boolean isValidPositionLocationTag = false;

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ----------------- Methods To Handle "Apply Now" Button ---------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidApplyNowButtonStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidApplyNowButtonTag = false;
        if (tag.equals(HTML.Tag.A) && mas.containsAttribute(HTML.Attribute.ID, "apply_button")) {
            isValidApplyNowButtonTag = true;
        }
        return isValidApplyNowButtonTag;
    }

    private boolean isValidApplyNowButtonEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.A);
    }

    private void formatApplyNowButton(char[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = '\0';
        }
        System.out.print(data);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- Methods To Handle Position Title ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidPositionTitleStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidPositionTitleTag = false;
        if (tag.equals(HTML.Tag.H1) && mas.containsAttribute(HTML.Attribute.CLASS, "app-title")) {
            isValidPositionTitleTag = true;
        }
        return isValidPositionTitleTag;
    }

    private boolean isValidPositionTitleEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.H1);
    }

    private void formatPositionTitle(char[] data) {
        String title = new String(data);
        System.out.print("" + title + " ");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------ Methods To Handle Position Location ---------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidPositionLocationStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidPositionLocationTag = false;
        if (tag.equals(HTML.Tag.DIV) && mas.containsAttribute(HTML.Attribute.CLASS, "location")) {
            isValidPositionLocationTag = true;
        }
        return isValidPositionLocationTag;
    }

    private boolean isValidPositionLocationEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.DIV);
    }

    private void formatPositionLocation(char[] data) {
        String location = new String(data);
        System.out.println("Location: " + location);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * --------------------- Methods To Handle Group Tags -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidGroupStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidStartTag = false;
        if (tag.equals(HTML.Tag.DIV) && (mas.containsAttribute(HTML.Attribute.ID, "header") || mas
                .containsAttribute(HTML.Attribute.ID, "content"))) {
            isValidStartTag = true;
        }
        return isValidStartTag;
    }

    private boolean isValidGroupEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.DIV);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------ Methods To Handle Individual Tags ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidIndividualTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.H1) ||
                tag.equals(HTML.Tag.H2) ||
                tag.equals(HTML.Tag.H3) ||
                tag.equals(HTML.Tag.SPAN) ||
                tag.equals(HTML.Tag.P) ||
                tag.equals(HTML.Tag.UL) ||
                tag.equals(HTML.Tag.DIV);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------- Overridden `HTMLEditorKit.ParserCallback` Class Methods ------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mas, int pos) {
        if (isValidGroupStartTag(tag, mas)) {
            isValidGroupTag = true;
        }
        if (isValidApplyNowButtonStartTag(tag, mas)) {
            isValidApplyNowButtonTag = true;
        }
        if (isValidPositionTitleStartTag(tag, mas)) {
            isValidPositionTitleTag = true;
        }
        if (isValidPositionLocationStartTag(tag, mas)) {
            isValidPositionLocationTag = true;
        }
        if (isValidIndividualTag(tag)) {
            isValidIndividualTag = true;
        }
    }

    public void handleEndTag(HTML.Tag tag, int pos) {
        if (isValidGroupEndTag(tag)) {
            isValidGroupTag = false;
        }
        if (isValidApplyNowButtonEndTag(tag)) {
            isValidApplyNowButtonTag = false;
        }
        if (isValidPositionTitleEndTag(tag)) {
            isValidPositionTitleTag = false;
        }
        if (isValidPositionLocationEndTag(tag)) {
            isValidPositionLocationTag = false;
        }
        if (isValidIndividualTag(tag)) {
            isValidIndividualTag = false;
        }
    }

    public void handleText(char[] data, int pos) {
        if (isValidGroupTag && isValidIndividualTag) {
            if (isValidApplyNowButtonTag) {
                formatApplyNowButton(data);
            } else if (isValidPositionTitleTag) {
                formatPositionTitle(data);
            } else if (isValidPositionLocationTag) {
                formatPositionLocation(data);
            } else {
                System.out.println(data);
            }
        }
    }
}
