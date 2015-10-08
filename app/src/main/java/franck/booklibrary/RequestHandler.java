package franck.booklibrary;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by franc on 08/10/2015.
 */
public class RequestHandler extends AsyncTask<String, Void> {

    public RequestHandler() {
    }


    public Book getBookByEAN(String ean) throws IOException {
        String title = "";
        String author = "";
        String isbn = "";
        InputStream is = null;
        try {
            URL url = new URL("http://www.google.com/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            System.out.println("RESPONSE : " + is.toString());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        /*
            http://webservices.amazon.com/onca/xml?
Service=AWSECommerceService&
AWSAccessKeyId=RUVnU8rdMHpCoBqN6Rv2chjvV2p7BBGh39kPVxwk&
AssociateTag=AKIAJXVLHNIGLILDHFGQ&
Operation=ItemLookup&
ItemId=ean&
SearchIndex=Electronics&
IdType=EAN
&Timestamp=[YYYY-MM-DDThh:mm:ssZ]
&Signature=[Request Signature]

             */
        return new Book(title, author, isbn);
    }
}
