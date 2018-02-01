package myproject.com.Translate;



import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer;
import com.google.api.services.translate.model.TranslationsListResponse;

public class TextTranslate implements ITranslateService{
	
	
	public String translate(String text, String sourceLang, String targetLang) {
		
		String result=null;
		
		//check if any passed agrument is null
		if(text==null)
		{
			throw new IllegalArgumentException("Text must not be null");
		}
		
		if((sourceLang==null) ||(sourceLang.length()>4))
		{
			throw new IllegalArgumentException(" sourceLang must not be null or sourceLang size must be less than 4 characters");
		}
		
		if((targetLang==null)||(targetLang.length()>4))
		{
			throw new IllegalArgumentException("targetLang must not be null or targetLang size must be less than 4 characters");
			
		}
		
		
		
		//translate
		
		try {
			
			 TranslateRequestInitializer translateRequestInitializer = new TranslateRequestInitializer(
				        "AIzaSyDIJ_5e47jqPt1DBj3QWHlJbS50wm67Lho");                           //API key =AIzaSyDIJ_5e47jqPt1DBj3QWHlJbS50wm67Lho
			 
			// Set up the HTTP transport and JSON factory
			 HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			 JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

			// set up translate
			  Translate translate = new Translate.Builder(httpTransport, jsonFactory, null)
			        .setApplicationName("My Apps").setTranslateRequestInitializer(translateRequestInitializer).build();

			 List<String> sourceTextList= Arrays.asList(text); // text to be translated
			    
			    TranslationsListResponse str=translate.translations().list(sourceTextList, targetLang).execute();
			    
			    String resultStr=str.toString();
			   
			   // get translate str
			    
			    JSONObject json=new JSONObject(resultStr);
			    JSONArray json1=json.getJSONArray("translations");
			    
			    for(int i=0;i<json1.length();i++)
			    {
			    	JSONObject json2=json1.getJSONObject(i);
			    	result=json2.getString("translatedText");
			    	
			    }
			   // System.out.println("text="+r);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	
	
	
	public static void main(String args[]) throws IOException, GeneralSecurityException, JSONException
	{  
	
		TextTranslate tt=new TextTranslate();
		String text="abcd";
		
        String translated=tt.translate(text,"en", "abc");   //fr /hi/spa
        
        System.out.println("Translated text= "+ translated);
		
		
      
	}


}
