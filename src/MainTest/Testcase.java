package MainTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Logintowebsite.Login;
import Propertymethod.DatafromPropertyFile;

public class Testcase {
	String file="G:\\Misc_Nest\\Guru99_Projects\\src\\Constants.properties";
	WebDriver driver;
	Login l;
  @BeforeTest
  @Parameters("browser")
  public void LaunchWebsite(String browser) throws FileNotFoundException, IOException {
	  
	  switch (browser) {
		case "CHROME":  
	  System.setProperty("webdriver.chrome.driver", DatafromPropertyFile.ReadfromPropertyfile(file,"ChromeDriver"));
	  driver=new ChromeDriver();
	  default:
		  break;
		 }
	  driver.get(DatafromPropertyFile.ReadfromPropertyfile(file,"URL"));
	  }
  /*Test to check the user login*/
  @Test
  @Parameters({"Username","Pass"})
  public void Executetestcase(String Username,String Pass) throws FileNotFoundException, IOException{
	  l=new Login(driver);
	  l.validatePage();
	  l.Log_in(Username,Pass);

	 
  }
  
}
