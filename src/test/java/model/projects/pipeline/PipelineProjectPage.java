package model.projects.pipeline;

import model.base.BaseProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PipelineProjectPage extends BaseProjectPage<PipelineProjectPage, PipelineProjectPageSideMenuFrame> {

    @FindBy(css = "#description > div:nth-child(1)")
    private WebElement textDescription;

    @FindBy(id = "description-link")
    private WebElement btnAddDescription;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement textareaDescription;

    @FindBy(id = "yui-gen2-button")
    private WebElement btnSaveDescription;

    @FindBy(xpath = "//td[@class='build-row-cell']//a[contains(text(),'#')]")
    private WebElement buildName;

    @FindBy(xpath = "//ul[@class='permalinks-list']/li")
    private List<WebElement> listPermalinks;

    @FindBy(xpath = "//a[@href ='lastBuild/']")
    private WebElement linkLastBuild;

    @FindBy(linkText = "Parameters")
    private WebElement menuParameters;

    @FindBy(css = ".build-name a.display-name")
    private List<WebElement> listBuildNamesOnPaneBuildHistory;

    public PipelineProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectPageSideMenuFrame getSideMenu() {
        return new PipelineProjectPageSideMenuFrame(getDriver());
    }

    @Override
    public String getProjectNameText() {
        return super.getProjectNameText().substring("Pipeline ".length());
    }

    public PipelineProjectPage clearProjectDescription() {
        btnAddDescription.click();
        textareaDescription.clear();
        btnSaveDescription.click();

        return this;
    }

    public boolean isDescriptionValueEmpty() {
        return textDescription.getText().isEmpty();
    }

    public PipelineProjectPage addTextDescriptionAndSave(String textDescription) {
        textareaDescription.sendKeys(textDescription);
        btnSaveDescription.click();

        return this;
    }

    public PipelineProjectPage clickAddDescriptionButton() {
        btnAddDescription.click();

        return this;
    }

    public PipelineProjectPage clickBuildNameWait() {
        getWait20().until(ExpectedConditions.elementToBeClickable(buildName));

        return this;
    }

    public PipelineProjectPage refreshPage() {
        getDriver().navigate().refresh();

        return this;
    }

    public String[] getPermalinksTextArray() {
        final int substringLength = "build".length();
        String[] permalinksText = new String[listPermalinks.size()];

        for (int i = 0; i < permalinksText.length; i++) {
            permalinksText[i] = listPermalinks.get(i).getText().substring(0,
                    listPermalinks.get(i).getText().indexOf("build") + substringLength);
        }

        return permalinksText;
    }

    public boolean isProjectStatusSuccess(String value) {
        return getWait20().until(ExpectedConditions.attributeToBe(
                By.cssSelector(".tobsTable-body .job"), "class", value));
    }

    public PipelineProjectPage clickLinkLastBuild() {
        getWait5().until(ExpectedConditions.elementToBeClickable(linkLastBuild)).click();

        return this;
    }

    public BuildParametersPage clickMenuParameters() {
        menuParameters.click();

        return new BuildParametersPage(getDriver());
    }

    public PipelineProjectPage waitForBuildNumber(int number) {
        getWait20().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(
                "//a[@class='tip model-link inside build-link display-name' and .='#%s']", number))));

        return this;
    }

    public List<Integer> getNumbersBuildsList() {
        List<Integer> buildNumberList = new ArrayList<>();

        for (WebElement element : listBuildNamesOnPaneBuildHistory) {
            buildNumberList.add(Integer.parseInt(element.getText().replace("#", "")));
        }

        return buildNumberList;
    }
}
