package modules.Pages;

import libs.WebDriverActions;
import org.openqa.selenium.By;

public class OldCabinPage extends CabinPage{
    WebDriverActions actions;

    public OldCabinPage(WebDriverActions actions) {
        this.actions = actions;
    }

    static private final By Book = By.xpath("(//a[@class='booknow primary-btn'])[1]");


    @Override
    public void waitForPageToLoad() {
        actions.waitForElementToBePresent(Book);
        actions.waitForElementToBeVisible(Book);

    }

    @Override
    public void bookCabin() {
        actions.click(Book);

    }


    @Override
    public void confirmLandedOnCheckoutPage() {


    }
}
