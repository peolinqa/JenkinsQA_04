package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

class JenkinsCLIPage extends BasePage{

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[1]/td/a")
    private WebElement addJobToViewCommand;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[2]/td/a")
    private WebElement buildCommand;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[3]/td/a")
    private WebElement cancelQuietDownCommand;

    public JenkinsCLIPage(WebDriver driver) {
        super(driver);
    }

    public String getAddJobToViewDescription(){
        return addJobToViewCommand.getText();
    }

    public JenkinsCLIPage clickAddJobToViewElement (WebElement element){
        addJobToViewCommand.click();
        return this;
    }

    public String getBuildDescription(){
        return buildCommand.getText();
    }

    public JenkinsCLIPage clickBuildElement (){
        buildCommand.click();
        return this;
    }

    public String getCancelQuiteDownDescription(){
        return cancelQuietDownCommand.getText();
    }

    public JenkinsCLIPage clickCancelQuietDownElement (){
        cancelQuietDownCommand.click();
        return this;
    }

}
