import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class StanKarpovTest extends BaseTest  {

    @Test
    public void testEmailRegistration(){
        getDriver().get("https://geteasyqa.com/");

        WebElement emailBox = getDriver().findElement(By.xpath("//input[@class='submit-try-field rounded-left emailSub']"));
        emailBox.sendKeys("test@email.com");
        getDriver().findElement(By.xpath("//button[@title='Try For Free']")).click();

        WebElement firstName = getDriver().findElement(By.xpath("//input[@id='user_first_name']"));
        WebElement lastName = getDriver().findElement(By.xpath("//input[@id='user_last_name']"));
        WebElement newPassword = getDriver().findElement(By.xpath("//form[@id='new_user_registration']//input[@id='user_password']"));
        WebElement passwordConfirmation = getDriver().findElement(By.xpath("//input[@id='user_password_confirmation']"));
        WebElement signUp = getDriver().findElement(By.xpath("//input[@value='Sign up']"));
        firstName.sendKeys("FirstName");
        lastName.sendKeys("LastName");
        newPassword.sendKeys("Password123");
        passwordConfirmation.sendKeys("Password123");
        signUp.click();
        WebElement sorry = getDriver().findElement(By.xpath("//h2[normalize-space()='Sorry :(']"));
        Assert.assertEquals(sorry.getText(), "Sorry :(");
    }

    @Test
    public void testPricing(){
        getDriver().get("https://geteasyqa.com/");

        WebElement pricing = getDriver().findElement(By.xpath("//a[@title='Pricing']"));
        pricing.click();
        WebElement number = getDriver().findElement(By.xpath("//input[@id='rangeInput500']"));
        number.sendKeys("00");

        WebElement slider = getDriver().findElement(By.xpath("//div[@class='rangeslider__handle']"));
        Actions move = new Actions(getDriver());
        move.moveToElement(slider).clickAndHold().moveByOffset(250, 0).release().perform();

        WebElement tryButton = getDriver().findElement(By.xpath("//div[@Class='front _tabForCloud']//a[text()='Try']"));
        tryButton.click();

        WebElement firstName = getDriver().findElement(By.xpath("//input[@id='user_first_name']"));
        WebElement lastName = getDriver().findElement(By.xpath("//input[@id='user_last_name']"));
        WebElement emailBox = getDriver().findElement(By.xpath("//input[@id='registration_user_email']"));
        WebElement newPassword = getDriver().findElement(By.xpath("//form[@id='new_user_registration']//input[@id='user_password']"));
        WebElement passwordConfirmation = getDriver().findElement(By.xpath("//input[@id='user_password_confirmation']"));
        WebElement signUp = getDriver().findElement(By.xpath("//input[@value='Sign up']"));
        firstName.sendKeys("FirstName");
        lastName.sendKeys("LastName");
        emailBox.sendKeys("test@email.com");
        newPassword.sendKeys("Password123");
        passwordConfirmation.sendKeys("Password123");
        signUp.click();

        WebElement sorry = getDriver().findElement(By.xpath("//h2"));
        Assert.assertEquals(sorry.getText(), "Sorry :(");
    }

    @Test
    public void testIntegrations(){
        getDriver().get("https://geteasyqa.com/");

        WebElement integrations = getDriver().findElement(By.xpath("//li[@id='menu-item-13223']/a"));
        integrations.click();
        WebElement comments = getDriver().findElement(By.xpath("//textarea[@id='comment']"));
        comments.sendKeys("Hello world!");
        WebElement checkbox = getDriver().findElement(By.xpath("//input[@id='wp-comment-cookies-consent']"));
        checkbox.click();
        WebElement sendButton = getDriver().findElement(By.xpath("//input[@id='submit']"));
        sendButton.click();
        WebElement name = getDriver().findElement(By.xpath("//input[@id='author']"));
        name.sendKeys("Full Name");
        WebElement email = getDriver().findElement(By.xpath("//input[@id='email']"));
        email.sendKeys("test@test.com");
        sendButton.click();

        WebElement noComments = getDriver().findElement(By.className("number"));
        Assert.assertEquals(noComments.getText(), "No");
    }
}
