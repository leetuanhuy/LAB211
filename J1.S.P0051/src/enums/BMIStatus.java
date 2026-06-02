package enums;

/**
 * Enum representing BMI (Body Mass Index) status categories
 */
public enum BMIStatus {
    UNDER_STANDARD("Under-standard: BMI < 19"),
    STANDARD("Standard: 19 <= BMI <= 25"),
    OVERWEIGHT("Overweight: 25 < BMI <= 30"),
    FAT("Fat - should lose weight: 30 < BMI <= 40"),
    VERY_FAT("Very fat - should lose weight immediately: BMI > 40");

    private final String description;

    BMIStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
