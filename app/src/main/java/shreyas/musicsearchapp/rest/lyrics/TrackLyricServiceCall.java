package shreyas.musicsearchapp.rest.lyrics;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static shreyas.musicsearchapp.utils.AppConstants.WIKIA_LYRICS_URL;

/**
 * Created by shreyasmp on 9/7/17.
 *
 * Lyrics Service call for hitting the wiki lyrics api
 * Have used xml format as the provided json format was not as per json standards
 * Could have used dirty json parser but standard json format is what most apps work on
 * hence went towards XML for this one.
 */

public class TrackLyricServiceCall {

    private static final String TAG = TrackLyricServiceCall.class.getSimpleName();

    private static String BASE_URL = WIKIA_LYRICS_URL;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;
    private static HttpLoggingInterceptor httpLoggingInterceptor = null;

    public static TrackLyricsAPI getClient() {
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(TrackLyricsAPI.class);
    }
}
