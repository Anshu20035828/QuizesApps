package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import digi.coders.quizesapps.Database;
import digi.coders.quizesapps.Helper.ShareadPrefManger;
import digi.coders.quizesapps.Model.RegisterModel;

import digi.coders.quizesapps.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());

        database = new Database(RegisterActivity.this);
        InsertDatabse();

        registerBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    public void InsertDatabse() {
        registerBinding.btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = registerBinding.username.getText().toString().trim();
                String email = registerBinding.emailregister.getText().toString().trim();
                String password = registerBinding.passwordregister.getText().toString().trim();
                String phoneNo = registerBinding.mobileNo.getText().toString().trim();
                String address = registerBinding.addresss.getText().toString().trim();

                if (name.length() == 0 || email.length() == 0 || password.length() == 0 || phoneNo.length() == 0 || address.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter field", Toast.LENGTH_SHORT).show();
                }
                else if (isEmailRegistered(email)) {
                    Toast.makeText(RegisterActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
                }

                else if (!isValidEmailFormat(email)) {
                    Toast.makeText(RegisterActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidPassword(password)) {
                    Toast.makeText(RegisterActivity.this, "Invalid password format", Toast.LENGTH_SHORT).show();
                }

                else {
                    RegisterModel registerModel=new RegisterModel(name, email, password, phoneNo, address);

                    database.insertData(registerModel);

//                    ShareadPrefManger shareadPrefManger=new ShareadPrefManger(getApplicationContext());
//                    shareadPrefManger.UserLogin(registerModel);

                    ShareadPrefManger.getInstance(RegisterActivity.this).UserLogin(registerModel);



                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, QuizeCategoryActivity.class));
                    finish();
                }
            }
        });
    }

    private boolean isEmailRegistered(String email) {
        List<RegisterModel> existingRecords = database.readDB();

        for (RegisterModel registerModel : existingRecords) {
            if (email.equalsIgnoreCase(registerModel.getEmail())) {
                return true;
            }
        }
        return false;


    }

  private boolean isValidEmailFormat(String email) {
    String regexPattern = "^(?=.*\\d)(?=.*[@gmail.com]).+$";
//    String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@gmail.com]).+$";
    return email.matches(regexPattern);
}


    private boolean isValidPassword(String password) {
        String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
//        String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@gmail.com]).+$";
        return password.matches(regexPattern);
    }

}





