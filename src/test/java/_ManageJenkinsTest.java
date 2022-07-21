import model.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class _ManageJenkinsTest extends BaseTest {

    @Test
    public void testCheckSectionNames() {
        List<String> expectedSectionNames = List.of(
                "System Configuration",
                "Security",
                "Status Information",
                "Troubleshooting",
                "Tools and Actions");

        List<String> actualManageJenkinsSectionNames = new HomePage(getDriver())
                .clickManageJenkins()
                .getActualManageJenkinsSectionNames();

        Assert.assertEquals(actualManageJenkinsSectionNames, expectedSectionNames);
    }

}
