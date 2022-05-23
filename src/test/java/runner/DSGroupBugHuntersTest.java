package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class DSGroupBugHuntersTest extends BaseTest {


    private WebElement sideMenuCategoryElectronics(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Электроника']"));
    }
    private WebElement sideMenuCategoryFurniture(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Мебель']"));
    }
    private WebElement sideMenuCategoryClothes(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Одежда']"));
    }
    private WebElement sideMenuCategoryBooks(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Книги']"));
    }
    private WebElement sideMenuCategoryPerfumes(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Парфюмерия']"));
    }
    private WebElement sideMenuCategoryToys(){
        return getDriver().findElement(By.xpath("//span[@class='ssc-ui-catalog-item__caption'][text()='Детские игрушки']"));
    }
    private WebElement searchField(){
        return getDriver().findElement(By.xpath("//input[@type='search']"));
    }
    private WebElement viewCartWithItems(){
        return getDriver().findElement(By.xpath("//div[@class='ssc-ui-count']"));
    }
    private WebElement checkoutCart(){
        return getDriver().findElement(By.xpath("//a[contains(text(),'Перейти в корзину')]"));
    }
    private WebElement  goToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Перейти в корзину')]"));
    }
    private WebElement  deleteItemFromCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Удалить')]"));
    }
    private WebElement  cartIsEmpty(){
        return getDriver().findElement(By.xpath("//div[contains(text(),'Корзина пуста')]"));
    }
    private WebElement deliveryMethodLocation(){
        return getDriver().findElement(By.xpath("//div[@id='vs2__combobox']//input[@class='vs__search']"));
    }
    private WebElement deliveryMethodPickup(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Самовывоз из офиса')]/../..//div[@role='checkbox']"));
    }
    private WebElement customerInfoName(){
        return getDriver().findElement(By.xpath("//div[contains(text(),'ФИО')]/../..//input[@type='text']"));
    }
    private WebElement customerInfoEmail(){
        return getDriver().findElement(By.xpath("//div[contains(text(),'E-mail')]/../..//input[@type='text']"));
    }
    private WebElement customerInfoPhone(){
        return getDriver().findElement(By.xpath("//div[contains(text(),'Телефон')]/../..//input[@name='customer_phone']"));
    }
    private WebElement paymentOptionCash(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Наличные деньги')]/../..//div[@role='checkbox']"));
    }
    private WebElement checkoutButton(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Оформить заказ')]"));
    }
    private WebElement successfulMessage(){
        return getDriver().findElement(By.xpath("//div[contains(text(),'Спасибо!')]"));
    }
    private WebElement  catalogElectronicsIPhoneAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Iphone 12')]/../../../../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogElectronicsIPhoneAddToCartConfirmButton(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'В корзину')]/../../button"));
    }
    private WebElement  catalogFurnitureCouchAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Кресло с подголовником')]/../../../../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogClothesHoodieAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Худи')]/../../../../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogClothesHoodieAddToCartConfirmation(){
        return getDriver().findElement(By.xpath("//input[@class='ssc-ui-counter__display']/../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogBooksNiSsAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'НИ СЫ.')]/../../../../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogPerfumesBlackOpiumAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'Black Opium')]/../../../../..//span[contains(text(),'В корзину')]"));
    }
    private WebElement  catalogToysLegoStarWarsAddToCart(){
        return getDriver().findElement(By.xpath("//span[contains(text(),'LEGO')]/../../../../..//span[contains(text(),'В корзину')]"));
    }

    private WebElement continueShoppingButton(){
        return getDriver().findElement(By.xpath("//button[contains(text(),'Продолжить')]"));
    }

    private void openMainPage() {
        getDriver().get("https://petstore.skystart.store/");
    }

    @Ignore
    @Test
    public void testCheckoutItem() {
        openMainPage();
        sideMenuCategoryElectronics().click();
        catalogElectronicsIPhoneAddToCart().click();
        catalogElectronicsIPhoneAddToCartConfirmButton().click();
        goToCart().click();
        deliveryMethodLocation().click();
        deliveryMethodLocation().sendKeys("г. Москва (Москва город)");
        deliveryMethodPickup().click();
        customerInfoName().sendKeys("Dmitry");
        customerInfoEmail().sendKeys("dmitry@testmail.com");
        customerInfoPhone().sendKeys("74990000001");
        paymentOptionCash().click();
        checkoutButton().click();
        Assert.assertEquals(successfulMessage().getText(), "Спасибо!");
    }

    @Test
    public void testCheckoutWithUniqueEmailErrorMsg() {
        openMainPage();
        sideMenuCategoryElectronics().click();
        catalogElectronicsIPhoneAddToCart().click();
        catalogElectronicsIPhoneAddToCartConfirmButton().click();
        goToCart().click();
        deliveryMethodLocation().click();
        deliveryMethodLocation().sendKeys("г. Москва (Москва город)");
        deliveryMethodPickup().click();
        customerInfoName().sendKeys("Dmitry");
        customerInfoEmail().sendKeys("dmitry@testmail.com");
        customerInfoPhone().sendKeys("74990000001");
        paymentOptionCash().click();
        checkoutButton().click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[@class='ssc-w-input__error']")).getText(),
                "Уже используется (должно быть уникальным).");
    }

    @Test
    public void testDeleteItemFromCart() {
        openMainPage();
        sideMenuCategoryFurniture().click();
        catalogFurnitureCouchAddToCart().click();
        goToCart().click();
        deleteItemFromCart().click();
        Assert.assertEquals(cartIsEmpty().getText(), "Корзина пуста");
    }

    @Test
    public void testMultipleItemsPresentInCart() {
        openMainPage();

        sideMenuCategoryElectronics().click();
        catalogElectronicsIPhoneAddToCart().click();
        catalogElectronicsIPhoneAddToCartConfirmButton().click();
        continueShoppingButton().click();

        sideMenuCategoryFurniture().click();
        catalogFurnitureCouchAddToCart().click();
        continueShoppingButton().click();

        sideMenuCategoryClothes().click();
        catalogClothesHoodieAddToCart().click();
        catalogClothesHoodieAddToCartConfirmation().click();
        continueShoppingButton().click();

        sideMenuCategoryBooks().click();
        catalogBooksNiSsAddToCart().click();
        continueShoppingButton().click();

        sideMenuCategoryPerfumes().click();
        catalogPerfumesBlackOpiumAddToCart().click();
        continueShoppingButton().click();

        sideMenuCategoryToys().click();
        catalogToysLegoStarWarsAddToCart().click();
        continueShoppingButton().click();

        viewCartWithItems().click();
        checkoutCart().click();

        Assert.assertEquals(getDriver().findElements(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']"))
                .size(), 6);
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'Iphone 12')]"))
                .isDisplayed(), "Iphone 12 - is not present in cart");
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'Кресло с подголовником')]"))
                .isDisplayed(), "Кресло с подголовником - is not present in cart");
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'Худи')]"))
                .isDisplayed(), "Худи - is not present in cart");
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'НИ СЫ.')]"))
                .isDisplayed(), "НИ СЫ. - is not present in cart");
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'Black Opium')]"))
                .isDisplayed(), "Black Opium - is not present in cart");
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-order-item ssc-b-order-item_large']//a[contains(text(),'Конструктор LEGO Star Wars Шлем штурмовика 75276')]"))
                .isDisplayed(), "Конструктор LEGO Star Wars Шлем штурмовика 75276 - is not present in cart");
    }

    @Test
    public void testSearchField() throws InterruptedException {
        openMainPage();
        searchField().click();
        searchField().sendKeys("Opium");
        Thread.sleep(1300);
        searchField().sendKeys(Keys.ENTER);
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='ssc-b-prd-card__data']")).getText().contains("Black Opium"));
    }
}
