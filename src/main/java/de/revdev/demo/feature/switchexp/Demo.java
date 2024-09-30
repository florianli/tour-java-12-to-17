package de.revdev.demo.feature.switchexp;

public class Demo {

    public static String alteSwitchStatementDayName(int day) {
        String dayName;
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            default:
                dayName = "Unknown";
                break;
        }
        return dayName;
    }

    public static String alteSwitchStatementTypeOfDay(int day) {
        String typeOfDay;
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                typeOfDay = "Weekday";
                break;
            case 6:
            case 7:
                typeOfDay = "Weekend";
                break;
            default:
                typeOfDay = "Unknown";
                break;
        }
        return typeOfDay;
    }

    public static String neuerSwitchCaseDayName(int day) {
        String dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> {
                System.out.println("Fall " + 2);
                yield "Tuesday";
            }
            case 3 -> "Wednesday";
            default -> "Unknown";
        };
        return dayName;
    }

    public static String neuerSwitchCaseTypeOfDay(int day) {
        String typeOfDay = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Unknown";
        };
        return typeOfDay;
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static String neuerSwitchCaseTypeOfDay(Day day) {
        String typeOfDay = switch (day) {
            case SATURDAY, SUNDAY -> "Weekend";
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
        };
        return typeOfDay;
    }

    public static void main(String[] args) {
        System.out.println(alteSwitchStatementDayName(2));
        System.out.println(alteSwitchStatementTypeOfDay(2));
        System.out.println(neuerSwitchCaseDayName(2));
        System.out.println(neuerSwitchCaseTypeOfDay(2));
        System.out.println(neuerSwitchCaseTypeOfDay(Day.TUESDAY));
    }
}
