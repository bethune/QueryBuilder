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

package com.itfsw.query.builder.support.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.regex.Pattern;

import com.itfsw.query.builder.exception.FilterException;
import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.JsonRule;
import com.itfsw.query.builder.support.model.enums.EnumBuilderType;

/**
 * ---------------------------------------------------------------------------
 * ArangoDB Query Language Injection Attack Filter
 * ---------------------------------------------------------------------------
 * @author: tantrieuf31
 * @time: 2020/08/26 11:15
 * ---------------------------------------------------------------------------
 */
public class ArangoDbInjectionAttackFilter implements IRuleFilter {
    private static final int MAX_FIELD_LENGTH = 64;
	private HashSet<String> keywords = new HashSet<>();
    private char beginningDelimiter;
    private char endingDelimiter;

    /**
     * Constructor
     * @param dbType
     */
    public ArangoDbInjectionAttackFilter() {
        this.beginningDelimiter = '`';
        this.endingDelimiter = '`';
        String file = "keywords-arangodb.txt";

        try (InputStream inputStream = ArangoDbInjectionAttackFilter.class.getClassLoader().getResourceAsStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                keywords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(JsonRule jsonRule, EnumBuilderType type) throws FilterException {
        if (!jsonRule.isGroup()) {
            IRule rule = jsonRule.toRule();
            String field = rule.getField();
            if ( field.length() > MAX_FIELD_LENGTH) {
                // field too long, AQL's max length is 64
                throw new FilterException("rule's field is too long for:" + jsonRule);
            }
            if (!Pattern.matches("^[A-Za-z0-9_]+$", field)) {
                // can not use Special word
                throw new FilterException("rule's field can only use [A-Za-z0-9_] for:" + jsonRule);
            }
            if (keywords.contains(field.toUpperCase())) {
                // keywords in AQL at https://www.arangodb.com/docs/stable/aql/fundamentals-syntax.html#keywords
                StringBuffer sb = new StringBuffer(field);
                sb.insert(0, beginningDelimiter);
                sb.append(endingDelimiter);
                rule.setField(sb.toString());
            }
        }
    }
}
