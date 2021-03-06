/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hoya.yarn.appmaster.web.view;

import com.google.inject.Inject;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.hadoop.yarn.webapp.view.HtmlBlock;
import org.apache.hoya.yarn.appmaster.state.AppState;
import org.apache.hoya.yarn.appmaster.web.WebAppApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class ClusterSpecificationBlock extends HtmlBlock {
  private static final Logger log = LoggerFactory.getLogger(ClusterSpecificationBlock.class);

  private AppState appState;

  @Inject
  public ClusterSpecificationBlock(WebAppApi hoya) {
    this.appState = hoya.getAppState();
  }

  @Override
  protected void render(Block html) {
    doRender(html);
  }

  // An extra method to make testing easier since you can't make an instance of Block
  protected void doRender(Hamlet html) {
    html.
      div("cluster_json").
        h2("JSON Cluster Specification").
        pre().
          _(getJson())._()._();
  }
  
  /**
   * Get the JSON, catching any exceptions and returning error text instead
   * @return
   */
  private String getJson() {
    try {
      return appState.clusterDescription.toJsonString();
    } catch (Exception e) {
      log.error("Could not create JSON from cluster description", e);
      return "Could not create JSON. See logs for more details.";
    }
  }

}
