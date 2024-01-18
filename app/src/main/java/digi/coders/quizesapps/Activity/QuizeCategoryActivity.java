package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import digi.coders.quizesapps.databinding.ActivityQuizecategoryBinding;

public class QuizeCategoryActivity extends AppCompatActivity {

    ActivityQuizecategoryBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityMainBinding = ActivityQuizecategoryBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizeCategoryActivity.this, QuizSolveActivity.class));
            }
        });

        activityMainBinding.linux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(QuizeCategoryActivity.this, LinuxCategoryActivity.class);
                startActivity(a);
            }
        });

        activityMainBinding.devops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizeCategoryActivity.this, DevOpsActivity.class));

            }
        });
    }
}