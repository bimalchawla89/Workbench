package arch.wb.retail.subcategorieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import arch.wb.retail.data.models.SubCategoryData;
import arch.wb.retail.data.models.SubcategoryList;
import arch.wb.retail.data.source.AppDataSource;
import arch.wb.retail.data.source.AppRepository;
import arch.wb.retail.util.SingleLiveEvent;

public class SubCategoryViewModel extends AndroidViewModel {

    public final MutableLiveData<List<SubCategoryData>> subCategoryDataList = new MutableLiveData<>();
    private final SingleLiveEvent<String> mOpenSubCategoryEvent = new SingleLiveEvent<>();

    private final AppRepository mAppRepository;


    public SubCategoryViewModel(
            Application context,
            AppRepository repository) {
        super(context);
        mAppRepository = repository;
    }

    public void getAllSubCategories(Context context, String categoryId) {
        mAppRepository.getSubCategoriesList(context, categoryId, new AppDataSource.GetSubCategoriesCallback() {

            @Override
            public void onSubCategoriesLoaded(SubcategoryList subcategoryList) {
                subCategoryDataList.setValue(subcategoryList.getValue());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public SingleLiveEvent<String> getOpenSubCategoryEvent() {
        return mOpenSubCategoryEvent;
    }

    public LiveData<List<SubCategoryData>> getSubCategoryData() {
        return subCategoryDataList;
    }
}