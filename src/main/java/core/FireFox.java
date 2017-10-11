package core;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.*;
import java.util.logging.*;

public class FireFox {
	static WebDriver driver;
	
	public static void main (String [] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);
		
       String driverPath=""; 
        if (System.getProperty("os.name").toUpperCase().contains("MAC"))   driverPath="./resources/webdrivers/mac/geckodriver.sh";
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) driverPath = "";
	System.setProperty("webdriver.gecko.driver", driverPath);
	
	
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	WebDriverWait wait = new WebDriverWait(driver,15);
	String url = "http://alex.academy/exe/payment_tax/indexE.html";
	driver.get(url);
	String payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_monthly_payment_and_tax"))).getText();
	
	
	String regex =  "^"
			 +"(?:\\w{1,}\\:\\s\\$)"
			 +"(\\d{2,}\\.\\d{2,})?"
			 + "(?:\\,\\s)?"
			 +"(?:\\w{3,}\\:\\s)"
			 +"(\\d\\.\\d{2,})?"
			 +"(?:\\%)"
			
	+ "$";
    

	 Pattern p = Pattern.compile(regex);
	Matcher m = p.matcher(payment);
	m.find();
	  double monthly_payment = Double.parseDouble(m.group(1).replaceAll(",", ""));
	  double tax = Double.parseDouble(m.group(2));
	double annual_payment = new BigDecimal((monthly_payment * tax)/100).setScale(2, RoundingMode.HALF_UP).doubleValue();

	double with_tax = new BigDecimal(monthly_payment + annual_payment).setScale(2, RoundingMode.HALF_UP).doubleValue();
	double annual = new BigDecimal(with_tax * 12).setScale(2, RoundingMode.HALF_UP).doubleValue();

	                    
	


	

	driver.findElement(By.id("id_annual_payment_with_tax")).sendKeys(String.valueOf(annual));

	

	                     driver.findElement(By.id("id_validate_button")).submit();

	

	                     String result = driver.findElement(By.id("id_result")).getText();


                         System.out.println("Browser FireFox");
                         System.out.println("http://alex.academy/exe/payment_tax/indexE.html");
	                     System.out.println("String: \t" + payment);



	                     System.out.println("Annual Payment with Tax: " + annual);

	

	                     System.out.println("Result: \t" + result);


	                     driver.quit();


	       }



	
	

    
	
	
	
	}


