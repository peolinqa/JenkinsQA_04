package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenamePage<ConfigPage> extends BaseProjectPage {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameInput;

    @FindBy(id = "yui-gen1-button")
    private WebElement confirmRenameButton;

    @FindBy(xpath = "//div[contains(@class, 'validation-error-area')]//div[@class='error']")
    private WebElement errorText;

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    private final ConfigPage configPage;

    public RenamePage(WebDriver driver, ConfigPage configPage) {
        super(driver);
        this.configPage = configPage;
    }

    public ErrorPage clickRenameAndGoToErrorPage() {
        confirmRenameButton.click();

        return new ErrorPage(getDriver());
    }

    public RenamePage<ConfigPage> setNewProjectName(String name) {
        renameInput.clear();
        renameInput.sendKeys(name);

        return this;
    }

    public void clickBack() {
        getDriver().navigate().back();
    }

    public ErrorPage setInvalidNameAndGoToErrorPage() {
        confirmRenameButton.click();

        return new ErrorPage(getDriver());
    }

    public ErrorPage setEmptyNameAndGoToErrorPage() {
        renameInput.clear();
        confirmRenameButton.click();

        return new ErrorPage(getDriver());
    }

    public ConfigPage clickRenameAndGoToProjectPage() {
        confirmRenameButton.click();

        return configPage;
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