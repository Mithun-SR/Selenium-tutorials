package modules.Pages;

import io.restassured.path.json.JsonPath;
import libs.WebDriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class OldSearchPage extends SearchPage {
    WebDriverActions actions;
    public static String [] fromDate;
    public static String [] toDate;

    public OldSearchPage(WebDriverActions actions) {
        this.actions = actions;
    }

    // Locators
    static final By LOADER_SPIRAL = By.cssSelector("[class='loader-spiral']");
    static final By SELECT_CRUISE_LINE = By.cssSelector("[placeholder='Select Cruise Line']");
    static final By SEARCH_BUTTON = By.cssSelector("button[data-ody-id='SearchButton']");
    static final By SAILING_DATES = By.cssSelector("[data-ody-id='sailingDates']");
    static final By CURRENT_YEAR = By.xpath("(//div[@class='bs-datepicker-head'])[1]//button[@class='current']");
    static final By RESULT_PAGE = By.cssSelector("[data-ody-id='CruiseSailingDatesSection']");
    static final String FROM_YEAR = "((//div[@class='bs-datepicker-body'])[1]//tr//td)//*[contains(normalize-space(),%s)]";
    static final String FROM_MONTH = "((//div[@class='bs-datepicker-body'])[1])//span[contains(normalize-space(),%s)]";
    static final String FROM_DATE = "((//div[@class='bs-datepicker-body'])[1])//tr//td//*[normalize-space()=%s][not(contains(@class,'is-other-month'))]";
    static final String TO_YEAR = "((//div[@class='bs-datepicker-body'])[1]//tr//td)//*[contains(normalize-space(),%s)]";
    static final String TO_MONTH = "((//div[@class='bs-datepicker-body'])[1])//span[contains(normalize-space(),%s)]";
    static final String TO_DATE = "((//div[@class='bs-datepicker-body'])[1])//tr//td//*[normalize-space()=%s][not(contains(@class,'is-other-month'))]";

    @Override
    public void waitForPageToLoad() {
        actions.waitForElementToDisappear(LOADER_SPIRAL);
        actions.waitForElementToBeVisible(SELECT_CRUISE_LINE);
        actions.waitForElementToBeVisible(SEARCH_BUTTON);
    }

    @Override
    public void fillSearchParameters(JsonPath testData) {
        actions.type(SELECT_CRUISE_LINE, testData.getString("searchPage.cruiseLine"), Keys.ENTER);
        selectsSailingDates(testData);
    }

    public void selectsSailingDates(JsonPath testData) {

        fromDate = testData.getString("searchPage.sailingDates.from").split("-");
        toDate = testData.getString("searchPage.sailingDates.to").split("-");
        actions.click(SAILING_DATES);
        actions.click(CURRENT_YEAR);
        actions.click(By.xpath(String.format(FROM_YEAR,fromDate[2])));
        actions.click(By.xpath(String.format(FROM_MONTH,fromDate[1])));
        actions.click(By.xpath(String.format(FROM_DATE,fromDate[0])));
        actions.click(CURRENT_YEAR);
        actions.click(By.xpath(String.format(TO_YEAR,toDate[2])));
        actions.click(By.xpath(String.format(TO_MONTH,toDate[1])));
        actions.click(By.xpath(String.format(TO_DATE,toDate[0])));

    }

    @Override
    public void clickOnSearch() {
        actions.click(SEARCH_BUTTON);
    }

    @Override
    public void ConfirmThatLandedOnResultspage() {
        actions.waitForElementToBePresent(RESULT_PAGE);
        actions.waitForElementToBeVisible(RESULT_PAGE);
    }
}
