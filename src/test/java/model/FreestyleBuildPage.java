package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreestyleBuildPage extends BaseBuildPage<FreestyleBuildPage, BuildFreestyleMultiConfigPageSideMenuFrame> {

    @FindBy(className = "jenkins-icon-adjacent")
    private WebElement buildHeader;

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement buildDescriptionField;

    @FindBy(xpath = "//a[@title='Edit Build Information']")
    private WebElement editBuildInfoMenu;

    public FreestyleBuildPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BuildFreestyleMultiConfigPageSideMenuFrame getSideMenu() {
        return new BuildFreestyleMultiConfigPageSideMenuFrame(getDriver());
    }

    public String getBuildHeader() {
        String[] textBuildHeader = buildHeader.getText().split(" ");

        return textBuildHeader[1];
    }

    public String getBuildDescription() {
        return buildDescriptionField.getText();
    }

    public EditBuildInformationPage clickEditBuildInfoButton() {
        editBuildInfoMenu.click();

        return new EditBuildInformationPage(getDriver());
    }
}
