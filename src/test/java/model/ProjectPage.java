package model;

import model.base.BaseProjectDeleteWithoutConfirmPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends BaseProjectDeleteWithoutConfirmPage {

    private static final By DISPLAY_NAME = By.cssSelector(".display-name");

    @FindBy(linkText = "Build Now")
    private WebElement buildButton;

    @FindBy(xpath = "//td[@class='build-row-cell']//a[@class='tip model-link inside build-link display-name']")
    private List<WebElement> buildList;

    @FindBy(id = "description-link")
    private WebElement addDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement addTextDescription;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveDescriptionButton;

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement descriptionValue;

    @FindBy(linkText = "Build with Parameters")
    private WebElement buildWithParameters;

    @FindBy(linkText = "Parameters")
    private WebElement parameters;

    @FindBy(xpath = "//a[@href ='lastBuild/']")
    private WebElement lastBuildButton;

    @FindBy(xpath = "//ul[@class='permalinks-list']/li")
    private List<WebElement> permalinks;

    @FindBy(xpath = "//td[@class='build-row-cell']//a[contains(text(),'#')]")
    private WebElement buildIcon;

    @FindBy(xpath = "//a[@class='tip model-link inside build-link display-name']")
    private WebElement buildName;

    @FindBy(xpath = "//div[@class='pane desc indent-multiline']")
    private WebElement buildDescription;

    @FindBy(xpath = "//span[text()='Back to Dashboard']")
    private WebElement backToDashboard;

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public RenamePage<ProjectPage> clickRenameAndGoToRenamePage() {
        clickRenameButton();

        return new RenamePage<>(getDriver(), new ProjectPage(getDriver()));
    }

    @Override
    public String getProjectName() {
        return projectName.getText().substring("Project ".length());
    }

    public boolean isProjectStatus(String value) {
        return getWait20().until(ExpectedConditions.attributeToBe(
                By.cssSelector(".tobsTable-body .job"), "class", value));
    }

    public ProjectPage clickBuildButton() {
        buildButton.click();

        return this;
    }

    public ProjectPage clickBuildButtonWait() {
        buildButton.click();
        getWait20().until(ExpectedConditions.elementToBeClickable(buildIcon));

        return this;
    }

    public ProjectPage clickAddDescription() {
        addDescription.click();

        return this;
    }

    public ProjectPage addTextDescriptionAndSave(String textDescription) {
        addTextDescription.sendKeys(textDescription);
        saveDescriptionButton.click();

        return this;
    }

    public ProjectPage clearUserDescription() {
        addDescription.click();
        addTextDescription.clear();
        saveDescriptionButton.click();

        return this;
    }

    public boolean checkDescriptionValue() {
        return descriptionValue.getText().isEmpty();
    }

    public ProjectPage waitForBuildToComplete() {
            getWait20().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".display-name")));

        return this;
    }

    public String getBuildNumber() {
        WebElement displayName = getWait20().until(ExpectedConditions.presenceOfElementLocated(DISPLAY_NAME));

        return displayName.getText().substring("#".length());
    }

    public boolean buildNumberIsDisplayed() {
        return getWait20().until(ExpectedConditions.presenceOfElementLocated(DISPLAY_NAME)).isDisplayed();
    }

    public ProjectPage waitForBuildNumber(int number) {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(
                "//a[@class='tip model-link inside build-link display-name' and .='#%s']", number))));

        return this;
    }

    public ProjectPage clickMultipleTimesBuildButton(int number) {
        for (int i = 0; i < number; ++i) {
            buildButton.click();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return this;
    }

    public ProjectPage refreshPage() {
        getDriver().navigate().refresh();

        return this;
    }

    public List<Integer> getNumbersBuildsList() {
        List<Integer> buildNumberList = new ArrayList<>();

        for (WebElement element : buildList) {
            buildNumberList.add(Integer.parseInt(element.getText().replace("#", "")));
        }

        return buildNumberList;
    }

    public BuildWithParametersPage clickBuildWithParameters() {
        buildWithParameters.click();

        return new BuildWithParametersPage(getDriver());
    }

    public ProjectPage clickLastBuildButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(lastBuildButton)).click();

        return this;
    }

    public LastBuildPage selectLastBuild() {
        getWait5().until(ExpectedConditions.elementToBeClickable(lastBuildButton)).click();

        return new LastBuildPage(getDriver());
    }

    public BuildParametersPage clickParametersButton() {
        parameters.click();

        return new BuildParametersPage(getDriver());
    }

    public HomePage clickBackToDashboard() {
        backToDashboard.click();

        return new HomePage(getDriver());
    }

    public String[] permalinksText() {
        final int substringLength = "build".length();
        String[] permalinksText = new String[permalinks.size()];

        for (int i = 0; i < permalinksText.length; i++) {
            permalinksText[i] = permalinks.get(i).getText().substring(0,
                    permalinks.get(i).getText().indexOf("build") + substringLength);
        }

        return permalinksText;
    }

    public String getPipelineProjectName() {
        return projectName.getText().substring("Pipeline ".length());
    }

    public String getBuildName() {
        return buildName.getText();
    }

    public String getBuildDescription() {
        return  buildDescription.getText();
    }
}
