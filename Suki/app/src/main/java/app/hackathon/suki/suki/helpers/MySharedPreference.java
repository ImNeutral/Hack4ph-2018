package app.hackathon.suki.suki.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static MySharedPreference mInstance;
    private Context mContext;

    private SharedPreferences mMyPreferences;
    public static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public static void clearSharedPreference(Context context){
         mInstance.mMyPreferences.edit().clear().commit();
    }

    //Shared Preferences Getters & Setters


    public static void setUserType(Context context, String userType) {
        getPreference(context).edit().putString(ConstantsHelper.PREFS_USERT_TYPE, userType ).apply();
    }
    public static String getUserType(Context context) {
        return getPreference(context).getString(ConstantsHelper.PREFS_USERT_TYPE, "");
    }
}
