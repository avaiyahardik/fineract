/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.core.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.springframework.util.StringUtils;

/**
 * Filter that returns a response with headers that allows for Cross-Origin Requests (CORs) to be performed against the
 * platform API.
 */

@Provider
public class ResponseCorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext request, final ContainerResponseContext response) {

        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        // .header("Access-Control-Expose-Headers",
        // "Fineract-Platform-TenantId")
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        final String reqHead = request.getHeaders().getFirst("Access-Control-Request-Headers");

        if (null != reqHead && StringUtils.hasText(reqHead)) {
            response.getHeaders().add("Access-Control-Allow-Headers", reqHead);
        }
    }
}
