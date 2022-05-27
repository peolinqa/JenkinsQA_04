package qa_java_beginners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Song99BottlesBahaSadyrTest extends BaseTest {

    public static final String BASE_URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testLanguageJ_TC_12_01() {

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='j.html']")).click();
        String expectedRes = "All languages starting with the letter J are shown, sorted by Language.";
        String actualRes = getDriver().findElement(By.xpath("//p[contains(text(),'All languages starting with the letter')]")).getText().trim();

        Assert.assertEquals(actualRes, expectedRes);
    }
    @Test
    public void test_TC_12_02(){

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        getDriver().findElement(By.xpath("//a[@href='m.html']")).click();
        String actualRes = getDriver().findElement(By.xpath("//table[@id='category']//tr[last()]//td//a")).getText();

        Assert.assertEquals(actualRes,"MySQL");
    }
    @Test
    public void test_TC_12_03(){

        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[contains(text(),'Browse Languages')][1]")).click();
        List<WebElement> listWithHeaders = getDriver().findElements(By.xpath("//table[@id='category']//tr//th"));
        List<String>actualRes = new ArrayList<>();
        for(int i = 0; i < listWithHeaders.size(); i++){
            actualRes.add(listWithHeaders.get(i).getText());
        }
        List<String>expectRes = Arrays.asList("Language", "Author", "Date", "Comments", "Rate");

        Assert.assertEquals(actualRes,expectRes);
    }

}
