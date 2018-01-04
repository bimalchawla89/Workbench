package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.SubCategoryData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Arun.Kumar04 on 1/4/2018.
 */

@RunWith(AndroidJUnit4.class)
public class SubCategoryDaoTest {

    private ProductsDatabase mProductsDatabase;

    private static final SubCategoryData SUB_CATEGORY = new SubCategoryData("id1", "title1", "description1", "parent1");

    @Before
    public void initDatabase() {
        mProductsDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), ProductsDatabase.class).build();
    }

    @After
    public void closeDb() {
        mProductsDatabase.close();
    }

    @Test
    public void insertSubCategoryTest() {
        SubCategoryDao subCategoryDao = mProductsDatabase.subCategoryDao();

        subCategoryDao.insertSubCategory(SUB_CATEGORY);

        SubCategoryData subCategoryWithId = subCategoryDao.getSubCategoryWithId(SUB_CATEGORY.getId());

        assertSubCategory(subCategoryWithId, "id1", "title1", "description1", "parent1");
    }

    @Test
    public void deleteSubCategoryTest() {
        SubCategoryDao subCategoryDao = mProductsDatabase.subCategoryDao();

        subCategoryDao.insertSubCategory(SUB_CATEGORY);

        subCategoryDao.deleteSubCategoryById(SUB_CATEGORY.getId());

        List<SubCategoryData> subCategoryDataList = subCategoryDao.getAllSubCategoriesData();

        assertThat(subCategoryDataList.size(), is(0));
    }

    private void assertSubCategory(SubCategoryData subCategoryData, String id, String title, String description, String parent) {
        assertThat(subCategoryData, notNullValue());
        assertThat(subCategoryData.getId(), is(id));
        assertThat(subCategoryData.getTitle(), is(title));
        assertThat(subCategoryData.getDescription(), is(description));
        assertThat(subCategoryData.getParent(), is(parent));
    }
}
