package model.credentials;

import model.base.BaseSideMenuPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class GlobalCredentialsPage extends BaseSideMenuPage<GlobalCredentialsPage,GlobalCredentialsPageSideMenuFrame> {

    @FindBy(xpath = "//div[@id='main-panel']/table/tbody/tr/td[3]")
    private List<WebElement> tableCredentials;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GlobalCredentialsPageSideMenuFrame getSideMenu() {
        return new GlobalCredentialsPageSideMenuFrame(getDriver());
    }

    public List<String> getTableText(){
        return tableCredentials.stream().map(elem -> elem.getText().substring(0, elem.getText().indexOf("/"))).collect(Collectors.toList());
    }
}
