package modules.Pages;

import io.restassured.path.json.JsonPath;

public abstract class CheckOutPage {

   public abstract void  waitForPageToLoad ();

   public abstract void fillCheckOutPageParameters(JsonPath testData) throws InterruptedException;

   public abstract void fillCreditCardDetails( JsonPath testData) throws InterruptedException;


}
