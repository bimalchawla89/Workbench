package architecturedemo.pkg.arun.retail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import architecturedemo.pkg.arun.retail.productslist.ProductDetailFragment;
import architecturedemo.pkg.arun.retail.productslist.ProductDetailViewModel;
import architecturedemo.pkg.arun.retail.productslist.ProductViewModel;
import architecturedemo.pkg.arun.retail.util.ActivityUtils;

public class ProductsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static final String EXTRA_PRODUCT_ID = "PRODUCT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        setupToolbar();
        obtainViewModel(this);
        ProductDetailFragment productDetailFragment = findOrCreateViewFragment();

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                productDetailFragment, R.id.contentFrame);

        obtainViewModel(this);

    }

    @NonNull
    private ProductDetailFragment findOrCreateViewFragment() {
        // Get the requested task id
        String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);

        ProductDetailFragment productDetailFragment = (ProductDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (productDetailFragment == null) {
            productDetailFragment = ProductDetailFragment.newInstance(productId);
        }
        return productDetailFragment;
    }


    public static ProductDetailViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        ProductDetailViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(ProductDetailViewModel.class);

        return viewModel;
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.products_toolbar);

        setTitle(getString(R.string.product));


        //setting toolbar title
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
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
}
