package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.ProductData;

/**
 * Created by Arun.Kumar04 on 12/22/2017.
 */

@Dao
public interface ProductsDao {

//    @Query("SELECT * FROM Product")
//    List<Product> getAllProducts();
//
    @Query("SELECT * FROM productData WHERE id = :productId")
    ProductData getProductById(String productId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertProduct(Product product);
//
//    @Update
//    int updateProduct(Product product);
//
//    @Query("DELETE FROM Product WHERE productId = :productId")
//    int deleteProductById(String productId);
//
//    @Query("DELETE FROM Product")
//    void deleteProducts();


    @Query("SELECT * FROM productData")
    List<ProductData> getAllProductsData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductData productData);
}
