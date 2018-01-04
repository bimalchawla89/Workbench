package architecturedemo.pkg.arun.networkinglib;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Arun.Kumar04 on 12/19/2017.
 */

public class NetworkInit {
    private static NetworkInit INSTANCE;
    private Context context;
    private final String baseUrl;
    private final Class<?> apiServices;

    private NetworkInit(Context context, String baseUrl, Class<?> apiServices) {
        this.context = context;
        this.baseUrl = baseUrl;
        this.apiServices = apiServices;
    }

    public static NetworkInit getInstance(Context context, String baseUrl, Class<?> apiServices) {
        if (INSTANCE == null) {
            INSTANCE = new NetworkInit(context, baseUrl, apiServices);
        }
        return INSTANCE;
    }

    public Object initNetworkLib() {
        int cacheSize = 5 * 1024 * 1024; // 5 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(NetworkConstants.API_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClient.connectTimeout(NetworkConstants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);

        // add cache path
        httpClient.cache(cache);

        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(httpClient.build()).
                        build().create(apiServices);
    }
}
