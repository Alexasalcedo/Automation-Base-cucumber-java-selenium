package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    /* 
    Declaracion de una variable estática 'driver' de tipo WebDriver
    Esta variable va a ser compartida por todas las instancias de BasePage y sus subclase
    */
    protected static WebDriver driver;
    /* 
     * Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia de WebDriverWait utilizando ek 'driver' estativo
     * WebDriverWait se usa para poner esperas explicitas en los elementos web
     */
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    /* 
     * Configura el Web driver para Chrome usando WebDriverManager.
     * WebDriver Manager va a estar descargando y configurando automaticamente el driver del navegador 
     */
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    //Este es el constructor de BasePage que acepta un objeto WebDriver como argumento
    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    //Metodo estatico para navegar a una URL
    public static void navigateTo(String url){
        driver.get(url);
    }

    //Encuentra y devuelve un WebElement en la pagina utilizando un locator Xpath, esperando a que este presente en el DOM
    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    //Hace click en un elemento
    public void clickElement(String locator){
        Find(locator).click();
    }

    //Limpia el campo y escribe en el
    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }

    //Selecciona un opcion del dropdown por valor
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByValue(value);
    }

    //Selecciona una opcion de un dropdown por indice
    public void selectFromDropdownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));

        dropdown.selectByIndex(index);
    }

    //Retorna el numero de opciones de un dropdown
    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));

        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();
    }

    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(Find(locator));
 
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }
 
        return values;
 
    }

    //Cierra la instancia del driver
    public static void closeBrowser(){
        driver.quit();
    }

}
