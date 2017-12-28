/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package architecturedemo.pkg.arun.retail.productslist;

import android.databinding.BindingAdapter;
import android.widget.ListView;

import java.util.List;

import architecturedemo.pkg.arun.retail.data.models.Product;

/**
 * Contains {@link BindingAdapter}s for the {@link Product} list.
 */
public class ProductsListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static void setItems(ListView listView, List<Product> items) {
        ProductsListAdapter adapter = (ProductsListAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.updateProductsList(items);
        }
    }
}
