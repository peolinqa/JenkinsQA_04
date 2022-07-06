package model;

import model.base.BaseBuildPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildDeletePage extends BaseBuildPage {

    @FindBy(css = "//form[@action='doDelete']")
    private WebElement header;

    public BuildDeletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageHeader() {
        String text = header.getText();
        return text.substring(0, text.indexOf('?') + 1);
    }
}
