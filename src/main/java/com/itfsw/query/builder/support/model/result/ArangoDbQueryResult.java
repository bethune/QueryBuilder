/*
 * Copyright (c) 2020.
 *
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
 */

package com.itfsw.query.builder.support.model.result;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: tantrieuf31
 * @time:2020/08/26 11:15
 * ---------------------------------------------------------------------------
 */
public class ArangoDbQueryResult extends AbstractResult {
    private String query;
    /**
     * Constructor
     * @param queryJson
     * @param query
     */
    public ArangoDbQueryResult(String queryJson, String query) {
        this.queryJson = queryJson;
        this.query = query;
    }

    /**
     * Getter method for property <tt>query</tt>.
     * @return property value of query
     * @author hewei
     */
    @Override
    public String getQuery() {
        return query;
    }
  
    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return getQuery();
    }
}
