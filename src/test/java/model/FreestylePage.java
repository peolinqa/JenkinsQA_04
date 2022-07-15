package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreestylePage extends BasePage {

    @FindBy(css = "h1.page-headline")
    private WebElement projectName;

    @FindBy(linkText = "Configure")
    private WebElement configureButton;

    @FindBy(xpath = "//div[@id='description']/div")
    private WebElement textDescription;

    @FindBy(xpath = "//a[text() = 'Edit description']")
    private WebElement editDescription;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardButton;

    @FindBy(xpath = "//div//button[@type='submit']")
    private WebElement multiButton;

    @FindBy(id = "enable-project")
    private WebElement text;

    @FindBy(linkText = "Rename")
    private WebElement renameButton;

    @FindBy(xpath = "//span[text()='Delete Project']")
    private WebElement deleteProjectButton;

    public FreestylePage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }

    public FreestyleConfigPage clickFreestyleConfigure() {
        configureButton.click();

        return new FreestyleConfigPage(getDriver());
    }

    public String _disableButton() {
        return multiButton.getText();

    }

    public FreestylePage clickMultiButton() {
        multiButton.click();

        return this;
    }

    public FreestylePage clickEditDescription() {
        editDescription.click();

        return this;
    }

    public FreestylePage editDescription(String text) {
        descriptionTextarea.clear();
        descriptionTextarea.sendKeys(text);

        return this;
    }

    public RenamePage clickAdnGoToRenamePage() {
        renameButton.click();

        return new RenamePage(getDriver());
    }

    public HomePage clickDeleteProject() {
        deleteProjectButton.click();
        getDriver().switchTo().alert().accept();
        return new HomePage(getDriver());
    }

    public String getDescriptionName() {
        return textDescription.getText();
    }

    public String[] getDisableName() {
        return text.getText().split("\n");
    }
}
