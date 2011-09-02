

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class Airad2 extends SeleneseTestCase {
	private WebDriver driver;
	private String baseUrl="www.airad.com";
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	}

	@Test
	public void testAirad() throws Exception {
		driver.get("http://www.airad.com/member.do?action=logout");
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("qinkun1234@163.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("984127");
		driver.findElement(By.cssSelector("button.btnBY.fl")).click();
		driver.findElement(By.cssSelector("a.btnS.fr > span")).click();
		driver.findElement(By.id("campaign.campaignName")).clear();
		driver.findElement(By.id("campaign.campaignName")).sendKeys("自动测试活动名称2");
		driver.findElement(By.id("startTime")).clear();
		driver.findElement(By.id("startTime")).sendKeys("2011-09-19");
		driver.findElement(By.id("buggetDay")).clear();
		driver.findElement(By.id("buggetDay")).sendKeys("50");
		driver.findElement(By.id("campaign.campaignState")).clear();
		driver.findElement(By.id("campaign.campaignState")).sendKeys("12323");
		driver.findElement(By.cssSelector("button.btnBY.fl")).click();
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
