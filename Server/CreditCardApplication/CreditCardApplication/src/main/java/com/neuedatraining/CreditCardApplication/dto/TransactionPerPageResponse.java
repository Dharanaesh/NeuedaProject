package com.neuedatraining.CreditCardApplication.dto;

import com.neuedatraining.CreditCardApplication.entity.Transactions;

import java.util.List;

public class TransactionPerPageResponse {

    int totalPages;
    long totalElements;
    int noofelements ;
    int pagesize ;
    List<Transactions> users;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getNoofelements() {
        return noofelements;
    }

    public void setNoofelements(int noofelements) {
        this.noofelements = noofelements;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<Transactions> getUsers() {
        return users;
    }

    public void setUsers(List<Transactions> users) {
        this.users = users;
    }
}