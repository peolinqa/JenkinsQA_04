import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.UUID;

public class DeletePipelineDescriptionTest extends BaseTest {
    public void createPipeline() {
        String uuid = UUID.randomUUID().toString();
        WebElement createItemButton = getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[1]/span/a/span[2]"));
        createItemButton.click();
        WebElement itemNameField = getDriver().findElement(By.id("name"));
        itemNameField.sendKeys(uuid);
        WebElement pipelineString = getDriver().findElement(By.xpath("//*[.=\"Pipeline\"]"));
        pipelineString.click();
        WebElement okButton = getDriver().findElement(By.id("ok-button"));
        okButton.click();
        WebElement descriptionField = getDriver().findElement(By.xpath("//*[@name=\"description\"]"));
        descriptionField.sendKeys("Test description");
        WebElement saveButton = getDriver().findElement(By.id("yui-gen6-button"));
        saveButton.click();
    }

    public void deletePipeline() {
        WebElement deletePipelineButton = getDriver().findElement(By.xpath("//*[@class=\"icon-edit-delete icon-md\"]"));
        deletePipelineButton.click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    @Test
    public void deletePipelineDescriptionTest() {
        createPipeline();

        WebElement changeDescriptionButton = getDriver().findElement(By.id("description-link"));
        changeDescriptionButton.click();
        WebElement descriptionField = getDriver().findElement(By.xpath("//textarea[@name=\"description\"]"));
        descriptionField.clear();
        WebElement saveDescriptionButton = getDriver().findElement(By.id("yui-gen2-button"));
        saveDescriptionButton.click();
        WebElement descriptionValue = getDriver().findElement(By.xpath("//*[@id=\"description\"]/div[1]"));
        Assert.assertEquals(descriptionValue.getText(), "");

        deletePipeline();
    }
}
