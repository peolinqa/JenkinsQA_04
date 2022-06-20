import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;


public class _JenkinsCLITest extends BaseTest {

    private static final String AddJobToViewElement = "//table[@class='jenkins-table sortable']/tbody/tr[1]";

    private void goToCliPage(){
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a/span[@class='task-link-text']")).click();
        getDriver().findElement(By.xpath("//div[@class='jenkins-section__item']/a[@href='cli']/dl/dt")).click();
    }

    private void returnToCLIPage(){
        getDriver().findElement(By.xpath("//a[@href='/cli/']")).click();
    }

    @Test
    public void checkAddJobToViewCommandTest(){
        goToCliPage();
        Assert.assertEquals(getDriver().findElement(By
                .xpath(AddJobToViewElement + "/td[2]")).getText(),
                "Adds jobs to view.");

        getDriver().findElement(By.xpath(AddJobToViewElement + "/td[1]/a")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//pre[@id='example']")).getText()
                .contains("-webSocket add-job-to-view VIEW JOB"));
        returnToCLIPage();
    }
}
