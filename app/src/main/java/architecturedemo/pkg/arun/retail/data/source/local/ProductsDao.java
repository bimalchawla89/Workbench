package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;

/**
 * Created by Arun.Kumar04 on 12/22/2017.
 */

@Dao
public interface ProductsDao {

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Query("SELECT * FROM Product WHERE productId = :productId")
    Product getProductById(String productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Update
    int updateProduct(Product product);

    @Query("DELETE FROM Product WHERE productId = :productId")
    int deleteProductById(String productId);

    @Query("DELETE FROM Product")
    void deleteProducts();
}
