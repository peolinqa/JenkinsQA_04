package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePipelineProject extends BasePage {


    public DeletePipelineProject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@data-message, 'Delete the Pipeline')]")
    private WebElement deletePipelineButton;

    public DeletePipelineProject deletePipelineProject() {
        deletePipelineButton.click();
        getDriver().switchTo().alert().accept();

        return this;
    }

    public Page404 switchToPage404() {
        getDriver().navigate().back();

        return new Page404(getDriver());
    }
}
