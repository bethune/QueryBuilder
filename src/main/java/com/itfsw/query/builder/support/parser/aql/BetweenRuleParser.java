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

package com.itfsw.query.builder.support.parser.aql;

import java.util.List;

import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.aql.AqlOperation;
import com.itfsw.query.builder.support.model.enums.EnumOperator;
import com.itfsw.query.builder.support.parser.AbstractArangoQueryRuleParser;
import com.itfsw.query.builder.support.parser.JsonRuleParser;

/**
 * @author: tantrieuf31
 * @since: 2020/08/26 11:15
 */
public class BetweenRuleParser extends AbstractArangoQueryRuleParser {

	public boolean canParse(IRule rule) {
		return EnumOperator.BETWEEN.equals(rule.getOperator());
	}

	public AqlOperation parse(IRule rule, JsonRuleParser parser) {
		List<Object> values = (List<Object>) rule.getValue();
		if (values.size() == 2) {
			StringBuffer operand = new StringBuffer();
			operand.append(" d.").append(rule.getField()).append(" >= ").append(values.get(0));
			operand.append(" AND d.").append(rule.getField()).append(" <= ").append(values.get(1));
			return new AqlOperation(operand);
		}
		return new AqlOperation();
	}
}
