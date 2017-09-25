/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracleateam.iam.javassltools.javassltools;

import java.io.IOException;
import java.lang.String;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author cmj
 */
public class SSLPing {

    private static final String defaultHostname = "www.oracle.com";
    private static final int defaultPort = 443;

    public static void main(String[] args) {
        String host = defaultHostname;
        int port = defaultPort;

        switch (args.length) {
            case 0:
                System.out.println("Running test with default host:" + host + " and default port:" + port);
                break;

            case 1:
                host = args[0];
                System.out.println("Running test with host:" + host + " and default port:" + port);
                break;

            case 2:
                host = args[0];
                port = Integer.parseInt(args[1]);
                System.out.println("Running test with host:" + host + " port:" + port);
                break;

            default:
                System.err.println("Invalid arguments");
                System.exit(-1);
        }

        try {

            System.out.println("Locating socket factory for SSL...");
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            System.out.println("Creating secure socket to " + host + ":" + port);
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);

            System.out.println("Enabling all available cipher suites...");
            String[] suites = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(suites);

            System.out.println("Registering a handshake listener...");
            socket.addHandshakeCompletedListener(new MyHandshakeListener());

            System.out.println("Starting handshaking...");
            socket.startHandshake();

            System.out.println("Sucessfully connected to " + socket.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyHandshakeListener implements HandshakeCompletedListener {

    public void handshakeCompleted(HandshakeCompletedEvent e) {
        System.out.println("Handshake succesful!");
        System.out.println("Using cipher suite: " + e.getCipherSuite());
        System.out.println("Protocols: " + Arrays.toString((e.getSocket().getSupportedProtocols())));
        //SSLSession sslsession = e.getSession();
    }
}
