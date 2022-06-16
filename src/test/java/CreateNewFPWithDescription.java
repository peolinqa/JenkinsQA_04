import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

public class CreateNewFPWithDescription extends BaseTest {

    @Test (invocationCount = 20) //  (description = "TC_002.007 | Freestyle project > Add description")
    public void test1(){
        SoftAssert asserts = new SoftAssert();

        String projectName = "TC_001_039_".concat(RandomStringUtils.randomAlphabetic(5));
        String dInput = "Physically I'm fine, emotionally I'm bruised";
        getDriver().findElement(By.linkText("New Item")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("description")).sendKeys(dInput);
        getDriver().findElement(By.name("Submit")).click();

        asserts.assertEquals(getDriver().getCurrentUrl(),
                            "http://localhost:8080/job/".concat(projectName).concat("/"),
                    "Current URL does not corresponds with created item");

        asserts.assertTrue(getDriver().findElement(By.id("description")).getText().contains(dInput), "Description was not saved");


        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().switchTo().alert().accept();

        getDriver().get("http://localhost:8080/");

        asserts.assertTrue(getDriver().findElements(By.xpath("//a[@href=\"job/".concat(dInput).concat("/\"]"))).isEmpty(),
                "Item was not deleted properly");


        asserts.assertAll();

    }


}
