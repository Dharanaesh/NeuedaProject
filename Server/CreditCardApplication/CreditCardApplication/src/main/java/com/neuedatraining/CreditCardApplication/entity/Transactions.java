package com.neuedatraining.CreditCardApplication.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection ="transactions")
public class Transactions {
    @Id
    private ObjectId _id;
    @Field ("trans_date_trans_time")
    private Date transDateTransTime;
    private double amt;

    @Field ("trans_num")
    private int transNum;
    private int customer_id;
    private String city;
    private String state;
    @Field ("city_population")
    private int cityPopulation;
    private String merchant;
    private String category;
    private String first;
    private String last;
    private String gender;
    @Field ("Job")
    private String job;
    private Date dob;


    public Transactions(Date transDateTransTime, double amt, int transNum, int customer_id, String city, String state, int cityPopulation, String merchant, String category, String first, String last, String gender, String job, Date dob) {
        this.transDateTransTime = transDateTransTime;
        this.amt = amt;
        this.transNum = transNum;
        this.customer_id = customer_id;
        this.city = city;
        this.state = state;
        this.cityPopulation = cityPopulation;
        this.merchant = merchant;
        this.category = category;
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Date getTransDateTransTime() {
        return transDateTransTime;
    }

    public void setTransDateTransTime(Date transDateTransTime) {
        this.transDateTransTime = transDateTransTime;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public int getTransNum() {
        return transNum;
    }

    public void setTransNum(int transNum) {
        this.transNum = transNum;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "_id=" + _id +
                ", transDateTransTime=" + transDateTransTime +
                ", amt=" + amt +
                ", transNum=" + transNum +
                ", customer_id=" + customer_id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cityPopulation=" + cityPopulation +
                ", merchant='" + merchant + '\'' +
                ", category='" + category + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }
}
