package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// OPTIONAL (recommended)
import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
    public static void main(String[] args) {

        // 🔥 Setup driver automatically (VERY IMPORTANT)
        WebDriverManager.chromedriver().setup();

        // Setup Chrome options for Jenkins / Linux
        ChromeOptions options = new ChromeOptions();

        // 🔥 CRITICAL FIXES
        options.setBinary("/usr/bin/google-chrome");  // ensure Jenkins uses correct Chrome
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        // 🔥 Extra stability flags (important in CI)
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
