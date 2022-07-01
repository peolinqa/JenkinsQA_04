package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemConfigPage extends BasePage {

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Save')]")
    private WebElement saveButton;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(name = "githubProject")
    private WebElement githubProjectCheckbox;

    @FindBy(name = "_.projectUrlStr")
    private WebElement githubUrl;

    public ItemConfigPage(WebDriver driver) {
        super(driver);
    }

    public ProjectPage saveConfigAndGoToProject() {
        saveButton.click();

        return new ProjectPage(getDriver());
    }

    public ItemConfigPage setDescription(String text) {
        descriptionTextarea.sendKeys(text);

        return this;
    }

    public String getDescription() {

        return  descriptionTextarea.getText();
    }

    public ItemConfigPage clickGithubProjectCheckbox() {
        githubProjectCheckbox.click();

        return this;
    }

    public ItemConfigPage setGithubUrl(String text) {

        githubUrl.sendKeys(text);

        return this;
    }

    public String getGithubUrl() {

         return githubUrl.getAttribute("value");
    }
}
