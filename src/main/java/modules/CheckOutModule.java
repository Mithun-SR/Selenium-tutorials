package modules;

import io.restassured.path.json.JsonPath;
import libs.WebDriverActions;
import modules.Pages.CheckOutPage;
import modules.Pages.NewCheckOutPage;
import modules.Pages.OldCheckOutPage;
import objects.PackageObject;

public class CheckOutModule {
    WebDriverActions actions;
    CheckOutPage checkOutPage;

    public CheckOutModule(WebDriverActions actions) {
        this.actions = actions;
    }

    public void initiatePageReference(PackageObject packageObject){
        if(packageObject.getSiid() == 130386)
            checkOutPage = new NewCheckOutPage(actions);
        else if (packageObject.getSiid() == 130385)
            checkOutPage = new OldCheckOutPage(actions);
    }

    public void waitForPageToLoad(PackageObject packageObject){
        initiatePageReference(packageObject);
        checkOutPage.waitForPageToLoad();
    }

    public void fillCheckOutPageParameters(JsonPath testData) throws InterruptedException {
        checkOutPage.fillCheckOutPageParameters(testData);
    }

    public void fillCreditCardDetails(JsonPath testData) throws InterruptedException {
        checkOutPage.fillCreditCardDetails(testData);
    }
}

