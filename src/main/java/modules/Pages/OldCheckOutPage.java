package modules.Pages;

import io.restassured.path.json.JsonPath;
import libs.WebDriverActions;

public class OldCheckOutPage extends CheckOutPage{

    WebDriverActions actions;
    public OldCheckOutPage(WebDriverActions actions) {
        this.actions = actions;
    }

    @Override
    public void waitForPageToLoad() {

    }

    @Override
    public void fillCheckOutPageParameters(JsonPath testData) {

    }

    @Override
    public void fillCreditCardDetails(JsonPath testData) {

    }


}
