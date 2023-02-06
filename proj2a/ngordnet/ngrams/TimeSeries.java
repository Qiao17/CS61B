package ngordnet.ngrams;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {

        super();
        for (int year = startYear; year <= endYear; year++) {
            if (ts.containsKey(year)) {
                this.put(year, ts.get(year));
            }
        }
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        List<Integer> years = new ArrayList<>();
        years.addAll(this.keySet());
        return years;
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        List<Double> data = new ArrayList<>();
        data.addAll(this.values());
        return data;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries res = new TimeSeries();
        List<Integer> years1 = years();
        List<Integer> years2 = ts.years();
        int size1 = years1.size();
        int size2 = years2.size();
        int index1 = 0;
        int index2 =0;
        int year1;
        int year2;
        while(index1 < size1 || index2 < size2) {
            if (index1 == size1) {
                year1 = 2100;
            } else {
                year1 = years1.get(index1);
            }
            if (index2 == size2) {
                year2 = 2100;
            } else {
                year2 = years2.get(index2);
            }

            if (year1 < year2) {
                res.put(year1, this.get(year1));
                index1 += 1;
            } else if (year1 > year2) {
                res.put(year2, ts.get(year2));
                index2 += 1;
            } else {
                res.put(year1, this.get(year1) + ts.get(year2));
                index1 += 1;
                index2 += 1;
            }
        }
        return res;
    }

     /** Returns the quotient of the value for each year this TimeSeries divided by the
      *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
      *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
      *  Should return a new TimeSeries (does not modify this TimeSeries). */
     public TimeSeries dividedBy(TimeSeries ts) {
         TimeSeries res = new TimeSeries();
         List<Integer> years1 = years();
         List<Integer> years2 = ts.years();
         int size1 = years1.size();
         int size2 = years2.size();
         int index1;
         int index2 = 0;
         int year1;
         int year2;
         for (index1 = 0; index1 < size1; ) {
             if (index2 == size2) {
                 throw new IllegalArgumentException();
             } else {
                 year2 = years2.get(index2);
             }
             year1 = years1.get(index1);
             if (year1 < year2) {
                 throw new IllegalArgumentException();
             } else if (year1 > year2) {
                 index2++;
             } else {
                 res.put(year1, this.get(year1) / ts.get(year1));
                 index1++;
                 index2++;
             }
         }
        return res;
    }
}
