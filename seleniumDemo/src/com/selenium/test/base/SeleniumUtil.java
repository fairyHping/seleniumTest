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
	 * 带参数的构造器，调用该类的方法必须有WebDriver对象
	 * @param driver -WebDriver对象
	 */
	public SeleniumUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 导航到相应页面的方法
	 * 
	 * @param url
	 *            页面URL
	 */
	public void openBrowser(String url) {
		driver.get(url);
	}

	/**
	 * 获取定位的页面元素的方法
	 * 
	 * @param by
	 *            By对象 定位某个元素
	 * @return 返回WebElement对象，即页面元素
	 */
	public WebElement getElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("元素不存在！");
			
		} catch (ElementNotVisibleException e) {
			throw new RuntimeException("匹配多个元素！");
		}
	}

	/**
	 * 元素的点击操作
	 * @param by
	 *            By对象 定位可点击元素
	 */
	public void doClick(By by) {
		getElement(by).click();
	}

	/**
	 * 清空文本框，并且在输入文本框中输入内容
	 * 
	 * @param by
	 *            By对象 定位文本框元素
	 * @param value
	 *            待输入的内容
	 */
	public void sendValue(By by, String value) {
		WebElement text = getElement(by);
		text.clear();
		text.sendKeys(value);
	}

	/**
	 * 获取文本框的内容
	 * @param by
	 *            By对象 定位文本框元素
	 * @return 返回值为String类型，文本框中的内容
	 */
	public String getTextValue(By by) {
		return getElement(by).getText();
	}

	/**
	 * 定位多个元素的方法
	 * 
	 * @param by
	 *            By对象 定位多个元素
	 * @return 返回值为List集合，存储定位到的所有元素（WebElement）
	 */
	public List<WebElement> getElements(By by) {
		try {
			return driver.findElements(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("元素不存在！");
		}
	}

	/**
	 * 通过遍历元素集合定位到相应元素并点击
	 * 
	 * @param by
	 *            By对象  定位一些可点击的元素
	 * @param text
	 *            元素包含的内容
	 */
	public void clickElementContainsText(By by, String text) {
		List<WebElement> elems = getElements(by);
		if (elems != null && !elems.isEmpty()) {
			// 如果元素集合中的元素包含某些内容，就点击该元素
			for (WebElement e : elems) {
				if (e.getText().contains(text)) {
					e.click();
					break;
				}
			}
		}
	}

	/**
	 * 获取元素集合中相应超链接的url
	 * 
	 * @param by
	 *            By对象 定位<a>标签
	 * @param text
	 *            元素包含的内容
	 * @return 链接的URL
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
	 * 切换到某个frame中，便于定位<frame>中的元素
	 * 
	 * @param by
	 *            By对象 定位<iframe>框架
	 */
	public void changeFrame(By by) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = getElement(by);
		driver.switchTo().frame(iframe);
	}
	
	public void toParentWindow() {
		driver.switchTo().parentFrame();
	}

	/**
	 * 判断页面上是否存在某个元素
	 * @param by By对象 定位某个元素
	 * @return Boolean类型，true：元素存在；false：元素不存在
	 */
	public Boolean isElementExist(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * 获取下拉列表对象
	 * @param by By对象 用于定位<select>标签
	 * @return 返回一个Select对象
	 */
	public Select select(By by) {
		WebElement elem=getElement(by);
		Select select=new Select(elem);
		return select;
	}
	/**
	 * 通过下拉列表的可视化文本选择其中的选项
	 * @param by By对象 用于定位<select>标签
	 * @param text option选项的可视化文本内容
	 */
	public void doSelectByText(By by,String text) {
		select(by).selectByVisibleText(text);
	}
	/**
	 * 通过下拉列表选项的值（value）选择其中的选项
	 * @param by By对象 用于定位<select>标签
	 * @param value option选项的value值
	 */
	public void doSelectByValue(By by,String value) {
		select(by).selectByValue(value);
	}
	/**
	 *  通过下拉列表选项的下标选择其中的选项
	 * @param by By对象 用于定位<select>标签
	 * @param index option选项的下标（下标从0开始）
	 */
	public void doSelectByIndex(By by,Integer index) {
		select(by).selectByIndex(index);
	}
	/**
	 * 对于<input>类型的下拉列表的选择处理
	 * @param openBy By对象，定位打开下拉列表的元素
	 * @param optionBy By对象，定位具体选项
	 */
	public void inputSelect(By openBy,By optionBy) {
		doClick(openBy);
		doClick(optionBy);
	}
	/**
	 * 从Excel(.xls)文件中读取数据(read data from excel file(only for .xls)
	 * 
	 * @param xlFilePath
	 *            工作簿文件位置
	 * @param sheetName
	 *            Excel中的工作表名
	 * @param cellName
	 *            起始单元格
	 * @return 返回值为String[][]类型
	 * @throws Exception 读取文件过程中抛出的异常
	 */
	public static String[][] getTableArray(String xlFilePath, String sheetName, String cellName) throws Exception {
		String[][] tabArray = null;
		//起始行号（0开始）
		int startRow;
		//起始列号（0开始）
		int startCol;
		//结束行号（0开始）
		int endRow; 
		//结束列号（0开始）
		int endCol;
		//二维数组的行
		int ci; 
		//二维数组的列
		int cj;

		//获取工作簿
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		//读取工作表
		Sheet sheet = workbook.getSheet(sheetName);
		//读取起始单元格
		Cell cellStart = sheet.findCell(cellName);
		
		startRow = cellStart.getRow();
		startCol = cellStart.getColumn();

		//结束的单元格
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
	 * 截屏操作
	 * @param prefixName -图片的部分前缀名
	 */
	public void getScreenShot(String prefixName) {
		
		// 设置保存路径：user.dir是系统的全局属性，表示在项目根目录下
		String path = System.getProperty("user.dir") + "/screenshots/";
		// 设置截图文件名称：1、使用随机数 2、当前时间 3模块名编号等
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
		String date = sf.format(new Date());
		String name = "user_" + date + ".png";
		TakesScreenshot take = (TakesScreenshot) driver;
		// 截到的内容在内存中
		File image = take.getScreenshotAs(OutputType.FILE);
		// 将截到的内容复制出来
		try {
			FileUtils.copyFile(image, new File(path + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件的处理
	 * @param selectFileBy By对象  用于定位选择文件的input标签
	 * @param uploadBy By对象   定位上传的按钮
	 * @param filePath 待上传文件的路径
	 */
	public void fileUpload(By selectFileBy,By uploadBy,String filePath) {
		WebElement fileElement=getElement(selectFileBy);
		//选择上传的文件
		File file=new File(filePath);
		fileElement.sendKeys(file.getPath());
		//点击上传按钮进行上传
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
	 * 关闭浏览器的方法
	 */
	public void closeBrowser() {
		driver.close();
	}
	/**
	 * 停止继续操作的方法
	 */
	public void quit() {
		driver.quit();
	}
}
