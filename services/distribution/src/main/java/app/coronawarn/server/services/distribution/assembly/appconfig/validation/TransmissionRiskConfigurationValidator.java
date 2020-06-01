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

package app.coronawarn.server.services.distribution.assembly.appconfig.validation;

import app.coronawarn.server.common.protocols.internal.RiskLevel;
import app.coronawarn.server.common.protocols.internal.TransmissionRiskConfiguration;
import app.coronawarn.server.common.protocols.internal.TransmissionRiskConfigurationValues;
import app.coronawarn.server.services.distribution.assembly.appconfig.validation.TransmissionRiskConfigurationValidationError.ErrorType;

public class TransmissionRiskConfigurationValidator extends ConfigurationValidator {

  private final TransmissionRiskConfiguration transmissionRiskConfiguration;


  public TransmissionRiskConfigurationValidator(TransmissionRiskConfiguration transmissionRiskConfiguration) {
    this.transmissionRiskConfiguration = transmissionRiskConfiguration;
  }

  @Override
  public ValidationResult validate() {
    errors = new ValidationResult();

    validateValues(this.transmissionRiskConfiguration.getTransmissionRisk());

    return errors;
  }

  private void validateValues(TransmissionRiskConfigurationValues transmissionRiskConfigurationValues) {
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey0());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey1());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey2());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey3());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey4());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey5());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey6());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey7());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey8());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey9());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey10());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey11());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey12());
    validateTransmissionRiskLevel(transmissionRiskConfigurationValues.getKey13());
  }

  private void validateTransmissionRiskLevel(RiskLevel transmissionRiskLevel) {
    if (!RiskScoreValidator.isInBounds(transmissionRiskLevel.getNumber())) {
      // TODO
      this.errors.add(new TransmissionRiskConfigurationValidationError(transmissionRiskLevel.name(),
          transmissionRiskLevel.getNumber(), ErrorType.RISK_LEVEL_OUT_OF_BOUND));
    }
  }

}
