package arch.wb.retail.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import arch.wb.retail.data.models.CategoryData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Arun.Kumar04 on 1/4/2018.
 */

@RunWith(AndroidJUnit4.class)
public class CategoryDaoTest {

    private ProductsDatabase mProductsDatabase;

    private static final CategoryData CATEGORY = new CategoryData("id1", "title1", "description1");

    @Before
    public void initDatabase() {
        mProductsDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), ProductsDatabase.class).build();
    }

    @After
    public void closeDb() {
        mProductsDatabase.close();
    }

    @Test
    public void insertCategoryTest() {
        CategoryDao categoryDao = mProductsDatabase.categoryDao();

        categoryDao.insertCategory(CATEGORY);

        CategoryData categoryWithId = categoryDao.getCategoryWithId(CATEGORY.getId());

        assertCategory(categoryWithId, "id1", "title1", "description1");
    }

    @Test
    public void deleteCategoryTest() {
        CategoryDao categoryDao = mProductsDatabase.categoryDao();

        categoryDao.insertCategory(CATEGORY);

        categoryDao.deleteCategoryById(CATEGORY.getId());

        List<CategoryData> categoryDataList = categoryDao.getAllCategoriesData();

        assertThat(categoryDataList.size(), is(0));
    }

    private void assertCategory(CategoryData categoryData, String id, String title, String description) {
        assertThat(categoryData, notNullValue());
        assertThat(categoryData.getId(), is(id));
        assertThat(categoryData.getTitle(), is(title));
        assertThat(categoryData.getDescription(), is(description));
    }
}
