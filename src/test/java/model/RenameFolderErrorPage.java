package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenameFolderErrorPage extends FolderPage {

    @FindBy(xpath = "//h1")
    private WebElement error;

    public RenameFolderErrorPage(WebDriver driver) {
        super(driver);
    }

    public String errorMessage() {

        return error.getText();
    }
}
