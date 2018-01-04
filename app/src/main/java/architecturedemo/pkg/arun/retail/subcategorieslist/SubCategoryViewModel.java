package architecturedemo.pkg.arun.retail.subcategorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import architecturedemo.pkg.arun.retail.data.models.SubcategoryData;
import architecturedemo.pkg.arun.retail.data.models.SubcategoryList;
import architecturedemo.pkg.arun.retail.data.source.AppDataSource;
import architecturedemo.pkg.arun.retail.data.source.AppRepository;
import architecturedemo.pkg.arun.retail.util.SingleLiveEvent;

public class SubCategoryViewModel extends AndroidViewModel {

    public final ObservableList<SubcategoryData> subcategoryDataList = new ObservableArrayList<>();
    private final SingleLiveEvent<String> mOpenSubCategoryEvent = new SingleLiveEvent<>();

    private final Context mContext;
    private final AppRepository mAppRepository;


    public SubCategoryViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mAppRepository = repository;
    }

    public void getAllSubCategories(String categoryId) {
        mAppRepository.getSubCategoriesList(mContext, categoryId, new AppDataSource.GetSubCategoriesCallback() {

            @Override
            public void onSubCategoriesLoaded(SubcategoryList subcategoryList) {
                subcategoryDataList.clear();
                subcategoryDataList.addAll(subcategoryList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public SingleLiveEvent<String> getOpenSubCategoryEvent() {
        return mOpenSubCategoryEvent;
    }
}