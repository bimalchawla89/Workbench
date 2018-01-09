package arch.wb.retail.categorieslist;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.databinding.RowCategoriesListBinding;

public class CategoryListAdapter extends BaseAdapter {

    private final CategoryViewModel mCategoryViewModel;
    private ArrayList<CategoryData> mCategoriesList;

    public CategoryListAdapter(ArrayList<CategoryData> categoryData, CategoryViewModel mCategoryViewModel) {
        this.mCategoriesList = categoryData;
        this.mCategoryViewModel = mCategoryViewModel;
    }

    @Override
    public int getCount() {
        return null == mCategoriesList ? 0 : mCategoriesList.size();
    }

    @Override
    public CategoryData getItem(int i) {
        return mCategoriesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RowCategoriesListBinding categoriesListBinding;
        if (null == view) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            categoriesListBinding = RowCategoriesListBinding.inflate(inflater, viewGroup, false);
        } else {
            categoriesListBinding = DataBindingUtil.getBinding(view);
        }

        CategoryItemClickListener categoryItemClickListener = new CategoryItemClickListener() {
            @Override
            public void onCategoryClicked(CategoryData categoryData) {
                mCategoryViewModel.getOpenCategoryEvent().setValue(categoryData.getId());
            }
        };

        categoriesListBinding.setListener(categoryItemClickListener);
        categoriesListBinding.setCategory(mCategoriesList.get(i));
        categoriesListBinding.executePendingBindings();

        return categoriesListBinding.getRoot();
    }

    public void updateCategoriesList(List<CategoryData> categoryDataList) {
        mCategoriesList = new ArrayList<>(categoryDataList);
        notifyDataSetChanged();
    }
}