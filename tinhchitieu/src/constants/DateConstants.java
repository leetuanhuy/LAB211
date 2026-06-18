package constants;

public final class DateConstants {

    private DateConstants() {
    }

    public static final String DATE_PATTERN = "dd-MMM-yyyy";
    public static final String DATE_REGEX = "\\d{1,2}-[A-Za-z]{3}-\\d{4}";
}
