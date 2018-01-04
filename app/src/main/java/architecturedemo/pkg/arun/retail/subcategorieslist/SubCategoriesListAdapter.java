package architecturedemo.pkg.arun.retail.subcategorieslist;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.SubcategoryData;
import architecturedemo.pkg.arun.retail.databinding.RowSubCategoriesListBinding;

public class SubCategoriesListAdapter extends BaseAdapter {

    private ArrayList<SubcategoryData> mSubCategoriesList;
    private final SubCategoryViewModel mSubCategoryViewModel;

    public SubCategoriesListAdapter(ArrayList<SubcategoryData> subcategoryData, SubCategoryViewModel mSubCategoryViewModel) {
        this.mSubCategoriesList = subcategoryData;
        this.mSubCategoryViewModel = mSubCategoryViewModel;
    }

    @Override
    public int getCount() {
        return null == mSubCategoriesList ? 0 : mSubCategoriesList.size();
    }

    @Override
    public SubcategoryData getItem(int i) {
        return mSubCategoriesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RowSubCategoriesListBinding subCategoriesListBinding;
        if (null == view) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            subCategoriesListBinding = RowSubCategoriesListBinding.inflate(inflater, viewGroup, false);
        } else {
            subCategoriesListBinding = DataBindingUtil.getBinding(view);
        }

        SubCategoryItemClickListener categoryItemClickListener = new SubCategoryItemClickListener() {
            @Override
            public void onSubCategoryClicked(SubcategoryData subcategoryData) {
                mSubCategoryViewModel.getOpenSubCategoryEvent().setValue(subcategoryData.getTitle());
            }
        };

        subCategoriesListBinding.setListener(categoryItemClickListener);
        subCategoriesListBinding.setSubcategory(mSubCategoriesList.get(i));
        subCategoriesListBinding.executePendingBindings();

        return subCategoriesListBinding.getRoot();
    }

    public void updateSubCategoriesList(List<SubcategoryData> categoryDataList) {
        mSubCategoriesList = new ArrayList<>(categoryDataList);
        notifyDataSetChanged();
    }
}