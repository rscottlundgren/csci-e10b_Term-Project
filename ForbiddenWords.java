import java.util.TreeSet;

/**
 * The "ForbiddenWordsTreeSet" class extends a variation of the Java "TreeSet"
 * package when the contained element is a String.
 */
public class ForbiddenWords extends TreeSet<String> {

    // Constructor(s)

    /**
     * The `ForbiddenWordsTreeSet()` zero-argument constructor accepts no
     * parameters, returning no values. The method creates a custom-instance of
     * Java's "TreeSet<String>" class for the use of the program.
     */
    public ForbiddenWords() {
        super();
    }

    // Setter(s) & Getter(s)

    /**
     * The `containsWord()` method accepts a single String parameter - 'word' -
     * returning a boolean value indicating whether the ForbiddenWordsTreeSet
     * object contains that parameter.
     * 
     * @param word A String parameter that the User is trying to determine
     *             whether it is present in the ForbiddenWordsTreeSet object.
     * @return A boolean value indicating whether the passed parameter is
     *         present (true) or not (false) in the ForbiddenWordsTreeSet
     *         object.
     */
    public boolean containsWord(String word) {
        return this.contains(word);
    }

    // Additional Helper Methods For The Next Version Of The Program...

    /**
     * The `addWord()` method accepts a single String parameter - 'word' -
     * returning a boolean value. The method attempts to add the 'word' to the
     * ForbiddenWordsTreeSet object and if successful (because it didn't
     * already exist in the object) returns a true value, otherwise returning
     * false.
     * 
     * @param word A String value that the User would like to add to the
     *             ForbiddenWordsTreeSet object.
     * @return A boolean value indicating whether or not the add attempt was
     *         successful.
     */
    public boolean appendWord(String word) {
        return this.add(word);
    }

    /**
     * The `removeWord()` method accepts a single String parameter - 'word' -
     * returning a boolean value. The method attempts to remove the 'word' from
     * the ForbiddenWordsTreeSet object and if successful (because the word
     * existed within the object) returns a true value, otherwise returning
     * false.
     * 
     * @param word A String value that the User would like to remove from the
     *             ForbiddenWordsTreeSet object.
     * @return A boolean value indicating whether or not the removal attempt
     *         was successful.
     */
    public boolean removeWord(String word) {
        return this.remove(word);
    }
}
