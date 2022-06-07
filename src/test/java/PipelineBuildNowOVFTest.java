import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PipelineBuildNowOVFTest extends BaseTest {
    private String createRandomJobName() {
        String jobNameSubstrate = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            builder.append(jobNameSubstrate.charAt((int)(Math.random() * jobNameSubstrate.length())));
        }

        return builder.toString();
    }

    private void createNewJob(String jobName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(jobName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys("Test job");
        getDriver().findElement(By.xpath("//button[@id='yui-gen6-button']")).click();
    }

    private void deleteCreatedJob(String jobName) {
        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + jobName + "/']")))
                .build().perform();

        action.moveToElement(getDriver().findElement(By.xpath(
                        "//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a/span[contains(text(),'Delete Pipeline')]")))
                .click().build().perform();

        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    @Test
    public void testPipelineBuildNow() {
        String[] buildSuccessfulPermalinks = new String[] {"Last build", "Last stable build", "Last successful build",
                "Last completed build"};
        String[] buildUnsuccessfulPermalinks = new String[] {"Last build", "Last stable build", "Last successful build",
                "Last failed build", "Last unsuccessful build", "Last completed build"};

        String jobName = createRandomJobName();
        createNewJob(jobName);

        String jobPageName = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertTrue(jobPageName.contains(jobName));

        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();

        List<WebElement> projectsListElements = getDriver().findElements(By.xpath(
                "//table[@id='projectstatus']//a[@class='jenkins-table__link model-link inside']"));
        boolean ifJobNameIsInTheListNames = false;
        for (WebElement projectsListElement : projectsListElements) {
            if ((projectsListElement.getText()).equals(jobName)) {
                ifJobNameIsInTheListNames = true;
            }
        }
        Assert.assertTrue(ifJobNameIsInTheListNames);

        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//a[@href='job/" + jobName + "/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/job/" + jobName + "/build?delay=0sec']")).click();
        getDriver().findElement(By.xpath("//ul[@id='breadcrumbs']//a[@href='/']")).click();
        getDriver().findElement(By.xpath("//a[@href='/view/all/builds']")).click();
        List<WebElement> buildListElements = getDriver().findElements(By.xpath(
                "//table[@id='projectStatus']//a[@class='jenkins-table__link model-link inside']"));
        boolean ifBuildNameIsInTheListNames = false;
        for (WebElement buildListElement : buildListElements) {
            if ((buildListElement.getText()).equals(jobName)) {
                ifBuildNameIsInTheListNames = true;
            }
        }
        Assert.assertTrue(ifBuildNameIsInTheListNames);

        getDriver().findElement(By.xpath("//a[@href='/job/" + jobName + "/']")).click();

        getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        String buildAlert = getDriver().findElement(By.xpath("//div[@class='alert alert-warning']"))
                .getText();

        if (buildAlert.contains("This Pipeline has run successfully")) {
            List<WebElement> permalinks = getDriver().findElements(
                    By.xpath("//ul[@class='permalinks-list']/li"));
            ArrayList<String> permalinksTexts = new ArrayList<>();
            for (int i = 0; i < permalinks.size(); i++) {
                permalinksTexts.add(permalinks.get(i).getText());
                Assert.assertTrue((permalinksTexts.get(i)).contains(buildSuccessfulPermalinks[i]));
            }
        } else {
            List<WebElement> permalinks = getDriver().findElements(
                    By.xpath("//ul[@class='permalinks-list']/li"));
            ArrayList<String> permalinksTexts = new ArrayList<>();
            for (int i = 0; i < permalinks.size(); i++) {
                permalinksTexts.add(permalinks.get(i).getText());
                Assert.assertTrue((permalinksTexts.get(i)).contains(buildUnsuccessfulPermalinks[i]));
            }
        }

        deleteCreatedJob(jobName);
    }
}
