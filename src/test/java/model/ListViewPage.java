package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ListViewPage extends BasePage {

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='item']")
    private WebElement viewNamesOnBreadcrumbs;

    @FindBy(css = "div .tab a")
    private WebElement viewNamesOnTabBar;

    @FindBy(xpath = "//div[@id='description']/div[not(@class)]")
    private WebElement descriptionText;

    public ListViewPage(WebDriver driver) {
        super(driver);
    }

    public String getDescriptionText() {

        return  descriptionText.getText();
    }

    public List<String> getNamesOfViewsOnBreadcrumbs() {

        return getDriver().findElements(By.xpath("//ul[@id='breadcrumbs']/li[@class='item']")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getNamesOfViewsOnTabBar() {

        return getDriver().findElements(By.cssSelector("div .tab a")).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}