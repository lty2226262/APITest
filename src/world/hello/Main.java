package world.hello;

//import java.awt.RenderingHints.Key;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.net.URI;
import java.util.ArrayList;
//import java.util.Base64;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

//import twitter4j.AccountSettings;
//import twitter4j.DirectMessage;
//import twitter4j.IDs;
//import twitter4j.Location;
//import twitter4j.Paging;
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.ResponseList;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//import twitter4j.User;
//import twitter4j.conf.ConfigurationBuilder;

public class Main {

	public static void search(String[] args)  {
//		String[] name={"hqwhuang"};
//		TwitterAPI(name);
//		AnalyzeImage();
//		SpellCheck();
//		TextAnalytics();
//		BreakSentence();
		/*
		JSONObject myJsonObject = new JSONObject();
        
        myJsonObject.put("id", "I am strong");
        myJsonObject.put("text", "haha");
        JSONArray myJsonArray = new JSONArray();
        myJsonArray.put(myJsonObject);
        JSONObject docJsonArray =new JSONObject();
        docJsonArray.put("documents", myJsonArray);
        System.out.println(docJsonArray.toString());
        System.out.println("{\"documents\":[{\"id\": \"哈哈\",\"text\": \"string\"}]}");
        */
//        "{\"documents\":[{\"id\": \"哈哈\",\"text\": \"string\"}]}"
		
		String aString="The Text Analytics API is a suite of text analytics web services built with Azure Machine Learning. The API can be used to analyze unstructured text for tasks such as sentiment analysis, key phrase extraction and language detection. ";
		String bString="I want to study Big data, and also public opinion, papers on nature, how about Computer Science?";
		
		JSONArray KeyWords =IdTransfer.KeyPhrase(bString);
		ArrayList<String> medium = new ArrayList<String>();
		
		for(Object i:KeyWords)
        	{
			medium.add(IdTransfer.interpret(i.toString()));
        	}
		System.out.println(requestString(medium));
	}
	
	
	public static String requestString(ArrayList<String> aSet){
		ArrayList<String> tmpCompares = new ArrayList<String>();
		int firstFlag = 1;
		int stringNum = 0;
		for (String i : aSet){
			if (firstFlag == 1){
				tmpCompares.add(i);
				stringNum = tmpCompares.size() - 1;
				firstFlag = 0;
			} else {
				if (tmpCompares.get(stringNum).length() < 1200){
					tmpCompares.set(stringNum, "Or("+i+","+tmpCompares.get(stringNum)+")");
				} else{
					tmpCompares.set(stringNum, "Or("+i+","+tmpCompares.get(stringNum)+")");
					firstFlag = 1;
				}
			}
		}
		return tmpCompares.get(0);
	}
//	
//	public static void DetectTopic(){
//		//需要至少100个文，docments是一个JSONArray,里面要有至少100个不相同的id,估计用不上
//		HttpClient httpclient = HttpClients.createDefault();
//
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/topics");
//
//            builder.setParameter("minDocumentsPerWord", "{integer}");
//            builder.setParameter("maxDocumentsPerWord", "{integer}");
//
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/json");
//            request.setHeader("Ocp-Apim-Subscription-Key", "ddb348218bdf4227a0a25a5d265d082f");
//
//
//            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//	}
//	public static void BreakSentence(){
//		//可以用，把没断句的英文加空格拆成单词
//		HttpClient httpclient = HttpClients.createDefault();
//
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/text/weblm/v1.0/breakIntoWords");
//
//            builder.setParameter("model", "body");
//            builder.setParameter("text", "IlikeyoubutyouneverknowhowmuchIlikeyou");
//            builder.setParameter("order", "1");
//            builder.setParameter("maxNumOfCandidatesReturned", "5");
//
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Ocp-Apim-Subscription-Key", "71fc99dc63de418bae528c5dd6994c22");
//
//
//            // Request body
////            StringEntity reqEntity = new StringEntity("{body}");
////            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//	}
//	public static void TextAnalytics(){
//			//可以用，检测文本是否well constructed，1.0是完全well constructed
//	        HttpClient httpclient = HttpClients.createDefault();
//
//	        try
//	        {
//	            URIBuilder builder = new URIBuilder("https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/languages");
//
//	            builder.setParameter("numberOfLanguagesToDetect", "2");
//
//	            URI uri = builder.build();
//	            HttpPost request = new HttpPost(uri);
//	            request.setHeader("Content-Type", "application/json");
//	            request.setHeader("Ocp-Apim-Subscription-Key", "ddb348218bdf4227a0a25a5d265d082f");
//
//
//	            // Request body
//	            JSONObject myJsonObject = new JSONObject();
//	            
//	            myJsonObject.put("id", "I am strong");
//	            myJsonObject.put("text", "The Text Analytics API is a suite of text analytics web services built with Azure Machine Learning. The API can be used to analyze unstructured text for tasks such as sentiment analysis, key phrase extraction and language detection. No training data is needed to use this API; just bring your text data. This API uses advanced natural language processing techniques to deliver best in class predictions. Further documentation can be found in https://azure.microsoft.com/en-us/documentation/articles/machine-learning-apps-text-analytics/");
//	            JSONArray myJsonArray = new JSONArray();
////	            myJsonArray.put(myJsonObject);
//	            JSONObject docJsonArray =new JSONObject();
//	            docJsonArray.put("documents", myJsonArray);
//	            StringEntity reqEntity = new StringEntity(docJsonArray.toString());
//	            request.setEntity(reqEntity);
//
//	            HttpResponse response = httpclient.execute(request);
//	            HttpEntity entity = response.getEntity();
//
//	            if (entity != null) 
//	            {
//	                System.out.println(EntityUtils.toString(entity));
//	            }
//	        }
//	        catch (Exception e)
//	        {
//	            System.out.println(e.getMessage());
//	        }
//	}
//	public static void LinguisticsAPI(){
//		HttpClient httpclient = HttpClients.createDefault();
//
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/linguistics/v1.0/analyze");
//
//
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/json");
//            request.setHeader("Ocp-Apim-Subscription-Key", "cb839db6b2a14962ac4435a371a6eacc");
//
//
//            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//	}
//	public static void SpellCheck(){
//		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//		cm.setMaxTotal(1000);        		
//	
//	CloseableHttpClient httpclient = HttpClients.custom()
//            .setConnectionManager(cm)
//            .build();
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://bingapis.azure-api.net/api/v5/spellcheck");
//
//            builder.setParameter("mode", "spell");
//            builder.setParameter("text", "have applee");
//            
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
//            request.setHeader("Ocp-Apim-Subscription-Key", "006ff3f8e00e4079a760218c2b877efa");
//
//
//            // Request body
//            JSONObject object = new JSONObject();
////            object.put("mode", "spell");
//            object.put("text", "I have applee");
////            object.put("preContextText","");
////            object.put("postContextText","");
//            StringEntity reqEntity = new StringEntity(object.toString());
//            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
//            }
//        }
//        catch (Exception e)
//        {
//            
//        	System.out.println(e.getMessage());
//        	e.printStackTrace();
//        }
//	}
//	public static void AnalyzeImage(){
//		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//		cm.setMaxTotal(1000);        		
//	
//	CloseableHttpClient httpclient = HttpClients.custom()
//            .setConnectionManager(cm)
//            .build();
//		HttpContext context = new BasicHttpContext();
//        try
//        {
//            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/vision/v1.0/analyze");
//
//            builder.setParameter("visualFeatures", "Categories");
//            builder.setParameter("details", "Celebrities");
//
//            URI uri = builder.build();
//            HttpPost request = new HttpPost(uri);
//            request.setHeader("Content-Type", "application/json");
//            request.setHeader("Ocp-Apim-Subscription-Key", "70df41f4adf84caebd060fadc1b96517");
//
//
//            // Request body
//            String url ="http://img.sc115.com/uploads1/sc/jpgs/1504/apic19867_sc115.com.jpg";
////            JSONObject urlJSON = new JSONObject(url);
////            StringEntity reqEntity = new StringEntity(urlJSON.toString());
////            request.setEntity(reqEntity);
//
//            HttpResponse response = httpclient.execute(request,context);
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) 
//            {
//                System.out.println(EntityUtils.toString(entity));
////            	JSONArray resultArray = (JSONArray) ((JSONObject)new JSONParser().parse(EntityUtils.toString(entity))).get("entities");
////            	System.out.println(resultArray);
//            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//	}
//	public static void MicrosoftScholarAPI() throws URISyntaxException{
//		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
//		cm.setMaxTotal(1000);        		
//	
//	CloseableHttpClient httpclient = HttpClients.custom()
//            .setConnectionManager(cm)
//            .build();
//	
//    
//    HttpContext context = new BasicHttpContext();
//    JSONArray resultArray;
//    HttpGet httpget;
//    String testCompare;
//    testCompare = "Id=2107710616";
//	URIBuilder builder = new URIBuilder("https://oxfordhk.azure-api.net/academic/v1.0/evaluate");          
//    builder.setParameter("expr", testCompare);
//    builder.setParameter("count", "999999");
//    builder.setParameter("attributes", "Id,AA.AuId");
//    URI uri = builder.build();
//    HttpGet request = new HttpGet(uri);
//    request.setHeader("Ocp-Apim-Subscription-Key", "f7cc29509a8443c5b3a5e56b0e38b5a6");
//    httpget = request;
//    
//	try {
//        CloseableHttpResponse response = httpclient.execute(httpget, context);
//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//            	resultArray = (JSONArray) ((JSONObject)new JSONParser().parse(EntityUtils.toString(entity))).get("entities");
//            	System.out.println(resultArray);
//            }
//        } finally {
//            response.close();
//        }
//    } catch (Exception e) {
//        System.out.println("124行" + e);
//    }
//	}
}
