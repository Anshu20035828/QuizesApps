package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import digi.coders.quizesapps.Database;
import digi.coders.quizesapps.Helper.ShareadPrefManger;
import digi.coders.quizesapps.Model.RegisterModel;
import digi.coders.quizesapps.R;
import digi.coders.quizesapps.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());


      db = new Database(LoginActivity.this);

        loginBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginBinding.emaillogin.getText().toString();
                String password = loginBinding.passwordlogin.getText().toString();

                if (email.length() == 0 || password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValidData(email, password)) {

                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, QuizeCategoryActivity.class));
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean isValidData(String email, String password) {
        List<RegisterModel> matchRecord = db.readDB();

        for (RegisterModel registerModel : matchRecord) {
            if (email.equalsIgnoreCase(registerModel.getEmail()) && password.equals(registerModel.getPassword())) {
                ShareadPrefManger.getInstance(LoginActivity.this).UserLogin(registerModel);
                return true;
            }
        }
        return false;
    }
}