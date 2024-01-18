package digi.coders.quizesapps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetroFit {

    public static ApiService QuizSolve(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://quizapi.io/api/v1/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
