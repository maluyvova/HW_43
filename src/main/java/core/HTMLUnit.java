package core;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import java.util.regex.*;


public class HTMLUnit {
	
	
	public static void main (String [] args) throws InterruptedException {
		Logger.getLogger("").setLevel(Level.OFF);
		
     
       WebDriver driver =new HtmlUnitDriver();
	
	
	((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
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

	                    
	


	

	driver.findElement(By.id("http://alex.academy/exe/payment_tax/indexE.html")).sendKeys(String.valueOf(annual));

	

	                     driver.findElement(By.id("id_validate_button")).submit();

	

	                     String result = driver.findElement(By.id("id_result")).getText();


                         System.out.println("Browser HMLUnit");
                         System.out.println("http://alex.academy/exe/payment_tax/index4.html");
	                     System.out.println("String: \t" + payment);



	                     System.out.println("Annual Payment with Tax: " + annual);

	

	                     System.out.println("Result: \t" + result);


	                     driver.quit();


	       }



	
	

    
	
	
	
	}


