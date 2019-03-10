package Client;

import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;

public class clientRPC {
    public static void main(String []args){
        AC cb = new AC();
        Vector<Integer> params2 = new Vector<>();
        params2.addElement(new Integer(3000));
        try{
            XmlRpcClient srv = new XmlRpcClient("http://localhost:12345");
            Vector<Integer> params = new Vector<Integer>();
            params.addElement(new Integer(13));
            params.addElement(new Integer(21));
            srv.executeAsync("MyServer.execAsy", params2, cb);
            System.out.println("wywolano asyc");
            Object result = srv.execute("MyServer.echo",params);
            int wynik = ((Integer)result).intValue();
            System.out.println(wynik);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Finito");
    }
}
