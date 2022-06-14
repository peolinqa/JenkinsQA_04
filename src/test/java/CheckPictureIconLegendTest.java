import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class CheckPictureIconLegendTest extends BaseTest {

    String[] nameIcons = {"icon-nobuilt icon", "icon-nobuilt-anime", "icon-disabled icon",
        "icon-disabled-anime", "icon-aborted icon", "icon-aborted-anime", "icon-blue icon",
        "icon-blue-anime", "icon-yellow icon", "icon-yellow-anime", "icon-red icon",
        "icon-red-anime", "80plus", "60to79", "40to59", "20to39", "00to19"};

    String[] textDescriptionIcon = {"The project has never been built.", "The first build is in progress.",
            "The project is disabled.", "The project is disabled, but a build is in progress.",
            "The last build was aborted.", "The last build was aborted. A new build is in progress.",
            "The last build was successful.", "The last build was successful. A new build is in progress.",
            "The last build was successful but unstable. This is primarily used to represent test failures.",
            "The last build was successful but unstable. A new build is in progress.",
            "The last build failed.",
            "The last build failed. A new build is in progress.",
            "Project health is over 80%. You can hover the mouse over the project’s icon for a more detailed explanation.", "" +
            "Project health is over 60% and up to 80%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 40% and up to 60%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is over 20% and up to 40%. You can hover the mouse over the project’s icon for a more detailed explanation.",
            "Project health is 20% or less. You can hover the mouse over the project’s icon for a more detailed explanation."};

    @Test
    public void testTC_133_001_CheckPictureIconLegendGizzyatov_DA() {

        getDriver().findElement(By.xpath("//a[@href='/legend']")).click();

        SoftAssert asserts1 = new SoftAssert();

        for (int i = 0; i<nameIcons.length; i++) {
            String tempXpath = String.format("//td/*[contains(@class, '%s')]", nameIcons[i]);
            Dimension dimension = getDriver().findElement(By.xpath(tempXpath)).getSize();
            asserts1.assertEquals(dimension.toString(), "(48, 48)");
        }
        asserts1.assertAll();
    }
}
