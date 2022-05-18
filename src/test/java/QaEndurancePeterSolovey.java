import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class QaEndurancePeterSolovey extends BaseTest {
    String firstName = "Peter";
    String middleName = "O";
    String lastName = "Solovey";

    @Test
    public void PeterS2() throws InterruptedException {
        getDriver().get("https://skryabin.com/market/quote.html");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateF = dateFormat.format(date);
        WebElement currentDate = getDriver().findElement(By.id("currentDate"));
        Assert.assertEquals(getDriver().findElement(By.id("currentDate")).getText(), dateF);

        getDriver().findElement(By.id("formSubmit")).click();
        Assert.assertEquals(getDriver().findElement(By.id("name-error")).getText(), "This field is required.");

        getDriver().findElement(By.name("name")).click();
        WebElement firstNameE = getDriver().findElement(By.name("firstName"));
        firstNameE.sendKeys(firstName);
        WebElement middleNameE = getDriver().findElement(By.name("middleName"));
        middleNameE.sendKeys(middleName);
        WebElement lastNameE = getDriver().findElement(By.name("lastName"));
        lastNameE.sendKeys(lastName);
        getDriver().findElement(By.xpath("//span[text() = 'Save']")).click();
        Thread.sleep(500);

        /*String value = getDriver().findElement(By.id("name")).getAttribute("value");
        System.out.println(value);
        Assert.assertEquals(getDriver().findElement(By.name("name")).getAttribute("value"), "Peter O Solovey");*/

        getDriver().findElement(By.name("username")).sendKeys("1");
        getDriver().findElement(By.id("formSubmit")).click();
        Assert.assertEquals(getDriver().findElement(By.id("username-error")).getText(), "Please enter at least 2 characters.");
        //getDriver().findElement(By.name("username")).sendKeys("PS123451645");
        getDriver().quit();
    }

    @Test
    public void PeterS3() throws InterruptedException {
        getDriver().get("https://skryabin.com/market/quote.html");
        getDriver().findElement(By.name("name")).click();
        WebElement firstNameE = getDriver().findElement(By.name("firstName"));
        firstNameE.sendKeys(firstName);
        WebElement middleNameE = getDriver().findElement(By.name("middleName"));
        middleNameE.sendKeys(middleName);
        WebElement lastNameE = getDriver().findElement(By.name("lastName"));
        lastNameE.sendKeys(lastName);
        getDriver().findElement(By.xpath("//span[text() = 'Save']")).click();
        Thread.sleep(500);
        getDriver().findElement(By.name("username")).sendKeys("Peter12345");
        getDriver().findElement(By.name("email")).sendKeys("12345@d.com");
        getDriver().findElement(By.name("password")).sendKeys("123456");
        getDriver().findElement(By.name("confirmPassword")).sendKeys("123456");


        Select selectCountry = new Select(getDriver().findElement(By.name("countryOfOrigin")));
        selectCountry.selectByValue("Belarus");
        getDriver().findElement(By.xpath("//input[@value = 'male']")).click();

        getDriver().findElement(By.name("dateOfBirth")).click();
        Select selectMonth = new Select(getDriver().findElement(By.className("ui-datepicker-month")));
        selectMonth.selectByValue("4");
        Select selectYear = new Select(getDriver().findElement(By.className("ui-datepicker-year")));
        selectYear.selectByValue("2020");
        getDriver().findElement(By.xpath("//table[@class = 'ui-datepicker-calendar']//a[text() = '20']")).click();
        getDriver().findElement(By.name("allowedToContact")).click();
        getDriver().findElement(By.id("address")).sendKeys("alskjdsngakjsngkjasgnksajgnaskghnaskuvnzdkjbndkjflgnsdkfn");

        Select selectCar = new Select(getDriver().findElement(By.name("carMake")));
        if (selectCar.isMultiple()) {
            selectCar.selectByValue("Ford");
            selectCar.selectByValue("Toyota");
            selectCar.selectByValue("BMW");
        }

        //iframe
        getDriver().switchTo().frame("additionalInfo");
        getDriver().findElement(By.id("contactPersonName")).sendKeys("XXX");
        getDriver().findElement(By.id("contactPersonPhone")).sendKeys("123456789");
        getDriver().switchTo().parentFrame();
        getDriver().findElement(By.name("agreedToPrivacyPolicy")).click();

        /*int countErrors = getDriver().findElements(By.className("error")).size();
        System.out.println(countErrors);*/

        getDriver().findElement(By.id("formSubmit")).click();

        Thread.sleep(1000);
        getDriver().quit();

    }

    /*@Test
    public void test4() throws InterruptedException {
        getDriver().get("https://www.exness.com/");
        getDriver().findElement(By.className("c591")).click();
        getDriver().findElement(By.className("c617")).click();
        Thread.sleep(1000);
        //String currentHandle = d.getWindowHandle();
        getDriver().findElement(By.xpath("//span[@class='c178'][text() = 'Sign in']")).click();
        Thread.sleep(1000);

        Set<String> tabs = new HashSet<String>(getDriver().getWindowHandles());
        List<String> listOfTabs = new ArrayList<String>(tabs);
        getDriver().switchTo().window(listOfTabs.get(1));
        getDriver().findElement(By.xpath("//input[@name = 'login']")).sendKeys("jhsdbjhas@kjnv.com");
        Thread.sleep(1000);

        getDriver().findElement(By.xpath("//a[@class = 'We8R9oQvHirB8JUpBnHbW']")).click();

        tabs = getDriver().getWindowHandles();
        listOfTabs = new ArrayList<String>(tabs);
        getDriver().switchTo().window(listOfTabs.get(2));
        String s = getDriver().findElement(By.xpath("//h3[text() = 'Cyprus']")).getText();
        System.out.println(s);
        getDriver().switchTo().window(listOfTabs.get(0));
        s = getDriver().findElement(By.xpath("//h2[@class='c98 c104'][text() = 'Instant withdrawals, 24/7']")).getText();
        System.out.println(s);
        getDriver().quit();
    }*/
}
