package Client;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

//Krzysztof Miarczyński 238036
public class AC implements AsyncCallback {
    @Override
    public void handleResult(Object result, URL url, String method) {
        System.out.println(result.toString());
    }

    @Override
    public void handleError(Exception exception, URL url, String method) {
        System.out.println("handleError");
    }
}
