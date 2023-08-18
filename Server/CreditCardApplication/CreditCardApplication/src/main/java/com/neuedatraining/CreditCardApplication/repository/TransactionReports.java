package com.neuedatraining.CreditCardApplication.repository;

import com.neuedatraining.CreditCardApplication.dto.CategorySpend;
import com.neuedatraining.CreditCardApplication.dto.GenderCategorySpend;
import com.neuedatraining.CreditCardApplication.dto.Merchantspend;
import com.neuedatraining.CreditCardApplication.dto.StateSales;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;


import java.util.List;


import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
public class TransactionReports implements TransactionReportsRepo{
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public List<StateSales> getTotalTransactionsByState(){
        GroupOperation groupByStateTransactions = group("state").sum("amt").as("sales");
        MatchOperation allStates = match(new Criteria("state").exists(true));
        ProjectionOperation includes = project("sales").and("state").previousOperation();
        SortOperation sortBySalesDesc = sort(Sort.by(Sort.Direction.DESC,"sales"));
        Aggregation stateAggregation = newAggregation(allStates, groupByStateTransactions, sortBySalesDesc, includes);
        AggregationResults<StateSales> groupResults = mongoTemplate.aggregate(stateAggregation, "transaction", StateSales.class);
        List<StateSales> result = groupResults.getMappedResults();
        return result;
    }



    @Override
    public List<CategorySpend> getCategorySpendByState(String state) {
        GroupOperation groupByStateCategoryASpend = group("category").sum("amt").as("spend");
        MatchOperation stateIs = match(new Criteria("state").is(state));
        ProjectionOperation includes = project("spend").and("category").previousOperation();
        SortOperation sortBySpendDesc = sort(Sort.by(Sort.Direction.DESC,"spend"));
        Aggregation stateAggregation = newAggregation(stateIs, groupByStateCategoryASpend, sortBySpendDesc, includes);
        AggregationResults<CategorySpend> groupResults = mongoTemplate.aggregate(stateAggregation, "transaction", CategorySpend.class);
        List<CategorySpend> result = groupResults.getMappedResults();
        return result;
    }

    @Override
    public List<Merchantspend> getCategorySpendByMerchant(){
        GroupOperation groupByStateTransactions = group("merchant").sum("amt").as("sales");
        MatchOperation allMerchants = match(new Criteria("merchant").exists(true));
        ProjectionOperation includes = project("sales").and("merchant").previousOperation();
        SortOperation sortBySalesDesc = sort(Sort.by(Sort.Direction.DESC,"sales"));
        //LimitOperation limitToTop10 = limit(10);
        SampleOperation randomSample = Aggregation.sample(10);
        Aggregation MerchantAggregation = newAggregation(allMerchants, groupByStateTransactions, sortBySalesDesc, includes,randomSample);
        AggregationResults<Merchantspend> groupResults = mongoTemplate.aggregate(MerchantAggregation, "transaction", Merchantspend.class);
        List<Merchantspend> result = groupResults.getMappedResults();
        return result;
    }
    @Override
    public List<GenderCategorySpend> getCategorySpendByGender(String gender) {
        GroupOperation groupByCategorySpend = group("category").sum("amt").as("spend");
        MatchOperation genderIs = match(new Criteria("gender").is(gender));
        ProjectionOperation includes = project("spend").and("category").previousOperation();
        SortOperation sortBySpendDesc = sort(Sort.by(Sort.Direction.DESC,"spend"));
        Aggregation GenderAggregation = newAggregation(genderIs, groupByCategorySpend, sortBySpendDesc, includes);
        AggregationResults<GenderCategorySpend> groupResults = mongoTemplate.aggregate(GenderAggregation, "transaction", GenderCategorySpend.class);
        List<GenderCategorySpend> result = groupResults.getMappedResults();
        return result;
    }


}