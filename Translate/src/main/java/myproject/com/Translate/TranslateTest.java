package myproject.com.Translate;

import static org.junit.Assert.*;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

public class TranslateTest {

	
	//check if passed text are null
	@Test(expected= IllegalArgumentException.class)
	public void textNull() {
		String text=null;
		TextTranslate t=new TextTranslate();
		String r= t.translate(text, "en", "fr");		
	}
	
	//test for the passed language types
	@Test(expected= IllegalArgumentException.class)
	public void textSourcelang() {
		String text="Hello World";
		TextTranslate t=new TextTranslate();
		String r= t.translate(text, null, "fr");		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void textTargetSize() {
		String text="Hello World";
		TextTranslate t=new TextTranslate();
		String r= t.translate(text, "en", "french");		
	}
	
	//test translation
	@Test
	public void TestTranslation()
	{
		String text="Hello World";
		TextTranslate t=new TextTranslate();
		//test for french
		String r= t.translate(text, "en", "fr");		
		Assert.assertEquals("Bonjour le monde",r);
		
		//test for spanish
		String r1= t.translate(text, "en", "spa");		
		Assert.assertEquals("Hola Mundo",r1);
		
		//test for italien
		String r3= t.translate(text, "en", "it");		
		Assert.assertEquals("Ciao mondo",r3);
				
	}
	
	//if the entered text is not a real language server returns 400 bad request	
	@Test
	public void WrongeKey() throws GoogleJsonResponseException
	{   
		System.out.println("Target language is not a real language"+"\n Server response code :400 Bad Request");
		
		String r=null;
		try {
		
		String text="hello";
		TextTranslate t=new TextTranslate();
		
		 r= t.translate(text, "en", "abc");	
		}
		catch(Exception e)
		{
			//System.out.println("exception="+e.getCause().getCause().getMessage().substring(0, 39));
			Assert.assertEquals("null",r);	
		}
				
	}

}
