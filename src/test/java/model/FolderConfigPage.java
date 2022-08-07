package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class FolderConfigPage extends BaseHeaderFooterPage {

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(css = "textarea.setting-input")
    private WebElement folderDescription;

    @FindBy(linkText = "Preview")
    private WebElement folderDescriptionPreview;

    @FindBy(id = "yui-gen3-button")
    private WebElement clickMetricsButton;

    @FindBy(id = "yui-gen1-button")
    private WebElement clickAddMetricButton;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement clickMetricsItem;

    @FindBy(xpath = "//b[text()='Child item with worst health']")
    private WebElement getMetricElement;

    @FindBy(xpath = "//button[@title ='Remove']")
    private WebElement deleteHealthMetric;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage setFolderDescription (String text) {
        folderDescription.sendKeys(text);

        return this;
    }

    public FolderConfigPage clickFolderDescriptionPreview() {
        folderDescriptionPreview.click();

        return new FolderConfigPage(getDriver());
    }

    public String getFolderDescriptionPreviewText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='textarea-preview'][@style='']"))).getText();
    }

    public FolderProjectPage saveConfigAndGoToFolderPage() {
        saveButton.click();

        return new FolderProjectPage(getDriver());
    }

    public FolderConfigPage openFolderMenuSelector(String folderName){
        getActions().moveToElement(getDriver().findElement((
                By.xpath(String.format("//a[@href='/job/%s/']", folderName))))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();

        return this;
    }

    public FolderProjectPage clickDeleteOnMenuSelector(){
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Folder')]")).click();

        return new FolderProjectPage(getDriver());
    }

    public FolderConfigPage clickMetricsButton() {
        clickMetricsButton.click();

        return this;
    }

    public FolderConfigPage clickAddMetricButton() {
        clickAddMetricButton.click();

        return this;
    }

    public FolderConfigPage clickMetricsItem() {
        clickMetricsItem.click();

        return this;
    }

    public WebElement getMetricElement() {

        return getMetricElement;
    }

    public FolderConfigPage deleteHealthMetric() {
        try {
            do {
                deleteHealthMetric.click();
            } while (deleteHealthMetric.isDisplayed());
        } catch (Exception ex) {
        }

        return this;
    }
}