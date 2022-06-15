import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class FreestyleProjectRenameWithInvalidData extends BaseTest {

    private String createRandomName() {
        String projectNameSubstrate = "0123456789qwertyuiopasdfghjklzxcvbnm";
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            builder.append(
                    projectNameSubstrate.charAt((int) (Math.random() * projectNameSubstrate.length())));
        }
        String projectName = builder.toString();
        return projectName;
    }

    private void createProject(String projectName) {

        getDriver().findElement(By.xpath(
                "//span[normalize-space(text())='New Item']")).click();
        getDriver().findElement(By.xpath(
                "//input[@id='name']")).sendKeys(projectName);
        getDriver().findElement(By.xpath(
                        "//div[@id='j-add-item-type-standalone-projects']/ul/li[1]/label/span"))
                .click();
        getDriver().findElement(By.xpath("//button[@id='ok-button']"))
                .click();
        getDriver().findElement(By.xpath(
                        "//div[@class='bottom-sticker-inner']/span/span/button[@type='submit']"))
                .click();
    }

    private String checkErrorMessage() {

        return getDriver().findElement(By.xpath("//div[@id='main-panel']//p")).getText();
    }

    private void deleteCreatedProject(String newProjectName) {

        getDriver().findElement(By.xpath("//a[normalize-space(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(
                By.xpath("//a[@href='job/" + newProjectName + "/']"))).click().build().perform();

        getDriver().findElement(By.xpath("//span[text()='Delete Project']")).click();

        getDriver().switchTo().alert().accept();

    }

    private void clearSendClick(String newProjectName) {
        getDriver().findElement(By.xpath("//input[@checkdependson='newName']")).clear();
        getDriver().findElement(
                By.xpath("//input[@checkdependson='newName']")).sendKeys(newProjectName);
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();

    }

    @Test
    public void test_TC_003_002_RenameWithInvalidData() {

        String projectName = createRandomName();
        String newProjectName = " ";
        String invalidData = "!@#$%^*/|\\;:?";

        createProject(projectName);

        getDriver().findElement(By.xpath("//a[(text())='Dashboard']")).click();

        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(By.xpath("//a[@href='job/" + projectName + "/']")))
                .build().perform();
        action.moveToElement(getDriver().findElement(By.xpath("//div[@id='menuSelector']")))
                .click().build().perform();
        action.moveToElement(getDriver().findElement(By.xpath(
                        "//a[@href='/job/" + projectName + "/confirm-rename']")))
                .click().build().perform();

        for (int i = 0; i < invalidData.length(); i++) {
            newProjectName = invalidData.substring(i, (i + 1));

            clearSendClick(newProjectName);

            String expectedResult = "‘" + newProjectName + "’ is an unsafe character";
            System.out.println("expectedResult = " + expectedResult);
            Assert.assertEquals(checkErrorMessage(), expectedResult);
            getDriver().navigate().back();
        }

        deleteCreatedProject(projectName);
    }
}
