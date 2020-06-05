package ru.mail.dimon1985;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class FirstTest {
    protected static WebDriver driver;
    private static String link = "https://223.rts-tender.ru/supplier/auction/Trade/Search.aspx";
    private static String startDate = "05.06.2020";
    private static String endDate = "06.06.2020";
    private static String startPrice = "0";
    private static int counter = 0;
    private static List<WebElement> EISNumberColumn;
    private static List<WebElement> priceColumn;
    private static Double sumTotal=0.0;
    private static int totalLots = 0;
    private static Double canceledSum=0.0;
    private static int canceledLots = 0;
    WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 15).withMessage("Element not found");
    private static Logger log = Logger.getLogger(FirstTest.class);


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(link);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
    }
   @Test
    public void tasktest() throws Exception {
       fillInTheField(By.id(ru.mail.dimon1985.ElementHelperr.getDateStartField()),startDate);

       fillInTheField(By.id(ru.mail.dimon1985.ElementHelperr.getDateEndField()),endDate);

       elementClick(By.id(ru.mail.dimon1985.ElementHelperr.getCheckBox223FZ()));

       elementClick(By.id(ru.mail.dimon1985.ElementHelperr.getCheckBoxCommercialPurchase()));

       fillInTheField(By.id(ru.mail.dimon1985.ElementHelperr.getPriceRangeFrom()),startPrice);

       elementClick(By.id(ru.mail.dimon1985.ElementHelperr.getButtonSearch()));

       searchLots(false);
       searchLots(true);
   }
    @AfterClass
    public static void write () throws Exception{
        FileWriter report = new FileWriter(".//report.txt");
        report.write("Total number of lots: "+totalLots+"\n");
        System.out.println("Total number of lots: "+totalLots+"\n");
        report.write("Total start price: ");
        System.out.println("Total start price: ");
        report.write(format("%(.2f",sumTotal));
        System.out.println(String.format("%(.2f",sumTotal));
        report.write("\nTotal start price of canceled lots: ");
        System.out.println("\nTotal start price of canceled lots: ");
        report.write(format("%(.2f",canceledSum));
        System.out.println(String.format("%(.2f",canceledSum));
        int lots = totalLots-canceledLots;
        report.write("\nTotal: "+lots);
        System.out.println("\nTotal: "+lots);
        report.close();
        System.out.println("Recorded");
        driver.quit();
    }

    public void fillInTheField(By by, String data){
        WebElement element = driver.findElement(by);
        element.click();
        element.sendKeys(data);
    }

    public void elementClick(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void searchLots(boolean canceled){
        if(canceled){
            WebElement cancel = driver.findElement(By.cssSelector(ru.mail.dimon1985.ElementHelperr.getCanceled()));
            cancel.click();
        }

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(ru.mail.dimon1985.ElementHelperr.getPageCount()))));
        WebElement pageCount = driver.findElement(By.id(ru.mail.dimon1985.ElementHelperr.getPageCount()));
        String pages = pageCount.getText();
        pages = pages.replaceAll(" ","").trim();
        int pagesValue = Integer.valueOf(pages);
        WebElement nextPage = driver.findElement(By.id(ru.mail.dimon1985.ElementHelperr.getNextPage()));

        while (counter<=pagesValue-1) {
            wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath(ru.mail.dimon1985.ElementHelperr.getPageNumber())),"value",String.valueOf(counter+1)));
            EISNumberColumn =driver.findElements(By.xpath(ru.mail.dimon1985.ElementHelperr.getEISNumberColumn()));
            priceColumn = driver.findElements(By.xpath(ru.mail.dimon1985.ElementHelperr.getPriceColumn()));
            for(int i=0;i<=EISNumberColumn.size()-1; i++){
                if(!"".equals(EISNumberColumn.get(i).getText().trim())){
                    String price = priceColumn.get(i).getText().substring(0, priceColumn.get(i).getText().length() - 4);
                    price = price.replaceAll(" ", "");
                    price = price.replaceAll(",", ".");
                    if(canceled){
                        canceledSum+=Double.parseDouble(price);
                        canceledLots+=1;
                    }
                    else{
                        sumTotal=Double.parseDouble(price)+sumTotal;
                        totalLots=totalLots+1;
                    }
                }
            }
            nextPage.click();
            counter++;
    }
}}
