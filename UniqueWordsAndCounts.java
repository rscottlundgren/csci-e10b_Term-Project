import java.util.Hashtable;
import java.util.Enumeration;

/**
 * The "UniqueWordsAndCounts" class is an extension of Java's Hashtable data
 * structure, where the keys are Strings and the values are Integers. The class
 * provides the User with a container that will record all unique words found
 * in a job description in addition to the total number of times that word
 * appears within the job description.
 */
public class UniqueWordsAndCounts extends Hashtable<String, Integer> {

    // Constructor(s)

    /**
     * The `UniqueWordsAndCounts()` method accepts no parameters, returning no
     * values, and uses 'super()' to create an instance of this class based on
     * the Hashtable<String, Integer> data structure.
     */
    public UniqueWordsAndCounts() {
        super();
    }

    // Instance Method(s)

    /**
     * The `emptyUniqueWordsAndCountsHashTable()` method accepts no parameters,
     * returning no values. The method loops through the contents of the
     * UniqueWordsAndCounts object, removing each key / value pair in the
     * process.
     */
    public void emptyUniqueWordsAndCountsHashTable() {
        Enumeration<String> uniqueWords = this.keys();
        while (uniqueWords.hasMoreElements()) {
            String word = uniqueWords.nextElement();
            this.remove(word);
        }
    }

    /**
     * The `stripOutSelectPunctuation()` method accepts a single String
     * parameter - 'input' - returning a String object that is a duplicate of
     * the passed parameter with the exception that it's been converted to
     * lower case and any "erroneous" punctuation has been removed.
     * 
     * @param input A String object that the User would like to strip of
     *              punctuation and convert to a standardized format for easier
     *              comparison against the "ForbiddenWordsTreeSet" object's
     *              contents.
     * @return A String value representing the parameter converted to a
     *         standardized format for easier comparison against the
     *         "ForbiddenWordsTreeSet" object's contents.
     */
    public String stripOutSelectPunctuation(String input) {
        String inputStripped = input.replaceAll("[^\\w\\-' ]", "");
        return inputStripped.toLowerCase().trim();
    }
}
