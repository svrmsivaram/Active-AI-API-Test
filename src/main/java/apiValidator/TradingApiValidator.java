package apiValidator;

import org.json.JSONException;
import org.json.JSONObject;

public class TradingApiValidator {

	public void getValue(String response, String symbols) throws JSONException {

		String sym[] = symbols.split(",");
		JSONObject jo = new JSONObject(response);
		
		try{
		for (String string : sym) {

			System.out.println("\n*************************************************************************");
			JSONObject jo1 = jo.getJSONObject(string.toUpperCase());
			JSONObject ja = jo1.getJSONObject("delayed-quote");
			System.out.println("Product Name is: " + string.toUpperCase() + "\n");
			System.out.println("Delayed Price Value is: " + ja.getString("delayedPrice"));
			System.out.println("High Value is: " + ja.getString("high"));
			System.out.println("Low Values is: " + ja.getString("low"));
			System.out.println("Delayed Size Value is: " + ja.getString("delayedSize"));

		}
		}
		catch (Exception e){
			System.err.println("Unble to redrive the value from the response");
			e.printStackTrace();
			
		}

	}

}
