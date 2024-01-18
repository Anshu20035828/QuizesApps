package digi.coders.quizesapps.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import digi.coders.quizesapps.Model.RegisterModel;

public class ShareadPrefManger {


    public static final String sharedPrefName="Quizes_app";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_NO = "Phone_no";
    private static final String KEY_ADDRES = "address";


    static  ShareadPrefManger mInstance;

    Context context;

    public ShareadPrefManger(Context context) {
        this.context = context;
    }

    public static ShareadPrefManger getInstance(Context context){
        if(mInstance==null){
            mInstance=new ShareadPrefManger(context);
        }
        return mInstance;

    }



     public void UserLogin(RegisterModel registerModel){

        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putInt(KEY_ID,registerModel.getId());
        editor.putString(KEY_NAME,registerModel.getName());
        editor.putString(KEY_EMAIL,registerModel.getEmail());
        editor.putString(KEY_PASSWORD,registerModel.getPassword());
        editor.putString(KEY_PHONE_NO,registerModel.getPhoneNo());
        editor.putString(KEY_ADDRES,registerModel.getAddress());

        editor.apply();

    }

   public RegisterModel getUserData(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);

        RegisterModel registerModel=new RegisterModel(
                sharedPreferences.getInt(KEY_ID,0),
                sharedPreferences.getString(KEY_NAME,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_PASSWORD,null),
                sharedPreferences.getString(KEY_PHONE_NO,null),
                sharedPreferences.getString(KEY_ADDRES,null)
        );

        return registerModel;

    }


    boolean isLogining(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        boolean status;
        int check=sharedPreferences.getInt(KEY_ID,0);
        if(check>0){
            status=true;
        }else {
            status=false;
        }
        return status;

    }

    public void logout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(sharedPrefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.clear();
        editor.apply();
        ShareadPrefManger.getInstance(context).logout();
    }


}
