package digi.coders.quizesapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import digi.coders.quizesapps.Model.RegisterModel;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LoginDataBase";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME = "tableName";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_NO = "Phone_no";
    private static final String KEY_ADDRES = "address";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_PHONE_NO + " TEXT,"
                + KEY_ADDRES + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //    public Long insertData(RegisterModel contectModel) {
    public void insertData(RegisterModel contectModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contectModel.getName());
        values.put(KEY_EMAIL, contectModel.getEmail());
        values.put(KEY_PASSWORD, contectModel.getPassword());
        values.put(KEY_PHONE_NO, contectModel.getPhoneNo());
        values.put(KEY_ADDRES, contectModel.getAddress());
        db.insert(TABLE_NAME, null, values);

/*
create table ko check karne ka tarika
  Long a = db.insert(TABLE_NAME, null, values);
        if (a != 0) {
            Log.d("insertData", "Data inserted success");
        } else {
            Log.d("insertData", "Data not inserted");
        }
        return a;
 */


    }

    public List<RegisterModel> readDB() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<RegisterModel> registerModelList = new ArrayList<>();
        while (cursor.moveToNext()) {
            RegisterModel registerModel = new RegisterModel();
            registerModel.id = cursor.getInt(0);
            registerModel.name = cursor.getString(1);
            registerModel.email = cursor.getString(2);
            registerModel.password = cursor.getString(3);
            registerModel.phoneNo = cursor.getString(4);
            registerModel.address = cursor.getString(5);

            registerModelList.add(registerModel);
        }

        cursor.close();

        return registerModelList;
    }


}
