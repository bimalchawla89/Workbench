package arch.wb.retail.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.data.models.ProductData;
import arch.wb.retail.data.models.SubCategoryData;

/**
 * Created by Arun.Kumar04 on 12/22/2017.
 */

@Database(entities = {ProductData.class, CategoryData.class, SubCategoryData.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {
    private static ProductsDatabase INSTANCE = null;

    public abstract ProductsDao productsDao();
    public abstract CategoryDao categoryDao();
    public abstract SubCategoryDao subCategoryDao();

    private static final Object lock = new Object();

    public static ProductsDatabase getInstance(Context context) {
        synchronized (lock) {
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, "products.db").build();
            }
            return INSTANCE;
        }
    }
}
