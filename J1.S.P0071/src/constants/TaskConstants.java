package constants;

/**
 * Business constants for task management rules.
 */
public final class TaskConstants {

    private TaskConstants() {
    }

    public static final int MIN_TASK_TYPE_ID = 1;
    public static final int MAX_TASK_TYPE_ID = 4;

    public static final int TASK_TYPE_CODE = 1;
    public static final int TASK_TYPE_TEST = 2;
    public static final int TASK_TYPE_DESIGN = 3;
    public static final int TASK_TYPE_REVIEW = 4;

    public static final double MIN_WORK_TIME = 8.0;
    public static final double MAX_WORK_TIME = 17.5;
    public static final double WORK_TIME_STEP = 0.5;
    
    public static final int MIN_TASK_ID = 1;
}
