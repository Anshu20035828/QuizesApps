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
import digi.coders.quizesapps.databinding.ActivityDevOpsBinding;
import digi.coders.quizesapps.databinding.ActivityLinuxCategoryBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevOpsActivity extends AppCompatActivity {

    ActivityDevOpsBinding devOpsBinding;

    String query = "yjoKm41lqBBPTbS4iRnlyMmMtF5bZNKTtvxa9T9O";
    int limits = 10;
    String categories = "DevOps";
    RadioButton radioButton;
    int count_point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        devOpsBinding = ActivityDevOpsBinding.inflate(getLayoutInflater());
        setContentView(devOpsBinding.getRoot());

        solveDevOpsQuestion(query, categories);
    }


    public void solveDevOpsQuestion(String XApiKey, String category) {

        GetRetroFit.QuizSolve().getDevOpsQuestion(XApiKey, category).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                try {
                    if (response.isSuccessful()) {
                        Log.d("Anshu", "isSuccessful" + response.body());


                        JsonArray jsonArray = response.body();
                        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                        String question = jsonObject.get("question").getAsString();
                        devOpsBinding.devquestion.setText(question);

                        JsonObject devsanswer = jsonObject.get("answers").getAsJsonObject();

                        AnswerModel answerModel = new Gson().fromJson(devsanswer, AnswerModel.class);


                        if (answerModel.getAnswer_a() == null || answerModel.getAnswer_a().equals("")) {
                            devOpsBinding.devques1.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques1.setText(answerModel.getAnswer_a());
                            devOpsBinding.devques1.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_b() == null || answerModel.getAnswer_b().equals("")) {
                            devOpsBinding.devques2.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques2.setText(answerModel.getAnswer_b());
                            devOpsBinding.devques2.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_c() == null || answerModel.getAnswer_c().equals("")) {
                            devOpsBinding.devques3.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques3.setText(answerModel.getAnswer_c());
                            devOpsBinding.devques3.setBackground(getDrawable(R.drawable.radio));
                        }
                        if (answerModel.getAnswer_d() == null || answerModel.getAnswer_d().equals("")) {
                            devOpsBinding.devques4.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques4.setText(answerModel.getAnswer_d());
                            devOpsBinding.devques4.setBackground(getDrawable(R.drawable.radio));
                        }

                        if (answerModel.getAnswer_e() == null || answerModel.getAnswer_e().equals("")) {
                            devOpsBinding.devques5.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques5.setText(answerModel.getAnswer_e());
                            devOpsBinding.devques5.setBackground(getDrawable(R.drawable.radio));
                        }
                        if (answerModel.getAnswer_f() == null || answerModel.getAnswer_f().equals("")) {
                            devOpsBinding.devques6.setVisibility(View.GONE);
                        } else {
                            devOpsBinding.devques6.setText(answerModel.getAnswer_f());
                            devOpsBinding.devques6.setBackground(getDrawable(R.drawable.radio));
                        }


                        devOpsBinding.devradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {

                                radioButton = findViewById(checkedId);
                            }
                        });

                        String correct_ans = jsonObject.get("correct_answer").getAsString();
                        String answer = devsanswer.get(correct_ans).getAsString();


                        devOpsBinding.devnextQue.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (radioButton != null) {
                                    String select_answer = radioButton.getText().toString();
                                    if (select_answer.equalsIgnoreCase("" + answer)) {
                                        Toast.makeText(DevOpsActivity.this, "correct_answer", Toast.LENGTH_SHORT).show();
                                        count_point++;
                                        devOpsBinding.devpoint.setText(" Point : " + count_point);
                                        radioButton.setBackgroundColor(Color.GREEN);
                                    } else {
                                        Toast.makeText(DevOpsActivity.this, "worng_answer", Toast.LENGTH_SHORT).show();
                                        count_point--;
                                        devOpsBinding.devpoint.setText(" Point : " + count_point);
                                        radioButton.setBackgroundColor(Color.RED);
                                    }

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            radioButton.setBackgroundColor(Color.TRANSPARENT);
                                        }
                                    }, 1000);

                                    solveDevOpsQuestion(query, categories);
                                } else {
                                    Toast.makeText(DevOpsActivity.this, " Please Select Answer", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    } else {
                        Log.d("Anshu", "is not Successful");
                    }
                } catch (Exception e) {
                    Log.d("Anshu", "Exception" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.d("Anshu", "onFailure" + t.getMessage());
            }
        });

    }


}