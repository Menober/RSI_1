package Server;

import org.apache.xmlrpc.WebServer;

import java.util.Calendar;
import java.util.Random;

public class serwerRPC {
    static WebServer webServer;
    String asyncStringi="";

    public static void main(String[]args){
        runServer(12345);
    }

    private static void runServer(int port){
        webServer = new WebServer(port);
        webServer.addHandler("Server", new serwerRPC());
        webServer.start();
    }



    private String createInitiateMessage() {
        String text = "";
        text+="Hello user. You has these method to use:\n";
        text+="[1] String show()\n";
        text+="[2] String funcOne(int a, String b, double c) - make a 'a' length tree from 'b' string and return result of multiply 'c' and number 2\n";
        text+="[3] String funcTwo(String a, String b, int c) - take 'a' string and replace every character like first char of string 'b' for 'c' number\n";
        text+="[4] String getTime() - returning current server time\n";
        text+="[5] String async(int a, int b, int c) - rand form 'a' to 'b' and wait 'c'\n";

        return text;
    }

    public String show(){
        return createInitiateMessage();
    }

    public String funcOne(int a, String b, double c){
        String result = "";

        for(int i=1;i<=a;i++){
            result+=i+":";
            for(int j=0;j<i;j++)
                result+=b;
            result+="\n";
        }
        result+=c*2;
        return result;
    }

    public String funcTwo(String a, String b, int c){
        String result = "";

        for(int i=0;i<a.length();i++){
            if(a.charAt(i)==b.charAt(0))
                result+=c;
            else
                result+=a.charAt(i);
        }

        return result;
    }

    public String getTime(){
        return Calendar.getInstance().getTime().toString();
    }

    public String async(int a, int b, int c){
        try{
            Thread.sleep(Integer.valueOf(c));
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return "Twoja liczba: "+(new Random().nextInt(b-a)+a);
    }
}
