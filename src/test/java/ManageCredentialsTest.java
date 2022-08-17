import model.HomePage;

import model.ManageCredentialsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

public class ManageCredentialsTest extends BaseTest {

    @Test
    public void testManageCredentialsCheckMenu() {
        final String newUsername = TestUtils.getRandomStr(8);
        final String newPassword = TestUtils.getRandomStr(9);

        String createCredentials = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickGlobalCredentials()
                .getSideMenu()
                .clickMenuAddCredentials()
                .createUserCredentials(newUsername, newPassword)
                .getTableText();

        Assert.assertTrue(createCredentials.contains(newUsername));
    }

    @Test
    public void testIconSizeChangePositive() {
        final String[] icon_style = new String[]{"icon-credentials-system-store icon-sm",
                "icon-credentials-system-store icon-md", "icon-credentials-system-store icon-lg"};

        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickSmallSizeIcon()
                .assertEquals(ManageCredentialsPage::getIconCredentialsProvider, icon_style[0])
                .clickMediumSizeIcon()
                .assertEquals(ManageCredentialsPage::getIconCredentialsProvider, icon_style[1])
                .clickLargeSizeIcon()
                .assertEquals(ManageCredentialsPage::getIconCredentialsProvider, icon_style[2]);
    }

    @Test
    public void testIconSizeCSSChangePositive() {
        final String grey = "rgba(248, 248, 248, 1)";
        final String transparent = "rgba(0, 0, 0, 0)";

        new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickSmallSizeIcon()
                .assertEquals(ManageCredentialsPage::getChangeIconButtonsBGColors, new String[]{grey, transparent, transparent})
                .clickMediumSizeIcon()
                .assertEquals(ManageCredentialsPage::getChangeIconButtonsBGColors, new String[]{transparent, grey, transparent})
                .clickLargeSizeIcon()
                .assertEquals(ManageCredentialsPage::getChangeIconButtonsBGColors, new String[]{transparent, transparent, grey});
    }

    @Test
    public void testCreateNewDomainViaDropDownMenu() {
        String domainName = TestUtils.getRandomStr(8);

        String addDomain = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .clickCredentialsStoreSystemMenu()
                .clickCredentialDropDownMenu()
                .clickMenuSelectorAddDomain()
                .createNewDomain(domainName)
                .getDomainHeaderText();

        Assert.assertEquals(addDomain, domainName);
    }

    @Test
    public void testValidateIconSize() {

        boolean expected = new HomePage(getDriver())
                .getSideMenu()
                .clickMenuManageJenkins()
                .clickManageCredentials()
                .isIconEqualSmallIcon();

         Assert.assertFalse(expected);
    }
}
