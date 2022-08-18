package model;

import model.helpPages.ErrorPage;
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
    private WebElement inputRename;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnConfirmRename;

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
        btnConfirmRename.click();

        return new ErrorPage(getDriver());
    }

    public RenamePage<ProjectPage, SideMenu> setNewProjectName(String name) {
        inputRename.clear();
        inputRename.sendKeys(name);

        return this;
    }

    public ErrorPage setEmptyName() {
        inputRename.clear();
        btnConfirmRename.click();

        return new ErrorPage(getDriver());
    }

    public ProjectPage clickRename() {
        btnConfirmRename.click();

        return projectPage;
    }

    public List<String> getErrorMessagesList(final List<String> names) {
        String baseName = "";
        final Pattern pattern = Pattern.compile("Rename Pipeline (\\w+)");
        final Matcher matcher = pattern.matcher(getProjectNameText());
        if (matcher.find()) {
            baseName = matcher.group(1);
        }

        List<String> errorMessages = new ArrayList<>();
        for (String name : names) {
            inputRename.sendKeys(name + Keys.TAB);
            errorMessages.add(errorText.getText());
            inputRename.clear();
        }
        inputRename.sendKeys(baseName + Keys.ENTER);

        return errorMessages;
    }
}
