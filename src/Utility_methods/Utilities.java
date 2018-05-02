package Utility_methods;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	 WebDriver driver;
	 WebElement element;
	 WebDriverWait wait;
	public Utilities(WebDriver driver){
	this.driver=driver;	
	}
	
	/**********************************************************************
	 * PageLoadTimeOut
	 */
	public void PageLoadTimeOut(){
		
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	
	/*********************************************************************
	 * Method:waitUntiVisible imposes"Explicit Wait" on the web element until its visibility
	 * @param locator
	 * @param element
	 * @return
	 */
	public  WebElement waitUntiVisible(By locator,int time){
		wait=new WebDriverWait(driver,time);
		element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
		
	}
	
	/*********************************************************************
	 * Method:VerifyTitle Verifies title of webpage* */
	 public  void VerifyTitle(String Expectedtitle){
		 PageLoadTimeOut();
		 System.out.println("Title of current page : "+driver.getTitle());
		 if(driver.getTitle().equals(Expectedtitle))
		 System.out.println("Landed to the correct webpage");
		 else
			 System.out.println("Landed to the incorrect webpage"); 
	 }
	 
	 
	 /***********************************************************
	  * Method:typeValues enters values in inputfields
	  * @param locator
	  * @param value
	  */
	 public  void typeValues(By locator,String value){
		 waitUntiVisible(locator, 30);
		 element.click();
		 element.clear();
		 element.sendKeys(value);
	 }
	 
	 
	 /******************************************
	  * Method:Click
	  * @param locator
	  */
public  void Click(By locator){
	waitUntiVisible(locator,30);
	 element.click();
}

public boolean  VerifyAlert_Actiontaken(int time,String Expectedtitle){
	wait=new WebDriverWait(driver,time);
	
	//if(wait.until(ExpectedConditions.alertIsPresent())!=null){
	try{
	Alert a=driver.switchTo().alert();
	System.out.println("Alert message :: "+a.getText());
	if(a.getText().equals("User or Password is not valid"));
	a.dismiss();
	System.out.println("Wrong Login attempt");
	return true;
	//System.exit(0);
		}
catch(NoAlertPresentException ae){
	System.out.println("No alert is present");
	VerifyTitle(Expectedtitle);
	return false;
}
}

public boolean IsElementPresent(int time,By locator){
	try{
	wait=new WebDriverWait(driver,time);
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	return true;
	}
	catch(NoSuchElementException e){
		return false;
	}
		
	}
public boolean VerifyAlert(int time){
	wait=new WebDriverWait(driver,time);
	if(wait.until(ExpectedConditions.alertIsPresent())!=null){
		System.out.println("Alert message :: ");
		Alert a=driver.switchTo().alert();
		a.accept();
		
	}
	else
		
		System.out.println("Alert message not present");
	return false;
}



/*************************************
 * Capturescreenshot
 * @param driver
 * @param name
 * @throws IOException
 */
public void Capturescreenshot(WebDriver driver,String name) throws IOException{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	name=name+"_"+this.Date_Time();
	FileUtils.copyFile(src, new File("./Screenshot/"+name+".png"));
	
}
public String Date_Time(){
	/*Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd_hh_mm_ss");  
    String strDate = dateFormat.format(date);  */
	LocalDateTime now=LocalDateTime.now();
	DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy_HH_mm_ss");
	String Datetime=now.format(format);
	return Datetime;
	}

public 	void mouseHover(int time,By locator){
	wait=new WebDriverWait(driver, time);
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	Actions a=new Actions(driver);
	a.moveToElement(element);
}
}

