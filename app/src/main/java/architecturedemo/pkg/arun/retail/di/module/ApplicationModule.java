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
 *
 */

package architecturedemo.pkg.arun.retail.di.module;

import android.content.Context;

import javax.inject.Singleton;

import architecturedemo.pkg.arun.retail.MyApplication;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private static final String TAG = ApplicationModule.class.getSimpleName();
    private final MyApplication mApp;

    public ApplicationModule(MyApplication app) {
        mApp = app;
    }

//    @Provides
//    @Singleton
//    public Context appContext() {
//        return mApp;
//    }
}