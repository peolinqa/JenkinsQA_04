package qa_java_beginners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

@Ignore
public class AnnaNezhevetsHW12T1Test extends BaseTest{
    public final String URL = "http://www.99-bottles-of-beer.net/";

    @Test
    public void testSubmenuJ(){
        String expectedResult = "All languages starting with the letter J are shown, sorted by Language.";

        getDriver().get(URL);
        WebElement browseLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        browseLanguage.click();
        WebElement subMenuJ = getDriver().findElement(By.xpath("//ul[@id='submenu']//li/a[@href='j.html']"));
        subMenuJ.click();
        WebElement subMenuJAll = getDriver().findElement(By.xpath("//div[@id='main']/p"));

        Assert.assertEquals(subMenuJAll.getText(), expectedResult);
    }

    @Test
    public void testSubmenuMMySQL(){
        String expectedResult = "MySQL";

        getDriver().get(URL);
        WebElement browseLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        browseLanguage.click();
        WebElement subMenuM = getDriver().findElement(By.xpath("//ul[@id='submenu']//li/a[@href='m.html']"));
        subMenuM.click();
        WebElement lastElementMySQL = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']//tr/td/a[@href='language-mysql-1252.html']"));

        Assert.assertEquals(lastElementMySQL.getText(), expectedResult);
    }

    @Test
    public void testTableNames(){
        String[] expectedResult = new String[]{"Language", "Author", "Date", "Comments", "Rate"};

        getDriver().get(URL);
        WebElement browseLanguage = getDriver().findElement(By.xpath("//ul[@id='menu']/li/a[@href='/abc.html']"));
        browseLanguage.click();
        WebElement language = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']/tbody/tr//th[@style='width: 40%;']"));
        WebElement author = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']/tbody/tr//th[@style='width: 30%;']"));
        WebElement date = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']/tbody/tr//th[(contains(text(),'Date'))]"));
        WebElement comments = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']/tbody/tr//th[(contains(text(),'Comments'))]"));
        WebElement rate = getDriver().findElement(
                By.xpath("//div[@id='main']/table[@id='category']/tbody/tr//th[(contains(text(),'Rate'))]"));

        String[] actualResult = new String[]{language.getText(), author.getText(), date.getText(), comments.getText(),
        rate.getText()};

        Assert.assertEquals(actualResult, expectedResult);
    }
}