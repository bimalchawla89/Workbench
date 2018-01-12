package arch.wb.retail.categorieslist;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import arch.wb.retail.data.models.CategoryData;

public class CategoriesListBindings {

    private static final String TAG = CategoriesListBindings.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:categories")
    public static void setCategories(ListView listView, List<CategoryData> items) {
        CategoryListAdapter adapter = (CategoryListAdapter) listView.getAdapter();
        if (adapter != null && null != items) {
            adapter.updateCategoriesList(items);
        }
    }
}
