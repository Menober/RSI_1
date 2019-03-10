package Client;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AC implements AsyncCallback {
    @Override
    public void handleResult(Object result, URL url, String method) {
        System.out.println("handleResult");
    }

    @Override
    public void handleError(Exception exception, URL url, String method) {
        System.out.println("handleError");
    }
}
