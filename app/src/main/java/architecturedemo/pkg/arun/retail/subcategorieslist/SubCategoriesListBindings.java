package architecturedemo.pkg.arun.retail.subcategorieslist;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.SubcategoryData;

/**
 * Created by apple on 04/01/18.
 */

public class SubCategoriesListBindings {
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:subcategoryDataList")
    public static void setSubCategories(ListView listView, List<SubcategoryData> items) {
        SubCategoriesListAdapter adapter = (SubCategoriesListAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.updateSubCategoriesList(items);
        }
    }
}