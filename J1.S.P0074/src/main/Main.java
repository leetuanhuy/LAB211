package main;

import constant.MatrixConstant;
import controller.MatrixController;
import service.MatrixService;
import utility.Validation;

/**
 * Entry point of the matrix calculator program.
 * Wires dependencies and starts the menu loop.
 */
public class Main {

    public static void main(String[] args) {
        MatrixService service = new MatrixService();
        MatrixController controller = new MatrixController(service);

        while (true) {
            displayMenu();
            int option = Validation.getInt("Please choose an option: ",
                    "Please choose an option (1-4): ",
                    "Please choose an option (1-4): ",
                    MatrixConstant.MENU_MIN, MatrixConstant.MENU_MAX);

            switch (option) {
                case MatrixConstant.ADD:
                    controller.add();
                    break;
                case MatrixConstant.SUB:
                    controller.sub();
                    break;
                case MatrixConstant.MUL:
                    controller.mul();
                    break;
                case MatrixConstant.EXIT:
                    System.exit(0);
            }
        }
    }

    /** Displays the menu options to the user. */
    private static void displayMenu() {
        System.out.println("=======Calculator program=======");
        System.out.println("1. Addition Matrixes");
        System.out.println("2. Subtraction Matrixes");
        System.out.println("3. Multiplication Matrixes");
        System.out.println("4. Quit");
    }
}
