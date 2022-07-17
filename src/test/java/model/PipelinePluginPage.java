package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PipelinePluginPage extends BasePage {

    public PipelinePluginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement titleRedirectedPage;

    public String checkRedirectionPage() {
        String pageTitle = titleRedirectedPage.getText();
        getDriver().navigate().back();

        return pageTitle;
    }
}