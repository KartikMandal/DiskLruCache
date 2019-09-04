package com.kartik.gause;

/**
 * This class defines some common utility methods.
 */
public class Utils {

    /**
     * Checks that the input number is not infinite and if it is, throws
     * an exception with the specified message.
     * 
     * @param value  the value to check.
     * @param errmsg the message to pass to the exception upon failure.
     */
    public static void checkNotInfinite(final double value, 
                                        final String errmsg) {
        if (Double.isInfinite(value)) {
            throw new IllegalArgumentException(errmsg);
        }
    }

    /**
     * Checks that the input number is not <code>NaN</code> and if it is,
     * throws an exception with the specified message.
     * 
     * @param value  the value to check.
     * @param errmsg the message to pass to the exception upon failure.
     */
    public static void checkNotNaN(final double value, final String errmsg) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("The value is NaN.");
        }
    }

    /**
     * Checks that the input number is not negative and if it is, throws an
     * exception with the specified message.
     * 
     * @param value  the value to check.
     * @param errmsg the message to pass to the exception upon failure.
     */
    public static void checkNotNegative(final double value, 
                                        final String errmsg) {
        if (value < 0.0) {
            throw new IllegalArgumentException(errmsg);
        }
    }

    /**
     * Checks that the input reference is not <code>null</code> and if it is,
     * throw an exception with the supplied error message.
     * 
     * @param o      the reference to check.
     * @param errmsg the error message to pass to the exception upon failure.
     */
    public static void checkNotNull(final Object o, final String errmsg) {
        if (o == null) {
            throw new IllegalArgumentException(errmsg);
        }
    }
}