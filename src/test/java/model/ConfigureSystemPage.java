package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

public class ConfigureSystemPage extends BaseHeaderFooterPage {

    public ConfigureSystemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[@name='system_message']")
    private WebElement systemMessageTextArea;

    @FindBy(xpath = "//a[normalize-space(.)='Preview']")
    private WebElement preview;

    @FindBy(className = "textarea-preview")
    private WebElement textareaPreview;

    public ConfigureSystemPage setSystemMessage(String text) {
        TestUtils.clearAndSend(getDriver(), By.xpath("//textarea[@name='system_message']"), text);

        return this;
    }

    public HomePage clickSaveButton() {
        saveButton.click();

        return new HomePage(getDriver());
    }

    public ConfigureSystemPage clickPreviewSystemMessage() {
        preview.click();

        return this;
    }

    public String getPreviewSystemMessageText() {
        return textareaPreview.getText();
    }
}
