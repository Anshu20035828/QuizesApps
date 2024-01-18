package digi.coders.quizesapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import java.util.ArrayList;
import java.util.List;

import digi.coders.quizesapps.GetRetroFit;
import digi.coders.quizesapps.Model.AnswerModel;
import digi.coders.quizesapps.R;
import digi.coders.quizesapps.databinding.ActivityQuizsolveBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizSolveActivity extends AppCompatActivity {
    RadioButton radioCheckedButton;
    String query = "yjoKm41lqBBPTbS4iRnlyMmMtF5bZNKTtvxa9T9O";
    int count = 0;
    ActivityQuizsolveBinding quizsolveBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizsolveBinding = ActivityQuizsolveBinding.inflate(getLayoutInflater());
        setContentView(quizsolveBinding.getRoot());

        GetQuizsSolevAllQuestion(query);

        quizsolveBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetQuizsSolevAllQuestion(query);
            }
        });


    }

    public void GetQuizsSolevAllQuestion(String XApiKey) {

        GetRetroFit.QuizSolve().getQuiz(XApiKey).enqueue(new Callback<JsonArray>() {

            List<AnswerModel> answerModelList;

            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    if (response.isSuccessful()) {

                        Log.d("Hello", "isSuccessful" + response.body());

                        JsonArray jsonArray = response.body();

                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

                        String question = jsonObject.get("question").getAsString();
                        quizsolveBinding.question.setText(question);

                        JsonObject answerObject = jsonObject.get("answers").getAsJsonObject();

                        AnswerModel answerModel = new Gson().fromJson(answerObject, AnswerModel.class);


                        if (answerModel.getAnswer_a() == null || answerModel.getAnswer_a().equals("")) {
                            quizsolveBinding.ques1.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques1.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques1.setText(answerModel.getAnswer_a());
                        }


                        if (answerModel.getAnswer_b() == null || answerModel.getAnswer_b().equals("")) {
                            quizsolveBinding.ques2.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques2.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques2.setText(answerModel.getAnswer_b());
                        }


                        if (answerModel.getAnswer_c() == null || answerModel.getAnswer_c().equals("")) {
                            quizsolveBinding.ques3.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques3.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques3.setText(answerModel.getAnswer_c());
                        }


                        if (answerModel.getAnswer_d() == null || answerModel.getAnswer_d().equals("")) {
                            quizsolveBinding.ques4.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques4.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques4.setText(answerModel.getAnswer_d());
                        }


                        if (answerModel.getAnswer_e() == null || answerModel.getAnswer_e().equals("")) {
                            quizsolveBinding.ques5.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques5.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques5.setText(answerModel.getAnswer_e());
                        }


                        if (answerModel.getAnswer_f() == null || answerModel.getAnswer_f().equals("")) {
                            quizsolveBinding.ques6.setVisibility(View.GONE);
                        } else {
                            quizsolveBinding.ques6.setBackground(getDrawable(R.drawable.radio));
                            quizsolveBinding.ques6.setText(answerModel.getAnswer_f());
                        }

                        quizsolveBinding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                radioCheckedButton = findViewById(checkedId);
                            }
                        });

                        String correct_answer = jsonObject.get("correct_answer").getAsString();
                        String correct_ans = answerObject.get(correct_answer).getAsString();




                        quizsolveBinding.nextQue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Check if any RadioButton is selected
                                if (radioCheckedButton != null) {
                                    String radioCheckedText = radioCheckedButton.getText().toString();

                                    if (radioCheckedText.equalsIgnoreCase("" + correct_ans)) {
                                        Toast.makeText(QuizSolveActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                                        radioCheckedButton.setBackgroundColor(Color.BLUE);
                                        count++;
                                        quizsolveBinding.point.setText("Point: " + count);
                                    } else {
                                        Toast.makeText(QuizSolveActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                                        count--;
                                        quizsolveBinding.point.setText("Point: " + count);

                                        radioCheckedButton.setBackgroundColor(Color.RED);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            radioCheckedButton.setBackgroundColor(Color.TRANSPARENT);
//                                            GetQuizsSolevAllQuestion(query);
                                        }
                                    }, 1000);

                                    GetQuizsSolevAllQuestion(query);
                                } else {
                                    // Handle the case where no RadioButton is selected
                                    Toast.makeText(QuizSolveActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



                    } else {
                        Log.d("Hello", "UnSuccessful: ");
                    }
                } catch (Exception e) {
                    Log.d("Hello", "Exception: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("Hello", "onFailure : " + t.getMessage());
            }
        });

    }




}