package modules.Pages;

import io.restassured.path.json.JsonPath;
import libs.WebDriverActions;
import libs.utils.JsonPathUtils;
import org.openqa.selenium.By;

public class NewCheckOutPage extends CheckOutPage {

    JsonPath guestData;
    WebDriverActions actions;
    JsonPathUtils guestJsonPathUtils = new JsonPathUtils("./src/test/resources/GuestInformation.json");
    JsonPathUtils creditCardJsonPathUtils = new JsonPathUtils("./src/test/resources/CreditCardData.json");


    public NewCheckOutPage(WebDriverActions actions) {
        this.actions = actions;
    }

    private static final By PASSENGER_DETAILS = By.xpath("//a[@data-ody-id='PassengerDetailsTab']");
    private static final String TITLE = "(//select[@placeholder='Select Title'])[%d]";
    private static final String GENDER = "(//div[@class='formselect genderselect']//select)[%d]";
    private static final String FIRST_NAME = "(//input[@placeholder='First Name'])[%d]";
    private static final String LAST_NAME = "(//input[@placeholder='Last Name'])[%d]";
    private static final String MONTH = "//select[@id='_ctl0_MainContentsPH__ctl0_BookingPassengers__ctl%d_TravelerAccount_%d_DateUC_Month']";
    private static final String DAY = "//select[@id='_ctl0_MainContentsPH__ctl0_BookingPassengers__ctl%d_TravelerAccount_%d_DateUC_Day']";
    private static final String YEAR = "//select[@id='_ctl0_MainContentsPH__ctl0_BookingPassengers__ctl%d_TravelerAccount_%d_DateUC_Year']";
    private static final String NATIONALITY = "//select[@data-ody-id='NationalityDropDown_%d']";
    private static final By STREET = By.xpath("(//input[@data-ody-id='AddressInputField'])[1]");
    private static final By COUNTRY = By.xpath("(//select[@data-ody-id='CountryDropDown'])[1]");
    private static final By STATE = By.xpath("(//select[@data-ody-id='StateDropDown'])[1]");
    private static final By CITY = By.xpath("(//input[@placeholder='City'])[1]");
    private static final By ZIP_CODE = By.xpath("(//input[@placeholder='Post Zip Code'])[1]");
    private static final By CONTACT_PERSON = By.xpath("//a[@data-ody-id='BookingContactPersonTab']");
    private static final By EMAIL_ID = By.xpath("//input[@data-ody-id='BookingDetailsEmailTextField']");
    private static final By PH_NO = By.xpath("//div[@class='pass-field-titles BookingContact']//input[@data-ody-id='1stPhoneNumber']");
    private static final By ADDITIONAL_SERVICE = By.xpath("//div[@data-ody-id='AdditionalServiceTab']//a[@href='#nogo']");
    private static final By DINING_PRE = By.xpath("//select[@data-ody-id='DiningPreferenceDropdown']");
    private static final By TRAVEL_INSURANCE = By.xpath("//div[@data-ody-id='TravelInsurance']//a[@href='#nogo']");
    private static final By YES = By.cssSelector("[class='custom-control custom-radio custom-control-inline mb-0 insurance-yes-name']");
    private static final By NO = By.cssSelector("[class='custom-control custom-radio mb-0 insurance-no-name']");
    private static final By OK = By.xpath("//button[@id='Continue']");
    private static final By CONTINUE_TO_PAYMENT = By.cssSelector("[value='Continue To Payment']");
    private static final By CREDIT_CARD_TYPE = By.cssSelector("[data-ody-id='CreditCardTypeDropDown']");
    private static final By CREDIT_CARD_NUMBER = By.cssSelector("[data-ody-id='CreditCardNumberField']");
    private static final By CARD_HOLDER_NAME = By.cssSelector("[data-ody-id='CreditCardHolderName']");
    private static final By CARD_EXPIRY_MONTH = By.cssSelector("[name='_ctl0:MainContentsPH:_ctl0:_ctl4:CreditCardInfo:expireDate:Month']");
    private static final By CARD_EXPIRY_YEAR = By.cssSelector("[id='_ctl0_MainContentsPH__ctl0__ctl4_CreditCardInfo_expireDate_Year']");
    private static final By CVV = By.cssSelector("[data-ody-id='CreditCardCVV']");
    private static final By ADDRESS_SAME_CHECK_BOX = By.cssSelector("[id='_ctl0_MainContentsPH__ctl0__ctl4_Billing_MailAddressTR']");
    private static final By AGREE_CHECK_BOX = By.cssSelector("[data-ody-id='TermsandConditionsCheckbox']");
    private static final By COMPLETE_PAYMENT = By.cssSelector("[data-ody-id='CompletePaymentButton']");


    @Override
    public void waitForPageToLoad() {
        actions.waitForElementToBePresent(PASSENGER_DETAILS);
        actions.waitForElementToBeVisible(PASSENGER_DETAILS);

    }

    @Override
    public void fillCheckOutPageParameters(JsonPath testData) throws InterruptedException {
        actions.click(PASSENGER_DETAILS);
        int x = testData.getInt("detailsPage.numberOfGuests");
        for (int i = 0; i < x; i++) {
            guestData = guestJsonPathUtils.getJsonPath(String.format("guestInformation[%d]", i));
            actions.select(By.xpath(String.format(TITLE, (i + 1))), guestData.getString("title"));
            actions.select(By.xpath(String.format(GENDER, (i + 1))), guestData.getString("gender"));
            actions.type(By.xpath(String.format(FIRST_NAME, (i + 1))), guestData.getString("firstName"));
            actions.type(By.xpath(String.format(LAST_NAME, (i + 1))), guestData.getString("lastName"));
            String[] DOB = guestData.getString("dateOfBirth").split("-");
            actions.select(By.xpath(String.format(MONTH, i, (i + 1))), DOB[1]);
            actions.select(By.xpath(String.format(DAY, i, (i + 1))), DOB[0]);
            actions.select(By.xpath(String.format(YEAR, i, (i + 1))), DOB[2]);
            actions.select(By.xpath(String.format(NATIONALITY, (i + 1))), guestData.getString("address.country"));

        }

        actions.type(STREET, guestData.getString("address.Street"));
        actions.select(COUNTRY, guestData.getString("address.country"));
        actions.select(STATE, guestData.getString("address.state"));
        actions.type(CITY, guestData.getString("address.city"));
        actions.type(ZIP_CODE, guestData.getString("address.zipCode"));
        actions.click(CONTACT_PERSON);
        actions.type(EMAIL_ID, testData.getString("checkoutPage.bookingContactPerson.email"));
        actions.type(PH_NO, testData.getString("checkoutPage.bookingContactPerson.phone"));
        if (Boolean.parseBoolean(testData.getString("checkoutPage.diningPreference"))) {
            actions.click(ADDITIONAL_SERVICE);
            actions.select(DINING_PRE, 1);
        }
        actions.click(TRAVEL_INSURANCE);
        if (Boolean.parseBoolean(testData.getString("checkoutPage.travelInsurance")))
            actions.click(YES);
        else {
            actions.click(NO);
            actions.click(OK);
        }
        actions.click(CONTINUE_TO_PAYMENT);

    }

    @Override
    public void fillCreditCardDetails(JsonPath testData) throws InterruptedException {
        JsonPath creditCardData = creditCardJsonPathUtils.getJsonPath("valid",testData.getString("searchPage.cruiseLine"));
        actions.select(CREDIT_CARD_TYPE, creditCardData.getString("creditCardType"));
        actions.type(CREDIT_CARD_NUMBER, creditCardData.getString("cardNumber"));
        actions.type(CARD_HOLDER_NAME, creditCardData.getString("cardHolderName"));
        actions.select(CARD_EXPIRY_MONTH, creditCardData.getString("expiryMonth"));
        actions.select(CARD_EXPIRY_YEAR, creditCardData.getString("expiryYear"));
        actions.type(CVV, creditCardData.getString("cvv"));
        actions.click(ADDRESS_SAME_CHECK_BOX);
        actions.click(AGREE_CHECK_BOX);
        actions.click(COMPLETE_PAYMENT);

        Thread.sleep(10000);
    }

}

