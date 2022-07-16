package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runner.TestUtils;

import java.util.List;

public class LegendPage extends BasePage {

    public LegendPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getTextIconsDescriptions() {
        return TestUtils.getTextFromList(getDriver(), By.xpath("//table[@id='legend-table']//tbody/tr/td"));
    }
}