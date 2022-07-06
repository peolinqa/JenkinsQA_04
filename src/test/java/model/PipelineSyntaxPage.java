package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class PipelineSyntaxPage extends BasePage {

    public PipelineSyntaxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li[@class='item']/a")
    private List<WebElement> breadcrumbsPipelineSyntaxPage;

    public void assertPipelineSyntaxHrefAtt(String text) {
        String hrefBreadcrumbsPipelineSyntaxPageAtt = breadcrumbsPipelineSyntaxPage.get(2).getAttribute("href");
        Assert.assertTrue(hrefBreadcrumbsPipelineSyntaxPageAtt.contains(text));
    }
}
