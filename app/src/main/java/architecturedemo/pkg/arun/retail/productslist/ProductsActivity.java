package architecturedemo.pkg.arun.retail.productslist;

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

import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.ViewModelFactory;
import architecturedemo.pkg.arun.retail.data.models.ProductData;
import architecturedemo.pkg.arun.retail.databinding.ActivityProductsBinding;
import architecturedemo.pkg.arun.retail.productdetails.ProductDetailActivity;
import architecturedemo.pkg.arun.retail.productslist.ProductViewModel;
import architecturedemo.pkg.arun.retail.productslist.ProductsListAdapter;

public class ProductsActivity extends AppCompatActivity {


    public static final String EXTRA_PRODUCT_ID = "PRODUCT_ID";
    private ProductViewModel mProductViewModel;
    private ProductsListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductsBinding activitySubCategoriesBinding = DataBindingUtil.setContentView(this, R.layout.activity_products);
        mProductViewModel = obtainViewModel(this);
        mProductViewModel.getAllProducts(getIntent().getStringExtra(EXTRA_PRODUCT_ID));

        activitySubCategoriesBinding.setModel(mProductViewModel);
        activitySubCategoriesBinding.executePendingBindings();

        setupToolbar();
        // Subscribe to "add to cart" event
        mProductViewModel.getOpenProductEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String categoryId) {
                if (categoryId != null) {
                    openProductDetails(categoryId);
                }
            }
        });
        setupListAdapter(activitySubCategoriesBinding);
    }

    private void openProductDetails(String categoryId) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, categoryId);
        startActivity(intent);
    }

    public static ProductViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(ProductViewModel.class);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.products_toolbar);

        setTitle(getString(R.string.product));


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

    private void setupListAdapter(ActivityProductsBinding activityProductsBinding) {
        ListView categoryListView =  activityProductsBinding.lvProducts;

        mListAdapter = new ProductsListAdapter(
                new ArrayList<ProductData>(0),
                mProductViewModel
        );
        categoryListView.setAdapter(mListAdapter);
    }
}
