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

package com.itfsw.query.builder;

import java.util.ArrayList;
import java.util.List;

import com.itfsw.query.builder.support.builder.ArangoDbBuilder;
import com.itfsw.query.builder.support.filter.ArangoDbInjectionAttackFilter;
import com.itfsw.query.builder.support.parser.AbstractArangoQueryRuleParser;
import com.itfsw.query.builder.support.parser.IRuleParser;
import com.itfsw.query.builder.support.parser.aql.BeginsWithRuleParser;
import com.itfsw.query.builder.support.parser.aql.BetweenRuleParser;
import com.itfsw.query.builder.support.parser.aql.ContainsRuleParser;
import com.itfsw.query.builder.support.parser.aql.DefaultGroupParser;
import com.itfsw.query.builder.support.parser.aql.EndsWithRuleParser;
import com.itfsw.query.builder.support.parser.aql.EqualRuleParser;
import com.itfsw.query.builder.support.parser.aql.GreaterOrEqualRuleParser;
import com.itfsw.query.builder.support.parser.aql.GreaterRuleParser;
import com.itfsw.query.builder.support.parser.aql.InRuleParser;
import com.itfsw.query.builder.support.parser.aql.IsEmptyRuleParser;
import com.itfsw.query.builder.support.parser.aql.IsNotEmptyRuleParser;
import com.itfsw.query.builder.support.parser.aql.IsNotNullRuleParser;
import com.itfsw.query.builder.support.parser.aql.IsNullRuleParser;
import com.itfsw.query.builder.support.parser.aql.LessOrEqualRuleParser;
import com.itfsw.query.builder.support.parser.aql.LessRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotBeginsWithRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotBetweenRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotContainsRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotEndsWithRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotEqualRuleParser;
import com.itfsw.query.builder.support.parser.aql.NotInRuleParser;


/**
 *
 * ArangoDb Query Builder Factory
 * 
 * @author: tantrieuf31
 * @since: 2020/08/26 11:15
 */
public class ArangoDbBuilderFactory extends AbstractQueryBuilderFactory {


    /**
     * Constructor
     * @param config
     */
    public ArangoDbBuilderFactory() {
    	super();
       

        // ------------------------ filter ---------------------------
        filters.add(new ArangoDbInjectionAttackFilter());  // filtering ArangoDb Query Injection

        // ------------------------- group parser ----------------------
        groupParser = new DefaultGroupParser();

        // ---------------------- rule parser ----------------------------
        ruleParsers.add(new EqualRuleParser());
        ruleParsers.add(new NotEqualRuleParser());
        ruleParsers.add(new InRuleParser());
        ruleParsers.add(new NotInRuleParser());
        ruleParsers.add(new LessRuleParser());
        ruleParsers.add(new LessOrEqualRuleParser());
        ruleParsers.add(new GreaterRuleParser());
        ruleParsers.add(new GreaterOrEqualRuleParser());
        ruleParsers.add(new BetweenRuleParser());
        ruleParsers.add(new NotBetweenRuleParser());
        ruleParsers.add(new BeginsWithRuleParser());
        ruleParsers.add(new NotBeginsWithRuleParser());
        ruleParsers.add(new ContainsRuleParser());
        ruleParsers.add(new NotContainsRuleParser());
        ruleParsers.add(new EndsWithRuleParser());
        ruleParsers.add(new NotEndsWithRuleParser());
        ruleParsers.add(new IsEmptyRuleParser());
        ruleParsers.add(new IsNotEmptyRuleParser());
        ruleParsers.add(new IsNullRuleParser());
        ruleParsers.add(new IsNotNullRuleParser());
    }

  

    /**
     * get builder
     * @return ArangoDbBuilder
     */
    public ArangoDbBuilder builder() {
        List<IRuleParser> sqlRuleParsers = new ArrayList<>();
        for (IRuleParser parser : ruleParsers) {
            if (parser instanceof AbstractArangoQueryRuleParser) {
                sqlRuleParsers.add(parser);
            }
        }
        return new ArangoDbBuilder(groupParser, sqlRuleParsers, filters);
    }
}
