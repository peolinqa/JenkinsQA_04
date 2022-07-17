package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FolderConfigPage extends BasePage {

    @FindBy(css = "[type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[@name='_.description']")
    private WebElement folderDescription;

    @FindBy(xpath = "//a[@class='textarea-show-preview']")
    private WebElement folderDescriptionPreview;

    @FindBy(xpath = "//div[@class='textarea-preview']")
    private WebElement folderDescriptionPreviewText;

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
        return folderDescriptionPreviewText.getText();
    }

    public FolderPage saveConfigAndGoToFolderPage() {
        saveButton.click();

        return new FolderPage(getDriver());
    }

    public FolderConfigPage openFolderMenuSelector(String folderName){
        getActions().moveToElement(getDriver().findElement((
                By.xpath(String.format("//a[@href='/job/%s/']", folderName))))).build().perform();
        getActions().moveToElement(getDriver().findElement(By.id("menuSelector"))).click().build().perform();

        return this;
    }

    public FolderPage clickDeleteOnMenuSelector(){
        getDriver().findElement(By.xpath("//span[contains(text(),'Delete Folder')]")).click();

        return new FolderPage(getDriver());
    }
}