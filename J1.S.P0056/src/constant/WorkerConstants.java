package constant;

/**
 * Contains constant values used throughout the worker management application
 * to avoid magic numbers and strings.
 */
public class WorkerConstants {

    /** Minimum age allowed for a worker. */
    public static final int MIN_AGE = 18;

    /** Maximum age allowed for a worker. */
    public static final int MAX_AGE = 50;

    /** Minimum salary allowed for a worker (must be greater than 0). */
    public static final double MIN_SALARY = 0;

    /** Minimum amount of money for salary adjustment (must be greater than 0). */
    public static final double MIN_ADJUSTMENT_AMOUNT = 0;

    /** Status string for salary increase. */
    public static final String STATUS_UP = "UP";

    /** Status string for salary decrease. */
    public static final String STATUS_DOWN = "DOWN";

    /** Menu option: Add worker. */
    public static final int OPTION_ADD_WORKER = 1;

    /** Menu option: Increase salary. */
    public static final int OPTION_INCREASE_SALARY = 2;

    /** Menu option: Decrease salary. */
    public static final int OPTION_DECREASE_SALARY = 3;

    /** Menu option: Show adjusted salary history. */
    public static final int OPTION_SHOW_HISTORY = 4;

    /** Menu option: Quit program. */
    public static final int OPTION_QUIT = 5;

    /** Minimum menu option. */
    public static final int MENU_MIN = 1;

    /** Maximum menu option. */
    public static final int MENU_MAX = 5;

    private WorkerConstants() {
    }
}
