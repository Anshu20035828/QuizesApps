package digi.coders.quizesapps;


import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {

    @GET("questions")
    Call<JsonArray> getQuiz(
            @Header("X-Api-Key") String XApiKey
    );

    @GET("questions")
    Call<JsonArray> getLinuxQuestion(
            @Header("X-Api-Key") String XApiKey,
            @Query("limit") int limit,
            @Query("category") String category
    );

    @GET("questions")
    Call<JsonArray> getDevOpsQuestion(
            @Header("X-Api-Key") String XApiKey,
            @Query("category") String DevOps
    );

}
