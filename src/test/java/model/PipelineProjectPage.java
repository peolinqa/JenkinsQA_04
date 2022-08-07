package model;

import model.base.BaseProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PipelineProjectPage extends BaseProjectPage<PipelineProjectPage, PipelineProjectPageSideMenuFrame> {

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement descriptionValue;

    @FindBy(id = "description-link")
    private WebElement addDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement addTextDescription;

    @FindBy(id = "yui-gen2-button")
    private WebElement saveDescriptionButton;

    @FindBy(xpath = "//td[@class='build-row-cell']//a[contains(text(),'#')]")
    private WebElement buildIcon;

    @FindBy(xpath = "//ul[@class='permalinks-list']/li")
    private List<WebElement> permalinks;

    @FindBy(xpath = "//a[@href ='lastBuild/']")
    private WebElement lastBuildButton;

    @FindBy(linkText = "Parameters")
    private WebElement parameters;

    @FindBy(xpath = "//td[@class='build-row-cell']//a[@class='tip model-link inside build-link display-name']")
    private List<WebElement> buildList;

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectPageSideMenuFrame getSideMenu() {
        return new PipelineProjectPageSideMenuFrame(getDriver());
    }

    public PipelineProjectPage clearUserDescription() {
        addDescription.click();
        addTextDescription.clear();
        saveDescriptionButton.click();

        return this;
    }

    public boolean checkDescriptionValue() {
        return descriptionValue.getText().isEmpty();
    }

    public PipelineProjectPage addTextDescriptionAndSave(String textDescription) {
        addTextDescription.sendKeys(textDescription);
        saveDescriptionButton.click();

        return this;
    }

    public PipelineProjectPage clickAddDescription() {
        addDescription.click();

        return this;
    }

    public PipelineProjectPage clickBuildButtonWait() {
        getWait20().until(ExpectedConditions.elementToBeClickable(buildIcon));

        return this;
    }

    public PipelineProjectPage refreshPage() {
        getDriver().navigate().refresh();

        return this;
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

    @Override
    public String getProjectName() {
        return super.getProjectName().substring("Pipeline ".length());
    }

    public boolean isProjectStatus(String value) {
        return getWait20().until(ExpectedConditions.attributeToBe(
                By.cssSelector(".tobsTable-body .job"), "class", value));
    }

    public PipelineProjectPage clickLastBuildButton() {
        getWait5().until(ExpectedConditions.elementToBeClickable(lastBuildButton)).click();

        return this;
    }

    public BuildParametersPage clickParametersButton() {
        parameters.click();

        return new BuildParametersPage(getDriver());
    }

    public PipelineProjectPage waitForBuildNumber(int number) {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(
                "//a[@class='tip model-link inside build-link display-name' and .='#%s']", number))));

        return this;
    }

    public List<Integer> getNumbersBuildsList() {
        List<Integer> buildNumberList = new ArrayList<>();

        for (WebElement element : buildList) {
            buildNumberList.add(Integer.parseInt(element.getText().replace("#", "")));
        }

        return buildNumberList;
    }
}
