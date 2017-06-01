package world.hello;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class IdTransfer {
	static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static{		
		cm.setMaxTotal(1000);        		
	}
	static CloseableHttpClient httpclient = HttpClients.custom()
            .setConnectionManager(cm)
            .build();
	static public JSONArray GetAcademicEvaluate(String testCompare,String attributes){
		JSONArray resultJSON = null; 
		try {
			CloseableHttpClient httpClient = httpclient;
			HttpContext context = new BasicHttpContext();
			HttpGet httpget;
			URIBuilder builder = new URIBuilder("https://oxfordhk.azure-api.net/academic/v1.0/evaluate");          
	        builder.setParameter("expr", testCompare);
	        builder.setParameter("count", "999999");
	        builder.setParameter("attributes",attributes);
	        URI uri = builder.build();
	        httpget = new HttpGet(uri);
	        httpget.setHeader("Ocp-Apim-Subscription-Key", "f7cc29509a8443c5b3a5e56b0e38b5a6");
	        CloseableHttpResponse response = httpClient.execute(httpget, context);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
            	resultJSON = (JSONArray) ((JSONObject)new JSONParser().parse(EntityUtils.toString(entity))).get("entities");
            }
	        response.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return resultJSON;	
	}
	static public JSONArray GetAcademicInterpret(String testCompare){
		JSONArray resultJSON = null; 
		try {
			CloseableHttpClient httpClient = httpclient;
			HttpContext context = new BasicHttpContext();
			HttpGet httpget;
			URIBuilder builder = new URIBuilder("https://oxfordhk.azure-api.net/academic/v1.0/evaluate");          
	        builder.setParameter("query", testCompare);
	        builder.setParameter("count", "999999");
	        
	        URI uri = builder.build();
	        httpget = new HttpGet(uri);
	        httpget.setHeader("Ocp-Apim-Subscription-Key", "f7cc29509a8443c5b3a5e56b0e38b5a6");
	        CloseableHttpResponse response = httpClient.execute(httpget, context);
	        HttpEntity entity = response.getEntity();
	        if (entity != null) {
            	resultJSON = (JSONArray) ((JSONObject)new JSONParser().parse(EntityUtils.toString(entity))).get("entities");
            }
	        response.close();
	        
		} catch (Exception e){
			e.printStackTrace();
		}
		return resultJSON;
	}
	public static JSONArray KeyPhrase(String input){
		//找出一段文本中的关键字
		CloseableHttpClient httpClient = httpclient;
		JSONArray resultArray2=new JSONArray();
        try
        {
            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "ddb348218bdf4227a0a25a5d265d082f");


            // Request body
            JSONObject inner = new JSONObject();
            JSONArray medium = new JSONArray();
            JSONObject finalstr= new JSONObject();
            inner.put("language", "en");
            inner.put("id", "request");
            inner.put("text",input );
            medium.add(inner);
            finalstr.put("documents", medium);
            
            StringEntity reqEntity = new StringEntity(finalstr.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
//                System.out.println(EntityUtils.toString(entity));
            	JSONObject resultObject = (JSONObject)new JSONParser().parse(EntityUtils.toString(entity));
            	JSONArray resultArray = (JSONArray) (resultObject.get("documents"));
            	JSONObject resultObject2 = (JSONObject) (resultArray.get(0));
            	resultArray2 = (JSONArray) (resultObject2.get("keyPhrases"));
            	return resultArray2;
//            	for(Object i:resultArray2)
//            	{
//            		System.out.println(i.toString());
//            	}
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return resultArray2;
	}
	
	public static String interpret(String input){
		HttpClient httpclient = HttpClients.createDefault();
		String result="";
        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/academic/v1.0/interpret");

            builder.setParameter("query", input);
            builder.setParameter("complete", "0");
            builder.setParameter("count", "10");
            builder.setParameter("offset", "0");
            builder.setParameter("timeout", "1000");
            builder.setParameter("model", "latest");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", "e244c584530e42c7b5c0e3cc21872833");


            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
//                System.out.println(EntityUtils.toString(entity));
                JSONObject resultObject = (JSONObject)new JSONParser().parse(EntityUtils.toString(entity));
                JSONArray resultArray = (JSONArray)(resultObject.get("interpretations"));
                JSONObject resultObject2 =(JSONObject)(resultArray.get(0));
                JSONArray resultArray2=(JSONArray)(resultObject2.get("rules"));
                JSONObject resultObject3=(JSONObject)(resultArray2.get(0));
                JSONObject resultObject4 =(JSONObject)(resultObject3.get("output"));
                result = (String)(resultObject4.get("value"));
                
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return result;
	}
	
}
