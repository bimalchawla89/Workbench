package architecturedemo.pkg.arun.retail.data.source.remote;

import architecturedemo.pkg.arun.networkinglib.ApiServices;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public interface RemoteServices extends ApiServices {
    @GET("/indexes/products/docs")
    Call<ProductList> findProductsByTitle(
            @Header("api-key") String apiKey,
            @Query("api-version") String apiVersion,
            @Query("search") String filter
    );
}
