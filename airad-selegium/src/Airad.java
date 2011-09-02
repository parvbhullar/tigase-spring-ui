

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class Airad extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://www.airad.com/";
		Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
		System.out.println("selenium="+selenium);
		//selenium.start();
	}

	@Test
	public void testAirad() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.eharmony.com/login");
        driver.findElement(By.name("j_username")).sendKeys("username");
        driver.findElement(By.name("j_password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("My Matches")).click();
        driver.findElement(By.linkText("New")).click();
        try {
            while (true) {
                try {
                    driver.findElement(By.linkText("View Match Details")).click();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    driver.findElement(By.linkText("Request my photo")).click();
                } catch (Exception e) {}
                driver.findElement(By.linkText("Send her a Message")).click();
                driver.findElement(By.name("chooseQuestionsButton.x")).click(); // Send Questions
                driver.findElement(By.linkText("Return to My Matches")).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

//		System.out.println("selenium="+selenium);
//		selenium.open("http://www.airad.com/");
//		selenium.type("id=email", "qinkun1234@163.com");
//		selenium.type("id=password", "984127");
//		selenium.click("css=button.btnBY.fl");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("css=a.btnS.fr > span");
//		selenium.waitForPageToLoad("30000");
//		selenium.type("id=campaign.campaignName", "自动测试活动名称2");
//		selenium.type("id=buggetDay", "50");
//		selenium.type("id=campaign.campaignState", "12323");
//		selenium.click("css=button.btnBY.fl");
//		selenium.waitForPageToLoad("30000");

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
