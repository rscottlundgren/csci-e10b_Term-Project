import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The "GreenhouseParser" class is a template class that was created for
 * tailored parsing of job descriptions hosted within the Greenhouse ATS
 * ecosystem.
 */
public class GreenhouseParser {

    // Instance State

    private String url;
    private String filePath;
    private String roleTitle;
    private boolean parserIsInHeader;
    private boolean parserIsInContent;

    // Constructor(s)

    /**
     * The "GreenhouseParser" zero-argument constructor creates a single
     * instance of the "GreenhouseParser" class where each of the instance
     * state variables are set to an empty or false default based on the
     * variable type; returns no values.
     */
    public GreenhouseParser() {
        setURL("");
        setFilePath("");
        setRoleTitle("");
        setParserIsInHeader(false);
        setParserIsInContent(false);
    }

    /**
     * The "GreenhouseParser" single-argument constructor accepts a single
     * String parameter - 'url' - returning no values. The constructor assigns
     * the passed parameter to the appropriate state variable, setting all
     * other instance variables to an empty or false default based on the
     * variable type.
     * 
     * @param url A String representation of the Uniform Resource Locator used
     *            to make a connection to a website.
     */
    public GreenhouseParser(String url) {
        setURL(url);
        setFilePath("");
        setRoleTitle("");
        setParserIsInHeader(false);
        setParserIsInContent(false);
    }

    /**
     * The "GreenhouseParser" two-argument constructor accepts two String
     * parameters - 'url' & 'filePath' - returning no values. The constructor
     * assigns the two parsed parameters to their appropriate state variables,
     * setting all other instance variables to an empty or false default based
     * on the variable type.
     * 
     * @param url      A String representation of the Uniform Resource Locator
     *                 used to make a connection to a website.
     * @param filePath A String representation of the File Path where the User
     *                 would like to store their copies of the job description.
     */
    public GreenhouseParser(String url, String filePath) {
        setURL(url);
        setFilePath(filePath);
        setRoleTitle("");
        setParserIsInHeader(false);
        setParserIsInContent(false);
    }

    // Setters & Getters

    /**
     * The `setUrl()` method accepts a single String parameter - 'url' -
     * returning no values. The method sets the 'url' instance variable to the
     * provided parameter.
     * 
     * @param url A String representation of the Uniform Resource Locator used
     *            to make a connection to a website.
     */
    private void setURL(String url) {
        this.url = url;
    }

    /**
     * The `setFilePath()` method accepts a single String parameter -
     * 'filePath' - returning no values. The method sets the 'filePath'
     * instance variable to the provided parameter.
     * 
     * @param filePath A String representation of the File Path where the User
     *                 would like to store their copies of the job description.
     */
    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The `setRoleTitle()` method accepts a single String parameter -
     * 'roleTitle' - returning no values. The method sets the 'roleTitle'
     * instance variable to the provided parameter.
     * 
     * @param roleTitle A String representation of the position title procured
     *                  from the provided Job Description URL.
     */
    private void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    /**
     * The `setParserIsInHeader()` method accepts a single Boolean parameter -
     * 'parserIsInHeader' - returning no values. The method sets the
     * 'parserIsInHeader' instance variable to the provided parameter.
     * 
     * @param parserIsInHeader A boolean representation of whether the program
     *                         is within the 'header' section of the job
     *                         description.
     */
    private void setParserIsInHeader(boolean parserIsInHeader) {
        this.parserIsInHeader = parserIsInHeader;
    }

    /**
     * The 'setParserIsInContent()' method accepts a single Boolean parameter -
     * 'parserIsInContent' - returning no values. The method sets the
     * 'parserIsInContent' instance variable to the provided parameter.
     * 
     * @param parserIsInContent A boolean representation of whether the program
     *                          is within the 'content' section of the job
     *                          description.
     */
    private void setParserIsInContent(boolean parserIsInContent) {
        this.parserIsInContent = parserIsInContent;
    }

    /**
     * The 'getURL()' method accepts no parameters and returns the String
     * representation of the value stored within the 'url' instance variable.
     * 
     * @return A String representation of the value stored within the 'url'
     *         instance variable.
     */
    private String getURL() {
        return this.url;
    }

    /**
     * The 'getFilePath()' method accepts no parameters and returns the String
     * representation of the value stored within the 'filePath' instance
     * variable.
     * 
     * @return A String representation of the value stored within the
     *         'filePath' instance variable.
     */
    private String getFilePath() {
        return this.filePath;
    }

    /**
     * The 'getRoleTitle()' method accepts no parameters and returns the String
     * representation of the value stored within the 'roleTitle' instance
     * variable.
     * 
     * @return A String representation of the value stored within the
     *         'roleTitle' instance variable.
     */
    private String getRoleTitle() {
        return this.roleTitle;
    }

    /**
     * The 'getParserIsInHeader()' method accepts no parameters and returns the
     * Boolean representation of the value stored within the 'parserIsInHeader'
     * instance variable.
     * 
     * @return A Boolean representation of the value stored within the
     *         'parserIsInHeader' instance variable.
     */
    private boolean getParserIsInHeader() {
        return this.parserIsInHeader;
    }

    /**
     * The `getParserIsInContent()` method accepts no parameters and returns
     * the Boolean representation of the value stored within the
     * 'parserIsInContent' instance variable.
     * 
     * @return A Boolean representation of the value stored within the
     *         'parserIsInContent' instance variable.
     */
    private boolean getParserIsInContent() {
        return this.parserIsInContent;
    }

    /**
     * The `instantiateCompanyDirectory()` method accepts a single String as a
     * parameter - 'programFilePath' - returning a String representation of the
     * company file path. This method is used to help build the file path
     * structure for easily referenceable job descriptions for the User.
     * 
     * @param programFilePath A String value representing the file path where
     *                        the documents created by the program will be
     *                        stored locally.
     * @return A String representation of the file path to the company
     *         directory where all of that company's parsed job descriptions
     *         will be stored locally.
     */
    public String instantiateCompanyDirectory(String programFilePath) {

        // Method State
        String companyName = getCompanyName();
        String companyFilePath = programFilePath + File.separator + companyName;

        // Method Behavior
        File directoryCompany = new File(companyFilePath);
        directoryCreateAndLog(directoryCompany, companyFilePath, companyName);
        return companyFilePath;
    };

    /**
     * The `getCompanyName()` method accepts no parameters, returning a String
     * representation of the Company Name as retrieved from the User provided
     * URL.
     * 
     * @return A String representation of the Company Name.
     */
    private String getCompanyName() {

        // Method State
        int[] substringCompanyName = new int[2];
        String companyName = "";

        // Method Behavior
        try {
            URL url = new URL(getURL());
            locateCompanyNameInGreenhouseURL(url.getPath(), substringCompanyName);
            companyName += url.getPath().substring(substringCompanyName[0], substringCompanyName[1]);
            return companyName;
        } catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            return "ERROR";
        }
    }

    /**
     * The `locateCompanyNameInGreenhouseURL()` method accepts a String and
     * array of Integers as parameters - 'url' and 'substringCompanyName' -
     * returning no values. The method takes the 'url' and loops through the
     * String searching for specific delimiters. The location of the delimiters
     * are stored within the 'substringCompanyName' and are then used to create
     * a substring of the original 'url' which only contains the company name.
     * 
     * @param url                  A String representation of the URL provided
     *                             by the User.
     * @param substringCompanyName An array of integers containing the
     *                             beginning and ending indices for the
     *                             creation of a substring from the User
     *                             provided 'url'.
     */
    private void locateCompanyNameInGreenhouseURL(String url, int[] substringCompanyName) {

        // Method State
        int count = 0;

        // Method Behavior
        for (int i = 0; i < url.length(); i++) {
            if (count == 0 && url.charAt(i) == '/') {
                substringCompanyName[0] = i + 1;
                count++;
            } else if (count == 1 && url.charAt(i) == '/') {
                substringCompanyName[1] = i;
                count++;
            }
        }
    }

    /**
     * The `instantiateRoleDirectory()` method accepts a single String
     * parameter - 'companyFilePath' - returning a single String value
     * representing the file path to locate the role directory.
     * 
     * @param companyFilePath A String representation of the file path where
     *                        the company information is going to be stored.
     * @return A String representation of the role File Path.
     */
    public String instantiateRoleDirectory(String companyFilePath) {

        // Method State
        String date = getTodaysDate();
        String role = returnJobDescriptionRoleTitle();
        String directoryName = date + "_" + role;
        String roleFilePath = companyFilePath + File.separator + directoryName;

        // Method Behavior
        File directoryRole = new File(roleFilePath);
        directoryCreateAndLog(directoryRole, companyFilePath, directoryName);
        setFilePath(roleFilePath);
        return roleFilePath;
    }

    /**
     * The `getTodaysDate()` method accepts no parameters, returning a String
     * representation of today's date in YYYY-MM-DD format.
     * 
     * @return A String representation of today's date in YYYY-MM-DD format.
     */
    public String getTodaysDate() {

        // Method State
        String today;

        // Method Behavior
        LocalDateTime dateToday = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        today = "" + dateFormatter.format(dateToday);
        return today;
    }

    /**
     * The `instantiateRawDataDirectory()` method accepts a single String
     * parameter - 'roleFilePath' - returning a String representation of the
     * file path where the 'raw data' will be stored locally within the User's
     * machine.
     * 
     * @param roleFilePath A String value representing the location of the role
     *                     data within the User's machine.
     * @return A String value representing the location of the URL's raw data
     *         within the User's machine.
     */
    public String instantiateRawDataDirectory(String roleFilePath) {

        // Method State
        String directoryName = "raw_data";
        String rawDataFilePath = roleFilePath + File.separator + directoryName;

        // Method Behavior
        File directoryRawData = new File(rawDataFilePath);
        directoryCreateAndLog(directoryRawData, rawDataFilePath, directoryName);
        setFilePath(rawDataFilePath);
        return rawDataFilePath;
    }

    /**
     * The `directoryCreateAndLog()` method accepts a File and two String
     * parameters - 'newFilePath', 'rootFilePath', and 'directoryName',
     * respectively - returning no values. The method creates, or doesn't
     * create, directories within the User's local machine, based on whether
     * such directories are already present at the time of the program running.
     * 
     * @param newFilePath   A File object where the new file path is going to
     *                      attempt creation.
     * @param rootFilePath  A String representation of the "root" file path
     *                      where the new file will exist within the User's
     *                      local machine.
     * @param directoryName A String representation of the directory name that
     *                      is going to be created.
     */
    private void directoryCreateAndLog(File newFilePath, String rootFilePath, String directoryName) {
        if (!newFilePath.exists()) {
            System.out.printf(
                    "'%s' Directory Does Not Exist; Attempting To Create The Directory...\n", directoryName);
            if (newFilePath.mkdir()) {
                System.out.printf("'%s' Directory Has Been Successfully Created At Path: %s\n", directoryName,
                        rootFilePath);
            } else {
                System.out.printf("Directory Cannot Be Created\n");
            }
        } else {
            System.out.printf("'%s' Directory Already Exists; Moving On To Next Step...\n", directoryName);
        }
    }

    /**
     * The `returnHTMLTagContent()` method accepts an Element and an
     * HTMLDocument as parameter objects - 'element' and 'htmlDoc' - returning
     * a String representation of the contents between the HTML tags.
     * 
     * @param element An Element object containing necessary information about
     *                the beginning and end of the HTML Tag's 'offset' data.
     * @param htmlDoc An HTMLDocument object containing the raw data that the
     *                method will parse.
     * @return A String representation of the contents of the provided HTML tag.
     */
    private String returnHTMLTagContent(Element element, HTMLDocument htmlDoc) {
        int count = element.getElementCount();
        StringBuilder content = new StringBuilder();
        try {
            for (int i = 0; i < count; i++) {
                Element child = element.getElement(i);
                int startOffset = child.getStartOffset();
                int endOffset = child.getEndOffset();
                int length = endOffset - startOffset;
                content.append(htmlDoc.getText(startOffset, length));
            }
        } catch (BadLocationException ble) {
            ble.getMessage();
        }
        return content.toString();
    }

    /**
     * The `returnJobDescriptionRoleTitle()` method accepts no parameters,
     * returning a String value representing the "role title" described in the
     * User provided job description URL.
     * 
     * @return A String representation of the role title described in the User
     *         provided job description URL.
     */
    public String returnJobDescriptionRoleTitle() {
        String roleTitle = "";
        String role = "";
        try {
            URL url = new URL(getURL());
            URLConnection connection = url.openConnection();
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            htmlKit.read(inputStream, htmlDoc, 0);
            // Parse
            ElementIterator iterator = new ElementIterator(htmlDoc);
            Element element;
            while ((element = iterator.next()) != null) {
                AttributeSet as = element.getAttributes();
                Object name = as.getAttribute(StyleConstants.NameAttribute);
                if (name == HTML.Tag.H1 && as.containsAttribute(HTML.Attribute.CLASS, "app-title")) {
                    roleTitle = returnHTMLTagContent(element, htmlDoc);
                }
            }
        } catch (MalformedURLException murle) {
            System.out.println(murle.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.getMessage());
        } catch (BadLocationException ble) {
            System.out.println(ble.getMessage());
        }
        role += roleTitle.replaceAll("(\n)", "");
        return role;
    }

    /**
     * The `recordJobDescriptionRawHTML()` method accepts no parameters,
     * returning no values. The method parses the User provided URL, writing
     * the full page to a newly created 'raw_data.html' file contained in the
     * 'raw_data' directory.
     */
    public void recordJobDescriptionRawHTML() {
        String rawDataFilePath = getFilePath() + File.separator + "raw_data.html";
        try {
            URL url = new URL(getURL());
            Scanner input = new Scanner(url.openStream());
            File rawDataFile = new File(rawDataFilePath);
            FileWriter filePen = new FileWriter(rawDataFile, false);
            BufferedWriter writer = new BufferedWriter(filePen);
            while (input.hasNextLine()) {
                writer.write(input.nextLine() + "\n");
            }
            writer.close();
        } catch (MalformedURLException murle) {
            System.out.println(murle.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * The `recordJobDescriptionParsedHTML()` method accepts no parameters,
     * returning no values. The method parses the User provided URL, writing
     * only the contents of a specific subset of HTML tags to a "ParsedJD.txt"
     * file contained within the role directory.
     */
    public void recordJobDescriptionParsedHTML() {
        String roleDataFilePath = getFilePath() + File.separator + "ParsedJD.txt";
        try {
            File roleDataFile = new File(roleDataFilePath);
            URL url = new URL(getURL());
            URLConnection connection = url.openConnection();
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            htmlKit.read(inputStream, htmlDoc, 0);

            // Parse
            ElementIterator iterator = new ElementIterator(htmlDoc);
            Element element;
            FileWriter filePen = new FileWriter(roleDataFile);
            BufferedWriter input = new BufferedWriter(filePen);

            while ((element = iterator.next()) != null) {
                AttributeSet as = element.getAttributes();
                Object name = as.getAttribute(StyleConstants.NameAttribute);

                if (name == HTML.Tag.DIV && as.containsAttribute(HTML.Attribute.ID, "content")) {
                    setParserIsInContent(true);
                }

                if (name == HTML.Tag.DIV && as.containsAttribute(HTML.Attribute.ID, "application")) {
                    setParserIsInContent(false);
                }

                if (getParserIsInContent()) {
                    if (name == HTML.Tag.H1) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.H2) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.H3) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.H4) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.H5) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.H6) {
                        input.write("\n" + formatHTag(element, htmlDoc));
                    }
                    if (name == HTML.Tag.UL) {
                        input.write("\n");
                    }
                    if (name == HTML.Tag.LI) {
                        input.write(" -- " + returnHTMLTagContent(element, htmlDoc));
                    }
                    if (name == HTML.Tag.P) {
                        input.write("\n" + returnHTMLTagContent(element, htmlDoc));
                    }
                }
            }
            input.close();
        } catch (MalformedURLException murle) {
            System.out.println(murle.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.getMessage());
        } catch (BadLocationException ble) {
            System.out.println(ble.getMessage());
        }
    }

    /**
     * The `formatHTag()` method accepts an Element and an HTMLDocument object
     * as parameters - 'element' and 'htmlDoc' - returning a formatted String
     * representation of the contents of that specific "H Tag" for easier
     * readability within the parsed job description.
     * 
     * @param element An Element object containing necessary information about
     *                the beginning and end of the HTML Tag's 'offset' data.
     * @param htmlDoc An HTMLDocument object containing the raw data that the
     *                method will parse.
     * @return A String representation of the contents of the "H Tag" which has
     *         been reformatted for easier readability within the
     *         "ParsedJD.txt" document.
     */
    private String formatHTag(Element element, HTMLDocument htmlDoc) {
        String hTagLowerCase = returnHTMLTagContent(element, htmlDoc).toLowerCase();
        char[] hTagCharArray = hTagLowerCase.toCharArray();
        hTagCharArray[0] = Character.toUpperCase(hTagCharArray[0]);
        for (int i = 0; i < hTagCharArray.length; i++) {
            if (hTagCharArray[i] == ' ') {
                hTagCharArray[i + 1] = Character.toUpperCase(hTagCharArray[i + 1]);
            }
        }
        String hTagContent = new String(hTagCharArray);
        return hTagContent;
    }
}
