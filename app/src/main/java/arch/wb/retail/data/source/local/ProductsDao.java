package arch.wb.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import arch.wb.retail.data.models.ProductData;

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

    @Query("SELECT * FROM productData WHERE subcategory = :subCategory")
    List<ProductData> getAllProductsDataBySubCategory(String subCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(ProductData productData);
}
