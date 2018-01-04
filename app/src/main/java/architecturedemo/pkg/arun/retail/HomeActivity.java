package architecturedemo.pkg.arun.retail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import architecturedemo.pkg.arun.retail.categorieslist.CategoriesFragment;
import architecturedemo.pkg.arun.retail.categorieslist.CategoryViewModel;
import architecturedemo.pkg.arun.retail.fragments.CartFragment;
import architecturedemo.pkg.arun.retail.fragments.LogoutFragment;
import architecturedemo.pkg.arun.retail.fragments.ProfileFragment;
import architecturedemo.pkg.arun.retail.fragments.PushFragment;
import architecturedemo.pkg.arun.retail.subcategorieslist.SubCategoriesActivity;
import architecturedemo.pkg.arun.retail.util.ActivityUtils;

public class HomeActivity extends AppCompatActivity implements CartFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        PushFragment.OnFragmentInteractionListener, LogoutFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private String[] mMenuItems;
    private Toolbar toolbar;
    private CategoryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();

        setupNavigationDrawer();

        obtainViewModel(this);

        mViewModel = obtainViewModel(this);

        // Subscribe to "open task" event
        mViewModel.getOpenCategoryEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String categoryId) {
                if (categoryId != null) {
                    openSubCategory(categoryId);
                }
            }
        });

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void openSubCategory(String categoryId) {
        Intent intent = new Intent(this, SubCategoriesActivity.class);
        intent.putExtra(SubCategoriesActivity.EXTRA_CATEGORY_ID, categoryId);
        startActivity(intent);

    }

    public static CategoryViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        CategoryViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(CategoryViewModel.class);

        return viewModel;
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = getTitle();

        //setting toolbar title
        setSupportActionBar(toolbar);
    }

    private void setupNavigationDrawer() {
        mMenuItems = getResources().getStringArray(R.array.menu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {
            // update the main content by replacing fragments
            case 0:
                fragment = new CategoriesFragment();
                break;
            case 1:
                fragment = new ProfileFragment();
                break;
            case 2:
                fragment = new CartFragment();
                break;
            case 3:
                fragment = new PushFragment();
                break;
            case 4:
                fragment = new LogoutFragment();
                break;
        }

        args.putInt("param1", position);
        args.putInt("param2", position);
        fragment.setArguments(args);

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), fragment, R.id.content_frame);

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuItems[position]);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            return;
        }
        finish();
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
}
