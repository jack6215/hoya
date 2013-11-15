/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hoya.yarn.appmaster.rpc;

import org.apache.hadoop.hoya.HoyaXmlConfKeys;
import org.apache.hadoop.security.authorize.PolicyProvider;
import org.apache.hadoop.security.authorize.Service;

/**
 * {@link PolicyProvider} for Hoya protocols.
 */

public class HoyaAMPolicyProvider extends PolicyProvider {
  
  private static final Service[] services = 
      new Service[] {
    new Service(HoyaXmlConfKeys.KEY_HOYA_ACL, HoyaClusterProtocolPB.class)
  };

  @SuppressWarnings("ReturnOfCollectionOrArrayField")
  @Override
  public Service[] getServices() {
    return services;
  }

}
