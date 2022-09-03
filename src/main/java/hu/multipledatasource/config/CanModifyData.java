package hu.multipledatasource.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CanModifyData {

    // set to false while dumping database
    private static boolean canModify = false;

    public CanModifyData() {
        canModify = true;
    }

    public static boolean isCanModify() {
        return canModify;
    }

    public static void setCanModify(boolean canModify) {
        CanModifyData.canModify = canModify;
    }
}
