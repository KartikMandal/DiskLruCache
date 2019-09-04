/*
*   Copyright 2002 Yodlee, Inc. All Rights Reserved.
*
*   This software is the confidential and proprietary information of
*   Yodlee, Inc. Use is subject to license terms.
*
*/

package com.man.cert;


/**
 *   HexEncode
 *
 *   @author Jad Boutros (jad@yodlee.com)
 *   $Date: 2018/03/15 $
 *   $Revision: #2 $   [$Change: 3531652 $]
 */
public class HexEncode {

    private static String FQCN = HexEncode.class.getName();

    private static char hex[] = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /* whole range of bytes  */
    static private byte[] codes = new byte[256];

    /* be good and accept both upper case and lower case hex digits when decoding */
    static {
        for (int i = 0; i < 256; i++) codes[i] = -1;
        for (int i = '0'; i <= '9'; i++) codes[i] = (byte) (i - '0');
        for (int i = 'a'; i <= 'f'; i++) codes[i] = (byte) (10 + i - 'a');
        for (int i = 'A'; i <= 'F'; i++) codes[i] = (byte) (10 + i - 'A');
    }

    /**
     * Returns a Hexadecimal-Encoded string of the given input or null if input was null
     * In such a low-level method, I thought returning a nullpointer like exception
     * was overburdening for the user
     *
     * @param raw
     * @return the hex-encoded string or null if input was null
     */
    public static String encode(byte[] raw) {

        if (raw == null) return null;

        char[] encoded = new char[raw.length * 2];

        for (int i=0, j=0; i < raw.length; i++) {
            encoded[j++] = hex[(raw[i] >> 4) & 0x0f];
            encoded[j++] = (hex[raw[i] & 0x0f]);
        }
        return new String(encoded);
    }

    /**
     * Returns a byte-array corresponding the the hex-decoding of the input string
     * null input throws an exception
     *
     * @param hex
     * @return
     * @throws InvalidHexEncodingException
     */
    public static byte[] decode(String hex)
            throws Exception {

        if (hex == null || ( (hex.length() %2) != 0))
            throw new RuntimeException("null");

        char encoded[] = hex.toCharArray();

        int upper, lower;
        byte raw[] = new byte[encoded.length / 2];

        for (int i = 0, j=0; j < raw.length; j++) {
            upper = codes[encoded[i++] & 0xFF];
            lower = codes[encoded[i++] & 0xFF];
            if ( (upper == -1) || (lower == -1))
                throw new RuntimeException(hex.toString());
            raw[j] = (byte) ( (upper << 4) + lower);
        }
        return raw;
    }

}
