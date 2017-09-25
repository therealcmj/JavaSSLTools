/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracleateam.iam.javassltools.javassltools;

import java.util.Arrays;

/**
 *
 * @author cmj
 */
public class CommandLine {

    public static void usage(String[] args) throws Exception {
        System.err.println("Usage:");

        System.err.println("");
        System.err.println(args[0] + " info");

        System.err.println("OR");

        System.err.println("");
        System.err.println(args[0] + " ping <hostname> [port]");

        System.err.println("");

        System.exit(-1);
    }

    public static void main(String[] args) throws Exception {
        
        System.err.println(args.length);
        for ( String arg : args )
            System.out.println(" " + arg);

        if (args.length == 0) {
            usage(args);
        }

        if ("info".equals(args[0])) {
            SSLInfo.main(args);
        }

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        if ("ping".equals(args[0])) {
            SSLPing.main(newArgs);
        }
    }
}
