package com.records.management.util;

public interface CountTotalParValueUtil {

    default double countTotalParValueUtil(long shares_amount, double par_value) {
        return shares_amount * par_value;
    }
}
