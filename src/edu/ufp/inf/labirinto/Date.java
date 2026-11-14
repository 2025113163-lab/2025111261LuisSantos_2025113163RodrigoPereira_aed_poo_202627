package edu.ufp.inf.labirinto;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Rui S. Moreira @ FCT - UFP
 *
 */

public class Date implements Comparable<Date> {

    private short day;
    private short month;
    private int year;

    /**
     * Creates a date object with given day, month and year attributes
     *
     * @param day - day of month
     * @param month - month of year
     * @param year - year
     */
    public Date(short day, short month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Copy constructor
     *
     * @param d - date to clone
     */
    public Date(Date d) {
        this.day = d.day;
        this.month = d.month;
        this.year = d.year;
    }

    /**
     * Creates a date from a given millis timestamp,
     * by using the equivalent (though deprecated) java.util.Date class
     *
     * @param millis - number of milliseconds from epoch.
     */
    public Date(long millis) {
        java.util.Date d = new java.util.Date(millis);
        this.day = (short) d.getDay();
        this.month = (short) (d.getMonth() + 1);
        this.year = d.getYear() + 1900;
    }

    /**
     * Creates a date representing the current date (today) based on the
     * GregorianCalendar of the TimeZone.
     */
    public Date() {
        Calendar gregCalendar = new GregorianCalendar();
        //TODO...
    }

    /**
     * @return the day
     */
    public short getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(short day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public short getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(short month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date{" + "day=" + day + ", month=" + month + ", year=" + year + '}';
    }


    /**
     * @param d - a given date to compare to
     * @return returns 0 if this date is equal to d date;
     *                 -1 if this date is < d date;
     *                 +1 if this date is > d date.
     */
    @Override
    public int compareTo(Date d) {
        //TODO...
        return 0;
    }

    /**
     * @param d - a given date to compare to
     * @return true if this date is after d date, false otherwise.
     */
    public boolean afterDate(Date d) {
        //TODO...
        return true;
    }

    /**
     * @param d - a given date to compare to
     * @return true if this date is after d date, false otherwise.
     */
    public boolean afterDateV2(Date d) {
        return this.compareTo(d) > 0;
    }

    /**
     * @param d - a given date to compare to
     * @return true if this date is before d date, false otherwise.
     */
    public boolean beforeDate(Date d) {
        //TODO...
        return false;
    }

    /**
     * @param d - a given date to compare to
     * @return true if this date is before d date, false otherwise
     */
    public boolean beforeDateV2(Date d) {
        return this.compareTo(d) < 0;
    }

    /**
     * @param d - reference date to which to calculate the difference of days
     * @return number of days between this date object and the d data object.
     */
    public int diferenceDays(Date d) {
        //TODO...
        //return DateSkeleton.daysCrawlerRecursive(new DateSkeleton(this), d);
        return 0;
    }

    /**
     * @param d - a given date to compare to
     * @return number of months between this date and the given d date parameter
     */
    public int diferenceMonths(edu.ufp.inf.labirinto.Date d) {
        //TODO...
        //return DateSkeleton.monthsCrawler(this, d);
        return 0;
    }

    /**
     *
     * @param d - a given date to compare to
     * @return difference of years between this date and the given date d
     */
    public int diferenceYears(edu.ufp.inf.labirinto.Date d) {
        return diferenceMonths(d) / 12;
    }

    /**
     * Increments this date by one day at a time by updating the attributes day,
     * month and year accordingly.
     */
    public void incrementDate() {
        //TODO...
        //Increment day but checking turn over new year and new month
    }

    /* ***********************************************************************
     * ******************** static methods go hereafter **********************
     * ***********************************************************************
     */
    /**
     * @param begin - a given begin date
     * @param end - a given end date
     * @return number of days between this date object and the d data object.
     * This method changes the begin Date hence it should be called with a copy
     * of a date that will be incremented.
     */
    private static long daysCrawlerRecursiveAux(Date begin, Date end) {
        //TODO...
        if (begin.beforeDate(end)) {
            //USe Date.daysCrawlerRecursiveAux(begin, end));
        }
        return 0;
    }

    public static int daysCrawlerRecursive(Date begin, Date end) {
        //TODO...
        return 0;
    }


    /**
     * @param begin - a given begin date
     * @param end - a given end date
     * @return returns the number of days between begin and end Dates
     */
    public static int daysCrawler(Date begin, Date end) {
        //TODO...
        return 0;
    }

    /**
     * @param begin - a given begin date
     * @param end - a given end date
     * @return number of days between two given dates [begin and end]
     */
    public static int monthsCrawler(edu.ufp.inf.labirinto.Date begin, edu.ufp.inf.labirinto.Date end) {
        //TODO...
        return 0;
    }

    /**
     * @param year - year to be considered
     * @return true if year is leap year, false otherwise.
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    /**
     * @param month - month of year (number between 1 and 12)
     * @param year - year to be considered
     * @return the number of days of a given month within a given year.
     */
    public static int daysMonth(short month, int year) {
        switch (month) {
            case 11:
            case 4:
            case 6:
            case 9:
                return 30;
            case 2:
                return (isLeapYear(year) ? 29 : 28);
            default:
                return 31;
        }
    }

    /**
     * @param beginDay - day to begin
     * @param beginMonth - month to begin
     * @param endDay - day to end
     * @param endMonth - month to end
     * @param year - year to consider
     * @return returns the number of days between two dates of a given year
     */
    public static int daysBetweenMonths(short beginDay, short beginMonth, short endDay, short endMonth, int year) {
        //TODO...
        return 0;
    }

    /**
     * @param beginDay - day to begin
     * @param beginMonth - month to begin
     * @param endDay - day to end
     * @param endMonth - month to end
     * @param year - year to consider
     * @return returns the number of days between two dates of a given year
     */
    public static int daysBetweenMonthsV2(short beginDay, short beginMonth, short endDay, short endMonth, int year) {
        //TODO...
        return 0;
    }

    /**
     * @param beginDay - day to begin
     * @param beginMonth - month to begin
     * @param endDay - day to end
     * @param endMonth - month to end
     * @param year - year to consider
     * @return the number of months between two dates of a given year
     */
    public static int monthsBetweenMonths(short beginDay, short beginMonth, short endDay, short endMonth, int year) {
        //TODO...
        return 0;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Aula 2
        Date now = new Date();

        Date next = new Date();
        next.incrementDate();

        System.out.println(now + " is before " + next + " : " + now.beforeDateV2(next));
        System.out.println(now + " is after " + next + " : " + now.afterDateV2(next));

        Date prev = new Date((short) 1, (short) 1, 2021);

        System.out.println(now + " is before " + prev + " : " + now.beforeDateV2(prev));
        System.out.println(now + " is after " + prev + " : " + now.afterDateV2(prev));

        // Aula 3
        Date jan1969=new Date((short) 18, (short) 1, 1969);
        Date jan2019=new Date((short) 18, (short) 1, 2019);
        long days=Date.daysCrawlerRecursive(jan1969, jan2019);
        System.out.println(jan1969 + " -> isLeapYear = " + edu.ufp.inf.labirinto.Date.isLeapYear(jan1969.getYear()));
        System.out.println(jan1969 + " -> beforeDate(" + jan2019 + ") = " + jan1969.beforeDate(jan2019));
        System.out.println("TestIntroDateApp - main(): " + jan1969 + " -> " + jan2019 + ":\t days=" + days);
        //System.out.println("TestIntroDateApp - main(): "+ jan1969 +" -> "+ jan2019 + ":\t hours="+days*24);
        //System.out.println("TestIntroDateApp - main(): "+ jan1969 +" -> "+ jan2019 + ":\t minutes="+days*24*60);
        //System.out.println("TestIntroDateApp - main(): "+ jan1969 +" -> "+ jan2019 + ":\t minutes="+days*24*60*60);
    }

}

