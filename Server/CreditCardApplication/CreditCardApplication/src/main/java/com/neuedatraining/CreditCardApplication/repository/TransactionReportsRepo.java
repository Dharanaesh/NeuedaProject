package com.neuedatraining.CreditCardApplication.repository;


import com.neuedatraining.CreditCardApplication.dto.CategorySpend;
import com.neuedatraining.CreditCardApplication.dto.GenderCategorySpend;
import com.neuedatraining.CreditCardApplication.dto.Merchantspend;
import com.neuedatraining.CreditCardApplication.dto.StateSales;

import java.util.List;

public interface TransactionReportsRepo {
    List<StateSales> getTotalTransactionsByState();
    List<CategorySpend> getCategorySpendByState(String state);
    List<GenderCategorySpend> getCategorySpendByGender(String gender);
    List<Merchantspend> getCategorySpendByMerchant();
}