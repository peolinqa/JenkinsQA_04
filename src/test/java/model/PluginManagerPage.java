package model;

import model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class PluginManagerPage extends BasePage {

    public PluginManagerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='filter-box']")
    private WebElement searchField;

    @FindBy(xpath = "//tbody//tr[not(contains(@class, 'jenkins-hidden'))]//a[@class = 'jenkins-table__link']")
    private List<WebElement> aList;

    public WebElement getSearchField(){
        return searchField;
    }

    public List<WebElement> getaList() {
        return aList;
    }

    public void searchFieldInput(String s){
        searchField.clear();
        searchField.sendKeys(s);
    }

    public static boolean checkWordsInLine(String needle, String str){
        boolean result = true;
        List<String> str_list = Arrays.asList(needle.split(" "));
        for (String s: str_list) {
            if (!str.contains(s)){
                result = false;
            }
        }

        return result;
    }
}
