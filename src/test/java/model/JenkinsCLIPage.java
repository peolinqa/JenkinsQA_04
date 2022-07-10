package model;

import model.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsCLIPage extends BasePage {

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[1]/td/a")
    private WebElement addJobToViewCommand;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[1]/td[2]")
    private WebElement addJobToViewCommandDescription;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[2]/td/a")
    private WebElement buildCommand;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[2]/td[2]")
    private WebElement buildCommandDescription;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[3]/td/a")
    private WebElement cancelQuietDownCommand;

    @FindBy(xpath = "//table[@class='jenkins-table sortable']/tbody/tr[3]/td[2]")
    private WebElement cancelQuietDownCommandDescription;

    public JenkinsCLIPage(WebDriver driver) {
        super(driver);
    }

    public String getAddJobToViewName(){
        return addJobToViewCommand.getText();
    }

    public String getAddJobToViewDescription(){
        return addJobToViewCommandDescription.getText();
    }

    public JenkinsCLIExamplesPage clickAddJobToViewElement (){
        addJobToViewCommand.click();
        return new JenkinsCLIExamplesPage(getDriver());
    }

    public String getBuildName(){
        return buildCommand.getText();
    }

    public String getBuildDescription(){
        return buildCommandDescription.getText();
    }

    public JenkinsCLIExamplesPage clickBuildElement (){
        buildCommand.click();
        return new JenkinsCLIExamplesPage(getDriver());
    }

    public String getCancelQuiteDownName(){
        return cancelQuietDownCommand.getText();
    }

    public String getCancelQuiteDownDescription(){
        return cancelQuietDownCommandDescription.getText();
    }

    public JenkinsCLIExamplesPage clickCancelQuietDownElement (){
        cancelQuietDownCommand.click();
        return new JenkinsCLIExamplesPage(getDriver());
    }

}
