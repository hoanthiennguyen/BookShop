package fptt.example.bookshop;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class Preferences {
    public static void setAccessToken(@NonNull Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESSTOKEN", token);
        editor.apply();
    }
    public static void removeAccessToken(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("ACCESSTOKEN");
        editor.apply();
    }
    public static String getAccessToken(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("ACCESSTOKEN", null);
    }
}
