package org.example;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/*
This is a helper class whenever the scanner needs to be used to get user input.
 */
public class Console {
    private static final Scanner sc = new Scanner(System.in);

    public static String getNonEmptyString(String prompt) {
        String input = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            input = sc.nextLine();

            // Validate the input
            if (input != null && !input.trim().isEmpty()) {
                isValid = true;
            } else {
                System.out.println("String can't be empty. Please try again.");
            }
        }
        return input;
    }

    public static String getValidNameString(String prompt) {
        while (true) {
            String value = getNonEmptyString(prompt);
            if(value.equalsIgnoreCase("q")){
                return null;
            }
            String regex = "^[A-Za-z-' ]+";
            if (value.matches(regex)) {
                return value;
            } else {
                System.out.println("Error! Name can only contain letters, - and '.");
            }
        }
    }

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid integer value.");
            }
        }
    }

    public static int getInt(String prompt, int min, int max) {
        while (true) {
            int value = getInt(prompt);
            if (value > min && value <= max) {
                return value;
            } else {
                System.out.println("Error! Number must be greater than "
                        + min + " and less than or equal to " + max + ".");
            }
        }
    }

    public static LocalDate getLocalDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeException e) {
                System.out.println("Error! Invalid date value");
            }
        }
    }

    public static LocalDate getPastLocalDate(String prompt) {
        while (true) {
            LocalDate value = getLocalDate(prompt);
            if (value.isBefore(LocalDate.now())) {
                return value;
            } else {
                System.out.println("Error! Date cannot be in the future.");
            }
        }
    }

    public static LocalDate getPastLocalDateWithinCurrentYear(String prompt) {
        while (true) {
            LocalDate value = getPastLocalDate(prompt);
            int valueYear = value.getYear();
            int currentYear = LocalDate.now().getYear();
            if (valueYear == currentYear) {
                return value;
            } else {
                System.out.println("Error! Date entered has to be of this year.");
            }
        }
    }

    public static LocalDate getPastLocalDateNotInList(String prompt, List<LocalDate> dates) {
        while (true) {
            LocalDate value = getPastLocalDateWithinCurrentYear(prompt);
            if (!dates.contains(value)){
                return value;
            } else {
                System.out.println("Date already entered in the list.");
            }
        }
    }

    public static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Error! Invalid double value.");
            }
        }
    }

    public static double getDouble(String prompt, double min, double max) {
        while (true) {
            double value = getDouble(prompt);
            if (value > min && value <= max) {
                return value;
            } else {
                System.out.println("Error! Number must be greater than "
                        + min + " and less than or equal to " + max + ".");
            }
        }
    }

    public static double getDoubleWithPrecision(String prompt, double min, int precision) {
        while (true) {
            double value = getDouble(prompt, min);
            if (BigDecimal.valueOf(value).scale() <= precision){
                return value;
            } else {
                System.out.println("Error! Number must have at most "+precision+ " decimal places.");
            }
        }
    }

    public static double getDouble(String prompt, double min) {
        while (true) {
            double value = getDouble(prompt);
            if (value > min) {
                return value;
            } else {
                System.out.println("Error! Number must be greater than "
                        + min + ".");
            }
        }
    }

    public static void waitForEnter(){
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
}