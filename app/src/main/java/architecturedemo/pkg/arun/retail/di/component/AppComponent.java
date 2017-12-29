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

package architecturedemo.pkg.arun.retail.di.component;

import android.content.Context;

import javax.inject.Singleton;

import architecturedemo.pkg.arun.retail.data.source.remote.RemoteServices;
import architecturedemo.pkg.arun.retail.data.source.remote.RemoteSource;
import architecturedemo.pkg.arun.retail.di.module.ApiModule;
import architecturedemo.pkg.arun.retail.di.module.ApplicationModule;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {
    RemoteServices remoteServices();

//    Context appContext();

    void inject(RemoteSource remoteSource);
}