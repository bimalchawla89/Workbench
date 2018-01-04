package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.CategoryData;

@Dao
public interface CategoryDao {

    //    @Query("SELECT * FROM Product")
//    List<Product> getAllProducts();
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


    @Query("SELECT * FROM categoryData")
    List<CategoryData> getAllCategoriesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryData categoryData);

    @Query("SELECT * FROM categoryData where id = :id")
    CategoryData getCategoryWithId(String id);

    @Query("DELETE FROM categoryData WHERE id = :id")
    int deleteCategoryById(String id);
}
