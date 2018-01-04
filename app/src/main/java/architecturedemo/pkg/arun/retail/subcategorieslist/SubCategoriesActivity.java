package architecturedemo.pkg.arun.retail.subcategorieslist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import architecturedemo.pkg.arun.retail.productslist.ProductsActivity;
import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.ViewModelFactory;
import architecturedemo.pkg.arun.retail.data.models.SubCategoryData;
import architecturedemo.pkg.arun.retail.databinding.ActivitySubCategoriesBinding;

public class SubCategoriesActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_ID = "CATEGORY_ID";
    private SubCategoryViewModel mSubCategoryViewModel;
    private SubCategoriesListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySubCategoriesBinding activitySubCategoriesBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub_categories);
        mSubCategoryViewModel = obtainViewModel(this);
        mSubCategoryViewModel.getAllSubCategories(getIntent().getStringExtra(EXTRA_CATEGORY_ID));

        activitySubCategoriesBinding.setModel(mSubCategoryViewModel);
        activitySubCategoriesBinding.executePendingBindings();

        setupToolbar();
        // Subscribe to "add to cart" event
        mSubCategoryViewModel.getOpenSubCategoryEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String categoryId) {
                if (categoryId != null) {
                    openSubCategory(categoryId);
                }
            }
        });
        setupListAdapter(activitySubCategoriesBinding);
    }

    private void openSubCategory(String categoryId) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra(ProductsActivity.EXTRA_PRODUCT_ID, categoryId);
        startActivity(intent);
    }

    public static SubCategoryViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(SubCategoryViewModel.class);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.sub_categories_toolbar);

        setTitle(getString(R.string.sub_category));


        //setting toolbar title
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupListAdapter(ActivitySubCategoriesBinding activitySubCategoriesBinding) {
        ListView categoryListView =  activitySubCategoriesBinding.lvSubCategories;

        mListAdapter = new SubCategoriesListAdapter(
                new ArrayList<SubCategoryData>(0),
                mSubCategoryViewModel
        );
        categoryListView.setAdapter(mListAdapter);
    }
}
