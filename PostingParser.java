import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.lang.NullPointerException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.util.Enumeration;

/**
 * The "PostingParser" class is responsible for executing the larger program by
 * initializing a new instance of the "PostingParserFrontEnd" class which
 * begins the process of building the application for the User to interact with.
 */
public class PostingParser {

    private static final String TITLE = "Posting Parser";

    public static void main(String[] args) {
        System.out.printf(
                "\"Posting Parser\" program executed; creating instance of \"PostingParserFrontEnd\" class...\n");
        PostingParserFrontEnd fe = new PostingParserFrontEnd(TITLE);
        fe.setVisible(true);
    }
}

/**
 * The "PostingParserFrontEnd" class is responsible for the creation of the
 * Front End / User Interface that the User will interact with when using the
 * application. It creates the various components and Event Listeners which
 * will allow the User to execute various actions allowing them to get analyze
 * word counts with as much or as little specificity as they desire.
 */
class PostingParserFrontEnd extends JFrame {

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -- BEGIN - "PostingParserFrontEnd" Class State Variables & Constants -- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    // Color
    private final Color CLR_BTN_BACKGROUND_RELEASED = new Color(223, 223, 116);
    private final Color CLR_BTN_BACKGROUND_PRESSED = new Color(150, 255, 200);
    private final Color CLR_BTN_BORDER_RELEASED = new Color(242, 242, 46);
    private final Color CLR_BTN_BORDER_PRESSED = new Color(75, 175, 100);
    private final Color CLR_DEFAULT_BACKGROUND = new Color(37, 38, 43);
    private final Color CLR_DEFAULT_TEXT_HEAD = new Color(75, 175, 100);
    private final Color CLR_DEFAULT_TEXT_BODY = new Color(200, 200, 200);
    private final Color CLR_ERROR_BORDER = new Color(175, 25, 25);
    private final Color CLR_ERROR_MESSAGE = new Color(225, 0, 0);
    private final Color CLR_ERROR_TITLE = new Color(225, 0, 0);
    private final Color CLR_SUCCESS_BORDER = new Color(0, 109, 37);
    private final Color CLR_SUCCESS_MESSAGE = new Color(0, 139, 51);
    private final Color CLR_SUCCESS_TITLE = new Color(0, 139, 51);
    private final Color CLR_FORM_PROMPT = new Color(150, 255, 200);
    private final Color CLR_COUNT_EQUALS_1 = new Color(200, 200, 200);
    private final Color CLR_COUNT_EQUALS_2 = new Color(187, 210, 164);
    private final Color CLR_COUNT_EQUALS_3 = new Color(175, 219, 132);
    private final Color CLR_COUNT_EQUALS_4 = new Color(161, 229, 94);
    private final Color CLR_COUNT_EQUALS_5 = new Color(150, 237, 64);
    private final Color CLR_COUNT_6_OR_MORE = new Color(127, 255, 0);

    // Border
    private final Border BRDR_BTN_PRESSED = BorderFactory.createLineBorder(CLR_BTN_BORDER_PRESSED, 3, true);
    private final Border BRDR_BTN_RELEASED = BorderFactory.createLineBorder(CLR_BTN_BORDER_RELEASED, 3, true);
    private Border lineBorder_UserMessage;

    // Dimension
    private final Dimension W0000_H0037 = new Dimension(0, 37);
    private final Dimension W0000_H0050 = new Dimension(0, 50);
    private final Dimension W0050_H0800 = new Dimension(50, 800);
    private final Dimension W0080_H0000 = new Dimension(80, 0);
    private final Dimension W0100_H0000 = new Dimension(100, 0);
    private final Dimension W0170_H0025 = new Dimension(170, 25);
    private final Dimension W0260_H0000 = new Dimension(260, 0);
    private final Dimension W0300_H0050 = new Dimension(300, 50);
    private final Dimension W0600_H0050 = new Dimension(600, 50);
    private final Dimension W0650_H0100 = new Dimension(650, 100);
    private final Dimension W0700_H0030 = new Dimension(700, 30);
    private final Dimension W0700_H0050 = new Dimension(700, 50);
    private final Dimension W0700_H0060 = new Dimension(700, 60);
    private final Dimension W0700_H0150 = new Dimension(700, 150);
    private final Dimension W0700_H0200 = new Dimension(700, 200);
    private final Dimension W0700_H0350 = new Dimension(700, 350);
    private final Dimension W0700_H0400 = new Dimension(700, 400);
    private final Dimension W0700_H0800 = new Dimension(700, 800);
    private final Dimension W0800_H0050 = new Dimension(800, 50);
    private final Dimension W0800_H0100 = new Dimension(800, 100);
    private final Dimension W0800_H0950 = new Dimension(800, 950);
    private final Dimension W1600_H0050 = new Dimension(1600, 50);
    private final Dimension W1600_H0950 = new Dimension(1600, 950);

    // Font
    private final Font HELVETICA_36 = new Font("Helvetica", 1, 36);
    private final Font HELVETICA_24 = new Font("Helvetica", 1, 24);
    private final Font HELVETICA_18 = new Font("Helvetica", 1, 18);
    private final Font HELVETICA_14 = new Font("Helvetica", 1, 14);

    // Integer
    private final int ALIGN_CENTER = SwingConstants.CENTER;
    private final int ALIGN_LEFT = SwingConstants.LEFT;
    private final int COLUMN = BoxLayout.PAGE_AXIS;
    private final int HEIGHT = 1000;
    private final int MESSAGE_TYPE_ERROR = 0;
    private final int MESSAGE_TYPE_SUCCESS = 1;
    private final int ROW = BoxLayout.LINE_AXIS;
    private final int WIDTH = 1600;
    private final int X_NEVER = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
    private final int Y_AS_NEEDED = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;

    // JButton
    private JButton btn_MenuBar_ParseNewJD = new JButton();
    private JButton btn_MenuBar_ParseOldJD = new JButton();
    private JButton btn_MenuBar_UpdateFW = new JButton();
    private JButton btn_ParsedNewJD_MiniForm_Append = new JButton();
    private JButton btn_ParsedNewJD_MiniForm_Remove = new JButton();
    private JButton btn_ParseNewJD_FormField_Browse = new JButton();
    private JButton btn_ParseNewJD_FormField_Submit = new JButton();
    private JButton btn_ParsedOldJD_MiniForm_Append = new JButton();
    private JButton btn_ParsedOldJD_MiniForm_Remove = new JButton();
    private JButton btn_ParseOldJD_FormField_Browse = new JButton();
    private JButton btn_ParseOldJD_FormField_Submit = new JButton();
    private JButton btn_UpdateFW_MiniForm_Append = new JButton();
    private JButton btn_UpdateFW_MiniForm_Remove = new JButton();

    // JFileChooser
    private JFileChooser fc_ParseNewJD = new JFileChooser();
    private JFileChooser fc_ParseOldJD = new JFileChooser();

    // JLabel
    private JLabel lbl_Left_Header = new JLabel();
    private JLabel lbl_ParsedNewJD_MiniForm_Append = new JLabel();
    private JLabel lbl_ParsedNewJD_MiniForm_Remove = new JLabel();
    private JLabel lbl_ParseNewJD_FormPrompt_FilePath = new JLabel();
    private JLabel lbl_ParseNewJD_FormPrompt_URL = new JLabel();
    private JLabel lbl_ParsedOldJD_MiniForm_Append = new JLabel();
    private JLabel lbl_ParsedOldJD_MiniForm_Remove = new JLabel();
    private JLabel lbl_ParseOldJD_FormPrompt_FilePath = new JLabel();
    private JLabel lbl_Right_Header = new JLabel();
    private JLabel lbl_UpdateFW_MiniForm_Append = new JLabel();
    private JLabel lbl_UpdateFW_MiniForm_Remove = new JLabel();

    // JPanel
    private JPanel pnl_Left_East = new JPanel();
    private JPanel pnl_Left_North = new JPanel();
    private JPanel pnl_Left_South = new JPanel();
    private JPanel pnl_Left_West = new JPanel();
    private JPanel pnl_MainDisplay = new JPanel();
    private JPanel pnl_MainDisplay_Left = new JPanel();
    private JPanel pnl_MainDisplay_Right = new JPanel();
    private JPanel pnl_MenuBar = new JPanel();
    private JPanel pnl_ParsedNewJD = new JPanel();
    private JPanel pnl_ParsedNewJD_MiniForm = new JPanel();
    private JPanel pnl_ParsedNewJD_MiniForm_Row_Button = new JPanel();
    private JPanel pnl_ParsedNewJD_MiniForm_Row_Field = new JPanel();
    private JPanel pnl_ParsedNewJD_MiniForm_Row_Prompt = new JPanel();
    private JPanel pnl_ParsedNewJD_UserMessage_Inner = new JPanel();
    private JPanel pnl_ParsedNewJD_UserMessage_Outer = new JPanel();
    private JPanel pnl_ParsedNewJD_WordResults = new JPanel();
    private JPanel pnl_ParsedOldJD = new JPanel();
    private JPanel pnl_ParsedOldJD_MiniForm = new JPanel();
    private JPanel pnl_ParsedOldJD_MiniForm_Row_Button = new JPanel();
    private JPanel pnl_ParsedOldJD_MiniForm_Row_Field = new JPanel();
    private JPanel pnl_ParsedOldJD_MiniForm_Row_Prompt = new JPanel();
    private JPanel pnl_ParsedOldJD_UserMessage_Inner = new JPanel();
    private JPanel pnl_ParsedOldJD_UserMessage_Outer = new JPanel();
    private JPanel pnl_ParsedOldJD_WordResults = new JPanel();
    private JPanel pnl_ParseNewJD = new JPanel();
    private JPanel pnl_ParseNewJD_Form = new JPanel();
    private JPanel pnl_ParseNewJD_FormField_FilePath = new JPanel();
    private JPanel pnl_ParseNewJD_FormField_Submit = new JPanel();
    private JPanel pnl_ParseNewJD_FormField_URL = new JPanel();
    private JPanel pnl_ParseNewJD_FormPrompt_FilePath = new JPanel();
    private JPanel pnl_ParseNewJD_FormPrompt_URL = new JPanel();
    private JPanel pnl_ParseNewJD_UserMessage_Inner = new JPanel();
    private JPanel pnl_ParseNewJD_UserMessage_Outer = new JPanel();
    private JPanel pnl_ParseOldJD = new JPanel();
    private JPanel pnl_ParseOldJD_Form = new JPanel();
    private JPanel pnl_ParseOldJD_FormButton_Submit = new JPanel();
    private JPanel pnl_ParseOldJD_FormField_FilePath = new JPanel();
    private JPanel pnl_ParseOldJD_FormPrompt_FilePath = new JPanel();
    private JPanel pnl_ParseOldJD_UserMessage_Inner = new JPanel();
    private JPanel pnl_ParseOldJD_UserMessage_Outer = new JPanel();
    private JPanel pnl_ParseOldJD_WordResults = new JPanel();
    private JPanel pnl_Right_Center = new JPanel();
    private JPanel pnl_Right_East = new JPanel();
    private JPanel pnl_Right_North = new JPanel();
    private JPanel pnl_Right_South = new JPanel();
    private JPanel pnl_Right_West = new JPanel();
    private JPanel pnl_UpdateFW = new JPanel();
    private JPanel pnl_UpdateFW_MiniForm = new JPanel();
    private JPanel pnl_UpdateFW_MiniForm_Row_Button = new JPanel();
    private JPanel pnl_UpdateFW_MiniForm_Row_Field = new JPanel();
    private JPanel pnl_UpdateFW_MiniForm_Row_Prompt = new JPanel();
    private JPanel pnl_UpdateFW_UserMessage_Inner = new JPanel();
    private JPanel pnl_UpdateFW_UserMessage_Outer = new JPanel();
    private JPanel pnl_UpdateFW_WordResults = new JPanel();

    // JScrollPane
    private JScrollPane sp_Left_Center = new JScrollPane();
    private JScrollPane sp_ParsedNewJD_WordResults = new JScrollPane();
    private JScrollPane sp_ParsedOldJD_WordResults = new JScrollPane();
    private JScrollPane sp_UpdateFW_WordResults = new JScrollPane();

    // JTextArea
    private JTextArea ta_Left_Center = new JTextArea();
    private JTextArea ta_ParsedNewJD_UserMessage = new JTextArea();
    private JTextArea ta_ParsedOldJD_UserMessage = new JTextArea();
    private JTextArea ta_ParseNewJD_UserMessage = new JTextArea();
    private JTextArea ta_ParseOldJD_UserMessage = new JTextArea();
    private JTextArea ta_UpdateFW_UserMessage = new JTextArea();

    // JTextField
    private JTextField tf_ParsedNewJD_MiniForm_Append = new JTextField();
    private JTextField tf_ParsedNewJD_MiniForm_Remove = new JTextField();
    private JTextField tf_ParsedOldJD_MiniForm_Append = new JTextField();
    private JTextField tf_ParsedOldJD_MiniForm_Remove = new JTextField();
    private JTextField tf_ParseNewJD_FormField_FilePath = new JTextField();
    private JTextField tf_ParseNewJD_FormField_URL = new JTextField();
    private JTextField tf_ParseOldJD_FormField_FilePath = new JTextField();
    private JTextField tf_UpdateFW_MiniForm_Append = new JTextField();
    private JTextField tf_UpdateFW_MiniForm_Remove = new JTextField();

    // LayoutManager
    private final LayoutManager LM_BORDER_LEFT = new BorderLayout();
    private final LayoutManager LM_BORDER_RIGHT = new BorderLayout();
    private final LayoutManager LM_FLOW_CTR_00 = new FlowLayout(FlowLayout.CENTER);
    private final LayoutManager LM_FLOW_CTR_01 = new FlowLayout(FlowLayout.CENTER);
    private final LayoutManager LM_FLOW_LFT_00 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_FLOW_LFT_01 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_FLOW_LFT_02 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_FLOW_LFT_03 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_FLOW_LFT_04 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_FLOW_LFT_05 = new FlowLayout(FlowLayout.LEFT);
    private final LayoutManager LM_GRID_R0_C4_UPD_FW = new GridLayout(0, 4);
    private final LayoutManager LM_GRID_R0_C4_NEW_JD = new GridLayout(0, 4);
    private final LayoutManager LM_GRID_R0_C4_OLD_JD = new GridLayout(0, 4);
    private final LayoutManager LM_GRID_R1_C2 = new GridLayout(1, 2);
    private final LayoutManager LM_GRID_R1_C3 = new GridLayout(1, 3);
    private final LayoutManager LM_GRIDBAG_00 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_01 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_02 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_03 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_04 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_05 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_06 = new GridBagLayout();
    private final LayoutManager LM_GRIDBAG_07 = new GridBagLayout();

    // String
    private final String BUTTON_TEXT_PARSE_NEW_JD = "Parse New Job Description";
    private final String BUTTON_TEXT_PARSE_OLD_JD = "Parse Old Job Description";
    private final String BUTTON_TEXT_UPDATE_FW = "Update 'Forbidden Words' List";
    private final String BUTTON_TEXT_BROWSE = "    Browse    ";
    private final String BUTTON_TEXT_PARSE = "    Parse The Description!    ";
    private final String ERROR_FILE_PATH_MISSING_MESSAGE = "A File Path / Save Location is required to run the program. Please provide a local directory where the program can save files.";
    private final String ERROR_FILE_PATH_MISSING_TITLE = "Missing File Path";
    private final String ERROR_INVALID_URL_MESSAGE = "This program can only parse Greenhouse ATS domains (i.e. `boards.greenhouse.io`). Please provide a valid Job Description with a Greenhouse ATS domain.";
    private final String ERROR_INVALID_URL_TITLE = "URL Domain Not Supported";
    private final String ERROR_URL_AND_FILE_PATH_MISSING_MESSAGE = "A URL & File Path are required to run the program. Please provide an entry in both fields and click the 'Parse The Description!' button.";
    private final String ERROR_URL_AND_FILE_PATH_MISSING_TITLE = "Missing URL & File Path";
    private final String ERROR_URL_MISSING_MESSAGE = "A URL is required to run the program. Please provide a valid job description URL to be parsed by the application.";
    private final String ERROR_URL_MISSING_TITLE = "Missing URL";
    private final String ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_MESSAGE = "A word was not provided in the appropriate text field below. Please provide a word in the appropriate text field and reattempt removal.";
    private final String ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_MESSAGE = "A word was not provided in the appropriate text field below. Please provide a word in the appropriate text field and reattempt appending.";
    private final String ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_TITLE = "Provided Word Not In List";
    private final String ERROR_WORD_EXISTS_IN_FW_LIST_TITLE = "Provided Word Exists In List";
    private final String ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_MESSAGE = "Please double-check your submission for errors against the \"Forbidden Words\" list above and reattempt removal.";
    private final String ERROR_WORD_EXISTS_IN_FW_LIST_MESSAGE = "Please double-check your submission for errors against the \"Forbidden Words\" list above and reattempt appending.";
    private final String ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_TITLE = "\"Remove\" Text Field Is Empty";
    private final String ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_TITLE = "\"Append\" Text Field Is Empty";
    private final String PARSE_NEW_JD_EXPLANATION_TEXT = "To use this application:\n\n1) Provide the application with a directory location to create a 'Job Search' folder in your local machine.\n\n2) Provide the URL of a job posting that you would like the program to parse\n\n(NOTE: At present, the program can only parse those jobs for companies that are using Greenhouse as their ATS - Applicant Tracking System).\n\nOnce you've provided this information, click the 'Parse The Description!' button and the program will either alert you to an error or, if no errors are present, will parse the posting.\n\nUpon parsing completion this window will refresh with a two panel display - on the left will be the parsed job description and on the right will be the words used in the job description with counts for the number of times that word was used in the posting.";
    private final String PARSE_NEW_JD_HEADING = "Parsing A New Job Description";
    private final String PARSE_OLD_JD_EXPLANATION_TEXT = "To use this application:\n\n1) Provide the application with the location of a specific job description's 'ParsedJD.txt' file within the 'PostingParser' directory in your local machine.\n\nOnce you've provided this information, click the 'Parse The Description!' button and the program will either alert you to an error or, if no errors are present, will parse the posting.\n\nUpon parsing completion this window will refresh with a two panel display - on the left will be the parsed job description and on the right will be the words used in the job description with counts for the number of times that word was used in the posting.";
    private final String PARSE_OLD_JD_HEADING = "Parsing An Old Job Description";
    private final String PROMPT_TEXT_DESCRIPTION_URL = "2) Paste Job Description URL To Parse:";
    private final String PROMPT_TEXT_SELECT_LOCATION = "1) Select Save Location:";
    private final String BUTTON_MINI_FORM_APPEND_AND_RERUN = "    Append & Rerun    ";
    private final String BUTTON_MINI_FORM_REMOVE_AND_RERUN = "    Remove & Rerun    ";
    private final String UPDATE_FW_EXPLANATION_TEXT = "You may find that you'll want to either append or remove words from the \"Forbidden Words\" list as some words are appearing too often (remove) or other words - previously deemed unimportant / unhelpful - are now considered insightful / helpful (append). Amending the \"Forbidden Words\" list periodically should help provide you with guidance on tailoring your application materials (i.e. resume, cover letter, etc.).\n\nYou can make these changes by using the form provided underneath the present iteration of your \"Forbidden Words\" list on the right-hand side of your screen.\n\n1) Enter a word into the appropriate text field (depending on whether you want the word appended or removed from the list).\n\n2) Click the button underneath the text field.\n\nOnce clicked, the list will refresh with your word either appended to or removed from the \"Forbidden Words\" list.\n\nNOTE: You'll want to make sure that the word is spelled exactly as it has been spelled in the list (in the case of removal) or spelled exactly as it has been spelled in the job description (in the case of appending).";
    private final String UPDATE_FW_HEADING = "Update \"Forbidden Words\" List";
    private final String SUCCESS_WORD_REMOVED_FROM_FW_LIST_TITLE = "SUCCESS - Word Removed";
    private final String SUCCESS_WORD_APPENDED_TO_FW_LIST_TITLE = "SUCCESS - Word Appended";
    private final String SUCCESS_WORD_REMOVED_FROM_FW_LIST_MESSAGE = "Word removed from both the \"Forbidden Words\" list & the `ForbiddenWords.txt` document.";
    private final String SUCCESS_WORD_APPENDED_TO_FW_LIST_MESSAGE = "Word appended to both the \"Forbidden Words\" list & the `ForbiddenWords.txt` document.";
    private final String PARSED_JD_HEADING = "Parsed Job Description";

    // TitledBorder
    private TitledBorder titledBorder_UserMessage;

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -- CLOSE - "PostingParserFrontEnd" Class State Variables & Constants -- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------- BEGIN - ANSI Constants For Custom Terminal Formatting -------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    // Reset
    private final String RESET = "\033[0m";

    // Regular Colors
    // private final String BLACK = "\033[0;30m";
    // private final String RED = "\033[0;31m";
    // private final String GREEN = "\033[0;32m";
    // private final String YELLOW = "\033[0;33m";
    // private final String BLUE = "\033[0;34m";
    // private final String PURPLE = "\033[0;35m";
    // private final String CYAN = "\033[0;36m";
    // private final String WHITE = "\033[0;37m";

    // Bold
    // private final String BLACK_BOLD = "\033[1;30m";
    private final String RED_BOLD = "\033[1;31m";
    // private final String GREEN_BOLD = "\033[1;32m";
    // private final String YELLOW_BOLD = "\033[1;33m";
    // private final String BLUE_BOLD = "\033[1;34m";
    // private final String PURPLE_BOLD = "\033[1;35m";
    // private final String CYAN_BOLD = "\033[1;36m";
    // private final String WHITE_BOLD = "\033[1;37m";

    // Underline
    // private final String BLACK_UNDERLINED = "\033[4;30m";
    // private final String RED_UNDERLINED = "\033[4;31m";
    // private final String GREEN_UNDERLINED = "\033[4;32m";
    // private final String YELLOW_UNDERLINED = "\033[4;33m";
    // private final String BLUE_UNDERLINED = "\033[4;34m";
    // private final String PURPLE_UNDERLINED = "\033[4;35m";
    // private final String CYAN_UNDERLINED = "\033[4;36m";
    // private final String WHITE_UNDERLINED = "\033[4;37m";

    // Background
    // private final String BLACK_BACKGROUND = "\033[40m";
    // private final String RED_BACKGROUND = "\033[41m";
    // private final String GREEN_BACKGROUND = "\033[42m";
    // private final String YELLOW_BACKGROUND = "\033[43m";
    // private final String BLUE_BACKGROUND = "\033[44m";
    // private final String PURPLE_BACKGROUND = "\033[45m";
    // private final String CYAN_BACKGROUND = "\033[46m";
    // private final String WHITE_BACKGROUND = "\033[47m";

    // High Intensity
    // private final String BLACK_BRIGHT = "\033[0;90m";
    // private final String RED_BRIGHT = "\033[0;91m";
    // private final String GREEN_BRIGHT = "\033[0;92m";
    private final String YELLOW_BRIGHT = "\033[0;93m";
    // private final String BLUE_BRIGHT = "\033[0;94m";
    // private final String PURPLE_BRIGHT = "\033[0;95m";
    // private final String CYAN_BRIGHT = "\033[0;96m";
    // private final String WHITE_BRIGHT = "\033[0;97m";

    // Bold High Intensity
    // private final String BLACK_BOLD_BRIGHT = "\033[1;90m";
    private final String RED_BOLD_BRIGHT = "\033[1;91m";
    private final String GREEN_BOLD_BRIGHT = "\033[1;92m";
    // private final String YELLOW_BOLD_BRIGHT = "\033[1;93m";
    // private final String BLUE_BOLD_BRIGHT = "\033[1;94m";
    // private final String PURPLE_BOLD_BRIGHT = "\033[1;95m";
    // private final String CYAN_BOLD_BRIGHT = "\033[1;96m";
    // private final String WHITE_BOLD_BRIGHT = "\033[1;97m";

    // High Intensity backgrounds
    // private final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";
    // private final String RED_BACKGROUND_BRIGHT = "\033[0;101m";
    // private final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";
    // private final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";
    // private final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";
    // private final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";
    // private final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";
    // private final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    // Terminal Messages
    private final String ATTEMPTING_TO_REMOVE_WORD = YELLOW_BRIGHT
            + "Attempting to remove \"%s\" from the \"Forbidden Words\" List...\n" + RESET;
    private final String ATTEMPTING_TO_APPEND_WORD = YELLOW_BRIGHT
            + "Attempting to append \"%s\" to the \"Forbidden Words\" List...\n" + RESET;
    private final String UNABLE_TO_REMOVE_AN_EMPTY_STRING = RED_BOLD_BRIGHT
            + "ACTION FAILED - Unable to remove an empty String from the \"Forbidden Words\" List"
            + RESET;
    private final String UNABLE_TO_APPEND_AN_EMPTY_STRING = RED_BOLD_BRIGHT
            + "ACTION FAILED - Unable to append an empty String to the \"Forbidden Words\" List"
            + RESET;
    private final String SUCCESSFULLY_REMOVED_WORD = GREEN_BOLD_BRIGHT
            + "ACTION SUCCESSFUL - \"%s\" successfully removed from the \"Forbidden Words\" List\n" + RESET;
    private final String SUCCESSFULLY_APPENDED_WORD = GREEN_BOLD_BRIGHT
            + "ACTION SUCCESSFUL - \"%s\" successfully appended to the \"Forbidden Words\" List\n" + RESET;
    private final String UNABLE_TO_REMOVE_WORD = RED_BOLD_BRIGHT
            + "ACTION FAILED - Unable to remove \"%s\" from the \"Forbidden Words\" List\n" + RESET;
    private final String UNABLE_TO_APPEND_WORD = RED_BOLD_BRIGHT
            + "ACTION FAILED - Unable to append \"%s\" to the \"Forbidden Words\" List\n" + RESET;

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------- CLOSE - ANSI Constants For Custom Terminal Formatting -------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ BEGIN - Constructor(s) ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The zero-argument `PostingParserFrontEnd()` constructor method returns
     * no values, creating an instance of the "PostingParserFrontEnd" class by
     * instantiating the "PostingParserBackEnd" class and executing both the
     * `layoutComponents()` and `addListeners()` methods.
     */
    public PostingParserFrontEnd() {
        String title = "Posting Parser";
        PostingParserBackEnd ppbe = new PostingParserBackEnd();
        layoutComponents(title);
        addListeners(ppbe);
    }

    /**
     * The single-argument `PostingParserFrontEnd()` constructor method accepts
     * a single String parameter - 'title' - returning no values. The method
     * creates an instance of the "PostingParserFrontEnd" class by
     * instantiating the "PostingParserBackEnd" class and executing both the
     * `layoutComponents()` and `addListeners()` methods.
     * 
     * @param title A String parameter representing the name of the application
     *              will appear in the top-center of the overall application
     *              window.
     */
    public PostingParserFrontEnd(String title) {
        PostingParserBackEnd ppbe = new PostingParserBackEnd();
        layoutComponents(title);
        addListeners(ppbe);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ CLOSE - Constructor(s) ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------- BEGIN - `layoutComponents()` & `addListeners()` Methods ------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `layoutComponents()` method accepts a single String parameter -
     * 'title' - returning no values. The method instantiates the format of the
     * main window / JFrame, building the constituent components that will be
     * used by the application to display the necessary information to the User.
     * 
     * @param title A String parameter representing the name of the application
     *              will appear in the top-center of the overall application
     *              window.
     */
    private void layoutComponents(String title) {
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        constructMenuBar();
        this.add(pnl_MenuBar, BorderLayout.NORTH);

        setJPanelFormatWithNonBoxLayout(
                pnl_MainDisplay,
                LM_GRID_R1_C2,
                W1600_H0950,
                null);
        this.add(pnl_MainDisplay, BorderLayout.CENTER);

        constructLeftPanel();
        pnl_MainDisplay.add(pnl_MainDisplay_Left);

        constructPanelParseNewJD();
        constructPanelParsedNewJD();
        constructPanelParseOldJD();
        constructPanelParsedOldJD();
        constructPanelUpdateFW();

        constructRightPanel();
        pnl_MainDisplay.add(pnl_MainDisplay_Right);
    }

    /**
     * The `addListeners()` method accepts a single PostingParserBackEnd
     * parameter - 'ppbe' - returning no values. The method instantiates all of
     * the Event Listeners that will allow a User to interact with the program.
     * 
     * @param ppbe An instance of the PostingParserBackEnd class that allows
     *             the instantiated listeners to interact with the class.
     */
    private void addListeners(PostingParserBackEnd ppbe) {

        /**
         * The `removeParsedNewJDInnerUserMessagePanel` is an "ActionListener"
         * that is configured to remove all of the inner components from the
         * User Message component and then repaint the empty outer component to
         * reflect the change in sub-components.
         */
        ActionListener removeParsedNewJDInnerUserMessagePanel = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pnl_ParsedNewJD_UserMessage_Outer.removeAll();
                revalidateAndRepaintJPanel(pnl_ParsedNewJD_UserMessage_Outer);
            }
        };

        /**
         * The `removeParsedOldJDInnerUserMessagePanel` is an "ActionListener"
         * that is configured to remove all of the inner components from the
         * User Message component and then repaint the empty outer component to
         * reflect the change in sub-components.
         */
        ActionListener removeParsedOldJDInnerUserMessagePanel = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pnl_ParsedOldJD_UserMessage_Outer.removeAll();
                revalidateAndRepaintJPanel(pnl_ParsedOldJD_UserMessage_Outer);
            }
        };

        /**
         * The `removeParseNewJDInnerUserMessagePanel` is an "ActionListener"
         * that is configured to remove all of the inner components from the
         * User Message component and then repaint the empty outer component to
         * reflect the change in sub-components.
         */
        ActionListener removeParseNewJDInnerUserMessagePanel = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pnl_ParseNewJD_UserMessage_Outer.removeAll();
                revalidateAndRepaintJPanel(pnl_ParseNewJD_UserMessage_Outer);
            }
        };

        /**
         * The `removeParseOldJDInnerUserMessagePanel` is an "ActionListener"
         * that is configured to remove all of the inner components from the
         * User Message component and then repaint the empty outer component to
         * reflect the change in sub-components.
         */
        ActionListener removeParseOldJDInnerUserMessagePanel = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pnl_ParseOldJD_UserMessage_Outer.removeAll();
                revalidateAndRepaintJPanel(pnl_ParseOldJD_UserMessage_Outer);
            }
        };

        /**
         * The `removeUpdateFWInnerUserMessagePanel` is an "ActionListener"
         * that is configured to remove all of the inner components from the
         * User Message component and then repaint the empty outer component to
         * reflect the change in sub-components.
         */
        ActionListener removeUpdateFWInnerUserMessagePanel = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                pnl_UpdateFW_UserMessage_Outer.removeAll();
                revalidateAndRepaintJPanel(pnl_UpdateFW_UserMessage_Outer);
            }
        };

        /**
         * An "ActionListener" is added to `btn_MenuBar_ParseNewJD` which
         * allows the button, when clicked, to visually "deactivate" the other
         * buttons and change the JFrame to show the components associated with
         * the "Parse New Job Description" screen.
         */
        btn_MenuBar_ParseNewJD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!ppbe.getParseNewJDWindowIsActive()) {

                    // Change Menu Bar Button Colors
                    ppbe.setParseNewJDWindowIsActive(
                            true);
                    refreshJButtonFormat(
                            btn_MenuBar_ParseNewJD,
                            true);
                    ppbe.setParseOldJDWindowIsActive(
                            false);
                    refreshJButtonFormat(
                            btn_MenuBar_ParseOldJD,
                            false);
                    ppbe.setUpdateFWWindowIsActive(
                            false);
                    refreshJButtonFormat(
                            btn_MenuBar_UpdateFW,
                            false);

                    // Update Left Panel Heading & Explanation Text
                    refreshJLabelFormat(
                            lbl_Left_Header,
                            PARSE_NEW_JD_HEADING);
                    setJTextAreaFormat(
                            ta_Left_Center,
                            HELVETICA_24,
                            CLR_DEFAULT_TEXT_BODY);
                    refreshJTextAreaFormat(
                            ta_Left_Center,
                            PARSE_NEW_JD_EXPLANATION_TEXT);

                    // Update Right Panel Heading & Explanation Text
                    pnl_Right_Center.removeAll();
                    pnl_Right_Center.add(pnl_ParseNewJD);
                    revalidateAndRepaintJPanel(pnl_Right_Center);
                }
            }
        });

        /**
         * An "ActionListener" is added to `btn_MenuBar_ParseOldJD` which
         * allows the button, when clicked, to visually "deactivate" the other
         * buttons and change the JFrame to show the components associated with
         * the "Parse Old Job Description" screen.
         */
        btn_MenuBar_ParseOldJD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!ppbe.getParseOldJDWindowIsActive()) {

                    // Change Menu Bar Button Colors
                    ppbe.setParseNewJDWindowIsActive(
                            false);
                    refreshJButtonFormat(
                            btn_MenuBar_ParseNewJD,
                            false);
                    ppbe.setParseOldJDWindowIsActive(
                            true);
                    refreshJButtonFormat(
                            btn_MenuBar_ParseOldJD,
                            true);
                    ppbe.setUpdateFWWindowIsActive(
                            false);
                    refreshJButtonFormat(
                            btn_MenuBar_UpdateFW,
                            false);

                    // Update Left Panel Heading & Explanation Text
                    refreshJLabelFormat(
                            lbl_Left_Header,
                            PARSE_OLD_JD_HEADING);
                    setJTextAreaFormat(
                            ta_Left_Center,
                            HELVETICA_24,
                            CLR_DEFAULT_TEXT_BODY);
                    refreshJTextAreaFormat(
                            ta_Left_Center,
                            PARSE_OLD_JD_EXPLANATION_TEXT);

                    // Update Right Panel Heading & Explanation Text
                    pnl_Right_Center.removeAll();
                    pnl_Right_Center.add(pnl_ParseOldJD);
                    revalidateAndRepaintJPanel(pnl_Right_Center);
                }
            }
        });

        /**
         * An "ActionListener" is added to `btn_MenuBar_UpdateFW` which allows
         * the button, when clicked, to visually "deactivate" the other buttons
         * and change the JFrame to show the components associated with the
         * "Update 'Forbidden Words' List" screen.
         */
        btn_MenuBar_UpdateFW.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!ppbe.getUpdateFWWindowIsActive()) {

                    // Change Menu Bar Button Colors
                    ppbe.setParseNewJDWindowIsActive(false);
                    refreshJButtonFormat(btn_MenuBar_ParseNewJD, false);
                    ppbe.setParseOldJDWindowIsActive(false);
                    refreshJButtonFormat(btn_MenuBar_ParseOldJD, false);
                    ppbe.setUpdateFWWindowIsActive(true);
                    refreshJButtonFormat(btn_MenuBar_UpdateFW, true);

                    // Update Left Panel Heading & Explanation Text
                    refreshJLabelFormat(
                            lbl_Left_Header,
                            UPDATE_FW_HEADING);
                    setJTextAreaFormat(
                            ta_Left_Center,
                            HELVETICA_24,
                            CLR_DEFAULT_TEXT_BODY);
                    refreshJTextAreaFormat(
                            ta_Left_Center,
                            UPDATE_FW_EXPLANATION_TEXT);

                    // Update Right Panel Heading & Explanation Text
                    pnl_UpdateFW_WordResults.removeAll();
                    refreshRightPanel_UpdateFW(ppbe);
                }
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParsedNewJD_MiniForm_Append`
         * which allows the User to append the provided word in the
         * `tf_ParsedNewJD_MiniForm_Append` JTextField to the "ForbiddenWords.
         * txt" file as well as to the "ForbiddenWords" Tree Set class. After
         * properly appending the word, the "UniqueWordsAndCounts" are
         * re-tallied and redisplayed within the program on the User's screen.
         */
        btn_ParsedNewJD_MiniForm_Append.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Collect the value held in the "Append" text field and strip
                // it of all white space, storing it in a variable
                String wordToAppend = tf_ParsedNewJD_MiniForm_Append.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_APPEND_WORD, wordToAppend);

                // If the word to be appended is equal to an empty String...
                if (wordToAppend == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_APPEND_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeParsedNewJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to append an empty String to the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParsedNewJD_UserMessage_Outer,
                            pnl_ParsedNewJD_UserMessage_Inner,
                            ta_ParsedNewJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be appended is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be appended is able to be removed from the
                    // "Forbidden Words" list (i.e. `appendWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().appendWord(wordToAppend)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_APPENDED_WORD,
                                wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedNewJDInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        // Refresh the Right Panel with the updated Unique Word
                        // count
                        refreshRightPanel_ParsedNewJD(ppbe);

                        // Remove the submitted word from the "Append" text
                        // field
                        tf_ParsedNewJD_MiniForm_Append.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to append the word
                        // to the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_ParsedNewJD_UserMessage_Outer,
                                pnl_ParsedNewJD_UserMessage_Inner,
                                ta_ParsedNewJD_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_TITLE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();

                        // If the word to be appended is NOT able to be appended
                        // to the "Forbidden Words" list (i.e. `appendWord()`
                        // returns false)...
                    } else {

                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_APPEND_WORD, wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedNewJDInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to append the word to the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_ParsedNewJD_UserMessage_Outer,
                                pnl_ParsedNewJD_UserMessage_Inner,
                                ta_ParsedNewJD_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_TITLE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParsedNewJD_MiniForm_Append`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParsedNewJD_MiniForm_Append.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Append & Rerun` button on the \"Parsed New Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedNewJD_MiniForm_Append,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Append & Rerun` button on the \"Parsed New Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedNewJD_MiniForm_Append,
                        false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParsedNewJD_MiniForm_Remove`
         * which allows the User to remove the provided word from the
         * `tf_ParsedNewJD_MiniForm_Remove` JTextField from the "ForbiddenWords.
         * txt" file as well as from the "ForbiddenWords" Tree Set class. After
         * properly removing the word, the "UniqueWordsAndCounts" are
         * re-tallied and redisplayed within the program on the User's screen.
         */
        btn_ParsedNewJD_MiniForm_Remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Collect the value held in the "Remove" text field and strip
                // it of all white space, storing it in a variable
                String wordToRemove = tf_ParsedNewJD_MiniForm_Remove.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_REMOVE_WORD, wordToRemove);

                // If the word to be removed is equal to an empty String...
                if (wordToRemove == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_REMOVE_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeParsedNewJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to append an empty String to the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParsedNewJD_UserMessage_Outer,
                            pnl_ParsedNewJD_UserMessage_Inner,
                            ta_ParsedNewJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be removed is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be removed is able to be removed from the
                    // "Forbidden Words" list (i.e. `removeWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().removeWord(wordToRemove)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_REMOVED_WORD,
                                wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedNewJDInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        refreshRightPanel_ParsedNewJD(ppbe);

                        // Remove the submitted word from the "Remove" text
                        // field
                        tf_ParsedNewJD_MiniForm_Remove.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to remove the word
                        // from the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_ParsedNewJD_UserMessage_Outer,
                                pnl_ParsedNewJD_UserMessage_Inner,
                                ta_ParsedNewJD_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_TITLE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();

                        // If the word to be removed is NOT able to be removed
                        // from the "Forbidden Words" list (i.e. `removeWord()`
                        // returns false)...
                    } else {

                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_REMOVE_WORD, wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedNewJDInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to remove the word from the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_ParsedNewJD_UserMessage_Outer,
                                pnl_ParsedNewJD_UserMessage_Inner,
                                ta_ParsedNewJD_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_TITLE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParsedNewJD_MiniForm_Remove`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParsedNewJD_MiniForm_Remove.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Remove & Rerun` button on the \"Parsed New Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedNewJD_MiniForm_Remove,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Remove & Rerun` button on the \"Parsed New Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedNewJD_MiniForm_Remove,
                        false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParsedOldJD_MiniForm_Append`
         * which allows the User to append the provided word in the
         * `tf_ParsedOldJD_MiniForm_Append` JTextField to the "ForbiddenWords.
         * txt" file as well as to the "ForbiddenWords" Tree Set class. After
         * properly appending the word, the "UniqueWordsAndCounts" are
         * re-tallied and redisplayed within the program on the User's screen.
         */
        btn_ParsedOldJD_MiniForm_Append.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Collect the value held in the "Append" text field and strip
                // it of all white space, storing it in a variable
                String wordToAppend = tf_ParsedOldJD_MiniForm_Append.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_APPEND_WORD, wordToAppend);

                // If the word to be appended is equal to an empty String...
                if (wordToAppend == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_APPEND_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeParsedOldJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to append an empty String to the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParsedOldJD_UserMessage_Outer,
                            pnl_ParsedOldJD_UserMessage_Inner,
                            ta_ParsedOldJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be appended is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be appended is able to be removed from the
                    // "Forbidden Words" list (i.e. `appendWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().appendWord(wordToAppend)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_APPENDED_WORD,
                                wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedOldJDInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        // Refresh the Right Panel with the updated Unique Word
                        // count
                        refreshRightPanel_ParsedOldJD(ppbe);

                        // Remove the submitted word from the "Append" text
                        // field
                        tf_ParsedOldJD_MiniForm_Append.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to append the word
                        // to the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_ParsedOldJD_UserMessage_Outer,
                                pnl_ParsedOldJD_UserMessage_Inner,
                                ta_ParsedOldJD_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_TITLE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_APPEND_WORD, wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedOldJDInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to append the word to the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_ParsedOldJD_UserMessage_Outer,
                                pnl_ParsedOldJD_UserMessage_Inner,
                                ta_ParsedOldJD_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_TITLE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParsedOldJD_MiniForm_Remove`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParsedOldJD_MiniForm_Append.addMouseListener(new MouseAdapter() {
            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Append & Rerun` button on the \"Parsed Old Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedOldJD_MiniForm_Append,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Append & Rerun` button on the \"Parsed Old Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedOldJD_MiniForm_Append,
                        false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParsedOldJD_MiniForm_Remove`
         * which allows the User to remove the provided word from the
         * `tf_ParsedOldJD_MiniForm_Remove` JTextField from the "ForbiddenWords.
         * txt" file as well as from the "ForbiddenWords" Tree Set class. After
         * properly removing the word, the "UniqueWordsAndCounts" are
         * re-tallied and redisplayed within the program on the User's screen.
         */
        btn_ParsedOldJD_MiniForm_Remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Collect the value held in the "Remove" text field and strip
                // it of all white space, storing it in a variable
                String wordToRemove = tf_ParsedOldJD_MiniForm_Remove.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_REMOVE_WORD, wordToRemove);

                // If the word to be removed is equal to an empty String...
                if (wordToRemove == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_REMOVE_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeParsedOldJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to append an empty String to the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParsedOldJD_UserMessage_Outer,
                            pnl_ParsedOldJD_UserMessage_Inner,
                            ta_ParsedOldJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be removed is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be removed is able to be removed from the
                    // "Forbidden Words" list (i.e. `removeWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().removeWord(wordToRemove)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_REMOVED_WORD,
                                wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedOldJDInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        // Refresh the Right Panel with the updated Unique Word
                        // count
                        refreshRightPanel_ParsedNewJD(ppbe);

                        // Remove the submitted word from the "Remove" text
                        // field
                        tf_ParsedOldJD_MiniForm_Remove.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to remove the word
                        // from the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_ParsedOldJD_UserMessage_Outer,
                                pnl_ParsedOldJD_UserMessage_Inner,
                                ta_ParsedOldJD_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_TITLE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();

                        // If the word to be removed is NOT able to be removed
                        // from the "Forbidden Words" list (i.e. `removeWord()`
                        // returns false)...
                    } else {

                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_REMOVE_WORD, wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeParsedOldJDInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to remove the word from the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_ParsedOldJD_UserMessage_Outer,
                                pnl_ParsedOldJD_UserMessage_Inner,
                                ta_ParsedOldJD_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_TITLE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParsedOldJD_MiniForm_Remove`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParsedOldJD_MiniForm_Remove.addMouseListener(new MouseAdapter() {
            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Remove & Rerun` button on the \"Parsed Old Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedOldJD_MiniForm_Remove,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Remove & Rerun` button on the \"Parsed Old Job Description\" screen");
                refreshJButtonFormat(
                        btn_ParsedOldJD_MiniForm_Remove,
                        false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParseNewJD_FormField_Browse`
         * which allows the platform to populate the
         * `tf_ParseNewJD_FormField_FilePath` with the full file path of the
         * User selected file once confirmed by the User, providing a visual
         * cue that the action has been completed for the User.
         */
        btn_ParseNewJD_FormField_Browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Set the JFileChooser so that the pop-up appears in the
                // middle of the JFrame
                fc_ParseNewJD.showOpenDialog(null);

                try {

                    // Attempt to pull the full file path of the selected file
                    // within the JFileChooser
                    String saveDirectory = fc_ParseNewJD.getSelectedFile().getPath();

                    // Set the selected file as the text in the JTextField
                    tf_ParseNewJD_FormField_FilePath.setText(saveDirectory);

                    // If the User doesn't select a file within the
                    // JFileChooser...
                } catch (NullPointerException npe) {
                    System.out.println(RED_BOLD + "User did not select a local directory, cancelling action" + RESET);
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParseNewJD_FormField_Browse`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParseNewJD_FormField_Browse.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println("User pressed the `Browse` button on the \"Parse New Job Description\" screen");
                refreshJButtonFormat(btn_ParseNewJD_FormField_Browse, true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println("User released the `Browse` button on the \"Parse New Job Description\" screen");
                refreshJButtonFormat(btn_ParseNewJD_FormField_Browse, false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParseNewJD_FormField_Submit`
         * which tests first to see if both `tf_ParseNewJD_FormField_URL` and
         * `tf_ParseNewJD_FormField_FilePath` JTextFields are populated with
         * the appropriate Strings. If the test passes, an attempt is made to
         * parse the User provided Job Description 'url' and, if successful,
         * the JFrame is refreshed with new data, logging all actions to the
         * terminal.
         */
        btn_ParseNewJD_FormField_Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Get the text values from both JTextFields, stripping them of
                // any additional whitespace and assign them to their
                // respective variables
                String url = tf_ParseNewJD_FormField_URL.getText().strip();
                String path = tf_ParseNewJD_FormField_FilePath.getText().strip();

                // If both the 'path' and 'url' variables are both empty
                // Strings...
                if (path == "" && url == "") {

                    // Create an instance of the Timer class which will execute
                    // the `removeParseNewJDInnerUserMessagePanel`
                    // ActionListener
                    Timer timer = new Timer(5000, removeParseNewJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to submit data for parsing if no data
                    // was submitted by the User
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParseNewJD_UserMessage_Outer,
                            pnl_ParseNewJD_UserMessage_Inner,
                            ta_ParseNewJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_URL_AND_FILE_PATH_MISSING_TITLE,
                            ERROR_URL_AND_FILE_PATH_MISSING_MESSAGE);

                    // Set the Timer object to run once and then start the Timer
                    timer.setRepeats(false);
                    timer.start();

                    // Log the action to the Terminal
                    System.out.println(
                            RED_BOLD + "Unable to parse - local file path and URL not provided by the User" + RESET);

                    // ...If the 'path' variable is an empty String and the
                    // 'url' variable is not...
                } else if (path == "" && url != "") {

                    // Create an instance of the Timer class which will execute
                    // the `removeParseNewJDInnerUserMessagePanel`
                    // ActionListener
                    Timer timer = new Timer(5000, removeParseNewJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to submit data for parsing if no
                    // 'path' data was submitted by the User
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParseNewJD_UserMessage_Outer,
                            pnl_ParseNewJD_UserMessage_Inner,
                            ta_ParseNewJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_FILE_PATH_MISSING_TITLE,
                            ERROR_FILE_PATH_MISSING_MESSAGE);

                    // Set the Timer object to run once and then start the Timer
                    timer.setRepeats(false);
                    timer.start();

                    // ...If the 'path' variable is not an empty String and the
                    // 'url' variable is an empty String...
                } else if (path != "" && url == "") {

                    // Create an instance of the Timer class which will execute
                    // the `removeParseNewJDInnerUserMessagePanel`
                    // ActionListener
                    Timer timer = new Timer(5000, removeParseNewJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to submit data for parsing if no
                    // 'url' data was submitted by the User
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParseNewJD_UserMessage_Outer,
                            pnl_ParseNewJD_UserMessage_Inner,
                            ta_ParseNewJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_URL_MISSING_TITLE,
                            ERROR_URL_MISSING_MESSAGE);

                    // Set the Timer object to run once and then start the Timer
                    timer.setRepeats(false);
                    timer.start();

                    // ...Otherwise, if both 'path' and 'url' variables do not
                    // contain empty Strings...
                } else {

                    // Test the value stored in the 'url' variable to see if
                    // it's valid
                    // If the value is valid...
                    if (ppbe.testForValidURL(url)) {

                        // Turn off the "Parse New Job Description" Menu Bar
                        // button (Part 1)
                        ppbe.setParseNewJDWindowIsActive(false);

                        // Set the 'url' and 'path' in the state of the
                        // "PostingParserBackEnd" class
                        ppbe.setURL(url);
                        ppbe.setRootDirectory(path);

                        // Build and create the various directories and
                        // documents required for the User's reference and for
                        // potential re-parsing at a later date
                        ppbe.createPostingParserDirectory();
                        ppbe.createCompanyDirectory();
                        ppbe.createRoleDirectory();
                        ppbe.createRawDataDirectoryAndFile();

                        // Turn off the "Parse New Job Description" Menu Bar
                        // button (Part 2)
                        refreshJButtonFormat(
                                btn_MenuBar_ParseNewJD,
                                false);

                        // Refresh and repaint the Left Panel with the parsed
                        // job description in a human-readable format
                        refreshJLabelFormat(
                                lbl_Left_Header,
                                PARSED_JD_HEADING);
                        setJTextAreaFormat(
                                ta_Left_Center,
                                HELVETICA_14,
                                CLR_DEFAULT_TEXT_BODY);
                        refreshJTextAreaFormat(
                                ta_Left_Center,
                                ppbe.readParsedJobDescription(ppbe.getRoleDataFile()));

                        // Remove components from the Right Panel & any
                        // previously populated words in the Word Results panel
                        pnl_Right_Center.removeAll();
                        pnl_ParsedNewJD_WordResults.removeAll();

                        // Talley the job description words for later
                        // population within the Word Results panel
                        ppbe.tallyJobDescriptionWords(ppbe.getRoleDataFile());

                        // Create keys of the unique words found in the job
                        // description so the program can loop through and
                        // display all the unique, approved words
                        Enumeration<String> uniqueWords = ppbe.getUniqueWordsAndCounts().keys();

                        // While there are more elements in the 'uniqueWords'
                        // Enum...
                        while (uniqueWords.hasMoreElements()) {

                            // Store next element in 'word' variable
                            String word = uniqueWords.nextElement();

                            // Store next element's 'count' variable
                            Integer count = ppbe.getUniqueWordsAndCounts().get(word);

                            // Instantiate a new anonymous JLabel
                            JLabel template = new JLabel();

                            // Set anonymous JLabel's default formatting
                            setJLabelFormat(
                                    template,
                                    count,
                                    W0170_H0025,
                                    HELVETICA_14,
                                    ALIGN_CENTER);

                            // Set the text of the JLabel and add it to the
                            // Word Results panel
                            template.setText(word + " (" + count + ")");
                            pnl_ParsedNewJD_WordResults.add(template);
                        }

                        // Refresh the Word Results panel
                        revalidateAndRepaintJPanel(pnl_ParsedNewJD_WordResults);

                        // Add the updated Parsed New Job Description panel to
                        // the Right panel
                        pnl_Right_Center.add(pnl_ParsedNewJD);

                        // Refresh the Right panel
                        revalidateAndRepaintJPanel(pnl_Right_Center);

                        // ...Otherwise, if the value is not valid...
                    } else {

                        // Create an instance of the Timer class which will
                        // execute the `removeParseNewJDInnerUserMessagePanel`
                        // ActionListener
                        Timer timer = new Timer(5000, removeParseNewJDInnerUserMessagePanel);

                        // Display a message to the User notifying them that the
                        // program is unable to submit data for parsing as the
                        // 'url' domain is not supported
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_ParseNewJD_UserMessage_Outer,
                                pnl_ParseNewJD_UserMessage_Inner,
                                ta_ParseNewJD_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_INVALID_URL_TITLE,
                                ERROR_INVALID_URL_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // Timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParseNewJD_FormField_Browse`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParseNewJD_FormField_Submit.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Parse The Description!` button on the \"Parse New Job Description\" screen");
                refreshJButtonFormat(btn_ParseNewJD_FormField_Submit, true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Parse The Description!` button on the \"Parse New Job Description\" screen");
                refreshJButtonFormat(btn_ParseNewJD_FormField_Submit, false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParseOldJD_FormField_Browse`
         * setting the FileFilter property of the JFileChooser so that only a
         * file named 'ParsedJD.txt' can be selected for parsing. Afterwards,
         * open the JFileChooser menu so the User can select an appropriately
         * named file. If a file is selected, post the full file path to the
         * `tf_ParseOldJD_FormField_FilePath` JTextField, logging all actions
         * to the terminal.
         */
        btn_ParseOldJD_FormField_Browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Set the FileFilter property for the FileChooser, overriding
                // both `accept()` and `getDescription()` methods within the
                // "FileChooser" class
                fc_ParseOldJD.setFileFilter(new FileFilter() {

                    // Override the `accept()` method so that only directories
                    // can be opened and - within those directories - only
                    // those files with the name 'ParsedJD.txt' can be selected
                    // for parsing
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || file.getName().equals("ParsedJD.txt");
                    }

                    // Override the `getDescription()` method so that the
                    // JFileChooser menu shows the dropdown option for
                    // ""ParsedJD.txt" Files Only"
                    @Override
                    public String getDescription() {
                        return "\"ParsedJD.txt\" Files Only";
                    }
                });

                // Sets the placement of the FileChooser method as centered
                // within the JFrame
                fc_ParseOldJD.showOpenDialog(null);

                try {
                    // Attempt to pull the full file path of the selected file
                    // within the JFileChooser
                    String saveDirectory = fc_ParseOldJD.getSelectedFile().getPath();

                    // Set the selected file as the text in the JTextField
                    tf_ParseOldJD_FormField_FilePath.setText(saveDirectory);

                    // If the User doesn't select a file within the
                    // JFileChooser...
                } catch (NullPointerException npe) {
                    System.out.println(RED_BOLD + "User did not select a local directory, cancelling action" + RESET);
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParseOldJD_FormField_Browse`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParseOldJD_FormField_Browse.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Browse` button on the \"Parse Old Job Description\" screen");
                refreshJButtonFormat(btn_ParseOldJD_FormField_Browse, true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Browse` button on the \"Parse Old Job Description\" screen");
                refreshJButtonFormat(btn_ParseOldJD_FormField_Browse, false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_ParseOldJD_FormField_Submit`
         * which tests first to see if the `tf_ParseOldJD_FormField_FilePath`
         * JTextField is populated with the appropriate String. If the test
         * passes, an attempt is made to parse the User provided 'ParsedJD.txt'
         * file and, if successful, the JFrame is refreshed with new data,
         * logging all actions to the terminal.
         */
        btn_ParseOldJD_FormField_Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Get the text values from the JTextField, stripping it of any
                // additional whitespace and assign it to the 'path' variable
                String path = tf_ParseOldJD_FormField_FilePath.getText().strip();

                // If the 'path' value is an empty String...
                if (path == "") {

                    // Create an instance of the Timer class which will execute
                    // the `removeParseOldJDInnerUserMessagePanel`
                    // ActionListener
                    Timer timer = new Timer(
                            5000,
                            removeParseOldJDInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to submit data for parsing if no
                    // 'path' data was submitted by the User
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_ParseOldJD_UserMessage_Outer,
                            pnl_ParseOldJD_UserMessage_Inner,
                            ta_ParseOldJD_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_FILE_PATH_MISSING_TITLE,
                            ERROR_FILE_PATH_MISSING_MESSAGE);

                    // Set the Timer object to run once and then start the Timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the 'path' value is not an empty String...
                } else {

                    // Turn off the "Parse Old Job Description" Menu Bar
                    // button
                    ppbe.setParseOldJDWindowIsActive(
                            false);
                    refreshJButtonFormat(
                            btn_MenuBar_ParseOldJD,
                            false);

                    // Refresh and repaint the Left Panel with the parsed
                    // job description in a human-readable format
                    refreshJLabelFormat(
                            lbl_Left_Header,
                            PARSED_JD_HEADING);
                    setJTextAreaFormat(
                            ta_Left_Center,
                            HELVETICA_14,
                            CLR_DEFAULT_TEXT_BODY);
                    refreshJTextAreaFormat(
                            ta_Left_Center,
                            ppbe.readParsedJobDescription(path));

                    // Set the 'path' variable value as the value of the
                    // 'roleDataFile' state variable in the
                    // "PostingParserBackEnd" class instance
                    ppbe.setRoleDataFile(path);

                    // Remove components from the Right Panel & any
                    // previously populated words in the Word Results panel
                    pnl_Right_Center.removeAll();
                    pnl_ParseOldJD_WordResults.removeAll();

                    // Talley the job description words for later
                    // population within the Word Results panel
                    ppbe.tallyJobDescriptionWords(path);

                    // Create keys of the unique words found in the job
                    // description so the program can loop through and
                    // display all the unique, approved words
                    Enumeration<String> uniqueWords = ppbe.getUniqueWordsAndCounts().keys();

                    // While there are more elements in the 'uniqueWords'
                    // Enum...
                    while (uniqueWords.hasMoreElements()) {

                        // Store next element in 'word' variable
                        String word = uniqueWords.nextElement();

                        // Store next element's 'count' variable
                        Integer count = ppbe.getUniqueWordsAndCounts().get(word);

                        // Instantiate a new anonymous JLabel
                        JLabel template = new JLabel();

                        // Set anonymous JLabel's default formatting
                        setJLabelFormat(
                                template,
                                count,
                                W0170_H0025,
                                HELVETICA_14,
                                ALIGN_CENTER);

                        // Set the text of the JLabel and add it to the
                        // Word Results panel
                        template.setText(word + " (" + count + ")");
                        pnl_ParsedOldJD_WordResults.add(template);
                    }

                    // Refresh the Word Results panel
                    revalidateAndRepaintJPanel(pnl_ParsedOldJD_WordResults);

                    // Add the updated Parsed New Job Description panel to
                    // the Right panel
                    pnl_Right_Center.add(pnl_ParsedOldJD);

                    // Refresh the Right panel
                    revalidateAndRepaintJPanel(pnl_Right_Center);
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_ParseOldJD_FormField_Browse`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_ParseOldJD_FormField_Submit.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Parse The Description!` button on the \"Parse Old Job Description\" screen");
                refreshJButtonFormat(btn_ParseOldJD_FormField_Submit, true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Parse The Description!` button on the \"Parse Old Job Description\" screen");
                refreshJButtonFormat(btn_ParseOldJD_FormField_Submit, false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_UpdateFW_MiniForm_Append`
         * which processes the String passed into the "Append" JTextArea,
         * processing the "Append" request if it's valid, and notifying the
         * User / logging all actions to the terminal through the process.
         */
        btn_UpdateFW_MiniForm_Append.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Collect the value held in the "Append" text field and strip
                // it of all white space, storing it in a variable
                String wordToAppend = tf_UpdateFW_MiniForm_Append.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_APPEND_WORD, wordToAppend);

                // If the word to be appended is equal to an empty String...
                if (wordToAppend == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_APPEND_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeUpdateFWInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to append an empty String to the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_UpdateFW_UserMessage_Outer,
                            pnl_UpdateFW_UserMessage_Inner,
                            ta_UpdateFW_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_APPEND_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be appended is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be appended is able to be removed from the
                    // "Forbidden Words" list (i.e. `appendWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().appendWord(wordToAppend)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_APPENDED_WORD,
                                wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeUpdateFWInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        // Refresh the screen with new list
                        pnl_UpdateFW_WordResults.removeAll();
                        refreshRightPanel_UpdateFW(ppbe);

                        // Remove the submitted word from the "Append" text
                        // field
                        tf_UpdateFW_MiniForm_Append.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to append the word
                        // to the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_UpdateFW_UserMessage_Outer,
                                pnl_UpdateFW_UserMessage_Inner,
                                ta_UpdateFW_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_TITLE,
                                SUCCESS_WORD_APPENDED_TO_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();

                        // If the word to be appended is NOT able to be appended
                        // to the "Forbidden Words" list (i.e. `appendWord()`
                        // returns false)...
                    } else {

                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_APPEND_WORD, wordToAppend);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeUpdateFWInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to append the word to the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_UpdateFW_UserMessage_Outer,
                                pnl_UpdateFW_UserMessage_Inner,
                                ta_UpdateFW_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_TITLE,
                                ERROR_WORD_EXISTS_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_UpdateFW_MiniForm_Append`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_UpdateFW_MiniForm_Append.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Append & Rerun` button on the \"Update \"Forbidden Words\" List\" screen");
                refreshJButtonFormat(
                        btn_UpdateFW_MiniForm_Append,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Append & Rerun` button on the \"Update \"Forbidden Words\" List\" screen");
                refreshJButtonFormat(
                        btn_UpdateFW_MiniForm_Append,
                        false);
            }
        });

        /**
         * An "ActionListener" is added to `btn_UpdateFW_MiniForm_Remove`
         * which processes the String passed into the "Remove" JTextArea,
         * processing the "Remove" request if it's valid, and notifying the
         * User / logging all actions to the terminal through the process.
         */
        btn_UpdateFW_MiniForm_Remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // Collect the value held in the "Remove" text field and strip
                // it of all white space, storing it in a variable
                String wordToRemove = tf_UpdateFW_MiniForm_Remove.getText().strip();

                // Log that the action was taken in the terminal
                System.out.printf(ATTEMPTING_TO_REMOVE_WORD, wordToRemove);

                // If the word to be removed is equal to an empty String...
                if (wordToRemove == "") {

                    // Log that the action was unable to be completed in the
                    // terminal
                    System.out.println(UNABLE_TO_REMOVE_AN_EMPTY_STRING);

                    // Create a new Timer object to execute clearing the User
                    // Message display after five seconds
                    Timer timer = new Timer(
                            5000,
                            removeUpdateFWInnerUserMessagePanel);

                    // Display a message to the User notifying them that the
                    // program is unable to remove an empty String from the
                    // "Forbidden Words" list
                    displayUserMessagePanel(
                            MESSAGE_TYPE_ERROR,
                            pnl_UpdateFW_UserMessage_Outer,
                            pnl_UpdateFW_UserMessage_Inner,
                            ta_UpdateFW_UserMessage,
                            CLR_ERROR_BORDER,
                            CLR_ERROR_TITLE,
                            CLR_ERROR_MESSAGE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_TITLE,
                            ERROR_WORD_TO_REMOVE_MISSING_FROM_TEXT_FIELD_MESSAGE);

                    // Set the Timer object to run once and then start the timer
                    timer.setRepeats(false);
                    timer.start();

                    // If the word to be removed is NOT equal to an empty
                    // String...
                } else {

                    // If the word to be removed is able to be removed from the
                    // "Forbidden Words" list (i.e. `removeWord()` returns
                    // true)...
                    if (ppbe.getForbiddenWords().removeWord(wordToRemove)) {

                        // Log that the action was able to be completed in the
                        // terminal
                        System.out.printf(
                                SUCCESSFULLY_REMOVED_WORD,
                                wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeUpdateFWInnerUserMessagePanel);

                        // Update the text file with the new "Forbidden Word"
                        // list for future reference
                        ppbe.updateForbiddenWordTxt();

                        // Refresh The Screen With New List
                        pnl_UpdateFW_WordResults.removeAll();
                        refreshRightPanel_UpdateFW(ppbe);

                        // Remove the submitted word from the "Remove" text
                        // field
                        tf_UpdateFW_MiniForm_Remove.setText("");

                        // Display a message to the User notifying them that
                        // the program was successfully able to remove the word
                        // from the "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_SUCCESS,
                                pnl_UpdateFW_UserMessage_Outer,
                                pnl_UpdateFW_UserMessage_Inner,
                                ta_UpdateFW_UserMessage,
                                CLR_SUCCESS_BORDER,
                                CLR_SUCCESS_TITLE,
                                CLR_SUCCESS_MESSAGE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_TITLE,
                                SUCCESS_WORD_REMOVED_FROM_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();

                        // If the word to be removed is NOT able to be removed
                        // from the "Forbidden Words" list (i.e. `removeWord()`
                        // returns false)...
                    } else {

                        // Log that the action was unable to be completed in
                        // the terminal
                        System.out.printf(UNABLE_TO_REMOVE_WORD, wordToRemove);

                        // Create a new Timer object to execute clearing the
                        // User Message display after five seconds
                        Timer timer = new Timer(
                                5000,
                                removeUpdateFWInnerUserMessagePanel);

                        // Display a message to the User notifying them that
                        // the program was unable to remove the word from the
                        // "Forbidden Words" list
                        displayUserMessagePanel(
                                MESSAGE_TYPE_ERROR,
                                pnl_UpdateFW_UserMessage_Outer,
                                pnl_UpdateFW_UserMessage_Inner,
                                ta_UpdateFW_UserMessage,
                                CLR_ERROR_BORDER,
                                CLR_ERROR_TITLE,
                                CLR_ERROR_MESSAGE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_TITLE,
                                ERROR_WORD_DOES_NOT_EXIST_IN_FW_LIST_MESSAGE);

                        // Set the Timer object to run once and then start the
                        // timer
                        timer.setRepeats(false);
                        timer.start();
                    }
                }
            }
        });

        /**
         * A "MouseListener" is added to `btn_UpdateFW_MiniForm_Remove`
         * which provide the User with color-change feedback when a click
         * action is executed on the button, logging all actions to the
         * terminal.
         */
        btn_UpdateFW_MiniForm_Remove.addMouseListener(new MouseAdapter() {

            // Action that occurs when the User presses the button
            public void mousePressed(MouseEvent me) {
                System.out.println(
                        "User pressed the `Remove & Rerun` button on the \"Update \"Forbidden Words\" List\" screen");
                refreshJButtonFormat(
                        btn_UpdateFW_MiniForm_Remove,
                        true);
            }

            // Action that occurs when the User releases the button
            public void mouseReleased(MouseEvent me) {
                System.out.println(
                        "User released the `Remove & Rerun` button on the \"Update \"Forbidden Words\" List\" screen");
                refreshJButtonFormat(
                        btn_UpdateFW_MiniForm_Remove,
                        false);
            }
        });
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------- CLOSE - `layoutComponents()` & `addListeners()` Methods ------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------------- BEGIN - Global Helper Methods -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------- `refresh<JComponent>Format()` Methods ---------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `refreshJButtonFormat()` method accepts two parameters - a JButton
     * ('btn') and a boolean ('isClicked') - returning no values. If
     * 'isClicked' is true, the passed 'btn' will be set to an active color
     * coding; if 'isClicked' is false, the passed 'btn' will be set to an
     * inactive color coding. Once the color coding is set, the JButton
     * component will be revalidated and repainted.
     * 
     * @param btn       A JButton component that the program wishes to change
     *                  the color scheme of to reflect active / clicked or
     *                  inactive / is not clicked status.
     * @param isClicked A boolean value representing whether the passed 'btn'
     *                  should be reformatted into active or inactive colors.
     */
    private void refreshJButtonFormat(JButton btn, boolean isClicked) {
        if (isClicked) {
            btn.setBackground(CLR_BTN_BACKGROUND_PRESSED);
            btn.setBorder(BRDR_BTN_PRESSED);
        } else {
            btn.setBackground(CLR_BTN_BACKGROUND_RELEASED);
            btn.setBorder(BRDR_BTN_RELEASED);
        }
        revalidateAndRepaintJButton(btn);
    }

    /**
     * The `refreshJLabelFormat()` method accepts two parameters - a JLabel
     * ('lbl') and a String ('txt') - returning no values. The method sets the
     * text value of the 'lbl' to 'txt', revalidating and repainting the
     * component afterwards.
     * 
     * @param lbl A JLabel component that the program wishes to update the text
     *            property of to reflect a new value.
     * @param txt A String object that the program would like to set the 'lbl'
     *            component's text property to.
     */
    private void refreshJLabelFormat(JLabel lbl, String txt) {
        lbl.setText(txt);
        revalidateAndRepaintJLabel(lbl);
    }

    /**
     * The `refreshJTextAreaFormat()` method accepts two parameters - a
     * JTextArea ('ta') and a String ('txt') - returning no values. The method
     * sets the value of the 'ta' to 'txt', revalidating and repainting the
     * component afterwards.
     * 
     * @param ta  A JTextArea Component that the program wishes to update the
     *            text property of to reflect a new value.
     * @param txt A String object that the program would like to set the 'ta'
     *            component's text property to.
     */
    private void refreshJTextAreaFormat(JTextArea ta, String txt) {
        ta.setText(txt);
        revalidateAndRepaintJTextArea(ta);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------- `revalidateAndRepaint<JComponent>()` Methods ------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `revalidateAndRepaintJButton()` method accepts a single JButton
     * parameter - 'btn' - returning no values. The method executes the
     * built-in methods of `revalidate()` and `repaint()` on the component to
     * refresh the component's display in the UI for the User.
     * 
     * @param btn A JButton component that the program would like to refresh in
     *            the UI.
     */
    private void revalidateAndRepaintJButton(JButton btn) {
        btn.revalidate();
        btn.repaint();
    }

    /**
     * The `revalidateAndRepaintJLabel()` method accepts a single JLabel
     * parameter - 'lbl' - returning no values. The method executes the
     * built-in methods of `revalidate()` and `repaint()` on the component to
     * refresh the component's display in the UI for the User.
     * 
     * @param lbl A JLabel component that the program would like to refresh in
     *            the UI.
     */
    private void revalidateAndRepaintJLabel(JLabel lbl) {
        lbl.revalidate();
        lbl.repaint();
    }

    /**
     * The `revalidateAndRepaintJPanel()` method accepts a single JPanel
     * parameter - 'pnl' - returning no values. The method executes the
     * built-in methods of `revalidate()` and `repaint()` on the component to
     * refresh the component's display in the UI for the User.
     * 
     * @param pnl A JPanel component that the program would like to refresh in
     *            the UI.
     */
    private void revalidateAndRepaintJPanel(JPanel pnl) {
        pnl.revalidate();
        pnl.repaint();
    }

    /**
     * The `revalidateAndRepaintJTextArea()` method accepts a single JTextArea
     * parameter - 'ta' - returning no values. The method executes the built-in
     * methods of `revalidate()` and `repaint()` on the component to refresh
     * the component's display in the UI for the User.
     * 
     * @param ta A JTextArea component that the program would like to refresh
     *           in the UI.
     */
    private void revalidateAndRepaintJTextArea(JTextArea ta) {
        ta.revalidate();
        ta.repaint();
    }

    /**
     * The `setJButtonFormat()` method accepts three parameters - a JButton
     * ('btn'), a String ('txt'), and a boolean ('isClicked') - returning no
     * values. When executed the method checks 'isClicked' to see which color
     * palette the 'btn' should be formatted with. Once the color palette is
     * established, the remaining properties of the 'btn' will be set.
     * 
     * @param btn       A JButton component that the program wishes to change
     *                  the color scheme of to reflect active / clicked or
     *                  inactive / is not clicked status.
     * @param txt       A String object that the program would like to set the
     *                  'btn' component's text property to.
     * @param isClicked A boolean value representing whether the passed 'btn'
     *                  should be reformatted into active or inactive colors.
     */
    private void setJButtonFormat(JButton btn, String txt, boolean isClicked) {
        if (isClicked) {
            btn.setBackground(CLR_BTN_BACKGROUND_PRESSED);
            btn.setBorder(BRDR_BTN_PRESSED);
        } else {
            btn.setBackground(CLR_BTN_BACKGROUND_RELEASED);
            btn.setBorder(BRDR_BTN_RELEASED);
        }
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.BLACK);
        btn.setOpaque(true);
        btn.setText(txt);
    }

    /**
     * The `setJLabelFormat()` method accepts five parameters - a JLabel
     * ('lbl'), a Dimension ('size'), a Font ('font'), a Color ('color'), and
     * an int ('align') - returning no values. When executed the method sets
     * the initial properties of the JLabel to those values provided as
     * parameters to the method.
     * 
     * @param lbl   A JLabel component that the program will format using the
     *              provided parameters.
     * @param size  A Dimension value that the program will use to set the
     *              Minimum, Maximum, and Preferred size of the JLabel.
     * @param font  A Font value that the program will use to set the Font
     *              property of the JLabel.
     * @param color A Color value that the program will use to set the
     *              Foreground property (text color) of the JLabel.
     * @param align An int value that the program will use to set the
     *              Horizontal Alignment of the text in the JLabel.
     */
    private void setJLabelFormat(
            JLabel lbl,
            Dimension size,
            Font font,
            Color color,
            int align) {

        lbl.setFont(font);
        lbl.setForeground(color);
        lbl.setMaximumSize(size);
        lbl.setMinimumSize(size);
        lbl.setPreferredSize(size);
        lbl.setHorizontalAlignment(align);
    }

    /**
     * The `setJLabelFormat()` method accepts five parameters - a JLabel
     * ('lbl'), an int ('wordCount'), a Dimension ('size'), a Font ('font'),
     * and an int ('align') - returning no values. When executed the method
     * sets the initial properties of the JLabel to those values provided as
     * parameters to the method, determining Foreground (aka text color) based
     * on the value held by the 'wordCount' parameter.
     * 
     * @param lbl       A JLabel component that the program will format using
     *                  the provided parameters.
     * @param wordCount An int value representing the number of occurrences of
     *                  the word that the label will contain; the value of this
     *                  parameter will determine the text color of the 'lbl'
     *                  parameter.
     * @param size      A Dimension value that the program will use to set the
     *                  Minimum, Maximum, and Preferred size of the JLabel.
     * @param font      A Font value that the program will use to set the Font
     *                  property of the JLabel.
     * @param align     An int value that the program will use to set the
     *                  Horizontal Alignment of the text in the JLabel.
     */
    private void setJLabelFormat(
            JLabel lbl,
            int wordCount,
            Dimension size,
            Font font,
            int align) {

        switch (wordCount) {
            case 1 -> {
                lbl.setForeground(CLR_COUNT_EQUALS_1);
            }
            case 2 -> {
                lbl.setForeground(CLR_COUNT_EQUALS_2);
            }
            case 3 -> {
                lbl.setForeground(CLR_COUNT_EQUALS_3);
            }
            case 4 -> {
                lbl.setForeground(CLR_COUNT_EQUALS_4);
            }
            case 5 -> {
                lbl.setForeground(CLR_COUNT_EQUALS_5);
            }
            default -> {
                lbl.setForeground(CLR_COUNT_6_OR_MORE);
            }
        }
        lbl.setFont(font);
        lbl.setMaximumSize(size);
        lbl.setMinimumSize(size);
        lbl.setPreferredSize(size);
        lbl.setHorizontalAlignment(align);
    }

    /**
     * The `setJPanelFormatWithBoxLayout()` method accepts four parameters - a
     * JPanel ('pnl'), an int ('direction'), a Dimension ('size'), and a Border
     * ('border') - returning no values. When executed the method sets the
     * initial properties of the JPanel to those values provided as parameters
     * to the method, specifically tailoring the Layout to a BoxLayout manager.
     * 
     * @param pnl       A JPanel component that the program will format using
     *                  the provided parameters.
     * @param direction An int value representing the direction that the JPanel
     *                  components will be added in (vertically, horizontally).
     * @param size      A Dimension value that the program will use to set the
     *                  Minimum, Maximum, and Preferred size of the JLabel.
     * @param border    A Border value that will set the properties of the
     *                  JPanel's border (type, style, color, etc.).
     */
    private void setJPanelFormatWithBoxLayout(
            JPanel pnl,
            int direction,
            Dimension size,
            Border border) {

        pnl.setBackground(CLR_DEFAULT_BACKGROUND);
        pnl.setBorder(border);
        pnl.setLayout(new BoxLayout(pnl, direction));
        pnl.setMaximumSize(size);
        pnl.setMinimumSize(size);
        pnl.setPreferredSize(size);
    }

    /**
     * The `setJPanelFormatWithNoLayout()` method accepts three parameters - a
     * JPanel ('pnl'), a Dimension ('size'), and a Border ('border') -
     * returning no values. When executed the method sets the initial
     * properties of the JPanel to those values provided as parameters to the
     * method, specifically choosing to not institute a Layout Manager beyond
     * the default associated with the JPanel class.
     * 
     * @param pnl    A JPanel component that the program will format using
     *               the provided parameters.
     * @param size   A Dimension value that the program will use to set the
     *               Minimum, Maximum, and Preferred size of the JLabel.
     * @param border A Border value that will set the properties of the
     *               JPanel's border (type, style, color, etc.).
     */
    private void setJPanelFormatWithNoLayout(
            JPanel pnl,
            Dimension size,
            Border border) {

        pnl.setBackground(CLR_DEFAULT_BACKGROUND);
        pnl.setBorder(border);
        pnl.setMaximumSize(size);
        pnl.setMinimumSize(size);
        pnl.setPreferredSize(size);
    }

    /**
     * The `setJPanelFormatWithNonBoxLayout()` method accepts four parameters -
     * a JPanel ('pnl'), a LayoutManager ('layout'), a Dimension ('size'), and
     * a Border ('border') - returning no values. When executed the method sets
     * the initial properties of the JPanel to those values provided as
     * parameters to the method.
     * 
     * @param pnl    A JPanel component that the program will format using
     *               the provided parameters.
     * @param layout A LayoutManager that will serve as the overall format for
     *               the layout of the JPanel component.
     * @param size   A Dimension value that the program will use to set the
     *               Minimum, Maximum, and Preferred size of the JLabel.
     * @param border A Border value that will set the properties of the
     *               JPanel's border (type, style, color, etc.).
     */
    private void setJPanelFormatWithNonBoxLayout(
            JPanel pnl,
            LayoutManager layout,
            Dimension size,
            Border border) {

        pnl.setBackground(CLR_DEFAULT_BACKGROUND);
        pnl.setBorder(border);
        pnl.setLayout(layout);
        pnl.setMaximumSize(size);
        pnl.setMinimumSize(size);
        pnl.setPreferredSize(size);
    }

    /**
     * The `setJScrollPaneFormatWithJPanel()` method accepts three parameters -
     * a JScrollPane ('sp'), a JPanel ('pnl'), and a Dimension ('size') -
     * returning no values. When executed the method sets the initial
     * properties of the JScrollPane to those values provided as parameters to
     * the method.
     * 
     * @param sp   A JScrollPane component that the program will format using
     *             the default parameters in addition to the provided JPanel and
     *             Dimension parameters.
     * @param pnl  A JPanel component that will be added to the JScrollPane as
     *             the display area for the JScrollPane.
     * @param size A Dimension value that the program will use to set the
     *             Minimum, Maximum, and Preferred size of the JLabel.
     */
    private void setJScrollPaneFormatWithJPanel(
            JScrollPane sp,
            JPanel pnl,
            Dimension size) {

        sp.setBackground(CLR_DEFAULT_BACKGROUND);
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(X_NEVER);
        sp.setMaximumSize(size);
        sp.setMinimumSize(size);
        sp.setPreferredSize(size);
        sp.setVerticalScrollBarPolicy(Y_AS_NEEDED);
        sp.setViewportView(pnl);
    }

    /**
     * The `setJScrollPaneFormatWithJTextArea()` method accepts three
     * parameters - a JScrollPane ('sp'), a JTextArea ('ta'), and a Dimension
     * ('size') - returning no values. When executed the method sets the initial
     * properties of the JScrollPane to those values provided as parameters to
     * the method.
     * 
     * @param sp   A JScrollPane component that the program will format using
     *             the default parameters in addition to the provided JPanel and
     *             Dimension parameters.
     * @param ta   A JTextArea component that will be added to the JScrollPane
     *             as the display area for the JScrollPane.
     * @param size A Dimension value that the program will use to set the
     *             Minimum, Maximum, and Preferred size of the JLabel.
     */
    private void setJScrollPaneFormatWithJTextArea(
            JScrollPane sp,
            JTextArea ta,
            Dimension size) {

        sp.setBackground(CLR_DEFAULT_BACKGROUND);
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(X_NEVER);
        sp.setMaximumSize(size);
        sp.setMinimumSize(size);
        sp.setPreferredSize(size);
        sp.setVerticalScrollBarPolicy(Y_AS_NEEDED);
        sp.setViewportView(ta);
    }

    /**
     * The `setJTextAreaFormat()` method accepts three parameters - a JTextArea
     * ('ta'), a Font ('font'), and a Color ('textColor') - returning no
     * values. When executed the method sets the initial properties of the
     * JTextArea to those values provided as parameters to the method.
     * 
     * @param ta        A JTextArea component that the program will format
     *                  using the default parameters.
     * @param font      A Font value that the program will use to set the Font
     *                  property of the JTextArea.
     * @param textColor A Color value that the program will use to set the
     *                  Foreground property of the JTextArea.
     */
    private void setJTextAreaFormat(
            JTextArea ta,
            Font font,
            Color textColor) {

        ta.setBackground(CLR_DEFAULT_BACKGROUND);
        ta.setEditable(false);
        ta.setFont(font);
        ta.setForeground(textColor);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
    }

    /**
     * The `setJTextAreaFormat()` method accepts four parameters - a JTextArea
     * ('ta'), a Dimension ('size'), a Font ('font'), and a Color ('textColor')
     * - returning no values. When executed the method sets the initial
     * properties of the JTextArea to those values provided as parameters to
     * the method.
     * 
     * @param ta        A JTextArea component that the program will format
     *                  using the default parameters.
     * @param size      A Dimension value that the program will use to set the
     *                  Minimum, Maximum, and Preferred size of the JLabel.
     * @param font      A Font value that the program will use to set the Font
     *                  property of the JTextArea.
     * @param textColor A Color value that the program will use to set the
     *                  Foreground property of the JTextArea.
     */
    private void setJTextAreaFormat(
            JTextArea ta,
            Dimension size,
            Font font,
            Color textColor) {

        ta.setBackground(CLR_DEFAULT_BACKGROUND);
        ta.setEditable(false);
        ta.setFont(font);
        ta.setForeground(textColor);
        ta.setMaximumSize(size);
        ta.setMinimumSize(size);
        ta.setPreferredSize(size);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
    }

    /**
     * The `setJTextFieldFormat()` method accepts three parameters - a
     * JTextField ('tf'), an int ('columns'), and a boolean ('isEditable') -
     * returning no values. When executed the method sets the initial
     * properties of the JTextField to those values provided as parameters to
     * the method.
     * 
     * @param tf         A JTextField component that the program will format
     *                   using the default parameters.
     * @param columns    An int value representing the length of the JTextField.
     * @param isEditable A boolean value indicating whether or not the
     *                   JTextField should be editable by the User.
     */
    private void setJTextFieldFormat(
            JTextField tf,
            int columns,
            boolean isEditable) {

        tf.setColumns(columns);
        tf.setEditable(isEditable);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------------- CLOSE - Global Helper Methods -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------- BEGIN - Full Form Creation Helper Methods -------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructFormPrompt()` method accepts nine parameters - a JPanel
     * ('pnl'), a LayoutManager ('layout'), a Dimension ('size'), a Border
     * ('border'), a JLabel('lbl'), a Font ('font'), a Color ('textColor'), an
     * int ('textAlign'), and a String ('lblText') - returning no values. The
     * method acts like a factory, using helper methods to construct a larger
     * "Form Prompt" component that can be added to a larger "Form Component"
     * allowing the User to submit information to the program.
     * 
     * @param pnl       A JPanel component that the program will format using
     *                  the provided parameters.
     * @param layout    A LayoutManager that will serve as the overall format
     *                  for the layout of the JPanel component.
     * @param size      A Dimension value that the program will use to set the
     *                  Minimum, Maximum, and Preferred size of the JPanel and
     *                  JLabel.
     * @param border    A Border value that will set the properties of the
     *                  JPanel's border (type, style, color, etc.).
     * @param lbl       A JLabel component that the program will format using
     *                  the provided parameters.
     * @param font      A Font value that the program will use to set the Font
     *                  property of the JLabel.
     * @param textColor A Color value that the program will use to set the
     *                  Foreground property of the JLabel.
     * @param textAlign An int value that the program will use to set the
     *                  Horizontal Alignment of the text in the JLabel.
     * @param lblText   A String value that the program will set the Text
     *                  property of the JLabel to.
     */
    private void constructFormPrompt(
            JPanel pnl,
            LayoutManager layout,
            Dimension size,
            Border border,
            JLabel lbl,
            Font font,
            Color textColor,
            int textAlign,
            String lblText) {

        setJPanelFormatWithNonBoxLayout(
                pnl,
                layout,
                size,
                border);
        setJLabelFormat(
                lbl,
                size,
                font,
                textColor,
                textAlign);
        refreshJLabelFormat(
                lbl,
                lblText);

        pnl.add(lbl);
    }

    /**
     * The `constructFormTextFieldWithButton()` method accepts ten parameters -
     * a JPanel ('pnl'), a LayoutManager ('layout'), a Dimension ('size'), a
     * Border ('border'), a JTextField ('tf'), an int ('tfColumnLength'), a
     * Boolean ('isEditable'), a JButton ('btn'), a String ('btnText'), and a
     * Boolean ('isClicked') - returning no values. The method acts like a
     * factory, using helper methods to construct a larger "Form Component"
     * that contains a text field and a button, allowing the User to submit
     * information to the program.
     * 
     * @param pnl            A JPanel component that the program will format
     *                       using the provided parameters.
     * @param layout         A LayoutManager that will serve as the overall
     *                       format for the layout of the JPanel component.
     * @param size           A Dimension value that the program will use to set
     *                       the Minimum, Maximum, and Preferred size of the
     *                       JPanel.
     * @param border         A Border value that will set the properties of the
     *                       JPanel's border (type, style, color, etc.).
     * @param tf             A JTextField component that the program will format
     *                       using the default parameters.
     * @param tfColumnLength An int value representing the length of the
     *                       JTextField.
     * @param isEditable     A boolean value indicating whether or not the
     *                       JTextField should be editable by the User.
     * @param btn            A JButton component that the program wishes to
     *                       change the color scheme of to reflect active /
     *                       clicked or inactive / is not clicked status.
     * @param btnText        A String object that the program would like to set
     *                       the 'btn' component's Text property to.
     * @param isClicked      A boolean value representing whether the passed
     *                       'btn' should be reformatted into active or inactive
     *                       colors.
     */
    private void constructFormTextFieldWithButton(
            JPanel pnl,
            LayoutManager layout,
            Dimension size,
            Border border,
            JTextField tf,
            int tfColumnLength,
            Boolean isEditable,
            JButton btn,
            String btnText,
            Boolean isClicked) {

        setJPanelFormatWithNonBoxLayout(
                pnl,
                layout,
                size,
                border);
        setJTextFieldFormat(
                tf,
                tfColumnLength,
                isEditable);
        setJButtonFormat(
                btn,
                btnText,
                isClicked);

        pnl.add(tf);
        pnl.add(btn);
    }

    /**
     * The `constructFormTextFieldWithoutButton()` method accepts seven
     * parameters - A JPanel ('pnl'), a LayoutManager ('layout'), a Dimension
     * ('size'), a Border ('border'), a JTextField ('tf'), an int
     * ('tfColumnLength'), and a Boolean ('isEditable') - returning no values.
     * The method acts like a factory, using helper methods to construct a
     * larger "Form Component" that contains a text field, allowing the User to
     * submit information to the program.
     * 
     * @param pnl            A JPanel component that the program will format
     *                       using the provided parameters.
     * @param layout         A LayoutManager that will serve as the overall
     *                       format for the layout of the JPanel component.
     * @param size           A Dimension value that the program will use to set
     *                       the Minimum, Maximum, and Preferred size of the
     *                       JPanel.
     * @param border         A Border value that will set the properties of the
     *                       JPanel's border (type, style, color, etc.).
     * @param tf             A JTextField component that the program will format
     *                       using the default parameters.
     * @param tfColumnLength An int value representing the length of the
     *                       JTextField.
     * @param isEditable     A boolean value indicating whether or not the
     *                       JTextField should be editable by the User.
     */
    private void constructFormTextFieldWithoutButton(
            JPanel pnl,
            LayoutManager layout,
            Dimension size,
            Border border,
            JTextField tf,
            int tfColumnLength,
            Boolean isEditable) {

        setJPanelFormatWithNonBoxLayout(
                pnl,
                layout,
                size,
                border);
        setJTextFieldFormat(
                tf,
                tfColumnLength,
                isEditable);

        pnl.add(tf);
    }

    /**
     * The `constructFormButton()` method accepts seven parameters - a JPanel
     * ('pnl'), a LayoutManager ('layout'), a Dimension ('size'), a Border
     * ('border'), a JButton ('btn'), a String ('btnText'), and a Boolean
     * ('isClicked') - returning no values. The method acts like a factory,
     * using helper methods to construct a larger "Form Component" that
     * contains a single button, allowing the User to submit information to the
     * program.
     * 
     * @param pnl       A JPanel component that the program will format
     *                  using the provided parameters.
     * @param layout    A LayoutManager that will serve as the overall
     *                  format for the layout of the JPanel component.
     * @param size      A Dimension value that the program will use to set
     *                  the Minimum, Maximum, and Preferred size of the
     *                  JPanel.
     * @param border    A Border value that will set the properties of the
     *                  JPanel's border (type, style, color, etc.).
     * @param btn       A JButton component that the program wishes to
     *                  change the color scheme of to reflect active /
     *                  clicked or inactive / is not clicked status.
     * @param btnText   A String object that the program would like to set
     *                  the 'btn' component's Text property to.
     * @param isClicked A boolean value representing whether the passed
     *                  'btn' should be reformatted into active or inactive
     *                  colors.
     */
    private void constructFormButton(
            JPanel pnl,
            LayoutManager layout,
            Dimension size,
            Border border,
            JButton btn,
            String btnText,
            Boolean isClicked) {

        setJPanelFormatWithNonBoxLayout(
                pnl,
                layout,
                size,
                border);
        setJButtonFormat(
                btn,
                btnText,
                isClicked);

        pnl.add(btn);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------- CLOSE - Full Form Creation Helper Methods -------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------- BEGIN - Mini Form Creation Helper Methods -------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructMiniFormRowPrompt()` method accepts three parameters - a
     * JPanel ('pnlRow') and two JLabels ('lblAppend' & 'lblRemove') -
     * returning no values. The method acts like a factory, using helper
     * methods to construct a larger "Mini Form Component" that contains two
     * labels for the Mini Form providing guidance to the User for which
     * JTextField and which JButton should be used to execute a specific action.
     * 
     * @param pnlRowPrompt A JPanel component that the program will format
     *                     using the provided parameters.
     * @param lblAppend    A JLabel component that the program will format using
     *                     the provided parameters.
     * @param lblRemove    A JLabel component that the program will format using
     *                     the provided parameters.
     */
    private void constructMiniFormRowPrompt(
            JPanel pnlRowPrompt,
            JLabel lblAppend,
            JLabel lblRemove) {

        setJPanelFormatWithBoxLayout(
                pnlRowPrompt,
                ROW,
                W0700_H0060,
                null);
        setJLabelFormat(
                lblAppend,
                W0300_H0050,
                HELVETICA_18,
                CLR_FORM_PROMPT,
                ALIGN_CENTER);
        setJLabelFormat(
                lblRemove,
                W0300_H0050,
                HELVETICA_18,
                CLR_FORM_PROMPT,
                ALIGN_CENTER);

        lblAppend.setText("Append A Forbidden Word");
        lblRemove.setText("Remove A Forbidden Word");

        pnlRowPrompt.add(lblAppend);
        pnlRowPrompt.add(Box.createRigidArea(W0100_H0000));
        pnlRowPrompt.add(lblRemove);
    }

    /**
     * The `constructMiniFormRowField()` method accepts three parameters - a
     * JPanel ('pnlRowField'), and two JTextFields ('tfAppend' & 'tfRemove') -
     * returning no values. The method acts like a factory, using helper
     * methods to construct a larger "Mini Form Component" that contains two
     * text fields for the Mini Form allowing the User to submit data to the
     * program for execution based on which action they are looking to take.
     * 
     * @param pnlRowField A JPanel component that the program will format
     *                    using the provided parameters.
     * @param tfAppend    A JTextField component that the program will format
     *                    using the default parameters.
     * @param tfRemove    A JTextField component that the program will format
     *                    using the default parameters.
     */
    private void constructMiniFormRowField(
            JPanel pnlRowField,
            JTextField tfAppend,
            JTextField tfRemove) {

        setJPanelFormatWithBoxLayout(
                pnlRowField,
                ROW,
                W0700_H0030,
                null);
        setJTextFieldFormat(
                tfAppend,
                30,
                true);
        setJTextFieldFormat(
                tfRemove,
                30,
                true);

        pnlRowField.add(tfAppend);
        pnlRowField.add(Box.createRigidArea(W0100_H0000));
        pnlRowField.add(tfRemove);
    }

    /**
     * The `constructMiniFormRowButton()` method accepts three parameters - a
     * JPanel ('pnlRowButton'), and two JButtons ('btnAppend' & 'btnRemove') -
     * returning no values. The method acts like a factory, using helper
     * methods to construct a larger "Mini Form Component" that contains two
     * buttons for the Mini Form allowing the User to submit data to the
     * program for execution based on which button they clicked.
     * 
     * @param pnlRowButton A JPanel component that the program will format
     *                     using the provided parameters.
     * @param btnAppend    A JButton component that the program will format
     *                     using the provided parameters.
     * @param btnRemove    A JButton component that the program will format
     *                     using the provided parameters.
     */
    private void constructMiniFormRowButton(
            JPanel pnlRowButton,
            JButton btnAppend,
            JButton btnRemove) {

        setJPanelFormatWithBoxLayout(
                pnlRowButton,
                ROW,
                W0700_H0060,
                null);
        setJButtonFormat(
                btnAppend,
                BUTTON_MINI_FORM_APPEND_AND_RERUN,
                false);
        setJButtonFormat(
                btnRemove,
                BUTTON_MINI_FORM_REMOVE_AND_RERUN,
                false);

        pnlRowButton.add(Box.createRigidArea(W0080_H0000));
        pnlRowButton.add(btnAppend);
        pnlRowButton.add(Box.createRigidArea(W0260_H0000));
        pnlRowButton.add(btnRemove);
        pnlRowButton.add(Box.createRigidArea(W0080_H0000));
    }

    /**
     * The `constructMiniForm()` method accepts ten parameters - four JPanels
     * ('pnlMain', 'pnlRowPrompt', 'pnlRowField', & 'pnlRowButton'), two
     * JLabels ('lblAppend' & 'lblRemove'), two JTextFields ('tfAppend' &
     * 'tfRemove'), and two JButtons ('btnAppend' & 'btnRemove'). The method
     * acts like a factory, using helper methods to construct a "Mini Form"
     * component that can later be inserted into another Swing component
     * allowing the User to interact with the program.
     * 
     * @param pnlMain      A JPanel component that the program will format
     *                     using the provided parameters.
     * @param pnlRowPrompt A JPanel component that the program will format
     *                     using the provided parameters.
     * @param lblAppend    A JLabel component that the program will format using
     *                     the provided parameters.
     * @param lblRemove    A JLabel component that the program will format using
     *                     the provided parameters.
     * @param pnlRowField  A JPanel component that the program will format
     *                     using the provided parameters.
     * @param tfAppend     A JTextField component that the program will format
     *                     using the default parameters.
     * @param tfRemove     A JTextField component that the program will format
     *                     using the default parameters.
     * @param pnlRowButton A JPanel component that the program will format
     *                     using the provided parameters.
     * @param btnAppend    A JButton component that the program will format
     *                     using the provided parameters.
     * @param btnRemove    A JButton component that the program will format
     *                     using the provided parameters.
     */
    private void constructMiniForm(
            JPanel pnlMain,
            JPanel pnlRowPrompt,
            JLabel lblAppend,
            JLabel lblRemove,
            JPanel pnlRowField,
            JTextField tfAppend,
            JTextField tfRemove,
            JPanel pnlRowButton,
            JButton btnAppend,
            JButton btnRemove) {

        setJPanelFormatWithBoxLayout(
                pnlMain,
                COLUMN,
                W0700_H0150,
                null);

        constructMiniFormRowPrompt(
                pnlRowPrompt,
                lblAppend,
                lblRemove);
        constructMiniFormRowField(
                pnlRowField,
                tfAppend,
                tfRemove);
        constructMiniFormRowButton(
                pnlRowButton,
                btnAppend,
                btnRemove);

        pnlMain.add(pnlRowPrompt);
        pnlMain.add(pnlRowField);
        pnlMain.add(pnlRowButton);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------- CLOSE - Mini Form Creation Helper Methods -------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------ BEGIN - "User Message" Components ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `setTitledBorderFormat()` method accepts four parameters - an int
     * ('messageType'), two Colors ('lineColor' & 'titleColor'), and a String
     * ('text') - returning no values. The method acts like a factory, using
     * helper methods to configure the properties of the
     * 'titledBorder_UserMessage' so they display the appropriate User
     * messaging and color coding based on whether the message is an Error or
     * Success.
     * 
     * @param messageType An int value that determines the title of the
     *                    TitledBorder format.
     * @param lineColor   A Color value that determines the Color of the line
     *                    in the TitledBorder.
     * @param titleColor  A Color value that determines the Color of the text /
     *                    title in the TitledBorder format.
     * @param text        A String value that represents any additional text
     *                    that the program would like to include in the title
     *                    section of the TitledBorder.
     */
    private void setTitledBorderFormat(
            int messageType,
            Color lineColor,
            Color titleColor,
            String text) {

        String title = "";

        if (messageType == 0) {
            title += "ERROR: " + text;
        } else if (messageType == 1) {
            title += "SUCCESS";
        }

        lineBorder_UserMessage = BorderFactory.createLineBorder(lineColor, 5, true);
        titledBorder_UserMessage = BorderFactory.createTitledBorder(lineBorder_UserMessage, title);

        titledBorder_UserMessage.setTitleColor(titleColor);
        titledBorder_UserMessage.setTitleFont(HELVETICA_24);
        titledBorder_UserMessage.setTitleJustification(TitledBorder.CENTER);
    }

    /**
     * The `displayUserMessagePanel()` method accepts nine parameters - an int
     * ('messageType'), two JPanels ('pnlOuter' & 'pnlInner'), a JTextArea
     * ('ta'), three Colors ('lineColor', 'titleColor', & 'messageColor'), and
     * two Strings ('title' & 'message') - returning no values. The method acts
     * like a factory, using helper methods to configure the overall "User
     * Message" panel for the User to see which will alert them to any
     * successes / failures they should be notified of if they are not actively
     * looking at the terminal.
     * 
     * @param messageType  An int value that determines the title of the
     *                     TitledBorder format.
     * @param pnlOuter     A JPanel component that the program will format
     *                     using the provided parameters.
     * @param pnlInner     A JPanel component that the program will format
     *                     using the provided parameters.
     * @param ta           A JTextArea component that the program will format
     *                     using the default parameters.
     * @param lineColor    A Color value that determines the Color of the line
     *                     in the TitledBorder.
     * @param titleColor   A Color value that determines the Color of the text /
     *                     title in the TitledBorder format.
     * @param messageColor A Color value that determines the Color of the
     *                     message in the TitledBorder format.
     * @param title        A String value that represents any additional text
     *                     that the program would like to include in the title
     *                     section of the TitledBorder.
     * @param message      A String value that represents any text that the
     *                     program would like to include in the body section of
     *                     the User Message / Alert.
     */
    private void displayUserMessagePanel(
            int messageType,
            JPanel pnlOuter,
            JPanel pnlInner,
            JTextArea ta,
            Color lineColor,
            Color titleColor,
            Color messageColor,
            String title,
            String message) {

        setTitledBorderFormat(
                messageType,
                lineColor,
                titleColor,
                title);
        setJPanelFormatWithNonBoxLayout(
                pnlInner,
                LM_GRIDBAG_00,
                W0650_H0100,
                titledBorder_UserMessage);
        setJTextAreaFormat(
                ta,
                W0600_H0050,
                HELVETICA_18,
                messageColor);

        refreshJTextAreaFormat(ta, message);
        revalidateAndRepaintJPanel(pnlInner);

        pnlInner.add(ta);
        pnlOuter.add(pnlInner);
        revalidateAndRepaintJPanel(pnlOuter);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------ CLOSE - "User Message" Components ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------------- BEGIN - "Menu Bar" Components -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructMenuBar()` method accepts no parameters returning no
     * values. The method sets the format of the various sub-components of the
     * Menu Bar before assembling them all into a single component that will be
     * added to the larger JFrame after construction.
     */
    private void constructMenuBar() {
        setJPanelFormatWithNonBoxLayout(
                pnl_MenuBar,
                LM_GRID_R1_C3,
                W1600_H0050,
                null);
        setJButtonFormat(
                btn_MenuBar_ParseNewJD,
                BUTTON_TEXT_PARSE_NEW_JD,
                true);
        setJButtonFormat(
                btn_MenuBar_ParseOldJD,
                BUTTON_TEXT_PARSE_OLD_JD,
                false);
        setJButtonFormat(
                btn_MenuBar_UpdateFW,
                BUTTON_TEXT_UPDATE_FW,
                false);

        pnl_MenuBar.add(btn_MenuBar_ParseNewJD);
        pnl_MenuBar.add(btn_MenuBar_ParseOldJD);
        pnl_MenuBar.add(btn_MenuBar_UpdateFW);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * -------------------- CLOSE - "Menu Bar" Components -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------ BEGIN - "Word Results Scroll Pane" Components ------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructScrollPaneWordResults()` method accepts three parameters -
     * a JScrollPane ('sp'), a JPanel ('pnl'), and a LayoutManager ('layout') -
     * returning no values. The method acts like a factory, creating a
     * JScrollPane with a JPanel display using the provided parameters to set
     * the properties of both components.
     * 
     * @param sp     A JScrollPane component that the program will format using
     *               the default parameters in addition to the provided JPanel
     *               and Dimension parameters.
     * @param pnl    A JPanel component that will be added to the JScrollPane as
     *               the display area for the JScrollPane.
     * @param layout A LayoutManager that will serve as the overall
     *               format for the layout of the JPanel component.
     */
    private void constructScrollPaneWordResults(
            JScrollPane sp,
            JPanel pnl,
            LayoutManager layout) {

        setJScrollPaneFormatWithJPanel(
                sp,
                pnl,
                W0700_H0400);
        setJPanelFormatWithNonBoxLayout(
                pnl,
                layout,
                null,
                null);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------ CLOSE - "Word Results Scroll Pane" Components ------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- BEGIN - "Left Panel" Components ------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructLeftPanel()` method accepts no parameters returning no
     * values. The method acts like a factory, assembling all of the disparate
     * components that make up the dynamic Left Panel - which, during active
     * use by a User, will either display instructions or the human-readable
     * results of a parsed job description.
     */
    private void constructLeftPanel() {
        setJPanelFormatWithNonBoxLayout(
                pnl_MainDisplay_Left,
                LM_BORDER_LEFT, W0800_H0950,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_Left_North,
                LM_GRIDBAG_01,
                W0800_H0100,
                null);
        setJLabelFormat(
                lbl_Left_Header,
                W0800_H0100,
                HELVETICA_36,
                CLR_DEFAULT_TEXT_HEAD,
                ALIGN_CENTER);
        refreshJLabelFormat(
                lbl_Left_Header,
                PARSE_NEW_JD_HEADING);

        pnl_Left_North.add(lbl_Left_Header);

        setJPanelFormatWithNoLayout(
                pnl_Left_East,
                W0050_H0800,
                null);
        setJPanelFormatWithNoLayout(
                pnl_Left_South,
                W0800_H0050,
                null);
        setJPanelFormatWithNoLayout(
                pnl_Left_West,
                W0050_H0800,
                null);

        pnl_MainDisplay_Left.add(pnl_Left_North, BorderLayout.NORTH);
        pnl_MainDisplay_Left.add(pnl_Left_East, BorderLayout.EAST);
        pnl_MainDisplay_Left.add(pnl_Left_South, BorderLayout.SOUTH);
        pnl_MainDisplay_Left.add(pnl_Left_West, BorderLayout.WEST);

        setJTextAreaFormat(
                ta_Left_Center,
                HELVETICA_24,
                CLR_DEFAULT_TEXT_BODY);
        refreshJTextAreaFormat(
                ta_Left_Center,
                PARSE_NEW_JD_EXPLANATION_TEXT);
        setJScrollPaneFormatWithJTextArea(
                sp_Left_Center,
                ta_Left_Center,
                W0700_H0800);

        pnl_MainDisplay_Left.add(sp_Left_Center, BorderLayout.CENTER);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- CLOSE - "Left Panel" Components ------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- BEGIN - "Right Panel" Components ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `constructRightPanel()` method accepts no parameters returning no
     * values. The method acts like a factory, assembling all of the disparate
     * components that make up the dynamic Right Panel - which can be one of
     * several displays based on the state of the application - and configuring
     * the default / initial display to be that of a request to "Parse New Job
     * Description".
     */
    private void constructRightPanel() {
        setJPanelFormatWithNonBoxLayout(
                pnl_MainDisplay_Right,
                LM_BORDER_RIGHT,
                W0800_H0950,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_Right_North,
                LM_GRIDBAG_02,
                W0800_H0100,
                null);
        setJLabelFormat(
                lbl_Right_Header,
                W0800_H0100,
                HELVETICA_36,
                CLR_DEFAULT_TEXT_HEAD,
                ALIGN_CENTER);
        setJPanelFormatWithNoLayout(
                pnl_Right_East,
                W0050_H0800,
                null);
        setJPanelFormatWithNoLayout(
                pnl_Right_South,
                W0800_H0050,
                null);
        setJPanelFormatWithNoLayout(
                pnl_Right_West,
                W0050_H0800,
                null);
        setJPanelFormatWithBoxLayout(
                pnl_Right_Center,
                COLUMN,
                W0700_H0800,
                null);

        pnl_Right_North.add(lbl_Right_Header);

        pnl_Right_Center.add(pnl_ParseNewJD);

        pnl_MainDisplay_Right.add(pnl_Right_North, BorderLayout.NORTH);
        pnl_MainDisplay_Right.add(pnl_Right_East, BorderLayout.EAST);
        pnl_MainDisplay_Right.add(pnl_Right_South, BorderLayout.SOUTH);
        pnl_MainDisplay_Right.add(pnl_Right_West, BorderLayout.WEST);
        pnl_MainDisplay_Right.add(pnl_Right_Center, BorderLayout.CENTER);
    }

    /**
     * The `constructPanelParsedNewJD()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the right panel associated with the
     * "Parsed New JD" screen for later insertion into the right panel when the
     * appropriate button is clicked by the User.
     */
    private void constructPanelParsedNewJD() {
        setJPanelFormatWithBoxLayout(
                pnl_ParsedNewJD,
                COLUMN,
                W0700_H0800,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_ParsedNewJD_UserMessage_Outer,
                LM_GRIDBAG_03,
                W0700_H0150,
                null);

        constructScrollPaneWordResults(
                sp_ParsedNewJD_WordResults,
                pnl_ParsedNewJD_WordResults,
                LM_GRID_R0_C4_NEW_JD);
        constructMiniForm(
                pnl_ParsedNewJD_MiniForm,
                pnl_ParsedNewJD_MiniForm_Row_Prompt,
                lbl_ParsedNewJD_MiniForm_Append, lbl_ParsedNewJD_MiniForm_Remove,
                pnl_ParsedNewJD_MiniForm_Row_Field,
                tf_ParsedNewJD_MiniForm_Append, tf_ParsedNewJD_MiniForm_Remove,
                pnl_ParsedNewJD_MiniForm_Row_Button,
                btn_ParsedNewJD_MiniForm_Append,
                btn_ParsedNewJD_MiniForm_Remove);

        pnl_ParsedNewJD.add(sp_ParsedNewJD_WordResults);
        pnl_ParsedNewJD.add(pnl_ParsedNewJD_UserMessage_Outer);
        pnl_ParsedNewJD.add(pnl_ParsedNewJD_MiniForm);
    }

    /**
     * The `constructPanelParsedOldJD()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the right panel associated with the
     * "Parsed Old JD" screen for later insertion into the right panel when the
     * appropriate button is clicked by the User.
     */
    private void constructPanelParsedOldJD() {
        setJPanelFormatWithBoxLayout(
                pnl_ParsedOldJD,
                COLUMN,
                W0700_H0800,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_ParsedOldJD_UserMessage_Outer,
                LM_GRIDBAG_04,
                W0700_H0150,
                null);

        constructScrollPaneWordResults(
                sp_ParsedOldJD_WordResults,
                pnl_ParsedOldJD_WordResults,
                LM_GRID_R0_C4_OLD_JD);
        constructMiniForm(
                pnl_ParsedOldJD_MiniForm,
                pnl_ParsedOldJD_MiniForm_Row_Prompt,
                lbl_ParsedOldJD_MiniForm_Append,
                lbl_ParsedOldJD_MiniForm_Remove,
                pnl_ParsedOldJD_MiniForm_Row_Field,
                tf_ParsedOldJD_MiniForm_Append,
                tf_ParsedOldJD_MiniForm_Remove,
                pnl_ParsedOldJD_MiniForm_Row_Button,
                btn_ParsedOldJD_MiniForm_Append,
                btn_ParsedOldJD_MiniForm_Remove);

        pnl_ParsedOldJD.add(sp_ParsedOldJD_WordResults);
        pnl_ParsedOldJD.add(pnl_ParsedOldJD_UserMessage_Outer);
        pnl_ParsedOldJD.add(pnl_ParsedOldJD_MiniForm);
    }

    /**
     * The `constructPanelUpdateFW()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the right panel associated with the
     * "Update "Forbidden Words" List" screen for later insertion into the
     * right panel when the appropriate button is clicked by the User.
     */
    private void constructPanelUpdateFW() {
        setJPanelFormatWithBoxLayout(
                pnl_UpdateFW,
                COLUMN,
                W0700_H0800,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_UpdateFW_UserMessage_Outer,
                LM_GRIDBAG_05,
                W0700_H0150,
                null);

        constructScrollPaneWordResults(
                sp_UpdateFW_WordResults,
                pnl_UpdateFW_WordResults,
                LM_GRID_R0_C4_UPD_FW);
        constructMiniForm(
                pnl_UpdateFW_MiniForm,
                pnl_UpdateFW_MiniForm_Row_Prompt,
                lbl_UpdateFW_MiniForm_Append,
                lbl_UpdateFW_MiniForm_Remove,
                pnl_UpdateFW_MiniForm_Row_Field,
                tf_UpdateFW_MiniForm_Append,
                tf_UpdateFW_MiniForm_Remove,
                pnl_UpdateFW_MiniForm_Row_Button,
                btn_UpdateFW_MiniForm_Append,
                btn_UpdateFW_MiniForm_Remove);

        pnl_UpdateFW.add(sp_UpdateFW_WordResults);
        pnl_UpdateFW.add(pnl_UpdateFW_UserMessage_Outer);
        pnl_UpdateFW.add(pnl_UpdateFW_MiniForm);
    }

    /**
     * The `constructParseOldJDForm()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the "Parse Old Job Description" form
     * so that it can be easily inserted into the construction of the "Parse
     * Old Job Description" panel upon creation.
     */
    private void constructParseOldJDForm() {
        setJPanelFormatWithBoxLayout(
                pnl_ParseOldJD_Form,
                COLUMN,
                W0700_H0200,
                null);

        constructFormPrompt(
                pnl_ParseOldJD_FormPrompt_FilePath,
                LM_FLOW_LFT_00,
                W0700_H0050,
                null,
                lbl_ParseOldJD_FormPrompt_FilePath,
                HELVETICA_18,
                CLR_FORM_PROMPT,
                ALIGN_LEFT,
                PROMPT_TEXT_SELECT_LOCATION);

        constructFormTextFieldWithButton(
                pnl_ParseOldJD_FormField_FilePath,
                LM_FLOW_LFT_01,
                W0700_H0050,
                null,
                tf_ParseOldJD_FormField_FilePath,
                49,
                false,
                btn_ParseOldJD_FormField_Browse,
                BUTTON_TEXT_BROWSE,
                false);

        constructFormButton(
                pnl_ParseOldJD_FormButton_Submit,
                LM_FLOW_CTR_00,
                W0700_H0050,
                null,
                btn_ParseOldJD_FormField_Submit,
                BUTTON_TEXT_PARSE,
                false);

        fc_ParseOldJD.setFileSelectionMode(JFileChooser.FILES_ONLY);

        pnl_ParseOldJD_Form.add(pnl_ParseOldJD_FormPrompt_FilePath);
        pnl_ParseOldJD_Form.add(pnl_ParseOldJD_FormField_FilePath);
        pnl_ParseOldJD_Form.add(Box.createRigidArea(W0000_H0050));
        pnl_ParseOldJD_Form.add(pnl_ParseOldJD_FormButton_Submit);
    }

    /**
     * The `constructParseNewJDForm()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the "Parse New Job Description" form
     * so that it can be easily inserted into the construction of the "Parse
     * New Job Description" panel upon creation.
     */
    private void constructParseNewJDForm() {
        setJPanelFormatWithBoxLayout(
                pnl_ParseNewJD_Form,
                COLUMN,
                W0700_H0350,
                null);

        constructFormPrompt(
                pnl_ParseNewJD_FormPrompt_FilePath,
                LM_FLOW_LFT_02,
                W0700_H0050,
                null,
                lbl_ParseNewJD_FormPrompt_FilePath,
                HELVETICA_18,
                CLR_FORM_PROMPT,
                ALIGN_LEFT,
                PROMPT_TEXT_SELECT_LOCATION);
        constructFormTextFieldWithButton(
                pnl_ParseNewJD_FormField_FilePath,
                LM_FLOW_LFT_03,
                W0700_H0050,
                null,
                tf_ParseNewJD_FormField_FilePath,
                49,
                false,
                btn_ParseNewJD_FormField_Browse,
                BUTTON_TEXT_BROWSE,
                false);
        constructFormPrompt(
                pnl_ParseNewJD_FormPrompt_URL,
                LM_FLOW_LFT_04,
                W0700_H0050,
                null,
                lbl_ParseNewJD_FormPrompt_URL,
                HELVETICA_18,
                CLR_FORM_PROMPT,
                ALIGN_LEFT,
                PROMPT_TEXT_DESCRIPTION_URL);
        constructFormTextFieldWithoutButton(
                pnl_ParseNewJD_FormField_URL,
                LM_FLOW_LFT_05,
                W0700_H0050,
                null,
                tf_ParseNewJD_FormField_URL,
                49,
                true);
        constructFormButton(
                pnl_ParseNewJD_FormField_Submit,
                LM_FLOW_CTR_01,
                W0700_H0050,
                null,
                btn_ParseNewJD_FormField_Submit,
                BUTTON_TEXT_PARSE,
                false);

        fc_ParseNewJD.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        pnl_ParseNewJD_Form.add(pnl_ParseNewJD_FormPrompt_FilePath);
        pnl_ParseNewJD_Form.add(pnl_ParseNewJD_FormField_FilePath);
        pnl_ParseNewJD_Form.add(Box.createRigidArea(W0000_H0050));
        pnl_ParseNewJD_Form.add(pnl_ParseNewJD_FormPrompt_URL);
        pnl_ParseNewJD_Form.add(pnl_ParseNewJD_FormField_URL);
        pnl_ParseNewJD_Form.add(Box.createRigidArea(W0000_H0050));
        pnl_ParseNewJD_Form.add(pnl_ParseNewJD_FormField_Submit);
    }

    /**
     * The `constructPanelParseOldJD()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the right panel associated with the
     * "Parse Old Job Description" screen for later insertion into the right
     * panel when the appropriate button is clicked by the User.
     */
    private void constructPanelParseOldJD() {
        setJPanelFormatWithBoxLayout(
                pnl_ParseOldJD,
                COLUMN,
                W0700_H0800,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_ParseOldJD_UserMessage_Outer,
                LM_GRIDBAG_06,
                W0700_H0150,
                null);

        constructParseOldJDForm();

        pnl_ParseOldJD.add(Box.createRigidArea(W0000_H0037));
        pnl_ParseOldJD.add(pnl_ParseOldJD_UserMessage_Outer);
        pnl_ParseOldJD.add(Box.createRigidArea(W0000_H0037));
        pnl_ParseOldJD.add(pnl_ParseOldJD_Form);
        pnl_ParseOldJD.add(Box.createVerticalGlue());
    }

    /**
     * The `constructPanelParseNewJD()` method accepts no parameters returning
     * no values. The method acts like a factory, assembling all of the
     * disparate components that make up the right panel associated with the
     * "Parse New Job Description" screen for later insertion into the right
     * panel when the appropriate button is clicked by the User.
     */
    private void constructPanelParseNewJD() {
        setJPanelFormatWithBoxLayout(
                pnl_ParseNewJD,
                COLUMN,
                W0700_H0800,
                null);
        setJPanelFormatWithNonBoxLayout(
                pnl_ParseNewJD_UserMessage_Outer,
                LM_GRIDBAG_07,
                W0700_H0150,
                null);

        constructParseNewJDForm();

        pnl_ParseNewJD.add(Box.createRigidArea(W0000_H0037));
        pnl_ParseNewJD.add(pnl_ParseNewJD_UserMessage_Outer);
        pnl_ParseNewJD.add(Box.createRigidArea(W0000_H0037));
        pnl_ParseNewJD.add(pnl_ParseNewJD_Form);
        pnl_ParseNewJD.add(Box.createVerticalGlue());
    }

    /**
     * The `refreshRightPanel_ParsedNewJD()` method accepts a single
     * PostingParserBackEnd parameter - 'ppbe' - returning no values. When the
     * method is called, the panel showing the "Word Results" of the most
     * recent job description parsing is removed and refreshed with an updated
     * parsing based on whether or not a word was appended to or removed from
     * the "Forbidden Words" list. Once updated the panel is refreshed and an
     * updated display is shown to the User.
     * 
     * @param ppbe An instance of the PostingParserBackEnd class that was
     *             created upon the initialization of the "PostingParser" program.
     */
    private void refreshRightPanel_ParsedNewJD(PostingParserBackEnd ppbe) {
        pnl_Right_Center.removeAll();
        pnl_ParsedNewJD_WordResults.removeAll();

        ppbe.retallyJobDescriptionWords(ppbe.getRoleDataFile());

        Enumeration<String> uniqueWords = ppbe.getUniqueWordsAndCounts().keys();

        while (uniqueWords.hasMoreElements()) {
            String word = uniqueWords.nextElement();
            Integer count = ppbe.getUniqueWordsAndCounts().get(word);
            JLabel template = new JLabel();

            setJLabelFormat(
                    template,
                    count,
                    W0170_H0025,
                    HELVETICA_14,
                    ALIGN_CENTER);

            template.setText(word + " (" + count + ")");
            pnl_ParsedNewJD_WordResults.add(template);
        }

        revalidateAndRepaintJPanel(pnl_ParsedNewJD_WordResults);

        pnl_Right_Center.add(pnl_ParsedNewJD);

        revalidateAndRepaintJPanel(pnl_Right_Center);
    }

    /**
     * The `refreshRightPanel_ParsedOldJD()` method accepts a single
     * PostingParserBackEnd parameter - 'ppbe' - returning no values. When the
     * method is called, the panel showing the "Word Results" of the most
     * recent job description parsing is removed and refreshed with an updated
     * parsing based on whether or not a word was appended to or removed from
     * the "Forbidden Words" list. Once updated the panel is refreshed and an
     * updated display is shown to the User.
     * 
     * @param ppbe An instance of the PostingParserBackEnd class that was
     *             created upon the initialization of the "PostingParser"
     *             program.
     */
    private void refreshRightPanel_ParsedOldJD(PostingParserBackEnd ppbe) {
        pnl_Right_Center.removeAll();
        pnl_ParsedOldJD_WordResults.removeAll();

        ppbe.retallyJobDescriptionWords(ppbe.getRoleDataFile());

        Enumeration<String> uniqueWords = ppbe.getUniqueWordsAndCounts().keys();

        while (uniqueWords.hasMoreElements()) {
            String word = uniqueWords.nextElement();
            Integer count = ppbe.getUniqueWordsAndCounts().get(word);
            JLabel template = new JLabel();

            setJLabelFormat(
                    template,
                    count,
                    W0170_H0025,
                    HELVETICA_14,
                    ALIGN_CENTER);

            template.setText(word + " (" + count + ")");
            pnl_ParsedOldJD_WordResults.add(template);
        }

        revalidateAndRepaintJPanel(pnl_ParsedOldJD_WordResults);

        pnl_Right_Center.add(pnl_ParsedOldJD);

        revalidateAndRepaintJPanel(pnl_Right_Center);
    }

    /**
     * The `refreshRightPanel_UpdateFW()` method accepts a single
     * PostingParserBackEnd parameter - 'ppbe' - returning no values. When the
     * method is called, the panel showing the "Word Results" of the most
     * recent job description parsing is removed and refreshed with an updated
     * parsing based on whether or not a word was appended to or removed from
     * the "Forbidden Words" list. Once updated the panel is refreshed and an
     * updated display is shown to the User.
     * 
     * @param ppbe An instance of the PostingParserBackEnd class that was
     *             created upon the initialization of the "PostingParser"
     *             program.
     */
    private void refreshRightPanel_UpdateFW(PostingParserBackEnd ppbe) {
        pnl_Right_Center.removeAll();

        for (String word : ppbe.getForbiddenWords()) {
            JLabel template = new JLabel();
            setJLabelFormat(
                    template,
                    W0170_H0025,
                    HELVETICA_14,
                    CLR_DEFAULT_TEXT_BODY,
                    ALIGN_CENTER);
            template.setText(word);
            pnl_UpdateFW_WordResults.add(template);
        }

        revalidateAndRepaintJPanel(pnl_UpdateFW_WordResults);

        pnl_Right_Center.add(pnl_UpdateFW);

        revalidateAndRepaintJPanel(pnl_Right_Center);
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------- CLOSE - "Right Panel" Components ------------------ *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
}
