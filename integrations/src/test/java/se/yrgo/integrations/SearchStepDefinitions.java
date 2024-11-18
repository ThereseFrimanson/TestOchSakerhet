package se.yrgo.integrations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchStepDefinitions {
    WebDriver driver;

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
        WebElement element = driver.findElement(By.xpath("//td[contains(@class, 'text-ellipsis') and text()='T-SQL Fundamentals']"));
        assertTrue(element.isDisplayed(), "Search results are not displayed");
        driver.close();
    }
}

