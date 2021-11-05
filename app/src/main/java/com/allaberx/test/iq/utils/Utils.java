package com.allaberx.test.iq.utils;

import androidx.annotation.Nullable;

import java.util.Collection;

public class Utils {

    @Nullable
    public static <T> T getFirst( Collection<T> collection )
    {
        if( collection != null && collection.size() > 0 )
        {
            return collection.iterator().next();
        }
        return null;
    }

    public static boolean isBlankString( String value )
    {
        return value == null || value.trim().length() == 0;
    }

    public static boolean notBlankString( String value )
    {
        return ! isBlankString(value);
    }
}