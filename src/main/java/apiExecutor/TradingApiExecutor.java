package apiExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apiResponse.TradingApiResponse;
import apiValidator.TradingApiValidator;
import utils.DataInputProvider;

public class TradingApiExecutor {

	private WebDriver driver;
	protected static Properties prop;
	public static String dataSheetName;

	@BeforeTest
	public void loadObjects() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream(new File("./src/main/java/utils/objects.properties")));
		dataSheetName = "TC001";
	}

	@BeforeMethod
	public void setup() {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "./drivers/chromedriver.exe"); driver = new ChromeDriver();
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 */
		// Assign TestData Sheet Name

	}

	@Test(dataProvider = "fetchData")
	public void test(String symbol, String types) throws ClientProtocolException, IOException, JSONException {
		try
		{
		// driver.get(prop.getProperty("BaseURL"));

		String url = prop.getProperty("BaseURL") + "?" + "symbols=" + symbol + "&" + "types=" + types;
		// System.out.println(url);
		TradingApiResponse tradingApiResponse = new TradingApiResponse();
		String ExpectedString = tradingApiResponse.GetResponse(url);
		System.out.println("Actual Response from the Server:");
		System.out.println(ExpectedString);
		TradingApiValidator tradingApiValidator = new TradingApiValidator();
		tradingApiValidator.getValue(ExpectedString, symbol);
		}
		catch(Exception e){
			
			System.err.println("Unable to get the response");
			e.printStackTrace();
		}
	}
	

	@DataProvider(name = "fetchData")
	public Object[][] getData() {

		return DataInputProvider.getAllSheetData("./data/" + dataSheetName + ".xlsx");
	}

}