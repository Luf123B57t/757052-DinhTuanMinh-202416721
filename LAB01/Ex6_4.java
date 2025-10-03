package LAB01;

import java.util.Scanner;

public class Ex6_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // month data
        String[] full_name = {"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
        String[] abbrv = {"Jan.", "Feb.", "Mar.", "Apr.", "May", "June","July", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
        String[] short_name = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int[] days_in_month= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int year = -1;
        while (year == -1) { //loop until valid year is entered
            System.out.print("Enter a year: ");
            String year_input = sc.nextLine();
            if (year_input.matches("\\d+")) {
                year = Integer.parseInt(year_input);
            } else {
                System.out.println("Invalid year, enter again.");
            }
        }
        
        int monthIndex = -1;
        while (monthIndex == -1) {//loop until valid month is entered
            System.out.print("Enter a month (full name, abbreviation, or number): ");
            String month_input = sc.nextLine();
            if (month_input.matches("\\d+")) { // if input is a number
                int month = Integer.parseInt(month_input);
                if (month >= 1 && month  <= 12) {
                    monthIndex = month  - 1;
                    break;
                }
            } else { // if input is a name
                for (int i = 0; i < 12; i++) {
                    if (full_name[i].equalsIgnoreCase(month_input) || abbrv[i].equalsIgnoreCase(month_input) || short_name[i].equalsIgnoreCase(month_input)) {
                        monthIndex = i;
                        break;
                    }
                }
            }
            if (monthIndex == -1) {
                System.out.print("Invalid month, enter again: ");
                month_input = sc.nextLine();
            }
        }

        // check for leap year
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        int days = days_in_month[monthIndex];
        if (monthIndex == 1 && isLeap) days = 29;
        System.out.println("It has " + days + " days.");

    }
}
