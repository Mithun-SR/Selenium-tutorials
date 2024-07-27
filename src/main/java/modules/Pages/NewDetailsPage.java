package modules.Pages;

import io.restassured.path.json.JsonPath;
import libs.WebDriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class NewDetailsPage extends DetailsPage{
    WebDriverActions actions;
    public NewDetailsPage(WebDriverActions actions) {
        this.actions = actions;
    }

    static private final By BOOK = By.cssSelector("[data-ody-id='BookButton']");
    static private final By GUEST_COUNT = By.xpath("//span[@data-ody-id='GuestCount']//span");
    static private final By ADD = By.xpath("//button[@data-ody-id='GuestIncreaseCount']");
    static private final By DELETE = By.xpath("//button[@data-ody-id='GuestDecreaseCount']");
    static private final String GUEST = "(//input[@type='number'])[%d]";
    static private final By CONTINUE_1 = By.xpath("(//button[@data-ody-id='ContinueButton'])[1]");
    static private final By CONTINUE_2 = By.xpath("(//button[@data-ody-id='ContinueButton'])[3]");
    static private final By CONTINUE_3 = By.xpath("//button[@data-ody-id='ContinueToBookingButton']");
    static private final By COUNTRY = By.xpath("//*[@data-ody-id='country']//*[@role='textbox']");
    static private final By COUNTRY_TEXT_BOX = By.xpath("//span[@style='position: absolute; top: 158.5px; left: 27px;']//input[@class='select2-search__field']");
    static private final By STATE = By.xpath("//*[@data-ody-id='state']//*[@role='textbox']");
    static private final By STATE_TEXT_BOX = By.xpath("//span[@style='position: absolute; top: 158.5px; left: 410px;']//input[@class='select2-search__field']");
    static private final By CATEGORY_PAGE = By.cssSelector("[class='nav-item  active-nav-item']");




    @Override
    public void waitForPageToLoad() {
        actions.waitForElementToBePresent(BOOK);
        actions.waitForElementToBeVisible(BOOK);
        actions.waitForElementToBeClickable(BOOK);
        actions.click(BOOK);
        actions.waitForElementToBePresent(By.xpath(String.format(GUEST,1)));
        actions.waitForElementToBeVisible(By.xpath(String.format(GUEST,1)));

    }

    @Override
    public void fillDetailsPageParameters(JsonPath jsonPath) {
        while (Integer.parseInt(actions.getWebDriver().findElement(GUEST_COUNT).getText())!=Integer.parseInt(jsonPath.getString("detailsPage.numberOfGuests"))) {
            if (Integer.parseInt(actions.getWebDriver().findElement(GUEST_COUNT).getText()) < Integer.parseInt(jsonPath.getString("detailsPage.numberOfGuests")))
                actions.click(ADD);
            else if (Integer.parseInt(actions.getWebDriver().findElement(GUEST_COUNT).getText()) > Integer.parseInt(jsonPath.getString("detailsPage.numberOfGuests")))
                actions.click(DELETE);
        }
        for (int i = 0; i<Integer.parseInt(jsonPath.getString("detailsPage.numberOfGuests")); i++){
            actions.type(By.xpath(String.format(GUEST,(i+1))),jsonPath.getString("detailsPage.guestAge["+i+"]"));
        }
        actions.click(CONTINUE_1);
        actions.sleep(1);
        actions.click(COUNTRY);
        actions.type(COUNTRY_TEXT_BOX,jsonPath.getString("detailsPage.country"),Keys.ENTER);
        actions.click(STATE);
        actions.type(STATE_TEXT_BOX,jsonPath.getString("detailsPage.state"),Keys.ENTER);
        actions.click(CONTINUE_2);
        actions.click(CONTINUE_3);

    }

    @Override
    public void confirmLandOnCategoryPage() {
      actions.waitForElementToBePresent(CATEGORY_PAGE);
      actions.waitForElementToBeVisible(CATEGORY_PAGE);
    }
}
