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

package com.itfsw.query.builder.support.builder;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.itfsw.query.builder.ArangoDbBuilderFactory;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.model.result.ArangoDbQueryResult;

/**
 * ArangoDb Query Builder Unit Test
 *
 * @author: tantrieuf31
 * @time:2020/08/26 15:30
 * ---------------------------------------------------------------------------
 */
public class ArangoDbBuilderTest {
    private static ArangoDbBuilder builder;

    @BeforeClass
    public static void init() {
        ArangoDbBuilderFactory factory = new ArangoDbBuilderFactory();
        builder = factory.builder();
    }
    
    /**
     * complex-arangodb-query
     */
    @Test
    public void testComplexArangoQuery() throws IOException {
        String json = FileHelper.getStringFrom("tasks/complex-arangodb-query.json");
        ArangoDbQueryResult result = builder.build(json);

       System.out.println(result.getQuery());
       String expected = " d.rate == 22  OR d.category == 38  OR  ( d.coord == \"B.3\"  AND  ( d.coord == \"A.1\"  AND d.coord == \"C.5\" ) ) ";
       Assert.assertEquals(expected, result.getQuery());
    }

    /**
     * equal 
     */
    @Test
    public void testOperatorEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-equal.json");
        ArangoDbQueryResult result = builder.build(json);
        Assert.assertEquals(" d.username == \"Mistic\" ", result.getQuery());
    }

    /**
     * not equal 
     */
    @Test
    public void testOperatorNotEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-equal.json");
        ArangoDbQueryResult result = builder.build(json);
        
        Assert.assertEquals(" d.username != \"Mistic\" ", result.getQuery());
    }

    /**
     * in 
     */
    @Test
    public void testOperatorIn() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-in.json");
        ArangoDbQueryResult result = builder.build(json);
        Assert.assertEquals(" d.age IN [ 1, 5, 10 ] ", result.getQuery());
    }

    /**
     * not in 
     */
    @Test
    public void testOperatorNotIn() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-in.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.age NOT IN [ 1, 5, 10 ] ", result.getQuery());
    }

    /**
     * less 
     */
    @Test
    public void testOperatorLess() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-less.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.age < 50 ", result.getQuery());
    }

    /**
     * less or equal 
     */
    @Test
    public void testOperatorLessOrEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-less-or-equal.json");
        ArangoDbQueryResult result = builder.build(json);
        
        Assert.assertEquals(" d.age <= 50 ", result.getQuery());
    }

    /**
     * greater 
     */
    @Test
    public void testOperatorGreater() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-greater.json");
        ArangoDbQueryResult result = builder.build(json);
        
        Assert.assertEquals(" d.age > 50", result.getQuery());
    }

    /**
     * greater or equal 
     */
    @Test
    public void testOperatorGreaterOrEqual() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-greater-or-equal.json");
        ArangoDbQueryResult result = builder.build(json);
        
        Assert.assertEquals(" d.age >= 50 ", result.getQuery());
    }

    /**
     * equal 
     */
    @Test
    public void testOperatorBetween() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-between.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.age >= 5 AND d.age <= 10", result.getQuery());
    }

    /**
     * not between 
     */
    @Test
    public void testOperatorNotBetween() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-between.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.age < 5 OR d.age > 10 ", result.getQuery());
    }

    /**
     * begins with 
     */
    @Test
    public void testOperatorBeginsWith() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-begins-with.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username LIKE \"Mistic%\" ", result.getQuery());
    }

    /**
     * not begins with 
     */
    @Test
    public void testOperatorNotBeginsWith() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-begins-with.json");
        ArangoDbQueryResult result = builder.build(json);

        
        Assert.assertEquals(" d.username NOT LIKE \"Mistic%\" ", result.getQuery());
    }

    /**
     * contains 
     */
    @Test
    public void testOperatorContains() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-contains.json");
        ArangoDbQueryResult result = builder.build(json);

    
        Assert.assertEquals(" d.username LIKE \"%Mistic%\" ", result.getQuery());
    }

    /**
     * not contains 
     */
    @Test
    public void testOperatorNotContains() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-contains.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username NOT LIKE \"%Mistic%\" ", result.getQuery());
    }

    /**
     * ends with 
     */
    @Test
    public void testOperatorEndsWith() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-ends-with.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username LIKE \"%Mistic\" ", result.getQuery());
    }

    /**
     * not ends with 
     */
    @Test
    public void testOperatorNotEndsWith() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-not-ends-with.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username NOT LIKE \"%Mistic\" ", result.getQuery());
    }

    /**
     * is empty 
     */
    @Test
    public void testOperatorIsEmpty() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-is-empty.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username == '' ", result.getQuery());
    }

    /**
     * is not empty 
     */
    @Test
    public void testOperatorIsNotEmpty() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-is-not-empty.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username != '' ", result.getQuery());
    }

    /**
     * is null 
     */
    @Test
    public void testOperatorIsNull() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-is-null.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username == null", result.getQuery());
    }

    /**
     * is not null 
     */
    @Test
    public void testOperatorIsNotNull() throws IOException {
        String json = FileHelper.getStringFrom("tasks/operator-is-not-null.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username != null ", result.getQuery());
    }

    /**
     * and 
     */
    @Test
    public void testConditionAnd() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-and-1.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username == \"Mistic\" ", result.getQuery());

        json = FileHelper.getStringFrom("tasks/condition-and-more.json");
        result = builder.build(json);
        
        Assert.assertEquals(" d.username == \"Mistic\"  AND d.age == 10 ", result.getQuery());
    }

    /**
     * or 
     */
    @Test
    public void testConditionOr() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-or-1.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals(" d.username == \"Mistic\" ", result.getQuery());

        json = FileHelper.getStringFrom("tasks/condition-or-more.json");
        result = builder.build(json);

      
        Assert.assertEquals(" d.username == \"Mistic\"  OR d.age == 10 ", result.getQuery());
    }

    /**
     * not 
     */
    @Test
    public void testConditionNot() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-not-and-1.json");
        ArangoDbQueryResult result = builder.build(json);

        Assert.assertEquals("NOT  ( d.username == \"Mistic\" ) ", result.getQuery());

        json = FileHelper.getStringFrom("tasks/condition-not-and-more.json");
        result = builder.build(json);

        Assert.assertEquals("NOT  ( d.username == \"Mistic\"  AND d.age == 10 ) ", result.getQuery());
    }
}