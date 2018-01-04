package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.SubcategoryData;

@Dao
public interface SubCategoryDao {

    @Query("SELECT * FROM subCategoryData")
    List<SubcategoryData> getAllSubCategoriesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategory(SubcategoryData categoryData);

    @Query("SELECT * FROM subCategoryData WHERE id = :categoryId")
    List<SubcategoryData> getAllSubCategoriesData(String categoryId);
}
