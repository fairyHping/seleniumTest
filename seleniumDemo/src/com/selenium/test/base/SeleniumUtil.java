package com.selenium.test.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class SeleniumUtil {
	private WebDriver driver;

	/**
	 * �������Ĺ����������ø���ķ���������WebDriver����
	 * @param driver -WebDriver����
	 */
	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * ��������Ӧҳ��ķ���
	 * 
	 * @param url
	 *            ҳ��URL
	 */
	public void openBrowser(String url) {
		driver.get(url);
	}

	/**
	 * ��ȡ��λ��ҳ��Ԫ�صķ���
	 * 
	 * @param by
	 *            By���� ��λĳ��Ԫ��
	 * @return ����WebElement���󣬼�ҳ��Ԫ��
	 */
	public WebElement getElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Ԫ�ز����ڣ�");
			
		} catch (ElementNotVisibleException e) {
			throw new RuntimeException("ƥ����Ԫ�أ�");
		}
	}

	/**
	 * Ԫ�صĵ������
	 * @param by
	 *            By���� ��λ�ɵ��Ԫ��
	 */
	public void doClick(By by) {
		getElement(by).click();
	}

	/**
	 * ����ı��򣬲����������ı�������������
	 * 
	 * @param by
	 *            By���� ��λ�ı���Ԫ��
	 * @param value
	 *            �����������
	 */
	public void sendValue(By by, String value) {
		WebElement text = getElement(by);
		text.clear();
		text.sendKeys(value);
	}

	/**
	 * ��ȡ�ı��������
	 * @param by
	 *            By���� ��λ�ı���Ԫ��
	 * @return ����ֵΪString���ͣ��ı����е�����
	 */
	public String getTextValue(By by) {
		return getElement(by).getText();
	}

	/**
	 * ��λ���Ԫ�صķ���
	 * 
	 * @param by
	 *            By���� ��λ���Ԫ��
	 * @return ����ֵΪList���ϣ��洢��λ��������Ԫ�أ�WebElement��
	 */
	public List<WebElement> getElements(By by) {
		try {
			return driver.findElements(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Ԫ�ز����ڣ�");
		}
	}

	/**
	 * ͨ������Ԫ�ؼ��϶�λ����ӦԪ�ز����
	 * 
	 * @param by
	 *            By����  ��λһЩ�ɵ����Ԫ��
	 * @param text
	 *            Ԫ�ذ���������
	 */
	public void clickElementContainsText(By by, String text) {
		List<WebElement> elems = getElements(by);
		if (elems != null && !elems.isEmpty()) {
			// ���Ԫ�ؼ����е�Ԫ�ذ���ĳЩ���ݣ��͵����Ԫ��
			for (WebElement e : elems) {
				if (e.getText().contains(text)) {
					e.click();
					break;
				}
			}
		}
	}

	/**
	 * ��ȡԪ�ؼ�������Ӧ�����ӵ�url
	 * 
	 * @param by
	 *            By���� ��λ<a>��ǩ
	 * @param text
	 *            Ԫ�ذ���������
	 * @return ���ӵ�URL
	 */
	public String getLinkUrlContainingText(By by, String text) {
		List<WebElement> subscribeButton = getElements(by);
		String url = null;

		for (WebElement e : subscribeButton) {
			if (e.getText().contains(text)) {
				url = e.getAttribute("href");
				break;
			}
		}
		return url;
	}

	/**
	 * �л���ĳ��frame�У����ڶ�λ<frame>�е�Ԫ��
	 * 
	 * @param by
	 *            By���� ��λ<iframe>���
	 */
	public void changeFrame(By by) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = getElement(by);
		driver.switchTo().frame(iframe);
	}
	
	public void toParentWindow() {
		driver.switchTo().parentFrame();
	}

	/**
	 * �ж�ҳ�����Ƿ����ĳ��Ԫ��
	 * @param by By���� ��λĳ��Ԫ��
	 * @return Boolean���ͣ�true��Ԫ�ش��ڣ�false��Ԫ�ز�����
	 */
	public Boolean isElementExist(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * ��ȡ�����б����
	 * @param by By���� ���ڶ�λ<select>��ǩ
	 * @return ����һ��Select����
	 */
	public Select select(By by) {
		WebElement elem=getElement(by);
		Select select=new Select(elem);
		return select;
	}
	/**
	 * ͨ�������б�Ŀ��ӻ��ı�ѡ�����е�ѡ��
	 * @param by By���� ���ڶ�λ<select>��ǩ
	 * @param text optionѡ��Ŀ��ӻ��ı�����
	 */
	public void doSelectByText(By by,String text) {
		select(by).selectByVisibleText(text);
	}
	/**
	 * ͨ�������б�ѡ���ֵ��value��ѡ�����е�ѡ��
	 * @param by By���� ���ڶ�λ<select>��ǩ
	 * @param value optionѡ���valueֵ
	 */
	public void doSelectByValue(By by,String value) {
		select(by).selectByValue(value);
	}
	/**
	 *  ͨ�������б�ѡ����±�ѡ�����е�ѡ��
	 * @param by By���� ���ڶ�λ<select>��ǩ
	 * @param index optionѡ����±꣨�±��0��ʼ��
	 */
	public void doSelectByIndex(By by,Integer index) {
		select(by).selectByIndex(index);
	}
	/**
	 * ����<input>���͵������б��ѡ����
	 * @param openBy By���󣬶�λ�������б��Ԫ��
	 * @param optionBy By���󣬶�λ����ѡ��
	 */
	public void inputSelect(By openBy,By optionBy) {
		doClick(openBy);
		doClick(optionBy);
	}
	/**
	 * ��Excel(.xls)�ļ��ж�ȡ����(read data from excel file(only for .xls)
	 * 
	 * @param xlFilePath
	 *            �������ļ�λ��
	 * @param sheetName
	 *            Excel�еĹ�������
	 * @param cellName
	 *            ��ʼ��Ԫ��
	 * @return ����ֵΪString[][]����
	 * @throws Exception ��ȡ�ļ��������׳����쳣
	 */
	public static String[][] getTableArray(String xlFilePath, String sheetName, String cellName) throws Exception {
		String[][] tabArray = null;
		//��ʼ�кţ�0��ʼ��
		int startRow;
		//��ʼ�кţ�0��ʼ��
		int startCol;
		//�����кţ�0��ʼ��
		int endRow; 
		//�����кţ�0��ʼ��
		int endCol;
		//��ά�������
		int ci; 
		//��ά�������
		int cj;

		//��ȡ������
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		//��ȡ������
		Sheet sheet = workbook.getSheet(sheetName);
		//��ȡ��ʼ��Ԫ��
		Cell cellStart = sheet.findCell(cellName);
		
		startRow = cellStart.getRow();
		startCol = cellStart.getColumn();

		//�����ĵ�Ԫ��
		Cell cellEnd = sheet.findCell(cellName, startCol + 1, startRow + 1, 100, 64000, false);

		endRow = cellEnd.getRow();
		endCol = cellEnd.getColumn();
		System.out.println(
				"startRow=" + startRow + ", endRow=" + endRow + ", " + "startCol=" + startCol + ", endCol=" + endCol);
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}
		return (tabArray);
	}

	/**
	 * ��������
	 * @param prefixName -ͼƬ�Ĳ���ǰ׺��
	 */
	public void getScreenShot(String prefixName) {
		
		// ���ñ���·����user.dir��ϵͳ��ȫ�����ԣ���ʾ����Ŀ��Ŀ¼��
		String path = System.getProperty("user.dir") + "/screenshots/";
		// ���ý�ͼ�ļ����ƣ�1��ʹ������� 2����ǰʱ�� 3ģ������ŵ�
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
		String date = sf.format(new Date());
		String name = "user_" + date + ".png";
		TakesScreenshot take = (TakesScreenshot) driver;
		// �ص����������ڴ���
		File image = take.getScreenshotAs(OutputType.FILE);
		// ���ص������ݸ��Ƴ���
		try {
			FileUtils.copyFile(image, new File(path + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ϴ��ļ��Ĵ���
	 * @param selectFileBy By����  ���ڶ�λѡ���ļ���input��ǩ
	 * @param uploadBy By����   ��λ�ϴ��İ�ť
	 * @param filePath ���ϴ��ļ���·��
	 */
	public void fileUpload(By selectFileBy,By uploadBy,String filePath) {
		WebElement fileElement=getElement(selectFileBy);
		//ѡ���ϴ����ļ�
		File file=new File(filePath);
		fileElement.sendKeys(file.getPath());
		//����ϴ���ť�����ϴ�
		doClick(uploadBy);
	}
	
	public void waitTime(long timeout) {
		try {
			driver.wait(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر�������ķ���
	 */
	public void closeBrowser() {
		driver.close();
	}
	/**
	 * ֹͣ���������ķ���
	 */
	public void quit() {
		driver.quit();
	}
}
