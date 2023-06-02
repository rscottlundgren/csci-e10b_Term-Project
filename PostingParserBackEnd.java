import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * The "PostingParserBackEnd" class performs state management duties for the
 * over-arching "PostingParser" application, coordinates the creation of the
 * local file directory(ies) for the User's machine, manages the testing of
 * valid URLs, and tallies/re-tallies unique words and counts.
 */
public class PostingParserBackEnd {

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ BEGIN - Instance State ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    // The "Uniform Resource Locator" of the Job Description
    // e.g. https://boards.greenhouse.io/rvohealth/jobs/4231267005
    private String url;

    // The ROOT file path where we're going to save the data
    // e.g. /Users/jarvis/Desktop
    private String rootDirectory;

    // The file path to the directory where we're going to save the data
    // e.g. /Users/jarvis/Desktop/PostingParser
    private String programDirectory;

    // The file path to the specific COMPANY where we're going to save the data
    // e.g. /Users/jarvis/Desktop/PostingParser/<COMPANY>
    private String companyDirectory;

    // The file path to the specific ROLE where we're going to save the data
    // e.g. /Users/jarvis/Desktop/PostingParser/<COMPANY>/<ROLE>
    private String roleDirectory;

    // The location of the specific job description for the role we've parsed
    // e.g. /Users/jarvis/Desktop/PostingParser/<COMPANY>/<ROLE>/ParsedJD.txt
    private String roleDataFile;

    // The file path to the specific RAW DATA where we're going to save the data
    // e.g. /Users/jarvis/Desktop/PostingParser/<COMPANY>/<ROLE>/raw_data
    private String rawDataFile;

    private boolean isGreenhouseATS = false;
    private boolean parseNewJDWindowIsActive = true;
    private boolean parseOldJDWindowIsActive = false;
    private boolean updateFWWindowIsActive = false;
    private UniqueWordsAndCounts uniqueWordsAndCounts = new UniqueWordsAndCounts();
    private ForbiddenWords forbiddenWords = new ForbiddenWords();

    private final String FORBIDDEN_WORDS_TXT = "ForbiddenWords.txt";

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ CLOSE - Instance State ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ BEGIN - Constructor(s) ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `PostingParserBackEnd()` constructor method accepts no parameters,
     * instantiating a new instance of the "PostingParserBackEnd" class and
     * setting the value of the 'forbiddenWords' state instance value.
     */
    public PostingParserBackEnd() {
        setForbiddenWords();
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ------------------------ CLOSE - Constructor(s) ----------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------------- BEGIN - Setters & Getters ---------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `setURL()` instance method accepts a single String parameter - 'url'
     * - returning no values. The method sets the String parameter ('url') to
     * be the value of instance state variable 'url'.
     * 
     * @param url A String value representing the URL provided by the User for
     *            parsing.
     */
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * The `getURL()` instance method accepts no parameters and returns a
     * String value representing the URL provided by the User for parsing.
     * 
     * @return A String value representing the URL provided by the User for
     *         parsing.
     */
    public String getURL() {
        return this.url;
    }

    /**
     * The `setRootDirectory()` method accepts a single String parameter -
     * 'path' - returning no values. The method sets the String parameter
     * ('path') to be the value of the instance state variable 'rootDirectory'.
     * 
     * @param path A String value representing the File Path where the User
     *             would like to create the root "PostingParser" directory.
     */
    public void setRootDirectory(String path) {
        this.rootDirectory = path;
    }

    /**
     * The `getRootDirectory()` method accepts no parameters and returns a
     * String value representing the File Path where the User would like to
     * create the root "PostingParser" directory.
     * 
     * @return A String value representing the File Path where the User would
     *         like to create the root "PostingParser" directory.
     */
    public String getRootDirectory() {
        return this.rootDirectory;
    }

    /**
     * The `setProgramDirectory()` method accepts a single String parameter -
     * 'programDirectory' - returning no values. The method sets the String
     * parameter ('programDirectory') to be the value of the instance state
     * variable 'programDirectory' which is meant to represent the full File
     * Path for the location of the "PostingParser" directory upon creation.
     * 
     * @param programDirectory A String value representing the full file path
     *                         for the location of the "PostingParser"
     *                         directory.
     */
    public void setProgramDirectory(String programDirectory) {
        this.programDirectory = programDirectory;
    }

    /**
     * The `getProgramDirectory()` method accepts no parameters and returns a
     * single String value representing the full File Path for the location of
     * the "PostingParser" directory upon creation.
     * 
     * @return A String value representing the full File Path for the location
     *         of the "PostingParser" directory upon creation.
     */
    public String getProgramDirectory() {
        return this.programDirectory;
    }

    /**
     * The `setCompanyDirectory()` method accepts a single String parameter -
     * 'companyDirectory' - returning no values. The method sets the String
     * parameter ('companyDirectory') to be the value of the instance state
     * variable 'companyDirectory' which is meant to represent the File Path
     * for the location of the Company Directory within the program's
     * "PostingParser" directory.
     * 
     * @param companyDirectory A String value representing the File Path for
     *                         the location of the Company Directory within the
     *                         program's "PostingParser" directory.
     */
    public void setCompanyDirectory(String companyDirectory) {
        this.companyDirectory = companyDirectory;
    }

    /**
     * The `getCompanyDirectory()` method accepts no parameters and returns a
     * single String value representing the File Path for the location of the
     * Company Directory within the program's "PostingParser" directory.
     * 
     * @return A String value representing the File Path for the location of
     *         the Company Directory within the program's "PostingParser"
     *         directory.
     */
    public String getCompanyDirectory() {
        return this.companyDirectory;
    }

    /**
     * The `setRoleDirectory()` method accepts a single String parameter -
     * 'roleDirectory' - returning no values. The method sets the String
     * parameter ('roleDirectory') to be the value of the instance state
     * variable 'roleDirectory' which is meant to represent the File Path for
     * the location of the Role Directory within the program's "PostingParser"
     * directory.
     * 
     * @param roleDirectory A String value representing the File Path for the
     *                      location of the Role Directory within the program's
     *                      "PostingParser" directory.
     */
    public void setRoleDirectory(String roleDirectory) {
        this.roleDirectory = roleDirectory;
    }

    /**
     * The `getRoleDirectory()` method accepts no parameters and returns a
     * single String value representing the File Path for the location of the
     * Role Directory within the program's "PostingParser" directory.
     * 
     * @return A String value representing the File Path for the location of
     *         the Role Directory within the program's "PostingParser"
     *         directory.
     */
    public String getRoleDirectory() {
        return this.roleDirectory;
    }

    /**
     * The `setRoleDataFile()` method accepts a single String parameter -
     * 'roleDataFile' - returning no values. THe method sets the String
     * parameter ('roleDataFile') to be the value of the instance state
     * variable 'roleDataFile' which is meant to represent the File Path for
     * the location of the Role Data File within the program's "PostingParser"
     * directory.
     * 
     * @param roleDataFile A String value representing the File Path for the
     *                     location of the Role Data File within the program's
     *                     "PostingParser" directory.
     */
    public void setRoleDataFile(String roleDataFile) {
        this.roleDataFile = roleDataFile;
    }

    /**
     * The `getRoleDataFile()` method accepts no parameters and returns a
     * single String value representing the File Path for the location of the
     * Role Data File within the program's "PostingParser" directory.
     * 
     * @return A String value representing the File Path for the location of
     *         the Role Data File within the program's "PostingParser"
     *         directory.
     */
    public String getRoleDataFile() {
        return this.roleDataFile;
    }

    /**
     * The `setRawDataFile()` method accepts a single String parameter -
     * 'rawDataFile' - returning no values. The method sets the String
     * parameter ('rawDataFile') to be the value of the instance state
     * 'rawDataFile' which is meant to represent the full File Path for the
     * location of the Raw Data File within the program's "PostingParser"
     * directory.
     * 
     * @param rawDataFile A String value which is meant to represent the full
     *                    File Path for the location of the Raw Data File
     *                    within the program's "PostingParser" directory.
     */
    public void setRawDataFile(String rawDataFile) {
        this.rawDataFile = rawDataFile;
    }

    /**
     * The `getRawDataFile()` method accepts no parameters returning a String
     * value meant to represent the full File Path for the location of the Raw
     * Data File within the program's "PostingParser" directory.
     * 
     * @return A String value representing the full File Path for the location
     *         of the Raw Data File within the program's "PostingParser"
     *         directory.
     */
    public String getRawDataFile() {
        return this.rawDataFile;
    }

    /**
     * The `setIsGreenhouseATS()` method accepts a single boolean parameter -
     * 'isGreenhouseATS' - returning no values. The method sets the boolean
     * parameter ('isGreenhouseATS') to be the value of the instance state
     * 'isGreenhouseATS' which is meant to represent whether the URL provided
     * by the User has a Greenhouse domain or not.
     * 
     * @param isGreenhouseATS A boolean value meant to represent whether the
     *                        URL provided by the User has a Greenhouse domain
     *                        or not.
     */
    public void setIsGreenhouseATS(boolean isGreenhouseATS) {
        this.isGreenhouseATS = isGreenhouseATS;
    }

    /**
     * The `getIsGreenhouseATS()` method accepts no parameters returning a
     * boolean value indicating whether the URL provided by the User has a
     * Greenhouse domain or not.
     * 
     * @return A boolean value indicating whether or not the URL provided by
     *         the User has a Greenhouse domain.
     */
    public boolean getIsGreenhouseATS() {
        return this.isGreenhouseATS;
    }

    /**
     * The `setParseNewJDWindowIsActive()` method accepts a single boolean
     * parameter - 'parseNewJDWindowIsActive' - returning no values. The method
     * sets the boolean parameter ('parseNewJDWindowIsActive') to be the value
     * of the instance state 'parseNewJDWindowIsActive' which is meant to
     * represent whether or not the "Parse New Job Description" window is
     * active for the User or not.
     * 
     * @param parseNewJDWindowIsActive A boolean value which is meant to
     *                                 represent whether or not the "Parse New
     *                                 Job Description" window is active for the
     *                                 User or not.
     */
    public void setParseNewJDWindowIsActive(boolean parseNewJDWindowIsActive) {
        this.parseNewJDWindowIsActive = parseNewJDWindowIsActive;
    }

    /**
     * The `getParseNewJDWindowIsActive()` method accepts no parameters
     * returning a boolean value indicating whether or not the "Parse New Job
     * Description" window is active for the User or not.
     * 
     * @return A boolean value indicating whether or not the "Parse New Job
     *         Description" window is active for the User or not.
     */
    public boolean getParseNewJDWindowIsActive() {
        return this.parseNewJDWindowIsActive;
    }

    /**
     * The `setParseOldJDWindowIsActive()` method accepts a single boolean
     * parameter - 'parseOldJDWindowIsActive' - returning no values. The method
     * sets the boolean parameter ('parseOldJDWindowIsActive') to be the value
     * of the instance state 'parseOldJDWindowIsActive' which is meant to
     * represent whether or not the "Parse Old Job Description" window is
     * active for the User or not.
     * 
     * @param parseOldJDWindowIsActive A boolean value which is meant to
     *                                 represent whether or not the "Parse Old
     *                                 Job Description" window is active for the
     *                                 User or not.
     */
    public void setParseOldJDWindowIsActive(boolean parseOldJDWindowIsActive) {
        this.parseOldJDWindowIsActive = parseOldJDWindowIsActive;
    }

    /**
     * The `getParseOldJDWindowIsActive()` method accepts no parameters
     * returning a boolean value indicating whether or not the "Parse Old Job
     * Description" window is active for the User or not.
     * 
     * @return A boolean value indicating whether or not the "Parse Old Job
     *         Description" window is active for the User or not.
     */
    public boolean getParseOldJDWindowIsActive() {
        return this.parseOldJDWindowIsActive;
    }

    /**
     * The `setUpdateFWWindowIsActive()` method accepts a single boolean
     * parameter - 'updateFWWindowIsActive' - returning no values. The method
     * sets the boolean parameter ('updateFWWindowIsActive') to be the value of
     * the instance state 'updateFWWindowIsActive' which is meant to represent
     * whether or not the "Update "Forbidden Words" List" window is active for
     * the User or not.
     * 
     * @param updateFWWindowIsActive A boolean value which is meant to
     *                               represent whether or not the "Update
     *                               "Forbidden Words" List" window is active
     *                               for the User or not.
     */
    public void setUpdateFWWindowIsActive(boolean updateFWWindowIsActive) {
        this.updateFWWindowIsActive = updateFWWindowIsActive;
    }

    /**
     * The `getUpdateFWWindowIsActive()` method accepts no parameters returning
     * a boolean value indicating whether or not the "Update "Forbidden Words"
     * List" window is active for the User or not.
     * 
     * @return A boolean value indicating whether or not the "Update "Forbidden
     *         Words" List" window is active for the User or not.
     */
    public boolean getUpdateFWWindowIsActive() {
        return this.updateFWWindowIsActive;
    }

    /**
     * The `setForbiddenWords()` method accepts no parameters, returning no
     * values. The method sets the value of the 'forbiddenWords' state variable
     * using a private helper method.
     */
    public void setForbiddenWords() {
        populateForbiddenWords(getForbiddenWords());
    }

    /**
     * The `getForbiddenWords()` method accepts no parameters, returning an
     * instance of the ForbiddenWords object which has been created.
     * 
     * @return An instance of the ForbiddenWords object.
     */
    public ForbiddenWords getForbiddenWords() {
        return this.forbiddenWords;
    }

    /**
     * The `getUniqueWordsAndCounts()` method accepts no parameters returning
     * an instance of the UniqueWordsAndCounts object which has been created.
     * 
     * @return An instance of the UniqueWordsAndCounts object.
     */
    public UniqueWordsAndCounts getUniqueWordsAndCounts() {
        return this.uniqueWordsAndCounts;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * ---------------------- CLOSE - Setters & Getters ---------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * --------------------- BEGIN - Class Helper Methods -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    /**
     * The `testForValidURL()` method accepts a single String parameter -
     * 'uniformResourceLocator' - returning a boolean value. The method
     * attempts to get the Host portion of the User-provided URL and to
     * validate it against the list of approved Hosts. If the Host is a member
     * of the approved list, the method returns true - otherwise it returns
     * false.
     * 
     * @param uniformResourceLocator A String representation of the URL
     *                               provided by the User to potentially be
     *                               parsed by the program.
     * @return A boolean value indicating whether or not the String provided by
     *         the User can be parsed as an approved URL.
     */
    public boolean testForValidURL(String uniformResourceLocator) {
        try {
            URL url = new URL(uniformResourceLocator);
            return isValidHost(url.getHost());
        } catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            return false;
        }
    }

    /**
     * The `isValidHost()` method accepts a single String parameter - 'url' -
     * returning a boolean value. The method tests to see if the 'url' is one
     * of the approved String values. If an approved String value, the method
     * returns true, otherwise it returns false.
     * 
     * @param url A String representation of the URL provided by the User to
     *            potentially be parsed by the program.
     * @return A boolean value indicating whether or not the String provided by
     *         the User can be parsed as an approved URL.
     */
    private boolean isValidHost(String url) {
        boolean isValidURL = switch (url) {
            case "boards.greenhouse.io" -> {
                setURL(url);
                setIsGreenhouseATS(true);
                yield getIsGreenhouseATS();
            }
            default -> {
                yield false;
            }
        };
        return isValidURL;
    }

    /**
     * The `createPostingParserDirectory()` method accepts no parameters and
     * returns no values. The method creates a String representing the file
     * path for the "PostingParser" directory, to fall within the file path set
     * by the User and creates a File object out of this newly created
     * directory. The method then logs and potentially creates the directory if
     * it does not exist. Finally, the method sets the created String as the
     * value for the 'programFilePath' instance state variable.
     */
    public void createPostingParserDirectory() {

        // Method State
        String directoryPostingParser = getRootDirectory() + File.separator + "PostingParser";
        File newDirectory = new File(directoryPostingParser);

        // Method Behavior
        directoryCreateAndLog(newDirectory, getRootDirectory(), "PostingParser");
        setProgramDirectory(directoryPostingParser);
    }

    /**
     * The `createCompanyDirectory()` method accepts no parameters returning no
     * values. The method creates an empty 'companyDirectory' String value. It
     * tests if the job description's URL provided by the User is posted by a
     * supported ATS. If so, the method creates the appropriate instance of
     * that ATS' "parser", assigning the value returned from that instance's
     * `instantiateCompanyDirectory()` method. Finally, the method sets
     * 'companyDirectory' String as the value for the 'companyFilePath'
     * instance state variable.
     */
    public void createCompanyDirectory() {

        // Method State
        String companyDirectory = "";

        // Method Behavior
        if (getIsGreenhouseATS()) {
            GreenhouseParser parser = new GreenhouseParser(getURL());
            companyDirectory = parser.instantiateCompanyDirectory(getProgramDirectory());
        }
        setCompanyDirectory(companyDirectory);
    };

    /**
     * The `createRoleDirectory()` method accepts no parameters returning no
     * values. The method creates an empty 'roleDirectory' String value. It
     * tests if the job description's URL provided by the User is posted by a
     * supported ATS. If so, the method creates the appropriate instance of
     * that ATS' "parser", assigning the value returned from that instance's
     * 'instantiateRoleDirectory()' method in addition to recording the job
     * description's parsed HTML. Finally, the method sets the 'roleDirectory'
     * String as the value for the 'roleDirectory' instance state variable and,
     * with a couple of additions, as the value for the 'roleDataFile' instance
     * state variable.
     */
    public void createRoleDirectory() {

        // Method State
        String roleDirectory = "";

        // Method Behavior
        if (getIsGreenhouseATS()) {
            GreenhouseParser parser = new GreenhouseParser(getURL());
            roleDirectory = parser.instantiateRoleDirectory(getCompanyDirectory());
            parser.recordJobDescriptionParsedHTML();
        }
        setRoleDirectory(roleDirectory);
        setRoleDataFile(roleDirectory + File.separator + "ParsedJD.txt");
    }

    /**
     * The `createRawDataDirectoryAndFile()` method accepts no parameters
     * returning no values. The method creates an empty 'rawDataDirectory'
     * String value. It tests if the job description's URL provided by the User
     * is posted by a supported ATS. If so, the method creates the appropriate
     * instance of That ATS' "parser", assigning the value returned from that
     * instance's 'instantiateRawDataDirectory()' method in addition to
     * recording the job description's raw HTML. Finally, the method sets the
     * 'rawDataFile' instance state variable.
     */
    public void createRawDataDirectoryAndFile() {

        // Method State
        String rawDataDirectory = "";

        // Method Behavior
        if (getIsGreenhouseATS()) {
            GreenhouseParser parser = new GreenhouseParser(getURL());
            rawDataDirectory = parser.instantiateRawDataDirectory(getRoleDirectory());
            parser.recordJobDescriptionRawHTML();
        }
        setRawDataFile(rawDataDirectory);
    }

    /**
     * The `readParsedJobDescription()` method accepts a single String
     * parameter - 'filePath' - returning a String value. The method creates a
     * connection to the 'filePath', reading through and appending each
     * line of the parsed job description to the 'documentAsString' variable.
     * Once the whole document has been read, the 'documentAsString' is
     * returned.
     * 
     * @return A String value containing the parsed job description.
     */
    public String readParsedJobDescription(String filePath) {

        // Method State
        File parsedJobDescriptionFile;
        Scanner parsedJobDescription;
        String documentAsString = "";

        // Method Behavior
        try {
            parsedJobDescriptionFile = new File(filePath);
            parsedJobDescription = new Scanner(parsedJobDescriptionFile);
            while (parsedJobDescription.hasNextLine()) {
                documentAsString += parsedJobDescription.nextLine() + "\n";
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("No File Found!");
        }
        return documentAsString;
    }

    /**
     * The `tallyJobDescriptionWords()` method accepts a single String
     * parameter - 'filePath' - returning no values. The method creates a
     * connection to the 'filePath' and empties the present contents of the
     * 'uniqueWordsAndCounts' state variable. Once empty, the method evaluates
     * each word in the connection to the 'filePath' to see whether that word
     * is present in the 'forbiddenWords' state variable. If the word is
     * forbidden, the method moves onto the next word in the 'filePath' for
     * evaluation; if the word is not forbidden, the method merges that word
     * into the 'uniqueWordsAndCounts' state variable. After the whole
     * 'filePath' has been evaluated, the connection is terminated.
     * 
     * @param filePath A String value representation of the location of a
     *                 document within the User's local machine that is a
     *                 pre-parsed job description.
     */
    public void tallyJobDescriptionWords(String filePath) {
        try {
            File jobDescriptionFile = new File(filePath);
            Scanner jobDescriptionLine = new Scanner(jobDescriptionFile);
            String word;
            String parsedWord;
            uniqueWordsAndCounts.emptyUniqueWordsAndCountsHashTable();
            while (jobDescriptionLine.hasNext()) {
                word = jobDescriptionLine.next();
                parsedWord = uniqueWordsAndCounts.stripOutSelectPunctuation(word);
                if (!getForbiddenWords().containsWord(parsedWord)) {
                    if (!parsedWord.matches("--") && !parsedWord.matches("-") && !parsedWord.matches("")) {
                        getUniqueWordsAndCounts().merge(parsedWord, 1, Integer::sum);
                    }
                }
            }
            jobDescriptionLine.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("FileNotFound");
        }
    }

    /**
     * The `retallyJobDescriptionWords()` method accepts a single String
     * parameter - 'roleDataFile' - returning no values. The method creates a
     * connection to the 'roleDataFile' and empties the present contents of the
     * 'uniqueWordsAndCounts' state variable. Once empty, the method evaluates
     * each word in the connection to the 'roleDataFile' to see whether that
     * word is present in the 'forbiddenWords' state variable. If the word is
     * forbidden, the method moves onto the next word in the 'roleDataFile' for
     * evaluation; if the word is not forbidden, the method merges that word
     * into the 'uniqueWordsAndCounts' state variable. After the whole
     * 'roleDataFile' has been evaluated, the connection is terminated.
     * 
     * @param roleDataFile A String value representation of the location of a
     *                     document within the User's local machine that is a
     *                     pre-parsed job description.
     */
    public void retallyJobDescriptionWords(String roleDataFile) {
        try {

            // Instantiate Method State
            String word;
            String parsedWord;

            // Attempt To Create A File Using The Passed String Parameter
            // (This Is Where The Method Will Break If The Passed String Is Bad)
            File jobDescriptionFile = new File(roleDataFile);

            // Create A Scanner Object From The Newly Created File
            Scanner jobDescriptionLine = new Scanner(jobDescriptionFile);

            // Empty The `uniqueWordsAndCounts` Instance Variable For A Fresh
            // Tally
            getUniqueWordsAndCounts().emptyUniqueWordsAndCountsHashTable();

            // While The Scanner Object Has Another Token To Process...
            while (jobDescriptionLine.hasNext()) {

                // Assign The Next String Token To The `word` Method State
                // Variable
                word = jobDescriptionLine.next();

                // Strip The `word` Of Select Punctuation, Reassigning The New
                // Parsed String To The `parsedWord` variable
                parsedWord = uniqueWordsAndCounts.stripOutSelectPunctuation(word);

                // If The `forbiddenWords` Instance Variable Does Not Contain
                // The `parsedWord`...
                if (!getForbiddenWords().containsWord(parsedWord)) {
                    if (!parsedWord.matches("--") && !parsedWord.matches("-") && !parsedWord.matches("")) {

                        // Merge The `parsedWord` Into The
                        // `uniqueWordsAndCounts` State Variable...
                        // ...If `parsedWord` Exists, Increment Key's Value By 1
                        // ...If `parsedWord` Does Not Exist, Create The Key &
                        // Set The Value To 1
                        getUniqueWordsAndCounts().merge(parsedWord, 1, Integer::sum);
                    }
                }
            }

            // After The While Loop Has Concluded, Close The Scanner Object
            jobDescriptionLine.close();

        } catch (FileNotFoundException fnfe) {
            System.out.println("FileNotFound");
        }

    }

    /**
     * The `directoryCreateAndLog()` method accepts three parameters - a File
     * ('newFilePath') and two Strings ('rootFilePath' & 'directoryName') -
     * returning no values. The method logs the action to the terminal,
     * providing further specificity to the logs by making use of the provided
     * parameters in its messaging.
     * 
     * @param newFilePath   A File object representing the proposed new file
     *                      path that would be created locally if it does not
     *                      already exist.
     * @param rootFilePath  A String representation of the base file path that
     *                      will be used in the creation of the new file path.
     * @param directoryName A String representation of the new directory name
     *                      that will be created at the root file path.
     */
    private void directoryCreateAndLog(
            File newFilePath,
            String rootFilePath,
            String directoryName) {

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
     * The `populateForbiddenWordsTreeSet()` method accepts no parameters,
     * returning a completely populated ForbiddenWordsTreeSet object based on
     * the contents of the 'ForbiddenWords.txt' file.
     * 
     * @return A completely populated ForbiddenWordsTreeSet object based on the
     *         contents of the 'ForbiddenWords.txt' file.
     */
    private void populateForbiddenWords(ForbiddenWords fw) {
        String word = "";
        try {
            File fwFile = new File(FORBIDDEN_WORDS_TXT);
            Scanner fwScanner = new Scanner(fwFile);
            while (fwScanner.hasNext()) {
                word = fwScanner.next();
                fw.appendWord(word);
            }
            fwScanner.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Can't Find - 'ForbiddenWords.txt'");
        }
    }

    /**
     * The `updateForbiddenWordTxt()` method accepts no parameters, returning
     * no values. The method creates a connection with the "ForbiddenWords.txt"
     * file and then overwrites the contents of that file with the contents of
     * the present ForbiddenWordsTreeSet object, refreshing the
     * ForbiddenWordsTreeSet object upon completion.
     */
    public void updateForbiddenWordTxt() {
        try {
            File forbiddenWordsFile = new File(FORBIDDEN_WORDS_TXT);
            PrintWriter wipeOriginalFile = new PrintWriter(forbiddenWordsFile);
            wipeOriginalFile.close();
            PrintWriter newForbiddenWords = new PrintWriter(forbiddenWordsFile);
            for (String forbiddenWord : getForbiddenWords()) {
                newForbiddenWords.println(forbiddenWord);
            }
            setForbiddenWords();
            newForbiddenWords.close();
        } catch (IOException ioe) {
            System.out.println("IOException");
        }
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * --------------------- CLOSE - Class Helper Methods -------------------- *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
}
