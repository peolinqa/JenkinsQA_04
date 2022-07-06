package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PipelinePluginPage extends BasePage {

    public PipelinePluginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement titleRedirectedPage;

    public void checkRedirectionPageAndAssert(String getText) {
        String pageTitle = titleRedirectedPage.getText();
        getDriver().navigate().back();

        Assert.assertEquals(pageTitle, getText);
    }
}
