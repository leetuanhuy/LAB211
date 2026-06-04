package enums;

/**
 * Enums representing BMI (Body Mass Index) status categories
 */
public enum BMIStatus {
    UNDER_STANDARD("Under-standard"),
    STANDARD("Standard"),
    OVERWEIGHT("Overweight"),
    FAT("Fat"),
    VERY_FAT("Very fat");

    private final String description;

    BMIStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
