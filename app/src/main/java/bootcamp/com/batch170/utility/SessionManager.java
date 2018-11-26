package bootcamp.com.batch170.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eric on 09/10/2018.
 */

public class SessionManager {
    //deklarasi shared preference
    protected static SharedPreferences retrieveSharedPreferences(Context context){
        return context.getSharedPreferences(Constanta.SHARED_PREFERENCE_NAME,
                context.MODE_PRIVATE);
    }

    //mode edit (CRUD)
    protected static SharedPreferences.Editor retrieveSharedPreferencesEditor(Context context){
        return retrieveSharedPreferences(context).edit();
    }


    /*deklarasikan getter/setter sesuai kebutuhan*/

    //simpan data register
    public static void saveRegistrationData(Context context,
                                            String fullname,
                                            int age,
                                            boolean register
                                            ){
        SharedPreferences.Editor editor = retrieveSharedPreferencesEditor(context);
        editor.putString(Constanta.KEY_FULLNAME, fullname);
        editor.putInt(Constanta.KEY_AGE, age);
        editor.putBoolean(Constanta.KEY_IS_REGISTER, register);

        editor.commit();
    }

    //ambil data register
    public static boolean isRegister(Context context){
        return retrieveSharedPreferences(context).getBoolean(Constanta.KEY_IS_REGISTER, false);
    }

    public static String getFullName(Context context){
        return retrieveSharedPreferences(context).getString(Constanta.KEY_FULLNAME, "");
    }

    public static int getAge(Context context){
        return retrieveSharedPreferences(context).getInt(Constanta.KEY_AGE, 0);
    }
}

