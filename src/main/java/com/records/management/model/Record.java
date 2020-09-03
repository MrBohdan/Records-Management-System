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
    private long sharesAmount;

    @Column(name = "total_par_value", nullable = false)
    private double totalParValue;

    @Column(name = "par_value", nullable = false)
    private double parValue;

    @Column(name = "pay_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime payDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Record() {
    }

    /**
     * @param usreou        - Unified State Register of Enterprises and Organizations of Ukraine.
     * @param comment       - comment
     * @param sharesAmount  - amount of shares.
     * @param totalParValue - total Par-Value (shares amount * Par-Value).
     * @param parValue      - price of single share.
     * @param payDate       - date when purchasing a stock.
     * @param status        - status of the record.
     */
    public Record(@JsonProperty("usreou") Long usreou,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("sharesAmount") long sharesAmount,
                  @JsonProperty("parValue") double parValue,
                  @JsonProperty("payDate") LocalDateTime payDate) {
        this.usreou = usreou;
        this.comment = comment;
        this.sharesAmount = sharesAmount;
        this.parValue = parValue;
        this.payDate = payDate;
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

    public long getSharesAmount() {
        return sharesAmount;
    }

    public void setSharesAmount(long sharesAmount) {
        this.sharesAmount = sharesAmount;
    }

    public double getTotalParValue() {
        return totalParValue;
    }

    public void setTotalParValue(double totalParValue) {
        this.totalParValue = totalParValue;
    }

    public double getParValue() {
        return parValue;
    }

    public void setParValue(double parValue) {
        this.parValue = parValue;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
