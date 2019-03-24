package Client;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;

public class clientRPC {

    static XmlRpcClient client;
    static Scanner scanner = new Scanner(System.in);
    static String message = "";

    public static void main(String[] args) throws XmlRpcException, IOException {
        IpAndPort ipAndPort = getIpAndPort();
        connectWithAServer("http://" + ipAndPort.ip, ipAndPort.port);
        System.out.println(sendRequestForInitialMessage());
        System.out.println("What do you want to choose?");
        message = scanner.nextLine();
        if (message.equals("getTime"))
            System.out.println(sendRequestWithoutParams("getTime"));
        if (message.equals("funcOne"))
            System.out.println(sendRequestFuncOne());
        if (message.equals("funcTwo"))
            System.out.println(sendRequestFuncTwo());
        if (message.equals("async"))
            sendRequestAsync();

    }

    private static String sendRequestFuncOne() throws XmlRpcException, IOException {
        Vector vector = new Vector();
        vector.addElement(Integer.valueOf(scanner.nextLine()));
        vector.addElement(scanner.nextLine());
        vector.addElement(Double.valueOf(scanner.nextLine()));
        Object result = client.execute("Server.funcOne", vector);

        return result.toString();

    }
    private static String sendRequestFuncTwo() throws XmlRpcException, IOException {
        Vector vector = new Vector();
        vector.addElement(scanner.nextLine());
        vector.addElement(scanner.nextLine());
        vector.addElement(Integer.valueOf(scanner.nextLine()));
        Object result = client.execute("Server.funcTwo", vector);

        return result.toString();

    }
    private static void sendRequestAsync() throws XmlRpcException, IOException {
        AC cb = new AC();
        Vector vector = new Vector();
        vector.addElement(scanner.nextLine());
        vector.addElement(scanner.nextLine());
        vector.addElement(scanner.nextLine());
        client.executeAsync("Server.async", vector,cb);

    }

    private static String sendRequestWithManyParams(String method, int paramsCount) throws XmlRpcException, IOException {
        Vector vector = new Vector();
        for (int i = 0; i < paramsCount; i++)
            vector.addElement(scanner.nextLine());
        Object result = client.execute("Server."+method, vector);

        return result.toString();

    }

    private static void connectWithAServer(String ip, int port) throws IOException, XmlRpcException {
        client = new XmlRpcClient(ip + ":" + port);
    }

    private static String sendRequestForInitialMessage() throws XmlRpcException, IOException {

        Object result = client.execute("Server.show", new Vector());

        return result.toString();
    }

    private static String sendRequestWithoutParams(String method) throws XmlRpcException, IOException {
        Object result = client.execute("Server." + method, new Vector());
        return result.toString();
    }

    private static IpAndPort getIpAndPort() {
        IpAndPort result = new IpAndPort();
        System.out.println("Type IP address or dont for default");
        message = scanner.nextLine();
        if (message.equals(""))
            result.ip = "192.168.1.95";
        else
            result.ip = message;
        System.out.println("Type port or dont for default");
        message = scanner.nextLine();
        if (message.equals(""))
            result.port = 12345;
        else
            result.port = Integer.valueOf(message);
        return result;
    }

}
