import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class _JenkinsCLITest extends BaseTest {

    private static final String AddJobToViewElement = "//table[@class='jenkins-table sortable']/tbody/tr[1]";
    private static final String BuildElement = "//table[@class='jenkins-table sortable']/tbody/tr[2]";

    private void goToCliPage() {
        getDriver().findElement(By.xpath("//div[@id='tasks']/div[4]/span/a/span[@class='task-link-text']")).click();
        getDriver().findElement(By.xpath("//div[@class='jenkins-section__item']/a[@href='cli']/dl/dt")).click();
    }

    private void returnToCLIPage() {
        getDriver().findElement(By.xpath("//a[@href='/cli/']")).click();
    }

    @Test
    public void checkAddJobToViewCommandTest() {
        goToCliPage();

        String getDescription = getDriver().findElement(By
                .xpath(AddJobToViewElement + "/td[2]")).getText();

        getDriver().findElement(By.xpath(AddJobToViewElement + "/td[1]/a")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//pre[@id='example']")).getText()
                .contains("-webSocket add-job-to-view VIEW JOB"));

        Assert.assertEquals(getDescription,
                "Adds jobs to view.");

        returnToCLIPage();
    }

    @Test
    public void checkBuildCommandTest() {
        goToCliPage();

        String getDescription = getDriver().findElement(By
                .xpath(BuildElement + "/td[2]")).getText();

        getDriver().findElement(By.xpath(BuildElement + "/td[1]/a")).click();
        Assert.assertTrue(getDriver().findElement(By.xpath("//pre[@id='example']")).getText()
                .contains("-webSocket build JOB [-c] [-f] [-p] [-r N] [-s] [-v] [-w]"));

        Assert.assertEquals(getDescription,
                "Builds a job, and optionally waits until its completion.");

        returnToCLIPage();
    }

}
