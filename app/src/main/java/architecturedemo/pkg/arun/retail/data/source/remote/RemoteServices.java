package architecturedemo.pkg.arun.retail.data.source.remote;

import architecturedemo.pkg.arun.retail.data.models.ApiTokenModel;
import architecturedemo.pkg.arun.retail.data.models.ProductList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public interface RemoteServices {

    @POST("/v3/directline/tokens/generate")
    Call<ApiTokenModel> getApiToken(
            @Header("Authorization") String apiKey);

    @GET("/indexes/products/docs?api-version=2015-02-28&search=touring bikes")
    Call<ProductList> findProductsByTitle(
            @Header("api-key") String apiKey
    );
}
