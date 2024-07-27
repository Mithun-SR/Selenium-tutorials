package modules.Pages;

import libs.WebDriverActions;
import org.openqa.selenium.By;

public class OldCategoryPage extends CategoryPage{
    WebDriverActions actions;

    public OldCategoryPage(WebDriverActions actions) {
        this.actions = actions;
    }

    static private final By BOOK = By.xpath("(//div[@class='categoryview-price-gride']//a[@class='booknow primary-btn'])[1]");
    static private final By CONTINUE = By.xpath("(//a[@onclick='SubmitSelection()'])[last()]");
    static private final By CABIN_PAGE = By.xpath("//li[@class='step-current']//div[normalize-space()='Staterooms']");


    @Override
    public void waitFoePageToLoad() {
        actions.waitForElementToBePresent(BOOK);
        actions.waitForElementToBeVisible(BOOK);
    }

    @Override
    public void clickBookButton() {
        actions.click(BOOK);
        try {
            actions.waitForElementToBePresent(CONTINUE);
            actions.waitForElementToBeVisible(CONTINUE);
            actions.click(CONTINUE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void confirmLandedOnCabinPage() {
        actions.waitForElementToBePresent(CABIN_PAGE);
        actions.waitForElementToBeVisible(CABIN_PAGE);

    }
}
