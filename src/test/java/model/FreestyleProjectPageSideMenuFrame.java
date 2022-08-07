package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public class FreestyleProjectPageSideMenuFrame extends BaseModel<FreestyleProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(linkText = "Build Now")
    private WebElement menuBuildNow;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    public FreestyleProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<FreestyleProjectPage, FreestyleProjectPageSideMenuFrame> clickRenameAndGoToRenamePage() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new FreestyleProjectPage(getDriver()));
    }

    public FreestyleProjectPage clickMenuBuildNow() {
        menuBuildNow.click();

        return new FreestyleProjectPage(getDriver());
    }

    public HomePage clickMenuDeleteProjectAndConfirm() {
        menuDelete.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }
}
