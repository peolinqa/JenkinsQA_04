package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class NewDomainPage extends BaseHeaderFooterPage {

    @FindBy(xpath = "//input[@name='_.name']")
    private WebElement domainName;

    @FindBy(id = "ok")
    private WebElement okButton;

    public NewDomainPage(WebDriver driver) {
        super(driver);
    }

    public CreatedDomainPage createNewDomain(String name) {
        domainName.sendKeys(name);
        okButton.click();

        return new CreatedDomainPage(getDriver());
    }
}