package arch.wb.retail.data.source;


import android.content.Context;
import android.content.SharedPreferences;

import arch.wb.retail.constants.Constants;

public class PreferenceRepository {
    /**
     * @return returns sharepreferences object
     */
    private static SharedPreferences getSharePreferences(Context context) {
        return context.getSharedPreferences(Constants
                .MY_PREFERENCES, Context
                .MODE_PRIVATE);
    }

    /**
     * @param sharedPreferences
     * @return returns sharepreferences Editor object
     */
    private static SharedPreferences.Editor getPreferenceEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }


    /**
     * saves a boolean value corresponding to a key in app preferences
     *
     * @param key   : key of the value to be saved
     * @param value : value to be saved
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getPreferenceEditor(getSharePreferences(context)).putBoolean(key, value).apply();
    }


    public static boolean getBoolean(Context context, String key, boolean defVal) {
        return getSharePreferences(context).getBoolean(key, defVal);
    }
}
