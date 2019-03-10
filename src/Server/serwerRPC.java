package Server;

import org.apache.xmlrpc.WebServer;

public class serwerRPC {
    public static void main(String[]args){
        try{
            System.out.println("Server start");
            int port = 12345;
            WebServer server = new WebServer(port);
            server.addHandler("MyServer", new serwerRPC());
            server.start();
            System.out.println("Server ON");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Integer echo(int x, int y){
        return new Integer(x+y);
    }
    public int execAsy(int x){
        System.out.println("odliczanie");
        try{
            Thread.sleep(x);
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("Po odliczaniu");
        return 123;
    }
}
