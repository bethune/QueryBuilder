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

package com.itfsw.query.builder.support.parser.arangodb;

import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.enums.EnumOperator;
import com.itfsw.query.builder.support.model.sql.Operation;
import com.itfsw.query.builder.support.parser.AbstractSqlRuleParser;
import com.itfsw.query.builder.support.parser.JsonRuleParser;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: tantrieuf31
 * @time:2020/08/25 20:57
 * ---------------------------------------------------------------------------
 */
public class BeginsWithRuleParser extends AbstractSqlRuleParser {
    public boolean canParse(IRule rule) {
        return EnumOperator.BEGINS_WITH.equals(rule.getOperator());
    }

    public Operation parse(IRule rule, JsonRuleParser parser) {
        return new Operation(new StringBuffer(rule.getField()).append(" LIKE \""), rule.getValue() + "%\"");
    }
}
