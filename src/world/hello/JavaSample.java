package world.hello;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.*;
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;


public class JavaSample 
{
    public static void main(String[] args) 
    {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/vision/v1.0/analyze");

            builder.setParameter("visualFeatures", "Categories,Tags,Description,Faces,ImageType,Color,Adult");
            builder.setParameter("details", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "7771debe2b4a46279646ecd0fa8e6f19");


            // Request body
//            JSONObject obj = new JSONObject();
//            System.out.println(obj.toJSONString());
            File file = new File("/Users/zc/Desktop/lena.jpg");
            byte[] a = getBytesFromFile(file);
            InputStream is = new ByteArrayInputStream(a);
//            request.setEntity(is);
            InputStreamEntity inputentity = new InputStreamEntity(is, is.available());
            request.setEntity(inputentity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static byte[] getBytesFromFile(File file) throws IOException {  
        InputStream is = new FileInputStream(file);  
        byte[] bytes;  
              
        try {  
            // Get the size of the file  
            long length = file.length();  
              
            // You cannot create an array using a long type.  
            // It needs to be an int type.  
            // Before converting to an int type, check  
            // to ensure that file is not larger than Integer.MAX_VALUE.  
            if (length > Integer.MAX_VALUE) {  
                // File is too large (>2GB)  
            }  
          
            // Create the byte array to hold the data  
            bytes = new byte[(int)length];  
          
            // Read in the bytes  
            int offset = 0;  
            int numRead = 0;  
            while (offset < bytes.length  
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {  
                offset += numRead;  
            }  
          
            // Ensure all the bytes have been read in  
            if (offset < bytes.length) {  
                throw new IOException("Could not completely read file " + file.getName());  
            }  
        }  
        finally {  
            // Close the input stream and return bytes  
            is.close();  
        }  
        return bytes;  
    }  
}
