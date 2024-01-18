package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import digi.coders.quizesapps.Helper.ShareadPrefManger;
import digi.coders.quizesapps.R;
import digi.coders.quizesapps.databinding.ActivityDashBoardBinding;

public class DashBoardActivity extends AppCompatActivity {

    ActivityDashBoardBinding dashBoardBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashBoardBinding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(dashBoardBinding.getRoot());

        dashBoardBinding.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this, QuizeCategoryActivity.class));

            }
        });

//        dashBoardBinding.txtName.setText(ShareadPrefManger.getInstance(DashBoardActivity.this).getUserData().getName());
    }
}