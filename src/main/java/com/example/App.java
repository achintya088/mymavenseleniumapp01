package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App 
{
    public static void main(String[] args)
    {
        // Setup Chrome options for headless execution (Jenkins compatible)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");                // Run without GUI
        options.addArguments("--no-sandbox");              // Required in Jenkins/VM
        options.addArguments("--disable-dev-shm-usage");   // Prevent crashes
        options.addArguments("--remote-allow-origins=*");  // Fix for newer Chrome issues

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open website
            driver.get("https://www.saucedemo.com/");

            // Perform login actions
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
