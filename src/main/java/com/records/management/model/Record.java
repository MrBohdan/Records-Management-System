package com.records.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "usreou", unique = true, updatable = false, length = 8, nullable = false)
    private Long usreou;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "shares_amount", nullable = false)
    private long shares_amount;

    @Column(name = "total_par_value", nullable = false)
    private double total_par_value;

    @Column(name = "par_value", nullable = false)
    private double par_value;

    @Column(name = "pay_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime pay_date;

    @Column(name = "status", nullable = false)
    private String status;

    public Record() {
    }

    /**
     * @param usreou        - Unified State Register of Enterprises and Organizations of Ukraine.
     * @param comment
     * @param shares_amount - amount of shares.
     * @param shares_amount - total Par-Value ( amount * par_value).
     * @param par_value     - price of single share.
     * @param pay_date      - date when purchasing a stock.
     * @param status        - status of the record.
     */
    public Record(@JsonProperty("usreou") Long usreou,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("shares_amount") long shares_amount,
                  @JsonProperty("par_value") double par_value,
                  @JsonProperty("pay_date") LocalDateTime pay_date) {
        this.usreou = usreou;
        this.comment = comment;
        this.shares_amount = shares_amount;
        this.total_par_value = total_par_value;
        this.par_value = par_value;
        this.pay_date = pay_date;
        this.status = status;
    }

    public Long getUsreou() {
        return usreou;
    }

    public void setUsreou(Long usreou) {
        this.usreou = usreou;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getShares_amount() {
        return shares_amount;
    }

    public void setShares_amount(long shares_amount) {
        this.shares_amount = shares_amount;
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
