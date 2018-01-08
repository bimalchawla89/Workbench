package arch.wb.retail.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import arch.wb.retail.data.models.SubCategoryData;

@Dao
public interface SubCategoryDao {

    @Query("SELECT * FROM SubCategoryData")
    List<SubCategoryData> getAllSubCategoriesData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategory(SubCategoryData categoryData);

    @Query("SELECT * FROM SubCategoryData WHERE id = :categoryId")
    List<SubCategoryData> getAllSubCategoriesData(String categoryId);

    @Query("DELETE FROM SubCategoryData WHERE id = :id")
    int deleteSubCategoryById(String id);

    @Query("SELECT * FROM SubCategoryData where id = :id")
    SubCategoryData getSubCategoryWithId(String id);
}
