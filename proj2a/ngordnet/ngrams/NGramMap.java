package ngordnet.ngrams;

import java.util.Collection;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    HashMap<String, TimeSeries> words;
    TimeSeries counts;
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        // counts
        counts = new TimeSeries();
        int year;
        double num;
        String s;
        In inCounts = new In(countsFilename);
        while ((s = inCounts.readLine()) != null ) {
            String[] items = s.split(",");
            year = Integer.valueOf(items[0]);
            num = Double.valueOf(items[1]);
            counts.put(year, num);
        }

        // words
        words = new HashMap<>();
        In inWords = new In(wordsFilename);
        String word;
        while((s = inWords.readLine()) != null) {
            String[] items = s.split("\t");
            word = items[0];
            year = Integer.valueOf(items[1]);
            num = Double.valueOf(items[2]);
            if (!words.containsKey(word)) {
                TimeSeries wordNum = new TimeSeries();
                wordNum.put(year, num);
                words.put(word, wordNum);
            } else {
                words.get(word).put(year, num);
            }
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        return new TimeSeries(words.get(word), 1400, 2100);
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(words.get(word), startYear, endYear);
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(counts, 1400, 2100);
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        return countHistory(word).dividedBy(counts);
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return countHistory(word, startYear, endYear).dividedBy(counts);
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries res = new TimeSeries();
        for(String word: words) {
            res.plus(weightHistory(word));
        }
        return res;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words,
                              int startYear, int endYear) {
        TimeSeries res = new TimeSeries();
        for(String word: words) {
            TimeSeries temp = countHistory(word, startYear, endYear);
            res = res.plus(temp);
        }
        return res.dividedBy(totalCountHistory());
    }


}
