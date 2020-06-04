/*
 * ---license-start
 * Corona-Warn-App
 * ---
 * Copyright (C) 2020 SAP SE and all other contributors
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 */

package app.coronawarn.server.services.submission.verification;

import feign.Client;
import feign.Feign;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;


@Configuration
public class VerificationServerClientConfiguration {

  @Autowired
  Environment environment;

  @Bean
  public Feign.Builder feignBuilder() {
    return Feign.builder().client(new Client.Default(getSslSocketFactory(), null));
  }

  SSLSocketFactory getSslSocketFactory() {
    String password = environment.getProperty("client.ssl.key-store-password");
    SSLContext sslContext = null;
    try {
      sslContext = SSLContextBuilder
          .create()
          .setKeyStoreType(environment.getProperty("client.ssl.key-store-type"))
          .loadKeyMaterial(ResourceUtils.getFile(environment.getProperty("key-store")), password.toCharArray(),
              password.toCharArray())
          .build();
    } catch (Exception e) {
      System.out.println(e);
    }
    return sslContext.getSocketFactory();
  }
}