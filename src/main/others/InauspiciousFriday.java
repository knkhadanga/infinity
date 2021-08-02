package main.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a given range of year, find all the 13th of months which falls on Friday
 */
public class InauspiciousFriday {

    //static map which has map of days to the number from 0-6 [i.e.: 7 days of a week]
    private Map<String, String[]> days_mapping_in_week = new HashMap<>();

    //number of days passed from the beginning of year on 13th of every month in a non leap year
    private int[] non_leap_year = {13, 44, 72, 103, 133, 164, 194, 225, 256, 286, 317, 347};

    //number of days passed from the beginning of year on 13th of every month in a leap year
    private int[] leap_year = {13, 44, 73, 104, 134, 165, 195, 226, 257, 287, 318, 348};

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        InauspiciousFriday test = new InauspiciousFriday();
        List<String> days = test.findInAuspiciousDays(2007, 2013, "Monday");

        if (days.isEmpty()) {
            System.out.println("No inauspicious 13th found which falls on Friday.");
        } else {
            System.out.println("List of 13th which falls on Friday are - ");
            for (String friday : days) {
                System.out.println(friday);
            }
        }
    }

    /**
     * Method to find the months whose 13th falls on Friday
     *
     * @param start_year        - start year
     * @param end_year          - end year
     * @param first_day_of_year - First day of the start year
     * @return List of 13th of those months which falls on Friday
     */
    List<String> findInAuspiciousDays(int start_year, int end_year, String first_day_of_year) {
        populateMap();

        List<String> output_list = new ArrayList<>();

        for (int current_year = start_year; current_year <= end_year; current_year++) {

            //get the mapping of days based on the day in 1st January.
            String[] current_year_day_mapping = days_mapping_in_week.get(first_day_of_year);

            //check if the current year is a leap year or not
            boolean is_leap_year = isLeapYear(current_year);

            //this has list of days passed on 13th of every month
            //these days have to be checked if they fall on Friday
            int[] days_of_the_year;
            if (is_leap_year) {
                days_of_the_year = leap_year;
            } else {
                days_of_the_year = non_leap_year;
            }

            //check if 13th of any month of a given year is a Friday
            for (int i = 0; i < days_of_the_year.length; i++) {
                int check_day = days_of_the_year[i];
                int reminder = check_day % 7; //divide by 7 (days in a week)
                String day = current_year_day_mapping[reminder];
                if ("Friday".equalsIgnoreCase(day)) {
                    String output = (i + 1) + "/13/" + current_year + " - Friday";
                    output_list.add(output);
                }
            }

            if (is_leap_year) {
                //if current year is leap year, then next 1st january is 367 day ahead
                int temp = 367 % 7;
                String first_day_of_next_year = current_year_day_mapping[temp];
                first_day_of_year = first_day_of_next_year;
            } else {
                //if current year is not a leap year, then next 1st january is 366 day ahead
                int temp = 366 % 7;
                String first_day_of_next_year = current_year_day_mapping[temp];
                first_day_of_year = first_day_of_next_year;
            }
        }

        return output_list;
    }

    /**
     * Method to populate static values
     */
    private void populateMap() {

        //1st day of january is "Monday" (index 1 in array is "Monday")
        String[] week1 = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        //1st day of january is "Tuesday" (index 1 in array is "Tuesday")
        String[] week2 = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        //1st day of january is "Wednesday" (index 1 in array is "Wednesday")
        String[] week3 = {"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday"};

        //1st day of january is "Thursday" (index 1 in array is "Thursday")
        String[] week4 = {"Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};

        //1st day of january is "Friday" (index 1 in array is "Friday")
        String[] week5 = {"Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday"};

        //1st day of january is "Saturday" (index 1 in array is "Saturday")
        String[] week6 = {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

        //1st day of january is "Sunday" (index 1 in array is "Sunday")
        String[] week7 = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        days_mapping_in_week.put("Monday", week1);
        days_mapping_in_week.put("Tuesday", week2);
        days_mapping_in_week.put("Wednesday", week3);
        days_mapping_in_week.put("Thursday", week4);
        days_mapping_in_week.put("Friday", week5);
        days_mapping_in_week.put("Saturday", week6);
        days_mapping_in_week.put("Sunday", week7);
    }

    /**
     * Method to verify if the year is leap year or not
     *
     * @param year
     * @return
     */
    boolean isLeapYear(int year) {

        boolean is_leap_year = false;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                if (year % 400 == 0)
                    is_leap_year = true;
                else
                    is_leap_year = false;
            } else {
                is_leap_year = true;
            }
        } else {
            is_leap_year = false;
        }

        return is_leap_year;
    }
}
