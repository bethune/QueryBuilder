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

package com.itfsw.query.builder.support.model.aql;

/**
 * ---------------------------------------------------------------------------
 * ArangoDB Data Operator
 * ---------------------------------------------------------------------------
 * @author: tantrieuf31
 * @time:2020/08/26 11:15
 * ---------------------------------------------------------------------------
 */
public class AqlOperation {
    private StringBuffer operate;
    
    /**
     * Constructor
     * @param operate
     * @param value
     */
    public AqlOperation(StringBuffer operate) {
        this.operate = operate;
    }
    
    /**
     * Constructor
     * @param operate
     * @param value
     */
    public AqlOperation() {
        this.operate = new StringBuffer("");
    }

    /**
     * Getter method for property <tt>operate</tt>.
     * @return property value of operate
     * @author tantrieuf31
     */
    public StringBuffer getOperate() {
        return operate;
    }

    /**
     * Setter method for property <tt>operate</tt>.
     * @param operate value to be assigned to property operate
     * @author tantrieuf31
     */
    public void setOperate(StringBuffer operate) {
        this.operate = operate;
    }
}
