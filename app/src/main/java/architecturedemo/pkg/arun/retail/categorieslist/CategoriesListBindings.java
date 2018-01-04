package architecturedemo.pkg.arun.retail.categorieslist;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.CategoryData;

public class CategoriesListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:categories")
    public static void setCategories(ListView listView, List<CategoryData> items) {
        CategoryListAdapter adapter = (CategoryListAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.updateCategoriesList(items);
        }
    }
}
