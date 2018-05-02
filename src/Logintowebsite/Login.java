package Logintowebsite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Propertymethod.DatafromPropertyFile;
import Utility_methods.Utilities;
  
public class Login {
	WebDriver driver;
	Logger log;
	Utilities util;
	String file="G:\\Misc_Nest\\Guru99_Projects\\src\\Constants.properties";
	By UserID=By.xpath("//input[@type='text'][@name='uid']");
	By Password=By.xpath("//input[@type='password'][@name='password']");
	By LOGIN=By.xpath("//input[@type='submit'][@name='btnLogin']");
	By RESET=By.xpath("//input[@type='reset'][@name='btnReset']");
    String URL;
	public Login(WebDriver driver) throws FileNotFoundException, IOException{
	this.driver=driver;
	log=Logger.getLogger("Events");
	util=new Utilities(driver);
	URL=DatafromPropertyFile.ReadfromPropertyfile(file,"URL");
	}
	public void validatePage() throws IOException{
		util.VerifyTitle(URL);
		util.Capturescreenshot(driver, "LoginPage");
	}
	public void Log_in(String Username,String Pass) throws FileNotFoundException, IOException{
PropertyConfigurator.configure("Log4j.properties");
util.typeValues(UserID,Username);
util.Capturescreenshot(driver, "UserID");
log.info("User ID entered");
util.typeValues(Password,Pass);
util.Capturescreenshot(driver, "Password");
log.info("Password entered");
util.Click(LOGIN);
if(util.VerifyAlert_Actiontaken(30,DatafromPropertyFile.ReadfromPropertyfile(file,"Title_HomePage"))==false){
log.info("Title of Home webpage is verified");
log.info("Login Successful");
util.Capturescreenshot(driver,"HomePage");
driver.findElement(By.linkText(DatafromPropertyFile.ReadfromPropertyfile(file, "Logout"))).click();
util.VerifyAlert_Actiontaken(30,DatafromPropertyFile.ReadfromPropertyfile(file,"Title_LogoutPage"));
	} 
	}

	}



