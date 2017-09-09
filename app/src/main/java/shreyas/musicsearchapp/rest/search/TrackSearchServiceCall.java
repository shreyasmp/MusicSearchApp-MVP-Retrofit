package shreyas.musicsearchapp.rest.search;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static shreyas.musicsearchapp.utils.AppConstants.APPLE_ITUNES_URL;

/**
 * Created by shreyasmp on 9/7/17.
 *
 * Service call for Track API which hits the iTunes api provided
 * JSON format was in standard format when validated hence went ahead with
 *  Gson json factory method.
 *  Okhttp here provides response caching and logging rather than repetitive network calls
 */

public class TrackSearchServiceCall {

    private static final String TAG = TrackSearchServiceCall.class.getSimpleName();

    private static String BASE_URL = APPLE_ITUNES_URL;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = null;


    public static TrackSearchAPI getClient() {
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(TrackSearchAPI.class);
    }
}
