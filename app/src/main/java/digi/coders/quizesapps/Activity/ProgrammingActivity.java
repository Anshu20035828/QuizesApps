package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import digi.coders.quizesapps.R;
import digi.coders.quizesapps.databinding.ActivityProgrammingBinding;
import digi.coders.quizesapps.databinding.ActivityRegisterBinding;

public class ProgrammingActivity extends AppCompatActivity {

    ActivityProgrammingBinding programmingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        programmingBinding = ActivityProgrammingBinding.inflate(getLayoutInflater());
        setContentView(programmingBinding.getRoot());


    }
}