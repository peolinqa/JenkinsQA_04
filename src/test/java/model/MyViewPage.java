package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MyViewPage extends BasePage{


        @FindBy(xpath = "//ul[@id='breadcrumbs']/li[@class='item']")
        private List<WebElement> viewNamesOnBreadcrumbs;

        @FindBy(css = "div .tab a")
        private List<WebElement> viewNamesOnTabBar;

        @FindBy(linkText = "Edit View")
        private WebElement editView;

        @FindBy(linkText = "Delete View")
        private WebElement deleteView;

        @FindBy(id = "yui-gen1-button")
        private WebElement submitDeleteViewButton;

        public MyViewPage(WebDriver driver) {
            super(driver);
        }

        public ViewConfigPage clickEditView() {
            editView.click();

            return new ViewConfigPage(getDriver());
        }

        public MyViewPage clickDeleteView() {
            deleteView.click();

            return new MyViewPage(getDriver());
        }

        public HomePage clickSubmitDeleteViewAndGoHome() {
            submitDeleteViewButton.click();

            return new HomePage(getDriver());
        }


        public List<String> getNamesOfViewsOnBreadcrumbs() {

            return viewNamesOnBreadcrumbs.stream().map(WebElement::getText).collect(Collectors.toList());
        }

        public List<String> getNamesOfViewsOnTabBar() {

            return viewNamesOnTabBar.stream().map(WebElement::getText).collect(Collectors.toList());
        }
}
