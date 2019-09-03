package com.mymovieportal.util;

import java.nio.charset.Charset;
import java.util.Base64;

// TODO: Auto-generated Javadoc

/**
 * The Class Encryption.
 */

public final class Encryption {

    private Encryption() {
    }

    /**
     * Encrypt.
     *
     * @param str the str
     * @return the string
     */
    public static String encrypt(String str) {
        String response = Base64.getEncoder().encodeToString(str.getBytes(Charset.forName("UTF-8")));
        return response;
    }

    /**
     * Decrypt.
     *
     * @param str the str
     * @return the string
     */
    public static String decrypt(String str) {
        // byte[] b = Base64.getDecoder().decode(str.getBytes());
        byte[] b = Base64.getDecoder().decode(str.getBytes(Charset.forName("UTF-8")));
         return new String(b);
       // return Arrays.toString(b);
    }

    /**
     * Main.
     *
     * @param args the args
     */

    // public static void main(String args) {
    // String st = encrypt("1qqQQQ");
    // System.out.println(st + "*********************");

    // System.out.println("****************************" + encrypt("MXFxUVFR"));
    // }

}
