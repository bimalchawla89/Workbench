package architecturedemo.pkg.arun.networkinglib;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Arun.Kumar04 on 12/19/2017.
 */

public class NetworkInit {
    private static NetworkInit INSTANCE;
    private Context context;
    private NetworkLibs networkLib = NetworkLibs.RETROFIT;
    private final String baseUrl;
    private final ApiServices apiServices;

    private NetworkInit(Context context, NetworkLibs networkLib, String baseUrl, ApiServices apiServices) {
        this.context = context;
        this.networkLib = networkLib;
        this.baseUrl = baseUrl;
        this.apiServices = apiServices;
    }

    public static NetworkInit getInstance(Context context, NetworkLibs networkLib, String baseUrl, ApiServices apiServices) {
        if (INSTANCE == null) {
            INSTANCE = new NetworkInit(context, networkLib, baseUrl, apiServices);
        }
        return INSTANCE;
    }

    public ApiServices initNetworkLib() {
        int cacheSize = 5 * 1024 * 1024; // 5 MB
        switch (networkLib) {
            case RETROFIT:
                Cache cache = new Cache(context.getCacheDir(), cacheSize);

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.readTimeout(NetworkConstants.API_READ_TIMEOUT, TimeUnit.SECONDS);
                httpClient.connectTimeout(NetworkConstants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS);

                // add cache path
                httpClient.cache(cache);
                return new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(JacksonConverterFactory.create(mapper))
                        .client(httpClient.build()).
                                build().create(apiServices.getClass());
            case VOLLEY:
//                com.android.volley.Cache vCache = new DiskBasedCache(context.getCacheDir(), cacheSize);
//                // Set up the network to use HttpURLConnection as the HTTP client.
//                Network network = new BasicNetwork(new HurlStack());
//
//                // Instantiate the RequestQueue with the cache and network.
//                RequestQueue mRequestQueue = new RequestQueue(vCache, network);
//
//                // Start the queue
//                mRequestQueue.start();
                break;
        }
        return null;
    }
}
