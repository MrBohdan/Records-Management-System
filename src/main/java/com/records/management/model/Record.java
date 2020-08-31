package com.records.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * JPA Annotated Pojo (Plain Old Java Object) that represents a record.
 *
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Entity(name = "record")
public class Record {

    @Id
    @Column(name = "usreou", unique = true, updatable = false, nullable = false)
    private Long usreou;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "amount", nullable = false)
    private long amount;

    @Column(name = "total_par_value", nullable = false)
    private double total_par_value;

    @Column(name = "par_value", nullable = false)
    private double par_value;

    @Column(name = "pay_date", nullable = false)
    private LocalDateTime pay_date;

    @Column(name = "status", nullable = false)
    private String status;

    public Record() {
    }

    /**
     * @param comment
     * @param usreou          - Unified State Register of Enterprises and Organizations of Ukraine.
     * @param amount          - amount of shares.
     * @param total_par_value - total Par-Value ( amount * par_value).
     * @param par_value       - price of single share.
     * @param pay_date        - date when purchasing a stock.
     * @param status          - status of the record.
     */
    public Record(@JsonProperty("comment") String comment,
                  @JsonProperty("usreou") long usreou,
                  @JsonProperty("amount") long amount,
                  @JsonProperty("total_par_value") double total_par_value,
                  @JsonProperty("par_value") double par_value,
                  @JsonProperty("pay_date") LocalDateTime pay_date,
                  @JsonProperty("status") String status) {
        this.comment = comment;
        this.usreou = usreou;
        this.amount = amount;
        this.total_par_value = total_par_value;
        this.par_value = par_value;
        this.pay_date = pay_date;
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUsreou() {
        return usreou;
    }

    public void setUsreou(long usreou) {
        this.usreou = usreou;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getTotal_par_value() {
        return total_par_value;
    }

    public void setTotal_par_value(double total_par_value) {
        this.total_par_value = total_par_value;
    }

    public double getPar_value() {
        return par_value;
    }

    public void setPar_value(double par_value) {
        this.par_value = par_value;
    }

    public LocalDateTime getPay_date() {
        return pay_date;
    }

    public void setPay_date(LocalDateTime pay_date) {
        this.pay_date = pay_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
