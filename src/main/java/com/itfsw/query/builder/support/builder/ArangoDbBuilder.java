package com.itfsw.query.builder.support.builder;


import java.io.IOException;
import java.util.List;

import com.itfsw.query.builder.exception.ParserNotFoundException;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.model.aql.AqlOperation;
import com.itfsw.query.builder.support.model.enums.EnumBuilderType;
import com.itfsw.query.builder.support.model.result.ArangoDbQueryResult;
import com.itfsw.query.builder.support.parser.IGroupParser;
import com.itfsw.query.builder.support.parser.IRuleParser;

/**
 * 
 * ArangoDb Query Builder
 * 
 * @author: tantrieuf31
 * @since: 2020/08/26 11:15
 * 
 */
public class ArangoDbBuilder extends AbstractBuilder {

    /**
     * Constructor
     * @param groupParser
     * @param ruleParsers
     * @param ruleFilters
     */
    public ArangoDbBuilder(IGroupParser groupParser, List<IRuleParser> ruleParsers, List<IRuleFilter> ruleFilters) {
        super(groupParser, ruleParsers, ruleFilters);
    }

    /**
     * build query and get result
     * @param query
     * @return
     * @throws IOException
     * @throws ParserNotFoundException
     */
    @Override
    public ArangoDbQueryResult build(String query) throws IOException, ParserNotFoundException {
        AqlOperation result = (AqlOperation) super.parse(query);

        // aql
        StringBuffer aql = new StringBuffer(result.getOperate());
        aql.delete(aql.length() - 2, aql.length());
        aql.delete(0, 2);

        return new ArangoDbQueryResult(query, aql.toString());
    }

    /**
     * get builder type
     * @return
     */
    @Override
    protected EnumBuilderType getBuilderType() {
        return EnumBuilderType.ARANGODB;
    }
}

