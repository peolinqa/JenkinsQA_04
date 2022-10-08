package model.projects.multiconfig;

import model.home.HomePage;
import model.RenamePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.BaseModel;

public final class MultiConfigurationProjectPageSideMenuFrame extends BaseModel<MultiConfigurationProjectPageSideMenuFrame> {

    @FindBy(linkText = "Rename")
    private WebElement menuRename;

    @FindBy(css = ".icon-edit-delete")
    private WebElement menuDelete;

    @FindBy(linkText = "Build Now")
    private WebElement menuBuildNow;

    public MultiConfigurationProjectPageSideMenuFrame(WebDriver driver) {
        super(driver);
    }

    public RenamePage<MultiConfigurationProjectPage, MultiConfigurationProjectPageSideMenuFrame> clickMenuRename() {
        menuRename.click();

        return new RenamePage<>(getDriver(), new MultiConfigurationProjectPage(getDriver()));
    }

    public HomePage clickMenuDeleteProject() {
        menuDelete.click();
        getDriver().switchTo().alert().accept();

        return new HomePage(getDriver());
    }

    public MultiConfigurationProjectPage clickMenuBuildNow() {
        menuBuildNow.click();

        return new MultiConfigurationProjectPage(getDriver());
    }
}
