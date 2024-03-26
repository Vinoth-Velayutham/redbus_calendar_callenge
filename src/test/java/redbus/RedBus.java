package redbus;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RedBus {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);

		driver.get("https://www.redbus.in/");

		driver.manage().window().maximize();

		iGetWeekEndList("Jul 2024");

		driver.quit();
	}

	public static void iGetWeekEndList(String givenMonth) throws InterruptedException {
		driver.findElement(By.cssSelector("#onwardCal")).click();

		WebElement month = driver
				.findElement(By.cssSelector("div[class*='DayNavigator__CalendarHeader'] > div:nth-child(2)"));
		String currentMonth;
		boolean found = true;
		WebElement nextButton = driver.findElement(By.xpath("//div[@class='holiday_count']//following::div[1]"));
		do {
			currentMonth = month.getText();
			if (!(currentMonth.toLowerCase().contains(givenMonth.toLowerCase()))) {
				nextButton.click();
			} else {
				found = false;
			}
		} while (found);

		System.out.println("Given month is " + currentMonth);
		try {
			WebElement holiday = driver.findElement(By.cssSelector(".holiday_count"));
			System.out.println("Total number of Holidays in the month " + holiday.getText());
		} catch (Exception e) {
			System.out.println("Total number of Holidays in the month Zero");
		}

		List<WebElement> weekend = driver.findElements(By.cssSelector(".bwoYtA"));

		for (WebElement weekEndDates : weekend) {
			System.out.println(weekEndDates.getText());
		}
	}

}
