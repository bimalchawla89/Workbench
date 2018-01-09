package arch.wb.retail.categorieslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import arch.wb.retail.HomeActivity;
import arch.wb.retail.data.models.CategoryData;
import arch.wb.retail.databinding.FragmentCategoriesBinding;

/**
 * A fragment representing a list of categories.
 */
public class CategoriesFragment extends Fragment {

    private CategoryViewModel mCategoryViewModel;

    private FragmentCategoriesBinding mCategoryListBinding;

    private CategoryListAdapter mListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriesFragment() {
    }

    @SuppressWarnings("unused")
    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCategoryListBinding = FragmentCategoriesBinding.inflate(inflater, container, false);
        mCategoryViewModel = HomeActivity.obtainViewModel(getActivity());
        mCategoryListBinding.setModel(mCategoryViewModel);
        return mCategoryListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupListAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCategoryViewModel.getAllCategories();
    }

    private void setupListAdapter() {
        ListView categoryListView =  mCategoryListBinding.lvCategories;

        mListAdapter = new CategoryListAdapter(
                new ArrayList<CategoryData>(0),
                mCategoryViewModel
        );
        categoryListView.setAdapter(mListAdapter);
    }
}
