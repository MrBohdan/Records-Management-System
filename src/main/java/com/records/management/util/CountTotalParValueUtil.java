package com.records.management.util;

/**
 * Utility to count total par value
 *
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
public interface CountTotalParValueUtil {

    default double countTotalParValueUtil(long shares_amount, double par_value) {
        return shares_amount * par_value;
    }
}
