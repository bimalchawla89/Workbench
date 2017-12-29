/*
 * Copyright (C) 2015 The Android Open Source Project
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

package architecturedemo.pkg.arun.retail.util;

import android.content.Context;
import android.support.annotation.NonNull;

import architecturedemo.pkg.arun.retail.data.source.ProductsRepository;
import architecturedemo.pkg.arun.retail.data.source.local.LocalSource;
import architecturedemo.pkg.arun.retail.data.source.local.ProductsDatabase;
import architecturedemo.pkg.arun.retail.data.source.remote.RemoteSource;

/**
 * Enables injection of mock implementations for
 * {@link architecturedemo.pkg.arun.retail.data.source.ProductsDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static ProductsRepository provideProductsRepository(@NonNull Context context) {
        ProductsDatabase database = ProductsDatabase.getInstance(context);
        return ProductsRepository.getInstance(LocalSource.getInstance(new AppExecutors(),
                database.productsDao()),
                RemoteSource.getInstance(context, new AppExecutors()));
    }
}
