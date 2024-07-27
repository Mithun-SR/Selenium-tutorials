package cruiseTests;

import com.aventstack.extentreports.Status;
import libs.utils.LoggerUtils;
import libs.utils.ReporterUtils;
import modules.*;
import libs.utils.TestNGRetry;
import libs.utils.ScreenGrabber;
import modules.Pages.CabinPage;
import objects.EnumContainer.*;
import objects.PackageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import libs.BaseTest;
import libs.WebDriverActions;

public class CruiseFlowTests extends BaseTest {
    @Test(testName = "Cruise Flow Full Payment Test", dataProvider = "genericDataProvider", groups = {"Id-01", "smoke"})
    @Parameters(value = {"cruise", "fullPayment"})
    public void cruiseFlowFullPaymentTest(WebDriverActions actions, JsonPath testData) throws Exception {

        // Variables
        PackageObject packageObject = new PackageObject();
        SearchModule searchModule = new SearchModule(actions);
        ResultsModule resultsModule = new ResultsModule(actions);
        DetailsModule detailsModule = new DetailsModule(actions);
        CategoryModule categoryModule = new CategoryModule(actions);
        CabinModule cabinModule = new CabinModule(actions);
        CheckOutModule checkOutModule = new CheckOutModule(actions);

        // Set Test Data
        packageObject.setSiid(130386);
        packageObject.setCreditCardType(CreditCardType.Valid);

        // Search Page actions
        searchModule.openSearchPageUrl(packageObject);
        searchModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS, "SearchPage Loaded");
        ScreenGrabber.getScreenshot(actions.getWebDriver(), Pages.SearchPage.toString());
        searchModule.fillSearchParameters(testData);
        searchModule.clickOnSearch();
        searchModule.confirmThatLandedOnResultsPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on results page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(), Pages.ResultsPage.toString());

        // Results Page actions
        resultsModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"results page loaded");
        resultsModule.clickBookNowButton();
        resultsModule.ConfirmThatLandedOnDetailsPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on details page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.DetailsPage.toString());

        //Details Page actions
        detailsModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Details page loaded");
        detailsModule.FillDetailsPageParameters(testData);
        detailsModule.ConfirmLandOnCategoryPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on category page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CategoryPage.toString());

        //CategoryPageActions
        categoryModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"category page loaded");
        categoryModule.clickBookButton();
        categoryModule.confirmLandOnCabinPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on cabin page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CabinPage.toString());

        //CabinPageActions
        cabinModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Cabin page loaded");
        cabinModule.bookCabin();
        cabinModule.confirmLandedOnCheckoutPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on Checkout page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CheckoutPage.toString());

        //Checkout Page Actions
        checkOutModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Checkout page loaded");
        checkOutModule.fillCheckOutPageParameters(testData);
        checkOutModule.fillCreditCardDetails(testData);
    }
    @Test(testName = "Cruise Flow Full Payment Test", dataProvider = "genericDataProvider", groups = {"Id-01", "smoke"})
    @Parameters(value = {"cruise", "fullPayment"})
    public void cruiseFlowFullPaymentTestOld(WebDriverActions actions, JsonPath testData) throws Exception {

        // Variables
        PackageObject packageObject = new PackageObject();
        SearchModule searchModule = new SearchModule(actions);
        ResultsModule resultsModule = new ResultsModule(actions);
        DetailsModule detailsModule = new DetailsModule(actions);
        CategoryModule categoryModule = new CategoryModule(actions);
        CabinModule cabinModule = new CabinModule(actions);
        CheckOutModule checkOutModule = new CheckOutModule(actions);

        // Set Test Data
        packageObject.setSiid(130385);

        // Search Page actions
        searchModule.openSearchPageUrl(packageObject);
        searchModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS, "SearchPage Loaded");
        ScreenGrabber.getScreenshot(actions.getWebDriver(), Pages.SearchPage.toString());
        searchModule.fillSearchParameters(testData);
        searchModule.clickOnSearch();
        searchModule.confirmThatLandedOnResultsPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on results page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(), Pages.ResultsPage.toString());

        // Results Page actions
        resultsModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"results page loaded");
        resultsModule.clickBookNowButton();
        resultsModule.ConfirmThatLandedOnDetailsPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on details page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.DetailsPage.toString());

        //Details Page actions
        detailsModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Details page loaded");
        detailsModule.FillDetailsPageParameters(testData);
        detailsModule.ConfirmLandOnCategoryPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on category page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CategoryPage.toString());

        //CategoryPageActions
        categoryModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"category page loaded");
        categoryModule.clickBookButton();
        categoryModule.confirmLandOnCabinPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on cabin page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CabinPage.toString());

        //CabinPageActions
        cabinModule.waitForPageToLoad(packageObject);
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Cabin page loaded");
        cabinModule.bookCabin();
        cabinModule.confirmLandedOnCheckoutPage();
        ReporterUtils.writeStatusToReportWithMsg(Status.PASS,"Everything went well and landed on Checkout page");
        ScreenGrabber.getScreenshot(actions.getWebDriver(),Pages.CheckoutPage.toString());

        //Checkout Page Actions
        checkOutModule.waitForPageToLoad(packageObject);
        checkOutModule.fillCheckOutPageParameters(testData);
    }

    @Test(testName = "Cruise Flow Deposit Payment Test", dataProvider = "genericDataProvider", groups = {"Id-02", "smoke"})
    @Parameters(value = {"cruise", "depositPayment"})
    public void cruiseFlowDepositPaymentTest(WebDriverActions actions, JsonPath testData) {
            System.out.println(System.getProperty("name"));


    }

    @Test(testName = "Cruise Flow Hold Payment Test", dataProvider = "genericDataProvider", groups = {"Id-03", "reg"})
    @Parameters(value = {"cruise", "holdPayment"})
    public void cruiseFlowHoldPaymentTest(WebDriverActions actions, JsonPath testData) {

    }
}















