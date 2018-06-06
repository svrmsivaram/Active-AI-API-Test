package apiResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TradingApiResponse {

	private final String USER_AGENT = "Chrome/56.02";

	public String GetResponse(String url) throws ClientProtocolException, IOException {

		StringBuffer result = new StringBuffer();
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpGet request = new HttpGet(url);
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);
			System.out.println("Get the Response Successfully");
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			String line = "";
			while ((line = reader.readLine()) != null) {

				result.append(line);
				//System.out.println(result.toString());
			}

			return result.toString();
		} catch (Exception e) {
			result.append("Get Response Failed");
			return result.toString();
		}
	}

}
