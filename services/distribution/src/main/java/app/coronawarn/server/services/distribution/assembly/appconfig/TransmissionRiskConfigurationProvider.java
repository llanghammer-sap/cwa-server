/*
 * Corona-Warn-App
 *
 * SAP SE and all other contributors /
 * copyright owners license this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package app.coronawarn.server.services.distribution.assembly.appconfig;

import app.coronawarn.server.common.protocols.internal.TransmissionRiskConfiguration;

public class TransmissionRiskConfigurationProvider {

  private TransmissionRiskConfigurationProvider() {
  }

  public static final String MASTER_FILE = "master-config/transmission-risk.yaml";

  /**
   * Fetches the master configuration as a {@link TransmissionRiskConfigurationProvider} instance.
   *
   * @return the transmission risks as {@link TransmissionRiskConfigurationProvider}
   * @throws UnableToLoadFileException when the file/transformation did not succeed
   */
  public static TransmissionRiskConfiguration readMasterFile() throws UnableToLoadFileException {
    return readFile(MASTER_FILE);
  }

  /**
   * Fetches a risk score classification file based on the given path. The path must be available in the classloader.
   *
   * @param path The path, e.g. folder/my-transmission-risk-configuration.yaml
   * @return the TransmissionRiskConfiguration
   * @throws UnableToLoadFileException when the file access/transformation did not succeed
   */
  public static TransmissionRiskConfiguration readFile(String path) throws UnableToLoadFileException {
    return YamlLoader.loadYamlIntoProtobufBuilder(path, TransmissionRiskConfiguration.Builder.class).build();
  }

}
