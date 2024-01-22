package pages;

public class PaginaFundamentos extends BasePage{
    
    private String introTestingLink = "//a[@href='/introduccion-al-testing-de-software'][normalize-space()='Ver producto']";

    public PaginaFundamentos(){
        super(driver);
    }

    public void clickIntroTesting(){
        clickElement(introTestingLink);
    } 
}
