import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.text.html.parser.ParserDelegator;

public class PostingParser {

    private static final String TITLE = "Posting Parser";

    public static void main(String[] args) /** throws IOException */
    {
        PostingParserFrontEnd parserFrontEnd = new PostingParserFrontEnd(TITLE);
        parserFrontEnd.setVisible(true);

        // // Create A New URL Object
        // URL demo = new URL(
        // "https://boards.greenhouse.io/doordash/jobs/1541859?gh_jid=1541859#app");
        // // Open A Connection To The URL
        // URLConnection connection = demo.openConnection();
        // //
        // InputStreamReader inputStream = new
        // InputStreamReader(connection.getInputStream());
        // //
        // BufferedReader reader = new BufferedReader(inputStream);
        // //
        // String i;
        // //
        // while ((i = reader.readLine()) != null) {
        // System.out.println(i);
        // }

        // OFFICIAL CODE BELOW

        // URL demo = new URL("https://boards.greenhouse.io/rvohealth/jobs/4231267005");
        // URLConnection connection = demo.openConnection();
        // InputStreamReader inputStream = new
        // InputStreamReader(connection.getInputStream());
        // BufferedReader reader = new BufferedReader(inputStream);
        // ParserDelegator pd = new ParserDelegator();
        // pd.parse(reader, new GreenhouseParse(), false);
    }
}

class PostingParserFrontEnd extends JFrame {

    //
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 1000;

    //
    private static final Dimension NORTH_BORDER = new Dimension(1600, 50);
    private static final Dimension WINDOW_PANEL = new Dimension(800, 950);
    private static final Dimension EAST_WEST_PANEL_BORDER = new Dimension(50, 700);
    private static final Dimension NORTH_PANEL_BORDER = new Dimension(800, 100);
    private static final Dimension SOUTH_PANEL_BORDER = new Dimension(800, 50);
    private static final Dimension EAST_PANEL_FORM = new Dimension(700, 450);
    private static final Dimension EAST_PANEL_ERROR = new Dimension(700, 100);

    //
    private static final Font HELVETICA_18 = new Font("Helvetica", 1, 18);
    private static final Font HELVETICA_24 = new Font("Helvetica", 1, 24);
    private static final Font HELVETICA_36 = new Font("Helvetica", 1, 36);

    //
    private static final Color BACKGROUND_COLOR = new Color(37, 38, 43);
    private static final Color HEADLINE_TEXT = new Color(75, 175, 100);
    private static final Color BODY_TEXT = new Color(200, 200, 200);
    private static final Color QUESTION_PROMPT = new Color(150, 255, 200);

    //
    private JPanel windowNorthPanel = new JPanel();
    private JPanel windowCenterPanel = new JPanel(new GridLayout(1, 2));

    //
    private JPanel windowCenterEastPanel = new JPanel(new BorderLayout());
    private JPanel windowCenterWestPanel = new JPanel(new BorderLayout());

    //
    private JPanel windowWestPanelEastBorder = new JPanel();
    private JPanel windowWestPanelWestBorder = new JPanel();
    private JPanel windowWestPanelNorthBorder = new JPanel();
    private JPanel windowWestPanelSouthBorder = new JPanel();

    //
    private JTextArea windowWestPanelBodyText = new JTextArea();
    private JScrollPane windowWestPanelCenter = new JScrollPane(windowWestPanelBodyText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    private JLabel westPanelHeading = new JLabel("Introduction");
    private String westPanelBodyLabel = "This application is a v0 'mock up' of what will (hopefully) be a full Single Page Application available for use on the web (and, with luck, via Google Chrome as a browser extension and in Gmail).\n\nTo use this application:\n\n1) Provide the application with a directory location to create a 'Job Search' folder in your local machine.\n\n2) Provide the URL of a job posting that you would like the program to parse\n\n(NOTE: At present, the program can only parse those jobs for companies that are using Greenhouse as their ATS - Applicant Tracking System).\n\nOnce you've provided this information, click the 'Parse The Posting!' button and the program will either alert you to an error or, if no errors are present, will parse the posting.\n\nUpon parsing completion this window will refresh with a two panel display - on the left will be the parsed job description and on the right will be the words used in the job description with counts for the number of times that word was used in the posting.";

    //
    private JPanel windowEastPanelEastBorder = new JPanel();
    private JPanel windowEastPanelWestBorder = new JPanel();
    private JPanel windowEastPanelNorthBorder = new JPanel();
    private JPanel windowEastPanelSouthBorder = new JPanel();
    private JPanel windowEastPanelCenter = new JPanel();

    //
    private JPanel windowEastPanelCenterTop = new JPanel();
    private JPanel windowEastPanelCenterBottom = new JPanel();
    private JPanel introEastPanelRow01 = new JPanel();
    private JLabel introEastPanelRow02 = new JLabel();
    private JPanel introEastPanelRow03 = new JPanel();
    private JPanel introEastPanelRow04 = new JPanel();
    private JLabel introEastPanelRow05 = new JLabel();
    private JPanel introEastPanelRow06 = new JPanel();
    private JPanel introEastPanelRow07 = new JPanel();
    private JPanel introEastPanelRow08 = new JPanel();
    private JPanel introEastPanelRow09 = new JPanel();

    //
    private JFileChooser introEastPanelChooser = new JFileChooser();
    private JTextField introEastSaveTextField = new JTextField(35);
    private JButton introEastPanelBrowse = new JButton("Browse");
    private JTextField introEastURLTextField = new JTextField(35);
    private JButton introEastPanelParseButton = new JButton("Parse The Description!");

    public PostingParserFrontEnd() {
        String title = "Posting Parser";
        layoutComponents(title);
        // addListeners();
    }

    public PostingParserFrontEnd(String title) {
        layoutComponents(title);
        // addListeners();
    }

    private void layoutComponents(String title) {
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        layoutWindowHeader();
        layoutIntroWestPanel();
        layoutIntroEastPanel();

        this.add(windowNorthPanel, BorderLayout.NORTH);
        this.add(windowCenterPanel, BorderLayout.CENTER);
    }

    private void layoutWindowHeader() {
        windowNorthPanel.setPreferredSize(NORTH_BORDER);
        windowNorthPanel.setBackground(BACKGROUND_COLOR);
    }

    private void layoutIntroWestPanel() {
        // Set Preferred Size Of Panel
        windowCenterWestPanel.setPreferredSize(WINDOW_PANEL);

        // Layout Borders
        windowWestPanelNorthBorder.setPreferredSize(NORTH_PANEL_BORDER);
        windowWestPanelNorthBorder.setBackground(BACKGROUND_COLOR);
        windowWestPanelEastBorder.setPreferredSize(EAST_WEST_PANEL_BORDER);
        windowWestPanelEastBorder.setBackground(BACKGROUND_COLOR);
        windowWestPanelSouthBorder.setPreferredSize(SOUTH_PANEL_BORDER);
        windowWestPanelSouthBorder.setBackground(BACKGROUND_COLOR);
        windowWestPanelWestBorder.setPreferredSize(EAST_WEST_PANEL_BORDER);
        windowWestPanelWestBorder.setBackground(BACKGROUND_COLOR);

        // Format North Border Of The West Panel
        windowWestPanelNorthBorder.setLayout(new GridBagLayout());
        windowWestPanelNorthBorder.add(westPanelHeading);
        westPanelHeading.setForeground(HEADLINE_TEXT);
        westPanelHeading.setFont(HELVETICA_36);

        // Format Center Of The West Panel
        windowWestPanelCenter.setBorder(null);
        windowWestPanelBodyText.setText(westPanelBodyLabel);
        windowWestPanelBodyText.setLineWrap(true);
        windowWestPanelBodyText.setWrapStyleWord(true);
        windowWestPanelBodyText.setBackground(BACKGROUND_COLOR);
        windowWestPanelBodyText.setForeground(BODY_TEXT);
        windowWestPanelBodyText.setFont(HELVETICA_24);

        // Place The Sub-Components Of The West Panel In The Panel
        windowCenterWestPanel.add(windowWestPanelNorthBorder, BorderLayout.NORTH);
        windowCenterWestPanel.add(windowWestPanelSouthBorder, BorderLayout.SOUTH);
        windowCenterWestPanel.add(windowWestPanelEastBorder, BorderLayout.EAST);
        windowCenterWestPanel.add(windowWestPanelWestBorder, BorderLayout.WEST);
        windowCenterWestPanel.add(windowWestPanelCenter, BorderLayout.CENTER);

        // Place The West Panel Component In The Window
        windowCenterPanel.add(windowCenterWestPanel);
    }

    private void layoutIntroEastPanel() {
        // Set Preferred Size Of Window Panel
        windowCenterEastPanel.setPreferredSize(WINDOW_PANEL);

        // Layout Borders
        windowEastPanelNorthBorder.setPreferredSize(NORTH_PANEL_BORDER);
        windowEastPanelNorthBorder.setBackground(BACKGROUND_COLOR);
        windowEastPanelSouthBorder.setPreferredSize(SOUTH_PANEL_BORDER);
        windowEastPanelSouthBorder.setBackground(BACKGROUND_COLOR);
        windowEastPanelEastBorder.setPreferredSize(EAST_WEST_PANEL_BORDER);
        windowEastPanelEastBorder.setBackground(BACKGROUND_COLOR);
        windowEastPanelWestBorder.setPreferredSize(EAST_WEST_PANEL_BORDER);
        windowEastPanelWestBorder.setBackground(BACKGROUND_COLOR);

        // Format North Border Of The East Panel

        // Format Center Top Of The East Panel
        windowEastPanelCenterTop.setLayout(new GridLayout(9, 1));
        windowEastPanelCenterTop.setPreferredSize(EAST_PANEL_FORM);
        windowEastPanelCenterTop.setBackground(BACKGROUND_COLOR);

        introEastPanelRow01.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow01.setBackground(BACKGROUND_COLOR);

        introEastPanelRow02.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow02.setText("1) Select Save Location:");
        introEastPanelRow02.setFont(HELVETICA_18);
        introEastPanelRow02.setForeground(QUESTION_PROMPT);

        introEastSaveTextField.setFont(HELVETICA_18);

        introEastPanelRow03.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow03.add(introEastSaveTextField);
        introEastPanelRow03.add(introEastPanelBrowse);
        introEastPanelRow03.setBackground(BACKGROUND_COLOR);

        introEastPanelRow04.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow04.setBackground(BACKGROUND_COLOR);

        introEastPanelRow05.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow05.setText("2) Paste Job Description URL To Parse:");
        introEastPanelRow05.setFont(HELVETICA_18);
        introEastPanelRow05.setForeground(QUESTION_PROMPT);

        introEastURLTextField.setFont(HELVETICA_18);

        introEastPanelRow06.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow06.add(introEastURLTextField);
        introEastPanelRow06.setBackground(BACKGROUND_COLOR);

        introEastPanelRow07.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow07.setBackground(BACKGROUND_COLOR);

        introEastPanelRow08.setLayout(new FlowLayout(FlowLayout.CENTER));
        introEastPanelRow08.add(introEastPanelParseButton);
        introEastPanelRow08.setBackground(BACKGROUND_COLOR);

        introEastPanelRow09.setLayout(new FlowLayout(FlowLayout.LEFT));
        introEastPanelRow09.setBackground(BACKGROUND_COLOR);

        windowEastPanelCenterTop.add(introEastPanelRow01);
        windowEastPanelCenterTop.add(introEastPanelRow02);
        windowEastPanelCenterTop.add(introEastPanelRow03);
        windowEastPanelCenterTop.add(introEastPanelRow04);
        windowEastPanelCenterTop.add(introEastPanelRow05);
        windowEastPanelCenterTop.add(introEastPanelRow06);
        windowEastPanelCenterTop.add(introEastPanelRow07);
        windowEastPanelCenterTop.add(introEastPanelRow08);
        windowEastPanelCenterTop.add(introEastPanelRow09);

        // Format Center Bottom Of The East Panel
        windowEastPanelCenterBottom.setPreferredSize(EAST_PANEL_ERROR);

        //
        windowEastPanelCenter.setBackground(BACKGROUND_COLOR);

        // Place The Sub-Components Of The East Window In The Panel
        windowEastPanelCenter.add(windowEastPanelCenterTop);
        windowEastPanelCenter.add(windowEastPanelCenterBottom);

        windowCenterEastPanel.add(windowEastPanelNorthBorder, BorderLayout.NORTH);
        windowCenterEastPanel.add(windowEastPanelSouthBorder, BorderLayout.SOUTH);
        windowCenterEastPanel.add(windowEastPanelEastBorder, BorderLayout.EAST);
        windowCenterEastPanel.add(windowEastPanelWestBorder, BorderLayout.WEST);
        windowCenterEastPanel.add(windowEastPanelCenter, BorderLayout.CENTER);

        // Place The East Panel Component In The Window
        windowCenterPanel.add(windowCenterEastPanel);
    }
}
