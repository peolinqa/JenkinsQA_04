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

public final class RenamePage<ProjectPage extends BaseProjectPage<?, SideMenu>, SideMenu> extends BaseProjectPage<RenamePage<?, SideMenu>, SideMenu> {

    @FindBy(xpath = "//input[@name='newName']")
    private WebElement renameInput;

    @FindBy(id = "yui-gen1-button")
    private WebElement confirmRenameButton;

    @FindBy(xpath = "//div[contains(@class, 'validation-error-area')]//div[@class='error']")
    private WebElement errorText;

    private final ProjectPage projectPage;

    public RenamePage(WebDriver driver, ProjectPage projectPage) {
        super(driver);
        this.projectPage = projectPage;
    }

    @Override
    public SideMenu getSideMenu() {
        return projectPage.getSideMenu();
    }

    public ErrorPage clickRenameAndGoToErrorPage() {
        confirmRenameButton.click();

        return new ErrorPage(getDriver());
    }

    public RenamePage<ProjectPage, SideMenu> setNewProjectName(String name) {
        renameInput.clear();
        renameInput.sendKeys(name);

        return this;
    }

    public ErrorPage setEmptyNameAndGoToErrorPage() {
        renameInput.clear();
        confirmRenameButton.click();

        return new ErrorPage(getDriver());
    }

    public ProjectPage clickRenameAndGoToProjectPage() {
        confirmRenameButton.click();

        return projectPage;
    }

    public List<String> getListErrorMessages(final List<String> names) {
        String baseName = "";
        final Pattern pattern = Pattern.compile("Rename Pipeline (\\w+)");
        final Matcher matcher = pattern.matcher(getProjectName());
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