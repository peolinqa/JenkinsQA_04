import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class QaEndurancePeterSolovey {
    String firstName = "Peter";
    String middleName = "O";
    String lastName = "Solovey";


    @Test
    public void PeterS1() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.get("https://google.com");
        System.out.println(d.getTitle()); // => "Google"
        d.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        WebElement searchBox = d.findElement(By.name("q"));
        WebElement searchButton = d.findElement(By.name("btnK"));
        searchBox.sendKeys("Selenium");
        searchButton.click();
        searchBox = d.findElement(By.name("q"));
        Assert.assertEquals(searchBox.getAttribute("value"), "Selenium"); // => "Selenium"
        d.quit();
    }

    @Test
    public void PeterS2() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.get("https://skryabin.com/market/quote.html");
        Dimension dim = new Dimension(1920, 1080);
        d.manage().window().setSize(dim);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateF = dateFormat.format(date);
        //WebElement currentDate = d.findElement(By.id("currentDate"));
        Assert.assertEquals(d.findElement(By.id("currentDate")).getText(), dateF);

        d.findElement(By.id("formSubmit")).click();
        Assert.assertEquals(d.findElement(By.id("name-error")).getText(), "This field is required.");

        d.findElement(By.name("name")).click();
        WebElement firstNameE = d.findElement(By.name("firstName"));
        firstNameE.sendKeys(firstName);
        WebElement middleNameE = d.findElement(By.name("middleName"));
        middleNameE.sendKeys(middleName);
        WebElement lastNameE = d.findElement(By.name("lastName"));
        lastNameE.sendKeys(lastName);
        d.findElement(By.xpath("//span[text() = 'Save']")).click();
        Thread.sleep(500);

        /*String value = d.findElement(By.id("name")).getAttribute("value");
        System.out.println(value);
        Assert.assertEquals(d.findElement(By.name("name")).getAttribute("value"), "Peter O Solovey");*/

        d.findElement(By.name("username")).sendKeys("1");
        d.findElement(By.id("formSubmit")).click();
        Assert.assertEquals(d.findElement(By.id("username-error")).getText(), "Please enter at least 2 characters.");
        //d.findElement(By.name("username")).sendKeys("PS123451645");
        d.quit();
    }

    @Test
    public void PeterS3() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.get("https://skryabin.com/market/quote.html");
        d.findElement(By.name("name")).click();
        WebElement firstNameE = d.findElement(By.name("firstName"));
        firstNameE.sendKeys(firstName);
        WebElement middleNameE = d.findElement(By.name("middleName"));
        middleNameE.sendKeys(middleName);
        WebElement lastNameE = d.findElement(By.name("lastName"));
        lastNameE.sendKeys(lastName);
        d.findElement(By.xpath("//span[text() = 'Save']")).click();
        Thread.sleep(500);
        d.findElement(By.name("username")).sendKeys("Peter12345");
        d.findElement(By.name("email")).sendKeys("12345@d.com");
        d.findElement(By.name("password")).sendKeys("123456");
        d.findElement(By.name("confirmPassword")).sendKeys("123456");


        Select selectCountry = new Select(d.findElement(By.name("countryOfOrigin")));
        selectCountry.selectByValue("Belarus");
        d.findElement(By.xpath("//input[@value = 'male']")).click();

        d.findElement(By.name("dateOfBirth")).click();
        Select selectMonth = new Select(d.findElement(By.className("ui-datepicker-month")));
        selectMonth.selectByValue("4");
        Select selectYear = new Select(d.findElement(By.className("ui-datepicker-year")));
        selectYear.selectByValue("2020");
        d.findElement(By.xpath("//table[@class = 'ui-datepicker-calendar']//a[text() = '20']")).click();
        d.findElement(By.name("allowedToContact")).click();
        d.findElement(By.id("address")).sendKeys("alskjdsngakjsngkjasgnksajgnaskghnaskuvnzdkjbndkjflgnsdkfn");

        Select selectCar = new Select(d.findElement(By.name("carMake")));
        if (selectCar.isMultiple()) {
            selectCar.selectByValue("Ford");
            selectCar.selectByValue("Toyota");
            selectCar.selectByValue("BMW");
        }

        //iframe
        d.switchTo().frame("additionalInfo");
        d.findElement(By.id("contactPersonName")).sendKeys("XXX");
        d.findElement(By.id("contactPersonPhone")).sendKeys("123456789");
        d.switchTo().parentFrame();
        d.findElement(By.name("agreedToPrivacyPolicy")).click();

        int countErrors = d.findElements(By.className("error")).size();
        System.out.println(countErrors);

        d.findElement(By.id("formSubmit")).click();

        Thread.sleep(3000);
        d.quit();

    }

    /*@Test
    public void test4() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.get("https://www.exness.com/");
        d.findElement(By.className("c591")).click();
        d.findElement(By.className("c617")).click();
        Thread.sleep(1000);
        String currentHandle = d.getWindowHandle();
        d.findElement(By.xpath("//span[@class='c178'][text() = 'Sign in']")).click();
        Thread.sleep(1000);

        Set<String> tabs = new HashSet<String>(d.getWindowHandles());
        List<String> listOfTabs = new ArrayList<String>(tabs);
        d.switchTo().window(listOfTabs.get(1));
        d.findElement(By.xpath("//input[@name = 'login']")).sendKeys("jhsdbjhas@kjnv.com");

        d.findElement(By.xpath("//a[@class = 'We8R9oQvHirB8JUpBnHbW']")).click();

        tabs = d.getWindowHandles();
        listOfTabs = new ArrayList<String>(tabs);
        d.switchTo().window(listOfTabs.get(2));
        String s = d.findElement(By.xpath("//h3[text() = 'Cyprus']")).getText();
        System.out.println(s);
        d.switchTo().window(listOfTabs.get(0));
        s = d.findElement(By.xpath("//h2[@class='c98 c104'][text() = 'Instant withdrawals, 24/7']")).getText();
        System.out.println(s);
        d.quit();
    }

    @Test
    public void test5() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver d = new ChromeDriver();
        d.get("https://www.exness.com/");
        *//*String str = "hdf";
        public void start(String str){

        }
        public void start(Integer i, String str){

        }*//*
        d.quit();
    }*/
}
