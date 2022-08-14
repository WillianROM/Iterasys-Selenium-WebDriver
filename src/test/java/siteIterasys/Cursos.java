//1 - Pacote
package siteIterasys;

// 2 - Bibliotecas
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;


// 3 - Classe
public class Cursos {
    //3.1 Atributos / Características
    private String url;         // endereço do site alvo
    private ChromeDriver driver;  // objeto do Selenium WebDriver

    //3.2 Métodos ou Funções
        //ANOTAÇÕES:
        //@Before - Tudo que vai acontecer antes de CADA teste
        //@BefereClass - Tudo que vai acontecer antes de TODOS os testes
        //@After - Tudo que vai acontecer depois de CADA teste
        //@AfterClass - Tudo que vai acontecer depos de TODOS os testes
        //@Test  - É o próprio teste

    @Before
    public void iniciar(){
        url = "https://iterasys.com.br/";

        //Com o webdrivermanager o driver fica salvo em C:\Users\ NOME DO USUÁRIO \.cache\selenium\chromedriver\win32
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS); //Espera milisegundos
        driver.manage().window().maximize();
    }

    @After
    public void finalizar(){
        //driver.quit();
    }

    @Test
    public void consultarCurso(){
        String curso = "Python";
        String resultadoEsperado = "Introdução ao Python";

        driver.get(url);
        driver.findElement(By.cssSelector("a[text='Cursos'][class='nav-item clickable has-border']")).click();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS); //Espera milisegundos

        driver.findElement(By.cssSelector("input[placeholder=\"Buscar...\"]")).clear();
        driver.findElement(By.cssSelector("input[placeholder=\"Buscar...\"]")).sendKeys(Keys.chord(curso)); //Keys.chord é usado para que seja digitado letra por letra no site
        driver.findElement(By.cssSelector("input[placeholder=\"Buscar...\"]")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS); //Espera milisegundos

        driver.findElement(By.xpath("//*[contains(text(),'" + curso + "')]/../..")).click();
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS); //Espera milisegundos

        String resultadoAtual = driver.findElement(By.cssSelector("h1:first-child")).getText();

        assertEquals(resultadoEsperado, resultadoAtual);

    }

}
