# PostingParser
## Concept
It's a pain having to tailor a resume and a cover letter to a specific job posting. Reading a job posting I often find myself remembering the perfect sentence, paragraph, or bullet point in a prior application to another company that would be completely applicable... if only I could remember which company that was or what date I applied (to help narrow the search). Rather than "reinvent the wheel" trying to rewrite the sentence, paragraph, or bullet, I'd much rather have that information stored in a database that gets called when certain keywords are parsed in a job description. Which brings us to "PostingParser" version 0 (patent pending).

In version 0, the "PostingParser" will parse individual postings for keywords that will be collected and vetted against (what I call) "Forbidden Words". These "Forbidden Words" are words that the User of the program has deemed fluff (or chaff) from the real meat (or wheat) of the posting that would help them tailor a better resume or cover letter. Once compared to the "Forbidden Words" list, these remaining unique words will be counted (weighted) based on how frequently a particular word appears, potentially impacting the sentences, paragraphs, or bullets that the User might include in their cover letter or resume.

## History
This program was designed over the course of a month as part of a semester project for [Harvard Extension School](https://extension.harvard.edu/)'s [CSCI-E10b - Introduction to Computer Science Using Java II](https://courses.dce.harvard.edu/?action=explore-program&program=gradcert%7Cgradcert-programming&_gl=1*r1674d*_ga*MTU3NDg1NTAwMy4xNjY4MjIxMTc4*_ga_N1Q4JMJ72W*MTY4NTcxNzkxMS40Mi4xLjE2ODU3MTc5NDIuMjkuMC4w). The class was given roughly seven weeks to design and submit a plan for a program written entirely in [Java](https://docs.oracle.com/en/java/javase/18/docs/api/index.html) (using [Java Swing](https://docs.oracle.com/en/java/javase/18/docs/api/java.desktop/javax/swing/package-summary.html) to create the GUI). It was recommended that we follow a [MVC](https://developer.mozilla.org/en-US/docs/Glossary/MVC) architecture and - ultimately - there were very few requirements beyond coming up with a unique template class that was used in the project. Anything that had been learned during the semester was fair game. Given that I was unemployed and job searching during the second half of the semester (still am at the time of this writing), this concept for a project sat well with me and seemed like a good piece to showcase my coding chops at the time.

## Application Architecture
As previously stated, a MVC architecture was used in the design of this program:

- The **Model (M)** is split between `ForbiddenWords.java` (which makes use of `ForbiddenWords.txt` as a ~database~ of sorts), `GreenhouseParser.java`, and `UniqueWordsAndCounts.java`
- The **View (V)** is contained entirely in `PostingParser.java`
- The **Controller (C)** is contained in `PostingParserBackEnd.java`

To help with understanding the generalized flow of events / data, I've included a rudimentary flow chart below.

![Rough Sketch of Application's MVC Architecture](img/MVC%20Architecture.jpg)

An example flow might be the act of parsing...

1. The User provides a File Path (where they would like the main 'PostingParser' directory to be created) and a URL (that they would like to parse). 
2. The User clicks the "Parse The Description!" button.
3. Upon clicking, the `PostingParser` Event Listener for that button (`btn_ParseNewJD_FormField_Submit`) collects the values found in both JTextFields and verifies that both are not empty Strings (if either or both are empty Strings, an error "toast" is shown on the screen).
4. After successfully verifying that both JTextFields are populated, `PostingParser` (View) asks `PostingParserBackEnd` (Controller) to verify that the provided URL is valid (if it's not a valid URL, an error "toast" is shown on the screen).
5. Assuming the URL is valid, the `PostingParserBackEnd` (Controller) begins calling the `GreenhouseParser` (Model) to create the files in the local machine.
6. Files created the `PostingParser` (View) begins to pull down the old window to build the new window while the `PostingParserBackEnd` (Controller) begins tallying the `UniqueWordsAndCounts` (Model) against the `ForbiddenWords` (Model).
7. The `PostingParser` (View) uses the `PostingParserBackEnd` (Controller) to create template JLabels that are inserted into the "Word Results" component.
8. Once all the words have been tallied and inserted as JLabels into the "Word Results" component, the `PostingParser` (View) refreshes the panel so that the new data is displayed.

That said, there's a bit more to how I structured this program than a simple MVC...

### Front End Structure
TBD

### Back End Structure
TBD

## HOWTO: Use This Application
TBD

## HOWTO: Expand This Application
TBD