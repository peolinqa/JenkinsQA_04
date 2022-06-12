import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.UUID;

public class CreateItemFromExistingPipelineWithDescriptionTest extends BaseTest {
    String descriptionFieldValue = "Test description";
    String createItemButtonLocator = "//*[@id=\"tasks\"]/div[1]/span/a/span[2]";
    String uuid = UUID.randomUUID().toString();
    String itemNameFieldLocator = "name";
    String okButtonLocator = "ok-button";
    String saveButtonLocator = "yui-gen6-button";

    public void createPipeline() {
        getDriver().findElement(By.xpath(createItemButtonLocator)).click();
        getDriver().findElement(By.id(itemNameFieldLocator)).sendKeys(uuid);
        WebElement pipelineString = getDriver().findElement(By.xpath("//*[.=\"Pipeline\"]"));
        pipelineString.click();
        getDriver().findElement(By.id(okButtonLocator)).click();
        WebElement descriptionField = getDriver().findElement(By.xpath("//*[@name=\"description\"]"));
        descriptionField.sendKeys(descriptionFieldValue);
        getDriver().findElement(By.id(saveButtonLocator)).click();
    }

    public void deletePipeline() {
        WebElement deletePipelineButton = getDriver().findElement(By.xpath("//*[@class=\"icon-edit-delete icon-md\"]"));
        deletePipelineButton.click();
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }

    public void deleteFirstPipeline() {
        WebElement pipelineForDeleting = getDriver().findElement(
                By.xpath("//a[@href=\"job/" + uuid + "/\"]")
        );
        pipelineForDeleting.click();
        deletePipeline();
    }

    @Test
    public void createItemFromExistingPipelineWithDescriptionTest() {
        createPipeline();

        WebElement backToDashboardButton = getDriver().findElement(By.xpath("//span[text()=\"Back to Dashboard\"]"));
        backToDashboardButton.click();
        getDriver().findElement(By.xpath(createItemButtonLocator)).click();
        WebElement copyFrom = getDriver().findElement(By.id("from"));
        copyFrom.sendKeys(uuid);
        String uuidForSecondPipeline = UUID.randomUUID().toString();
        getDriver().findElement(By.id(itemNameFieldLocator)).sendKeys(uuidForSecondPipeline);
        getDriver().findElement(By.id(okButtonLocator)).click();
        getDriver().findElement(By.id(saveButtonLocator)).click();
        WebElement pipelineName = getDriver().findElement(By.xpath("//h1[@class=\"job-index-headline page-headline\"]"));
        Assert.assertEquals(pipelineName.getText(), "Pipeline " + uuidForSecondPipeline);
        WebElement descriptionValue = getDriver().findElement(By.xpath("//*[@id=\"description\"]/div[1]"));
        Assert.assertEquals(descriptionValue.getText(), descriptionValue.getText());

        deletePipeline();
        deleteFirstPipeline();
    }

}
