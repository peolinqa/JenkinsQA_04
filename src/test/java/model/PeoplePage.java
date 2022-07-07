package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PeoplePage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//ol/li[1]")
    private WebElement iconSizeSButton;

    @FindBy(xpath = "//ol/li[2]/a")
    private WebElement iconSizeMButton;

    @FindBy(xpath = "//ol/li[3]/a")
    private WebElement iconSizeLButton;

    @FindBy(xpath = "//table[@id='people']/tbody/tr[1]/td[4]")
    private WebElement lastCommitActivityFirstCell;

    @FindBy(xpath = "//table[@id='people']/tbody/tr[1]/td[2]/a")
    private WebElement userIDFirstCell;

    public PeoplePage(WebDriver driver) {
        super(driver);
    }

    public PeoplePage setSizeS() {
        iconSizeSButton.click();
        return this;
    }

    public PeoplePage setSizeM() {
        iconSizeMButton.click();
        return this;
    }

    public PeoplePage setSizeL() {
        iconSizeLButton.click();
        return this;
    }

    public int heightLastCommitActivityFirstCell() {
        return lastCommitActivityFirstCell.getSize().height;
    }

    public UserStatusPage goToUserStatusPage(){
        userIDFirstCell.click();

        return new UserStatusPage(getDriver());
    }
}
