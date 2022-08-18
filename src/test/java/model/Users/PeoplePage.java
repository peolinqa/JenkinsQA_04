package model.Users;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class PeoplePage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//ol/li[1]")
    private WebElement iconSizeSButton;

    @FindBy(xpath = "//ol/li[2]/a")
    private WebElement iconSizeMButton;

    @FindBy(xpath = "//ol/li[3]/a")
    private WebElement iconSizeLButton;

    @FindBy(xpath = "//table[@id='people']/tbody/tr[1]/td[4]")
    private WebElement lastCommitActivityFirstCell;

    @FindBy(xpath = "//tr[@id='person-admin']/td[2]/a")
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

    public int getHeightLastCommitActivityFirstCell() {
        return lastCommitActivityFirstCell.getSize().height;
    }

    public UserStatusPage goToUserStatusPage(){
        userIDFirstCell.click();

        return new UserStatusPage(getDriver());
    }
}