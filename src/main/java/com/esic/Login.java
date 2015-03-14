package com.esic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
public static void main(String[] args) {
	WebDriver driver = new FirefoxDriver();
    String baseUrl = "http://www.esic.in";

    driver.get(baseUrl);
    //open login page: findElement(By.linkText("REGISTRATION"))
    WebElement loginElement=driver.findElement(By.id("LinkLoginpage"));
    String loginLink = loginElement.getAttribute("href");
    System.out.println(loginLink);
    loginElement.click();
}
}
