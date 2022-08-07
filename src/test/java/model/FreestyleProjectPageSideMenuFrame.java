package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class FreestyleProjectPageSideMenuFrame extends BaseModel<FreestyleProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    public FreestyleProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<FreestyleProjectPage, FreestyleProjectPageSideMenuFrame> clickRenameAndGoToRenamePage() {
        renameButton.click();

        return new RenamePage<>(getDriver(), new FreestyleProjectPage(getDriver()));
    }
}
