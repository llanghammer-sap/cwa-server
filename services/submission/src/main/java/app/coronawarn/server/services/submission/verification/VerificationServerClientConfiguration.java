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
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
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
  public Client feignClient() {
    return new Client.Default(getSslSocketFactory(), new NoopHostnameVerifier());
  }

  private SSLSocketFactory getSslSocketFactory() {
    try {
      String keyStorePath = environment.getProperty("client.ssl.key-store");
      String keyStorePassword = environment.getProperty("client.ssl.key-store-password");
      String keyPassword = environment.getProperty("client.ssl.key-password");

      String trustStorePath = environment.getProperty("client.ssl.trust-store");
      String trustStorePassword = environment.getProperty("client.ssl.trust-store-password");

      SSLContext sslContext = SSLContextBuilder
          .create()
          .loadKeyMaterial(ResourceUtils.getFile(keyStorePath), keyStorePassword.toCharArray(),
              keyPassword.toCharArray())
          .loadTrustMaterial(ResourceUtils.getFile(trustStorePath), trustStorePassword.toCharArray())
          .build();
      return sslContext.getSocketFactory();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
}