package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class FolderConfigPage extends BaseHeaderFooterPage {

    @FindBy(css = "[type='submit']")
    private WebElement btnSave;

    @FindBy(css = "textarea.setting-input")
    private WebElement textareaDescription;

    @FindBy(linkText = "Preview")
    private WebElement linkDescriptionPreview;

    @FindBy(id = "yui-gen3-button")
    private WebElement btnHealthMetrics;

    @FindBy(id = "yui-gen1-button")
    private WebElement btnAddMetric;

    @FindBy(xpath = "//a[text()='Child item with worst health']")
    private WebElement btnChildItem;

    @FindBy(xpath = "//b[text()='Child item with worst health']")
    private WebElement textChildItem;

    @FindBy(xpath = "//button[@title ='Remove']")
    private WebElement btnDeleteChildItem;

    public FolderConfigPage(WebDriver driver) {
        super(driver);
    }

    public FolderConfigPage setTextareaDescription(String text) {
        textareaDescription.sendKeys(text);

        return this;
    }

    public FolderConfigPage clickLinkDescriptionPreview() {
        linkDescriptionPreview.click();

        return new FolderConfigPage(getDriver());
    }

    public String getDescriptionPreviewText() {
        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='textarea-preview'][@style='']"))).getText();
    }

    public FolderProjectPage saveProjectConfiguration() {
        btnSave.click();

        return new FolderProjectPage(getDriver());
    }

    public FolderConfigPageSelectorMenuFrame clickFolderDropDownMenu(String folderName) {
        getActions().moveToElement(getDriver().findElement((
                By.xpath(String.format("//a[@href='/job/%s/']", folderName))))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();

        return new FolderConfigPageSelectorMenuFrame(getDriver());
    }

    public FolderConfigPage clickHealthMetricsButton() {
        btnHealthMetrics.click();

        return this;
    }

    public FolderConfigPage clickAddMetricButton() {
        btnAddMetric.click();

        return this;
    }

    public FolderConfigPage clickChildItem() {
        btnChildItem.click();

        return this;
    }

    public boolean isAddMetricDisplayed() {

        return btnAddMetric.isDisplayed();
    }

    public String getChildItemText() {

        return textChildItem.getText();
    }

    public FolderConfigPage clickDeleteChildItem() {
        getWait5().until(ExpectedConditions.elementToBeClickable(btnDeleteChildItem)).click();

        return this;
    }
}
