package arch.wb.retail.categorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;

public class CategoryViewModel extends AndroidViewModel {

    public final ObservableList<CategoryData> categories = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenCategoryEvent = new SingleLiveEvent<>();

    private final Context mContext;
    private final AppRepository mAppRepository;


    public CategoryViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mAppRepository = repository;
    }

    public void getAllCategories() {
        mAppRepository.getCategoriesList(mContext, new AppDataSource.GetCategoriesCallback() {

            @Override
            public void onCategoriesLoaded(CategoryList categoryList) {
                categories.clear();
                categories.addAll(categoryList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public SingleLiveEvent<String> getOpenCategoryEvent() {
        return mOpenCategoryEvent;
    }
}