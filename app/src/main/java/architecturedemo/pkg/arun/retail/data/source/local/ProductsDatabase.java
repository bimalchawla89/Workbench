package architecturedemo.pkg.arun.retail.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import architecturedemo.pkg.arun.retail.data.models.ProductData;

/**
 * Created by Arun.Kumar04 on 12/22/2017.
 */

@Database(entities = {ProductData.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {
    private static ProductsDatabase INSTANCE = null;

    public abstract ProductsDao productsDao();

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