package architecturedemo.pkg.arun.retail.categorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import architecturedemo.pkg.arun.retail.data.models.CategoryData;
import architecturedemo.pkg.arun.retail.data.models.CategoryList;
import architecturedemo.pkg.arun.retail.data.source.AppDataSource;
import architecturedemo.pkg.arun.retail.data.source.AppRepository;
import architecturedemo.pkg.arun.retail.util.SingleLiveEvent;

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