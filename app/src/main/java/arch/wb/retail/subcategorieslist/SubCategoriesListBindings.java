package arch.wb.retail.subcategorieslist;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import arch.wb.retail.data.models.SubCategoryData;

/**
 * Created by apple on 04/01/18.
 */

public class SubCategoriesListBindings {
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:subCategoryDataList")
    public static void setSubCategories(ListView listView, List<SubCategoryData> items) {
        SubCategoriesListAdapter adapter = (SubCategoriesListAdapter) listView.getAdapter();
        if (adapter != null && null != items) {
            adapter.updateSubCategoriesList(items);
        }
    }
}