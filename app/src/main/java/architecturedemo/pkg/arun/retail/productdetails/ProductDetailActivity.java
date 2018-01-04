package architecturedemo.pkg.arun.retail.productdetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import architecturedemo.pkg.arun.retail.R;
import architecturedemo.pkg.arun.retail.ViewModelFactory;
import architecturedemo.pkg.arun.retail.databinding.ActivityProductDetailBinding;
import architecturedemo.pkg.arun.retail.productdetails.AddToCartActionListener;
import architecturedemo.pkg.arun.retail.productdetails.ProductDetailViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_ID = "PRODUCT_ID";
    private ProductDetailViewModel mDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProductDetailBinding activityProductsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        mDetailViewModel = obtainViewModel(this);
        mDetailViewModel.getProductData(getIntent().getStringExtra(EXTRA_PRODUCT_ID));
        activityProductsBinding.setModel(mDetailViewModel);


        AddToCartActionListener addToCartActionListener = new AddToCartActionListener() {
            @Override
            public void onAddToCartClicked() {
                mDetailViewModel.addToCart();
            }
        };
        activityProductsBinding.setListener(addToCartActionListener);
        activityProductsBinding.executePendingBindings();

        setupToolbar();
        // Subscribe to "add to cart" event
        mDetailViewModel.addToCartEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                addToCart();
            }
        });

    }

    public void addToCart() {
        mDetailViewModel.addedToCart.set(true);
        Toast.makeText(this, " added to cart  " + mDetailViewModel.addedToCart, Toast.LENGTH_SHORT).show();

    }

    public static ProductDetailViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(ProductDetailViewModel.class);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.products_detail_toolbar);

        setTitle(getString(R.string.product_detail));

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
}
