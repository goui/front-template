package fr.goui.fronttemplate.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Stores endpoints and the retrofit object.
 */
public interface NetworkService {

    String BASE_URL = "";

    /**
     * Put endpoints here, like:
     */
    @GET("user/getMessage")
    Observable<String> getMessage();

    /**
     * Creates the retrofit object.
     */
    class Factory {
        public static NetworkService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }
}
