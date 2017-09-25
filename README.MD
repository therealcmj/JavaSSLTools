# JavaSSLTools

Quick and dirty SSL info / test tool

To compile/package:

 1. git pull this repository
 2. cd into the JavaSSLTools directory
 3. run "mvn package"

The code will be compiled and jar'd into target/JavaSSLTools-1.0-SNAPSHOT.jar

To get info on your JRE's SSL stack:
`java -jar target/JavaSSLTools-1.0-SNAPSHOT.jar info`

To make an SSL/TLS connection to a server

`java -jar target/JavaSSLTools-1.0-SNAPSHOT.jar ping [<hostname> [port]]`

That is to say that hostname and port are optional. If you don't specify one the tool will use www.oracle.com and 443 as defaults.
