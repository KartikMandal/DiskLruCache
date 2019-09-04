/*
*   Copyright 2002 Yodlee, Inc. All Rights Reserved.
*
*   This software is the confidential and proprietary information of
*   Yodlee, Inc. Use is subject to license terms.
*
*/
package com.man.cert;


/**
 *   Hex
 *
 *   @author Jad Boutros (jad@yodlee.com)
 *   $Date: 2018/03/15 $
 *   $Revision: #2 $   [$Change: 3531652 $]
 */

public class Base64 {

    private static final String FQCN = Base64.class.getName();

    /* RCS version information */
    public static final String RCS_ID = "$Id: //razor/core/platform/src/crypto/tool/com/yodlee/crypto/Tool/mini/Base64.java#2 $";

    public static void main(String args[]) {

        if (args.length < 2) {
            System.out.println("Format: -encode|-decode string");
            System.exit(0);
        }

        String op = args[0];

        try {
            if (op.equalsIgnoreCase("-encode")) {
                System.out.println("Encoded: " + new String( Base64Encode.encode(args[1].getBytes())) );
            }
            else if (op.equalsIgnoreCase("-decode")) {
                System.out.println("Decoded: " + new String(Base64Encode.decode(args[1].toCharArray())));
            }
            else {
                System.err.println("Invalid operation: " + op + ": Should be -decode or -encode");
                System.exit(1);
            }
        }
        catch (Exception e) {
            System.out.println("Received exception: " + e.getMessage());
            e.printStackTrace();
        }


    }

}
