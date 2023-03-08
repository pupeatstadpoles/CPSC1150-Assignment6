
/**
 * Program Name: LeapYearCheck
 * Author: Pup Abdulgapul
 * Student ID: 100362791
 * Date: Oct 21, 2022
 * Course: CPSC1150-03
 * Professor: Hengameh Hamavand
 */
import java.util.Scanner;

public class LeapYearCheck { // Program to check if a year is a leap year


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year, answer, nextLeap, zellerDay;
        boolean repeat = true, leap;
        String day = "";

        do {
            System.out.print("\nPlease enter in a year after 1582: ");
            year = input.nextInt();
            if (year <= 1582) { // checks to see that year is after 1582
                System.out.print("\nError! Year must be after 1582.");
                repeat = false;
            }
            

            if (repeat) { //repeat should be false if the year entered was less than/equal to 1582
                leap = valid(year);
                if (leap) { // run this if it is a leap year
                    zellerDay = zeller(year, 2, 29); // checking for what day feb 29 of that year fell on
                    day = dayOfWeek(zellerDay);
                    System.out.println("February 29, " + year + " fell on a " + day);

                    do { //loop to find the next leap year where feb 29 falls on the same day as given in line 33
                        year += 4;
                        leap = valid(year);
                        if (!leap) {
                            year += 4; //if not a leap year, add 4 more years to get to the next leap year
                        }
                        nextLeap = zeller(year, 2, 29); //what day feb 29 fell on that year
                    } while (nextLeap != zellerDay); //keep running the loop if the days arent the same
                    nextLeap = year;
                    System.out
                            .println("The next leap year where February 29 is on " + day + " is " + nextLeap);
                } else { // runs if num is not a leap year
                    System.out.println(year + " is not a leap year.");
                }
            }
            System.out.print("\nDo you want to repeat the program? Press [1] for YES and [2] for NO: ");
            answer = input.nextInt();
            repeat = checkAnswer(answer);
        } while (repeat);     
        System.out.println("Ending program.");   
    }

    /**
     * Checks if a given year is a leap year
     * Leap years must be divisible by 4, but not 100. If divisible by 400, it is a leap year.
     * 
     * @param y is the year to check
     * @return true if it is a leap year, false if it is not
     */
    public static boolean valid(int y) {
        if ((y % 400) == 0) { // if divisible by 400, its definitely divisible by 4, so its a leap year
            return true;
        } else if ((y % 4) == 0) {
            if ((y % 100) == 0) {
                return false; // not a leap year if its divisible by 100
            }
            return true; // leap year if divisible by 4 but not 100
        }
        return false; // if it fails all the checks, its not a leap year

    }

    /**
     * method that uses Zeller's Congruence to check the day of a certain date
     * 
     * @param y is the year to be checked
     * @param m is the month to be checked
     * @param d is the date to be checked
     * @return dayNumber as an integer 0-6 where 0 corresponds to Sunday and 6
     *         corresponds to Saturday
     */
    public static int zeller(int y, int m, int d) { // zeller's congruence
        int dayNumber, leapFactor, startMonth, startYear;
        if (m < 3) {
            startMonth = 0;
            startYear = y - 1;
        } else {
            startMonth = (int) ((0.4 * m) + 2.3);
            startYear = y;
        }

        leapFactor = (startYear / 4) - (startYear / 100) + (startYear / 400);
        dayNumber = ((365 * y + 31 * (m - 1) + d + leapFactor - startMonth) - 1) % 7;

        return dayNumber; // returns the day of the week that date is on in int format (0-6 with 0 being
                          // sunday and 6 being saturday)
    }

    /**
     * Gets the day of the week when passed an integer value corresponding to the
     * day
     * 
     * @param d is the day of week from 0-6 according to zeller's congruence
     * @return the day of the week as a string
     */
    public static String dayOfWeek(int d) {
        String day = "";
        switch (d) { // sets day to the appropriate day using the integer value from zeller()
            case 0:
                day = "Sunday";
                break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
        }
        return day;
    }

    /**
     * Method to check if the user's answer indicates desire to repeat program or
     * not
     * 
     * @param ans is an integer that is input by the user
     * @return true or false
     */
    public static boolean checkAnswer(int ans) {
        Scanner input = new Scanner(System.in);
        while ((ans != 1) && (ans != 2)) { //as long as answer is not 1 or 2, prompt user for input
            System.out.print(
                    "\n Error, invalid answer. \nDo you want to repeat the program? Press [1] for YES and [2] for NO: ");
            ans = input.nextInt();
        }
        if (ans == 2) {
            return false;
        }
        return true;
    } 
}