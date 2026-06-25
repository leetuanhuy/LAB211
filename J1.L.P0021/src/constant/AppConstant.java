package constant;

import java.util.List;

public final class AppConstant {

    private AppConstant() {
        
    }

    public static final int MENU_CREATE = 1;
    public static final int MENU_FIND_SORT = 2;
    public static final int MENU_UPDATE_DELETE = 3;
    public static final int MENU_REPORT = 4;
    public static final int MENU_EXIT = 5;

    public static final int MIN_STUDENTS = 10;
    public static final List<String> VALID_COURSES = List.of("Java", ".Net", "C/C++");
    
    
    public static final int FIRST_RECORD_INDEX = 0;
    public static final int SPLIT_LIMIT = 2;
    public static final int DISPLAY_PART_INDEX = 1;

}
