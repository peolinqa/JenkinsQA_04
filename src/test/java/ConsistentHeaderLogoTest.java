import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class ConsistentHeaderLogoTest extends BaseTest {
    public void verifyImageOrder(String uri){
        String currentUrl = getDriver().getCurrentUrl();
        if(uri != null){
            getDriver().get(currentUrl + uri);
        }

        List<WebElement> images = getDriver().findElements(By.xpath("//div[@class='logo']/a/img"));
        Assert.assertNotNull(images);
        Assert.assertEquals(images.size(), 2);
        Assert.assertEquals(images.get(0).getAttribute("id"), "jenkins-head-icon");
        Assert.assertEquals(images.get(1).getAttribute("id"), "jenkins-name-icon");
    }

    @Test
    public void testHeaderLogoIsConsistentOnMainPage(){
        verifyImageOrder(null);
    }

    @Test
    public void testHeaderLogoIsConsistentOnNewItemPage(){
        verifyImageOrder("view/all/newJob");
    }

    @Test
    public void testHeaderLogoIsConsistentOnPeoplePage(){
        verifyImageOrder("asynchPeople/");
    }

    @Test
    public void testHeaderLogoIsConsistentOnManageJenkinsPage(){
        verifyImageOrder("manage");
    }
    @Test
    public void testHeaderLogoIsConsistentOnMyViewsPage() {
        verifyImageOrder("me/my-views/view/all/");
    }

    @Test
    public void testHeaderLogoIsConsistentOnNewViewPage() {
        verifyImageOrder("user/admin/my-views/newView");
    }
}

