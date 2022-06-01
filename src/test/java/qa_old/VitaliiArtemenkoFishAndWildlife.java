package qa_old;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

@Ignore
public class VitaliiArtemenkoFishAndWildlife extends BaseTest {

    public static String getNames(List<WebElement> list){
        StringBuilder result = new StringBuilder();
        for (WebElement webElement : list) {
            result.append(webElement.getText()).append(" ");
        }
        return result.toString().trim();
    }

    @Test
    public void testVitaliiArtemenkoFishAndWildlife(){
        getDriver().get("https://wdfw.wa.gov/");

        getDriver().findElement(By
                .xpath("//a[@data-drupal-link-system-path and @href='/licenses']")).click();

        List<WebElement> rules =
                getDriver().findElements(By.xpath("//span/a[contains(@href, '/licenses/')]"));

        String listOfRules = getNames(rules);

        Assert.assertEquals("Fishing and shellfishing licenses " +
                        "Hunting licenses Construction and environmental permits " +
                        "Parking and access passes " +
                        "License plates License dealers " +
                        "Commercial licensing Roadkill salvage permit",
                listOfRules);
    }
}