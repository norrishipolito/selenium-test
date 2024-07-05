package com.tests.common.utilities;

import org.testng.annotations.DataProvider;

public final class DataProviderUtil {

    public static Object[][] LoginData(){
        // [username][password]
        return new Object[][]{
                {"standard_user","secret_sauce"},
                {"locked_out_user","secret_sauce"},
                {"problem_user","secret_sauce"},
                {"performance_glitch_user","secret_sauce"},
                {"error_user","secret_sauce"},
                {"visual_user","secret_sauce"},
        };
    }
}
