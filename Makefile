ALL:
javac -cp .:lib/wsif.jar --add-modules jdk.naming.rmi --add-exports='jdk.naming.rmi/com.sun.jndi.rmi.registry=ALL-UNNAMED' RMIServer.java
