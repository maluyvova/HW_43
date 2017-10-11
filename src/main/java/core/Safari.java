package core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Safari {
	static WebDriver driver;
	public static void main (String [] args) {
		
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);
		
		if (!System.getProperty("os.name").contains("Mac")) {throw new IllegalArgumentException("Safari is available only on Mac");}
		
		driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,15);
		String url = "http://alex.academy/exe/payment_tax/index5.html";
		driver.get(url);
		String payment = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_monthly_payment_and_tax"))).getText();


		 String regex =  "^"
				
				 +"(\\d{2,}\\.\\d{2,})?"
				 + "(?:\\s)?"
				 +"(\\d\\.\\d{2,})?"
				
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

		

		                      WebElement line = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_validate_button")));
		            	     ((JavascriptExecutor)driver).executeScript("arguments[0].click();", line);

		

		                     String result = driver.findElement(By.id("id_result")).getText();


	                         System.out.println("Browser Safari");
	                         System.out.println("http://alex.academy/exe/payment_tax/index5.html");
		                     System.out.println("String: \t" + payment);



		                     System.out.println("Annual Payment with Tax: " + annual);

		

		                     System.out.println("Result: \t" + result);


		                     driver.quit();


		       }



		
		

	    
		
		
		
		}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
