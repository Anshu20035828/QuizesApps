package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import digi.coders.quizesapps.GetRetroFit;
import digi.coders.quizesapps.Model.AnswerModel;
import digi.coders.quizesapps.R;
import digi.coders.quizesapps.databinding.ActivityLinuxCategoryBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinuxCategoryActivity extends AppCompatActivity {

    String query = "yjoKm41lqBBPTbS4iRnlyMmMtF5bZNKTtvxa9T9O";
    int limits = 10;
    String categories = "Linux";
    ActivityLinuxCategoryBinding linuxCategoryBinding;
    RadioButton radioButton;
    int count_point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linuxCategoryBinding = ActivityLinuxCategoryBinding.inflate(getLayoutInflater());
        setContentView(linuxCategoryBinding.getRoot());


        solveLinuxQuestion(query, limits, categories);
    }

    public void solveLinuxQuestion(String XApiKey, int limit, String category) {

        GetRetroFit.QuizSolve().getLinuxQuestion(XApiKey, limit, category).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d("linuxs", "isSuccessful" + response.body());


                        JsonArray jsonArray = response.body();
                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                        String question = jsonObject.get("question").getAsString();
                        linuxCategoryBinding.question.setText(question);

                        JsonObject linusanswer = jsonObject.get("answers").getAsJsonObject();

                        AnswerModel answerModel = new Gson().fromJson(linusanswer,AnswerModel.class);



                        if (answerModel.getAnswer_a() == null || answerModel.getAnswer_a().equals("")){
                            linuxCategoryBinding.ques1.setVisibility(View.GONE);
                        } else {
                            linuxCategoryBinding.ques1.setText(answerModel.getAnswer_a());
                            linuxCategoryBinding.ques1.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_b() == null || answerModel.getAnswer_b().equals("")){
                            linuxCategoryBinding.ques2.setVisibility(View.GONE);
                        } else {
                            linuxCategoryBinding.ques2.setText(answerModel.getAnswer_b());
                            linuxCategoryBinding.ques2.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_c() == null || answerModel.getAnswer_c().equals("")){
                            linuxCategoryBinding.ques3.setVisibility(View.GONE);
                        }else {
                            linuxCategoryBinding.ques3.setText(answerModel.getAnswer_c());
                            linuxCategoryBinding.ques3.setBackground(getDrawable(R.drawable.radio));
                        }
                        if (answerModel.getAnswer_d() == null || answerModel.getAnswer_d().equals("")){
                            linuxCategoryBinding.ques4.setVisibility(View.GONE);
                        }else {
                            linuxCategoryBinding.ques4.setText(answerModel.getAnswer_d());
                            linuxCategoryBinding.ques4.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_e() == null || answerModel.getAnswer_e().equals("")){
                            linuxCategoryBinding.ques5.setVisibility(View.GONE);
                        }else {
                            linuxCategoryBinding.ques5.setText(answerModel.getAnswer_e());
                            linuxCategoryBinding.ques5.setBackground(getDrawable(R.drawable.radio));
                        }
                        if (answerModel.getAnswer_f() == null || answerModel.getAnswer_f().equals("")){
                            linuxCategoryBinding.ques6.setVisibility(View.GONE);
                        }else {
                            linuxCategoryBinding.ques6.setText(answerModel.getAnswer_f());
                            linuxCategoryBinding.ques6.setBackground(getDrawable(R.drawable.radio));
                        }


                        linuxCategoryBinding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {

                                radioButton = findViewById(checkedId);
                            }
                        });

                        String correct_ans = jsonObject.get("correct_answer").getAsString();
                        String answer = linusanswer.get(correct_ans).getAsString();


                        linuxCategoryBinding.nextQue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (radioButton != null){
                                    String select_answer  = radioButton.getText().toString();
                                    if (select_answer.equalsIgnoreCase(""+answer)){
                                        Toast.makeText(LinuxCategoryActivity.this, "correct_answer", Toast.LENGTH_SHORT).show();
                                        count_point++;
                                        linuxCategoryBinding.point.setText(" Point : " +count_point);
                                        radioButton.setBackgroundColor(Color.GREEN);
                                    }
                                    else {
                                        Toast.makeText(LinuxCategoryActivity.this, "worng_answer", Toast.LENGTH_SHORT).show();
                                        count_point--;
                                        linuxCategoryBinding.point.setText(" Point : " +count_point);
                                        radioButton.setBackgroundColor(Color.RED);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                           radioButton.setBackgroundColor(Color.TRANSPARENT);
                                        }
                                    },1000);

                                    solveLinuxQuestion(query, limits, categories);
                                }
                                else {
                                    Toast.makeText(LinuxCategoryActivity.this, " Please Select Answer", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    } else {
                        Log.d("linuxs", "is not Successful");
                    }
                } catch (Exception e) {
                    Log.d("linuxs", "Exception" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("linuxs", "onFailure" + t.getMessage());
            }
        });

    }
}