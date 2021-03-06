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

package arch.wb.retail.di.module;

import javax.inject.Singleton;

import arch.wb.network.NetworkInit;
import arch.wb.retail.MyApplication;
import arch.wb.retail.data.source.remote.RemoteServices;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    private static final String TAG = ApiModule.class.getSimpleName();
    private static final String BASE_URL = "https://adventure-works-search2.search.windows.net";
    private MyApplication app;

    public ApiModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public RemoteServices remoteServices() {
        NetworkInit networkInit = NetworkInit.getInstance(app, BASE_URL, RemoteServices.class);
        return (RemoteServices) networkInit.initNetworkLib();
    }
}
