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
    private WebElement healthMetricsButton;

    @FindBy(id = "yui-gen1-button")
    private WebElement addMetricButton;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement childItem;

    @FindBy(xpath = "//b[text()='Child item with worst health']")
    private WebElement textChildItem;

    @FindBy(xpath = "//button[@title ='Remove']")
    private WebElement deleteChildItemButton;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage setFolderDescription(String text) {
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

    public FolderConfigPageSelectorMenuFrame openFolderMenuSelector(String folderName) {
        getActions().moveToElement(getDriver().findElement((
                By.xpath(String.format("//a[@href='/job/%s/']", folderName))))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();

        return new FolderConfigPageSelectorMenuFrame(getDriver());
    }

    public FolderConfigPage clickHealthMetricsButton() {
        healthMetricsButton.click();

        return this;
    }

    public FolderConfigPage clickAddMetricButton() {
        addMetricButton.click();

        return this;
    }

    public FolderConfigPage clickChildItem() {
        childItem.click();

        return this;
    }

    public boolean isAddMetricDisplayed() {

        return addMetricButton.isDisplayed();
    }

    public String getTextChildItem() {

        return textChildItem.getText();
    }

    public FolderConfigPage clickDeleteChildItem() {
        getWait5().until(ExpectedConditions.elementToBeClickable(deleteChildItemButton)).click();

        return this;
    }
}