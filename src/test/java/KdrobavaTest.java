import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import runner.BaseTest;

public class KdrobavaTest extends BaseTest{

        @Test
        public void automationpracticeTest () throws InterruptedException {
            getDriver().get("https://automationpractice.com");

            WebElement signIn = getDriver().findElement(By.xpath("//div[@class = 'header_user_info']/a"));
            Thread.sleep(2000);
            signIn.click();

            WebElement emailAddressCreateAccount = getDriver().findElement(By.xpath("//input[@id = 'email_create']"));
            emailAddressCreateAccount.click();
            emailAddressCreateAccount.sendKeys("dddddd@gmail.com");

            WebElement createAccountButton =
                    getDriver().findElement(By.xpath("//span[normalize-space()='Create an account']"));

            Thread.sleep(2000);
            createAccountButton.click();

            WebElement mrsElement = getDriver().findElement(By.xpath("//input[@id='id_gender2']"));
            mrsElement.click();

            WebElement firstNameCreate = getDriver().findElement(By.xpath("//input[@id='customer_firstname']"));
            firstNameCreate.sendKeys("Kristina");

            WebElement lastNameCreate = getDriver().findElement(By.xpath("//input[@id = 'customer_lastname']"));
            lastNameCreate.sendKeys("Drobava");

            WebElement passwordCreate = getDriver().findElement(By.xpath("//input[@id = 'passwd']"));
            passwordCreate.sendKeys("zxcvbnm123");

            WebElement dateOfBirth = getDriver().findElement(By.xpath("//select[@id='days']"));
            dateOfBirth.click();
            dateOfBirth.sendKeys("21");

        }
}
