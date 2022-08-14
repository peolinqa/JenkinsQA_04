package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

public final class ConfigureSystemPage extends BaseHeaderFooterPage {

    public ConfigureSystemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSave;

    @FindBy(xpath = "//textarea[@name='system_message']")
    private WebElement textareaSystemMessage;

    @FindBy(xpath = "//a[normalize-space(.)='Preview']")
    private WebElement linkPreview;

    @FindBy(className = "textarea-preview")
    private WebElement textareaPreview;

    public ConfigureSystemPage setSystemMessage(String text) {
        TestUtils.clearAndSend(getDriver(), By.xpath("//textarea[@name='system_message']"), text);

        return this;
    }

    public HomePage clickSaveButton() {
        btnSave.click();

        return new HomePage(getDriver());
    }

    public ConfigureSystemPage clickPreviewSystemMessage() {
        linkPreview.click();

        return this;
    }

    public String getPreviewSystemMessageText() {
        return textareaPreview.getText();
    }
}
