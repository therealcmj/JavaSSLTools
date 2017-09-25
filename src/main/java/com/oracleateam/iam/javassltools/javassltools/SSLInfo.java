/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracleateam.iam.javassltools.javassltools;

import java.security.KeyStore;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

/**
 *
 * @author cmj
 * 
 * Based on code from
 * https://answers.launchpad.net/ubuntu/+question/239272
 */
public class SSLInfo {

    public static void main(String[] args)
            throws Exception {

//        SSLContext ctx = SSLContext.getInstance("TLSv1");
        
        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
// commented because I want the default CA
//         //Create an empty TrustManagerFactory to avoid loading default CA
//        KeyStore ks = KeyStore.getInstance("JKS");
//                
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//        tmf.init(ks);
//        ctx.init(null, tmf.getTrustManagers(), null);

        ctx.init(null,null,null);

        //SSLSocket socket = (SSLSocket) ctx.getSocketFactory().createSocket("www.oracle.com", 443);
//        SSLSocket socket = (SSLSocket) ctx.getSocketFactory().createSocket("140.86.3.84", 7002);
//        SSLSocket socket = (SSLSocket) ctx.getSocketFactory().createSocket("tetradev-webgate.tetratech.com", 443);
        SSLSocket socket = (SSLSocket) ctx.getSocketFactory().createSocket("na1.etadirect.com", 443);
        printSupportedCiphers(socket);
        printEnabledCiphers(socket);
    }

    private static void printSupportedCiphers(SSLSocket socket) {
        printInfo("Supported cipher suites", socket.getSupportedCipherSuites());
    }

    private static void printEnabledCiphers(SSLSocket socket) {
        printInfo("Enabled cipher suites", socket.getEnabledCipherSuites());
    }

    private static void printInfo(String prefix, String[] values) {
        Arrays.sort(values);
        System.out.println(prefix + " (" + values.length + " values):");
        for (int i = 0; i < values.length; i++) {
            System.out.println("  * " + values[i]);
        }
    }
}
