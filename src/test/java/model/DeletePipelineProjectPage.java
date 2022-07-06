package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DeletePipelineProjectPage extends BasePage {


    public DeletePipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@data-message, 'Delete the Pipeline')]")
    private WebElement deletePipelineButton;

    public DeletePipelineProjectPage deletePipelineProject() {
        deletePipelineButton.click();
        getDriver().switchTo().alert().accept();

        return this;
    }

    public Page404Page switchToPage404() {
        getDriver().navigate().back();

        return new Page404Page(getDriver());
    }
}
