package arch.wb.retail.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public class AppPreferences {
    private static final String PREFS_FILE_APP_DATA = "prefs_file_app_data";
    private static final String PREFS_APP_DATA_TOKEN = "prefs_app_data_token";

    public static void saveToken(Context context, String token) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_APP_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFS_APP_DATA_TOKEN, token).apply();
    }

    public static final String getToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE_APP_DATA, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_APP_DATA_TOKEN, null);
    }
}
