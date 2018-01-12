package arch.wb.retail.categorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.data.models.CategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;

public class CategoryViewModel extends AndroidViewModel {

    public final MutableLiveData<List<CategoryData>> categories = new MutableLiveData<>();
    private final SingleLiveEvent<String> mOpenCategoryEvent = new SingleLiveEvent<>();

    private final AppRepository mAppRepository;


    public CategoryViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mAppRepository = repository;
    }

    public void getAllCategories(Context context) {
        mAppRepository.getCategoriesList(context.getApplicationContext(), new AppDataSource.GetCategoriesCallback() {

            @Override
            public void onCategoriesLoaded(CategoryList categoryList) {
                categories.setValue(categoryList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public LiveData<List<CategoryData>> getCategoryData() {
        return categories;
    }

    public SingleLiveEvent<String> getOpenCategoryEvent() {
        return mOpenCategoryEvent;
    }
}