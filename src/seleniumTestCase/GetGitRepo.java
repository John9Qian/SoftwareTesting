package seleniumTestCase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GetGitRepo {
	public static void main(String[] args) throws Exception{
		Vector repo = new Vector();
		try {
			File excelFile = new File(System.getProperty("user.dir") + "/Selenium+Lab2020.xlsx");
			FileInputStream in = new FileInputStream(excelFile);
			Workbook wb = new XSSFWorkbook(in);
			XSSFSheet sheet =  (XSSFSheet) wb.getSheetAt(0);
			 for (Iterator rowIterator=sheet.iterator();rowIterator.hasNext();) 
             {
                 XSSFRow row=(XSSFRow) rowIterator.next();  
                 for (Iterator iterator=row.cellIterator();iterator.hasNext();) 
                 {  
                     XSSFCell cell=(XSSFCell) iterator.next();
                     String s = cell.getStringCellValue();
                     if(s == "") break;
     				 repo.add(s);
                 }  
             }
			wb.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		String driverPath = System.getProperty("user.dir") + "/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		WebDriver driver = new FirefoxDriver();
		for(int i=0; i<repo.size(); i+= 2) {
			driver.get("http://103.120.226.190/selenium-demo/git-repo");
		    driver.manage().window().setSize(new Dimension(550, 695));
		    driver.findElement(By.name("user_number")).click();
		    driver.findElement(By.name("user_number")).sendKeys(repo.get(i).toString());
		    driver.findElement(By.name("password")).click();
		    driver.findElement(By.name("password")).sendKeys(repo.get(i+1).toString());
		    driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[3]/input")).click();
		    
		    WebElement gitRepo = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code"));
		    System.out.println(gitRepo.getText());
		}
		
	    
	    driver.close();
	}
}
