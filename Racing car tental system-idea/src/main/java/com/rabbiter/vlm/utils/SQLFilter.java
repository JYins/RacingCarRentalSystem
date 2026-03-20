
package com.rabbiter.vlm.utils;

import org.apache.commons.lang3.StringUtils;

import com.rabbiter.vlm.entity.EIException;

/**
 * SQL Filter
 */
public class SQLFilter {

    /**
     * SQL injection filter
     * @param str  string to check
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        // remove '|"|;|\ chars
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        // convert to lowercase
        str = str.toLowerCase();

        // illegal keywords
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        // check for illegal keywords
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new EIException("Contains illegal characters");
            }
        }

        return str;
    }
}
