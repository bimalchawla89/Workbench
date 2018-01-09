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

package arch.wb.retail.di.component;

import javax.inject.Singleton;

import arch.wb.retail.data.source.remote.RemoteServices;
import arch.wb.retail.data.source.remote.RemoteSource;
import arch.wb.retail.di.module.ApiModule;
import arch.wb.retail.di.module.ApplicationModule;
import arch.wb.retail.login.LoginActivity;
import arch.wb.retail.util.LoginUtil;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {
    RemoteServices remoteServices();

    LoginUtil LoginUtil();


    void inject(LoginActivity loginActivity);

    void inject(RemoteSource remoteSource);

    void inject(LoginUtil loginUtil);
}