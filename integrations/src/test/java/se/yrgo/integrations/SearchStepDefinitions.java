package se.yrgo.integrations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {
    WebDriver driver;
//Scenario 1
    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        driver = GeneralStepDefinitions.getDriver();
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        WebElement searchform = driver.findElement(By.tagName("form"));
        assertTrue(searchform.isDisplayed(), "searchform did not show");
    }
//Scenario 2
    @Given("the user is on the search page.")
    public void the_user_is_on_the_search_page() {
        driver = GeneralStepDefinitions.getDriver();
        driver.get("http://frontend/search");
    }

    @When("the user writes a title in the title field")
    public void the_user_writes_a_title_in_the_title_field() {
        WebElement titleInput = driver.findElement(By.xpath("//input[@placeholder='Title']"));
        titleInput.sendKeys("SQL");
    }

    @When("clicks on search.")
    public void clicks_on_search() {
        driver.findElement(By.cssSelector("input[value='Search']")).click();
    }

    @Then("they can see the book they searched for.")
    public void they_can_see_the_book_they_searched_for() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(@class, 'text-ellipsis') and contains(text(), 'T-SQL')]")
        ));
        assertTrue(element.isDisplayed(), "The book title was not displayed in the search results.");
        driver.quit();
    }

    //Scenario 3

    @Given("the user starts on the search page.")
    public void the_user_starts_on_the_search_page() {
        driver = GeneralStepDefinitions.getDriver();
        driver.get("http://frontend/search");
    }

    @When("the user writes an isbn in the isbn field")
    public void the_user_writes_an_isbn_in_the_isbn_field() {
        WebElement titleInput = driver.findElement(By.xpath("//input[@placeholder='ISBN']"));
        titleInput.sendKeys("9781509302000");
    }

    @When("clicks on search button.")
    public void clicks_on_search_button() {
        driver.findElement(By.cssSelector("input[value='Search']")).click();
    }

    @Then("they can see the book with correct isbn.")
    public void they_can_see_the_book_with_correct_isbn() {
        WebElement element = driver.findElement(By.xpath("//td[contains(@class, 'text-ellipsis') and text()='T-SQL Fundamentals']"));
        assertTrue(element.isDisplayed(), "Search results are not displayed");
        driver.close();
    }
}

