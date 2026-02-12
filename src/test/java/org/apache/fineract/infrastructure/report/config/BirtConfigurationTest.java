/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.fineract.infrastructure.report.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.birt.report.engine.api.IReportEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * Test to verify BIRT Engine initialization. This ensures dependencies are found and
 * Platform.startup() works.
 */
@SpringBootTest(classes = BirtConfiguration.class)
class BirtConfigurationTest {

  private final ApplicationContext applicationContext;
  private final IReportEngine reportEngine;

  @Autowired
  BirtConfigurationTest(
      ApplicationContext applicationContext,
      @Autowired(required = false) IReportEngine reportEngine) {
    this.applicationContext = applicationContext;
    this.reportEngine = reportEngine;
  }

  @Test
  void contextLoads() {
    // Verify Spring Context loaded our configuration
    assertNotNull(applicationContext, "Spring Application Context should not be null");
  }

  @Test
  void birtEngineStartsSuccessfully() {
    // Verify the BIRT Engine Bean exists
    assertNotNull(reportEngine, "BIRT Report Engine bean should be created by BirtConfiguration");

    // Verify it's actually running (checking the version)
    String version = reportEngine.getVersion();
    System.out.println("--- VALIDATION SUCCESS: BIRT Engine Version: " + version + " ---");

    assertNotNull(version, "BIRT Engine should have a valid version string");
    // We can assert specific version too, if requried
  }
}
