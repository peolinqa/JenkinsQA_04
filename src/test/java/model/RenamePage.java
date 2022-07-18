package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenamePage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement renameButton;

    @FindBy(xpath = "//div[contains(@class, 'validation-error-area')]//div[@class='error']")
    private WebElement errorText;

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    public RenamePage(WebDriver driver) {
        super(driver);
    }

    public FolderPage clickRenameAndGoToFolder() {
        renameButton.click();

        return new FolderPage(getDriver());
    }

    public ErrorPage clickRenameAndGoToErrorPage() {
        renameButton.click();

        return new ErrorPage(getDriver());
    }

    public RenamePage setNewProjectName(String name) {
        renameInput.clear();
        renameInput.sendKeys(name);

        return this;
    }

    public FreestylePage clickRenameAndGoToFreestyle() {
        renameButton.click();

        return new FreestylePage(getDriver());
    }

    public OrganizationFolderProjectPage clickRenameAndGoToOrganizationFolder() {
        renameButton.click();

        return new OrganizationFolderProjectPage(getDriver());
    }

    public RenamePage clickBack() {
        getDriver().navigate().back();

        return new RenamePage(getDriver());
    }

    public MultiConfigurationProjectPage clickRenameAndGoToMultiConfigurationProject() {
        renameButton.click();

        return new MultiConfigurationProjectPage(getDriver());
    }

    public ErrorPage setInvalidNameAndGoToErrorPage() {
        renameButton.click();

        return new ErrorPage(getDriver());
    }

    public ErrorPage setEmptyNameAndGoToErrorPage() {
        renameInput.clear();
        renameButton.click();

        return new ErrorPage(getDriver());
    }

    public ProjectPage clickRenameAndGoToProjectPage() {
        renameButton.click();

        return new ProjectPage(getDriver());
    }

    public List<String> getListErrorMessages(final List<String> names) {
        String baseName = "";
        final Pattern pattern = Pattern.compile("Rename Pipeline (\\w+)");
        final Matcher matcher = pattern.matcher(pageHeader.getText());
        if (matcher.find()) {
            baseName = matcher.group(1);
        }

        List<String> errorMessages = new ArrayList<>();
        for (String name : names) {
            renameInput.sendKeys(name + Keys.TAB);
            errorMessages.add(errorText.getText());
            renameInput.clear();
        }
        renameInput.sendKeys(baseName + Keys.ENTER);

        return errorMessages;
    }
}