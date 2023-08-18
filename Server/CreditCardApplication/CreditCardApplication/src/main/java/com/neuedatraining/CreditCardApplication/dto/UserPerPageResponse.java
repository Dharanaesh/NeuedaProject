package com.neuedatraining.CreditCardApplication.dto;


import com.neuedatraining.CreditCardApplication.entity.CreditCardUser;

import java.util.List;

public class UserPerPageResponse {

    int totalPages;
    long totalElements;
    int noofelements ;
    int pagesize ;
    List<CreditCardUser> users;

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

    public List<CreditCardUser> getUsers() {
        return users;
    }

    public void setUsers(List<CreditCardUser> users) {
        this.users = users;
    }
}
