/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public enum BMIStatus {
    UNDER_STANDARD("Under-standard: BMI is less than 19"),
    STANDARD("Standard: BMI is between 19-25"),
    OVERWEIGHT("Over weight: BMI is between 25-30"),
    FAT("FAt: BMI is between 30-40"),
    VERRY_FAT("Verry Fat: BMI is over 40");
    private final String description;

    private BMIStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
