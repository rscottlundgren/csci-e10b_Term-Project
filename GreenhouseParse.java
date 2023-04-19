
// import javax.swing.text.html.parser.*;
import javax.swing.text.html.*;
import javax.swing.text.*;
// import java.io.*;
// import java.util.Arrays;

public class GreenhouseParse extends HTMLEditorKit.ParserCallback {

    // Instance State
    private boolean isValidApplyNowButtonTag = false;
    private boolean isValidPositionTitleTag = false;
    private boolean isValidCompanyNameTag = false;
    private boolean isValidViewAllJobsTag = false;
    private boolean isValidPositionLocationTag = false;
    private boolean isValidHeaderGroupTag = false;
    private boolean isValidContentGroupTag = false;
    private boolean isValidHTag = false;
    private boolean isValidLITag = false;
    private boolean isValidPTag = false;
    private boolean isValidSTRONGTag = false;
    private boolean isValidATag = false;
    private boolean isValidDIVTag = false;

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
        emptyCharArray(data);
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
        System.out.println("Position:\t" + title + " ");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- Methods To Handle Company Name -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidCompanyNameStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidCompanyNameTag = false;
        if (tag.equals(HTML.Tag.SPAN) && mas.containsAttribute(HTML.Attribute.CLASS, "company-name")) {
            isValidCompanyNameTag = true;
        }
        return isValidCompanyNameTag;
    }

    private boolean isValidCompanyNameEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.SPAN);
    }

    private void formatCompanyName(char[] data) {
        char[] companyName = new char[data.length - 3];
        for (int i = 3; i < data.length; i++) {
            companyName[i - 3] = data[i];
        }
        System.out.println("Company:\t" + new String(companyName));
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle "View All Jobs" Link --------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidViewAllJobsStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidViewAllJobsTag = false;
        if (tag.equals(HTML.Tag.A) && mas.containsAttribute("data-mapped", "true")) {
            isValidViewAllJobsTag = true;
        }
        return isValidViewAllJobsTag;
    }

    private boolean isValidViewAllJobsEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.A);
    }

    private void formatViewAllJobsLink(char[] data) {
        emptyCharArray(data);
        System.out.print(data);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ----------------- Methods To Handle Position Location ----------------- *
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
        System.out.println("Location:\t" + location);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ----------------- Methods To Handle Header Group Tags ----------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidHeaderGroupStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidHeaderGroupTag = false;
        if (tag.equals(HTML.Tag.DIV) && mas.containsAttribute(HTML.Attribute.ID, "header")) {
            isValidHeaderGroupTag = true;
        }
        return isValidHeaderGroupTag;
    }

    private boolean isValidHeaderGroupEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.DIV);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ----------------- Methods To Handle Content Group Tags ---------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidContentGroupStartTag(HTML.Tag tag, MutableAttributeSet mas) {
        boolean isValidContentGroupTag = false;
        if (tag.equals(HTML.Tag.DIV) && mas.containsAttribute(HTML.Attribute.ID, "content")) {
            isValidContentGroupTag = true;
        }
        return isValidContentGroupTag;
    }

    private boolean isValidContentGroupEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.DIV);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ----------- Methods To Handle H1, H2, H3, H4, H5, & H6 Tags ----------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidHStartTag(HTML.Tag tag) {
        boolean isValidHTag = false;
        if (tag.equals(HTML.Tag.H1) ||
                tag.equals(HTML.Tag.H2) ||
                tag.equals(HTML.Tag.H3) ||
                tag.equals(HTML.Tag.H4) ||
                tag.equals(HTML.Tag.H5) ||
                tag.equals(HTML.Tag.H6)) {
            isValidHTag = true;
        }
        return isValidHTag;
    }

    private boolean isValidHEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.H1) ||
                tag.equals(HTML.Tag.H2) ||
                tag.equals(HTML.Tag.H3) ||
                tag.equals(HTML.Tag.H4) ||
                tag.equals(HTML.Tag.H5) ||
                tag.equals(HTML.Tag.H6);
    }

    private void formatHTag(char[] data) {
        String transformedArray = new String(data).toLowerCase();
        char[] textHTag = transformedArray.toCharArray();
        textHTag[0] = Character.toUpperCase(textHTag[0]);
        for (int i = 0; i < textHTag.length; i++) {
            if (textHTag[i] == ' ') {
                textHTag[i + 1] = Character.toUpperCase(textHTag[i + 1]);
            }
        }
        String contentSectionHeading = new String(textHTag);
        System.out.println("\n" + contentSectionHeading);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle List Item (LI) Tags ---------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidLIStartTag(HTML.Tag tag) {
        boolean isValidLITag = false;
        if (tag.equals(HTML.Tag.LI)) {
            isValidLITag = true;
        }
        return isValidLITag;
    }

    private boolean isValidLIEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.LI);
    }

    private void formatLITag(char[] data) {
        String listItem = new String(data);
        System.out.println("\t\u23FA " + listItem);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle Paragraph (P) Tags ----------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidPTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.P);
    }

    private void formatPTag(char[] data) {
        String paragraph = new String(data);
        System.out.println("\n" + paragraph);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle Strong (STRONG) Tags --------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidSTRONGTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.STRONG);
    }

    private void formatSTRONGTag(char[] data) {
        String strong = new String(data);
        System.out.print(" " + strong + " ");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle A (A) Tags --------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidATag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.A);
    }

    private void formatATag(char[] data) {
        String a = new String(data);
        System.out.print(" " + a + " ");
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- Methods To Handle Div (DIV) Tags --------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private boolean isValidDIVStartTag(HTML.Tag tag) {
        boolean isValidDIVTag = false;
        if (tag.equals(HTML.Tag.DIV)) {
            isValidDIVTag = true;
        }
        return isValidDIVTag;
    }

    private boolean isValidDIVEndTag(HTML.Tag tag) {
        return tag.equals(HTML.Tag.DIV);
    }

    private void formatDIVTag(char[] data) {
        String div = new String(data);
        System.out.println(div);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------- Overridden `HTMLEditorKit.ParserCallback` Class Methods ------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mas, int pos) {
        if (isValidHeaderGroupStartTag(tag, mas)) {
            isValidHeaderGroupTag = true;
        }
        if (isValidApplyNowButtonStartTag(tag, mas)) {
            isValidApplyNowButtonTag = true;
        }
        if (isValidPositionTitleStartTag(tag, mas)) {
            isValidPositionTitleTag = true;
        }
        if (isValidCompanyNameStartTag(tag, mas)) {
            isValidCompanyNameTag = true;
        }
        if (isValidViewAllJobsStartTag(tag, mas)) {
            isValidViewAllJobsTag = true;
        }
        if (isValidPositionLocationStartTag(tag, mas)) {
            isValidPositionLocationTag = true;
        }
        if (isValidContentGroupStartTag(tag, mas)) {
            isValidContentGroupTag = true;
        }
        if (isValidHStartTag(tag)) {
            isValidHTag = true;
        }
        if (isValidLIStartTag(tag)) {
            isValidLITag = true;
        }
        if (isValidPTag(tag)) {
            isValidPTag = true;
        }
        if (isValidSTRONGTag(tag)) {
            isValidSTRONGTag = true;
        }
        if (isValidATag(tag)) {
            isValidATag = true;
        }
        if (isValidDIVStartTag(tag)) {
            isValidDIVTag = true;
        }
    }

    public void handleEndTag(HTML.Tag tag, int pos) {
        if (isValidHeaderGroupEndTag(tag)) {
            isValidHeaderGroupTag = false;
        }
        if (isValidApplyNowButtonEndTag(tag)) {
            isValidApplyNowButtonTag = false;
        }
        if (isValidPositionTitleEndTag(tag)) {
            isValidPositionTitleTag = false;
        }
        if (isValidCompanyNameEndTag(tag)) {
            isValidCompanyNameTag = false;
        }
        if (isValidViewAllJobsEndTag(tag)) {
            isValidViewAllJobsTag = false;
        }
        if (isValidPositionLocationEndTag(tag)) {
            isValidPositionLocationTag = false;
        }
        if (isValidContentGroupEndTag(tag)) {
            isValidContentGroupTag = false;
        }
        if (isValidHEndTag(tag)) {
            isValidHTag = false;
        }
        if (isValidLIEndTag(tag)) {
            isValidLITag = false;
        }
        if (isValidPTag(tag)) {
            isValidPTag = false;
        }
        if (isValidSTRONGTag(tag)) {
            isValidSTRONGTag = false;
        }
        if (isValidATag(tag)) {
            isValidATag = false;
        }
        if (isValidDIVEndTag(tag)) {
            isValidDIVTag = false;
        }
    }

    public void handleText(char[] data, int pos) {
        if (isValidHeaderGroupTag) {
            if (isValidApplyNowButtonTag) {
                formatApplyNowButton(data);
            } else if (isValidPositionTitleTag) {
                formatPositionTitle(data);
            } else if (isValidCompanyNameTag) {
                formatCompanyName(data);
            } else if (isValidViewAllJobsTag) {
                formatViewAllJobsLink(data);
            } else if (isValidPositionLocationTag) {
                formatPositionLocation(data);
            } else {
                System.out.println(data);
            }
        }
        if (isValidContentGroupTag) {
            if (isValidHTag) {
                formatHTag(data);
            } else if (isValidLITag) {
                formatLITag(data);
            } else if (isValidPTag) {
                formatPTag(data);
            } else if (isValidSTRONGTag) {
                formatSTRONGTag(data);
            } else if (isValidATag) {
                formatATag(data);
            } else if (isValidDIVTag) {
                formatDIVTag(data);
            }
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------------- Miscellaneous Helper Methods --------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    private void emptyCharArray(char[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = '\0';
        }
    }
}
