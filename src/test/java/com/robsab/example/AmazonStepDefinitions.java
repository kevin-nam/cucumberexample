package com.robsab.example;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.robsab.example.StaticVariables.ADDED_TO_CART_SUCCESS_MSG;
import static com.robsab.example.StaticVariables.ADDED_TO_CART_SUCCESS_MSG_CONTAINER;
import static com.robsab.example.StaticVariables.ADD_TO_CART_BTN_ID;
import static com.robsab.example.StaticVariables.CURSOR_NOT_ALLOWED;
import static com.robsab.example.StaticVariables.FIRST_PRODUCT_RESULT_CONTAINER_CLASS;
import static com.robsab.example.StaticVariables.PRODUCT_URL_TO_BASKETBALL_HOOP_SYSTEM;
import static com.robsab.example.StaticVariables.SEARCH_BAR_ID;
import static com.robsab.example.StaticVariables.SEARCH_BAR_SUBMIT_CLASS;
import static com.robsab.example.StaticVariables.SELECT_BY_SIZE_ID;
import static com.robsab.example.StaticVariables.STYLE_ATTR;

public class AmazonStepDefinitions {

    private WebDriver driver;

    // User configuration
    private final int TIMEOUT = 3;
    private final String PATH_TO_CHROME_DRIVER_MAC = "src/test/resources/drivers/chromedriver-mac";
    private final String PATH_TO_CHROME_DRIVER_WINDOWS = "src/test/resources/drivers/chromedriver-windows.exe";

    /**
     * Given
     */

    @Given("^I am using a Google Chrome Browser$")
    public void iAmUsingAGoogleChromeBrowser() throws Throwable {
        if (driver == null || !ChromeDriver.class.isInstance(driver)) {
            System.out.println("Setting up ChromeDriver... ");
            System.setProperty("webdriver.chrome.driver", PATH_TO_CHROME_DRIVER_MAC);
            driver = new ChromeDriver();
            System.out.print("Done!\n");
        }
    }

    @And("^I am on the Amazon front page$")
    public void iAmOnAmazonFrontPage() throws Throwable {
        navigateTo("http://www.amazon.ca");
    }

    /**
     * When
     */

    @When("^Navigating to a known product page$")
    public void whenNavigatingToAKnownProductPage() throws Throwable {
        navigateTo(PRODUCT_URL_TO_BASKETBALL_HOOP_SYSTEM);
    }

    @When("^I press \"Add to cart\"$")
    public void iPressAddToCart() throws Throwable {
        try {
            System.out.println("Attempting to find Add to cart button... ");
            WebElement btn = (new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(By.id(ADD_TO_CART_BTN_ID)));
            System.out.print("Found!\n");
            btn.click();
            System.out.println("Clicking add to cart button");
        } catch (Exception e) {
            System.out.println("No add to cart button present");
        }
    }

    @When("^I search for \"([^\"]*)\"$")
    public void whenISearchFor(String productToSearch) throws Throwable {
        try {
            System.out.println("Attempting to find search bar... ");
            WebElement searchBar = (new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_BAR_ID)));
            System.out.print("Found!\n");
            System.out.println("Searching for " + productToSearch + "...");
            searchBar.sendKeys(productToSearch);
            WebElement searchBarSubmitBtn = (new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className(SEARCH_BAR_SUBMIT_CLASS)));
            searchBarSubmitBtn.click();
        } catch (Exception e) {
            System.out.println("No search bar present");
        }
    }

    @When("^Choosing the first product found in the results$")
    public void whenChoosingFirstProductFound() throws Throwable {
        try {
            System.out.println("Attempting to find container of first product result...");
            WebElement firstProductLinkContainer = (new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.className(FIRST_PRODUCT_RESULT_CONTAINER_CLASS)));
            System.out.print("Found!\n");
            navigateTo(firstProductLinkContainer.getAttribute("href"));

        } catch (Exception e) {
            System.out.println("No first product result present");
        }
    }

    @When("^Selecting the first size available$")
    public void selectingTheFirstSizeAvailable() throws Throwable {
        try {
            System.out.println("Attempting to find select size dropdown...");
            Select dropdown = new Select((new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(SELECT_BY_SIZE_ID))));
            System.out.print("Found!\n");
            System.out.println("Selecting first size available\n");
            dropdown.selectByIndex(1);

            // Wait for add to cart button to change
            new WebDriverWait(driver, TIMEOUT)
                    .until((ExpectedCondition<Boolean>) driver -> {
                        WebElement btn = driver.findElement(By.id(ADD_TO_CART_BTN_ID));
                        return !btn.getAttribute(STYLE_ATTR).equals(CURSOR_NOT_ALLOWED);
                    });

        } catch (Exception e) {
            System.out.println("No dropdown present");
        }
    }


    /**
     * Then
     */

    @Then("^The item should be successfully added to cart")
    public void thenItemShouldBeSuccessfullyAddedToCart() throws Throwable {
        try {
            System.out.println("Attempting to find Add To Cart success message... ");
            WebElement successMsgContainer = (new WebDriverWait(driver, TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(ADDED_TO_CART_SUCCESS_MSG_CONTAINER)));
            System.out.print("Found!\n");

            Assert.assertTrue(isStringFoundInText(ADDED_TO_CART_SUCCESS_MSG, successMsgContainer.getText()));
        } catch (Exception e) {
            driver.quit();
            Assert.fail("Unable to find Added To Cart success message.");
        }

        driver.quit();
    }

    /**
     *  Useful helper functions
     */

    private void navigateTo(String url) {
        if (driver != null) {
            System.out.println("Navigating to " + url + "\n");
            driver.get(url);
        }
    }

    private boolean isStringFoundInText(String stringToFind, String text) {
        return text.contains(stringToFind);
    }

    private String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
