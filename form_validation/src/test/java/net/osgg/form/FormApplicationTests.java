//Tarea 1

package net.osgg.form;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class FormApplicationTests {

	private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/registration"); //contenido a verificar
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterAll
    public void teardown() throws InterruptedException {
        if (driver != null) {
        	//Thread.sleep(1000); //no recomendable, sólo como ejemplo
        	driver.close();
            driver.quit();
        }
    }

    @Test //CP01
    public void testAgeVECP01() throws InterruptedException {
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("name")).clear();
    	driver.findElement(By.id("age")).sendKeys("22");
    	driver.findElement(By.id("name")).sendKeys("Cristopher");
    	driver.findElement(By.id("age")).submit();	    	

    	WebElement msgElement = driver.findElement(By.id("msg"));
    	
    	
    	assertTrue( msgElement.getText().contains("Welcome") 
    				 //&& isInteger && 
    				//(Integer.valueOf(ageElement.getText()) >= 18 && 
    			     //Integer.valueOf(ageElement.getText()) <= 65) 
    				);
    }


    @Test //para probar ingreso del nombre correcto
    public void testName() throws InterruptedException {
    	driver.findElement(By.id("name")).clear();
    	driver.findElement(By.id("age")).sendKeys("18");
    	driver.findElement(By.id("name")).sendKeys("Andy");
    	driver.findElement(By.id("name")).submit();
    	
    	WebElement idContent = driver.findElement(By.id("name"));
    	assertEquals("Andy", idContent.getText());
    	//Thread.sleep(1500); //no recomendable, sólo como ejemplo
    }
    
    
    @Test // ingresa campo nombre vacio
    public void testAgeNVECP07() throws InterruptedException {
    	driver.findElement(By.id("name")).clear();
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("20");
    	driver.findElement(By.id("name")).sendKeys("");
    	driver.findElement(By.id("age")).submit();	
    	driver.findElement(By.id("name")).submit();	    	

    	WebElement idContent = driver.findElement(By.id("name"));
    	assertEquals("", idContent.getText());
    }    
    
    @Test // ingresa campo nombre vacio
    public void testAgeNVECP06() throws InterruptedException {
    	driver.findElement(By.id("name")).clear();
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("19");
    	driver.findElement(By.id("name")).sendKeys("a");
    	driver.findElement(By.id("age")).submit();	
    	driver.findElement(By.id("name")).submit();	    	

    	WebElement idContent = driver.findElement(By.id("name"));
    	assertEquals("", idContent.getText());
    }  
    
    @Test // ingresa campo nombre y numero
    public void testAgeNVECP08() throws InterruptedException {
    	driver.findElement(By.id("name")).clear();
    	driver.findElement(By.id("age")).clear();
    	driver.findElement(By.id("age")).sendKeys("22");
    	driver.findElement(By.id("name")).sendKeys("Cristop33r");
    	driver.findElement(By.id("age")).submit();	
    	driver.findElement(By.id("name")).submit();	    	

    	WebElement idContent = driver.findElement(By.id("name"));
    	assertEquals("", idContent.getText());
    }  
    
}


