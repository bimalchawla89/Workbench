package arch.wb.retail.data.source.remote;

import arch.wb.retail.data.models.ApiTokenModel;
import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.models.ProductList;
import arch.wb.retail.data.models.SubcategoryList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Arun.Kumar04 on 12/28/2017.
 */

public interface RemoteServices {

    @POST("/v3/directline/tokens/generate")
    Call<ApiTokenModel> getApiToken(
            @Header("Authorization") String apiKey);

    @GET
    Call<ProductList> findProductsByTitle(@Url String url,
                                          @Header("api-key") String apiKey
    );

    @GET
    Call<CategoryList> getCategories(@Url String url,
                                     @Header("api-key") String apiKey
    );

    @GET
    Call<SubcategoryList> getSubCategories(@Url String url,
                                           @Header("api-key") String apiKey
    );
}
