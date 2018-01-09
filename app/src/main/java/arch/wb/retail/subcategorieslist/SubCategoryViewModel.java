package arch.wb.retail.subcategorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import arch.wb.retail.data.models.SubCategoryData;
import arch.wb.retail.data.models.SubcategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;

public class SubCategoryViewModel extends AndroidViewModel {

    public final ObservableList<SubCategoryData> subCategoryDataList = new ObservableArrayList<>();
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
                subCategoryDataList.clear();
                subCategoryDataList.addAll(subcategoryList.getValue());
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