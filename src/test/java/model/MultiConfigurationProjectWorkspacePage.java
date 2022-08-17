package model;

import model.base.BaseHeaderFooterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class MultiConfigurationProjectWorkspacePage extends BaseHeaderFooterPage {

    @FindBy(tagName = "h1")
    private WebElement h1Header;

    @FindBy(xpath = "//div[@id='main-panel']/p")
    private List<WebElement> listErrorMessages;

    public MultiConfigurationProjectWorkspacePage(WebDriver driver) {
        super(driver);
    }

    public String getH1Header() {
        return h1Header.getText();
    }

    public Set<String> getErrorMessagesList() {
        Set<String> errorMessagesTextList = new TreeSet<>();
        listErrorMessages.stream().map(WebElement::getText).forEach(errorMessagesTextList::add);

        return errorMessagesTextList;
    }
}
