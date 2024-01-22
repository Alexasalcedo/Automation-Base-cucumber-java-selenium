package pages;
 
public class PaginaPrincipal extends BasePage {
 
    private String sectionLink = "//a[normalize-space()='%s' and @href]";
    private String elegirPlan = "//a[normalize-space()='Elegir Plan' and @href]";
 
    public PaginaPrincipal() {
        super(driver);
    }
 
    // Método para navegar a www.freerangetesters.com
    public void navigateToFreeRangeTesters() {
        navigateTo("https://www.freerangetesters.com");
    }

    public void clickOnSectionNavigationBar(String section){
        //remplazar el marcador de posicion en sectionLink con el nombre
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

    public void clickOnElegirPlan(){
        clickElement(elegirPlan);
    }
 
}
