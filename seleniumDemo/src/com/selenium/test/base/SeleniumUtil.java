package com.selenium.test.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil extends InitDriver {
	/**
	 * ��������Ӧҳ��ķ���
	 * 
	 * @param url
	 *            ҳ��URL
	 */
	public static void openBrowser(String url) {
		driver.get(url);
	}

	/**
	 * ��ȡ��λ��ҳ��Ԫ�صķ���
	 * 
	 * @param by
	 *            By���󣬶�λԪ�صķ�ʽ
	 * @return ����WebElement���󣬼�ҳ��Ԫ��
	 */
	public static WebElement getElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Ԫ�ز����ڣ�");

		} catch (ElementNotVisibleException e) {
			throw new RuntimeException("ƥ����Ԫ�أ�");
		}
	}

	/**
	 * ����Ԫ��ʱ����Ĭ�ϵȴ�ʱ�䣬��ҳ���Ҳ���Ԫ�أ���ȴ�һ��ʱ�����ң�ֱ����ʱ
	 * 
	 * @param timeout
	 *            �ȴ�ʱ�䣬��λΪ��
	 * @param by
	 *            By���󣬶�λԪ�صķ�ʽ
	 * @return
	 */
	public static WebElement getElement(long timeout, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// ����ʱ���׳��쳣���ʿ����ڴ˲����쳣

		return wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});

	}

	/**
	 * ͨ��Ԫ�ص�id����ȡԪ�صķ���
	 * 
	 * @param id
	 *            ҳ��Ԫ�ص�id����
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byId(String id) {
		return getElement(By.id(id));
	}

	/**
	 * ͨ��Ԫ�ص�class��������λԪ��
	 * 
	 * @param className
	 *            ҳ��Ԫ�ص�class����
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byClassName(String className) {
		return getElement(By.className(className));

	}

	/**
	 * ͨ��Ԫ�ص�cssѡ��������λԪ��
	 * 
	 * @param cssSelector
	 *            Ԫ�ص�cssѡ����
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byCssSelector(String cssSelector) {
		return getElement(By.cssSelector(cssSelector));
	}

	/**
	 * ͨ��Ԫ�ص�name��������λ��ȡԪ��
	 * 
	 * @param name
	 *            ҳ��Ԫ�ص�name����
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byName(String name) {
		return getElement(By.name(name));
	}

	/**
	 * ʹ��xpath����λ��ȡԪ��
	 * 
	 * @param xpath
	 *            Ԫ�صĽڵ�·��
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byXpath(String xpath) {
		return getElement(By.xpath(xpath));
	}

	/**
	 * ͨ������������������λ����Ԫ��
	 * 
	 * @param linkText
	 *            ���ӵ���������
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byLinkText(String linkText) {
		return getElement(By.linkText(linkText));
	}

	/**
	 * ͨ�����ӵĲ���������������λԪ��
	 * 
	 * @param partialText
	 *            ���ӵĲ�����������
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byPartialText(String partialText) {
		return getElement(By.partialLinkText(partialText));
	}

	/**
	 * ʹ��HTMLҳ��������λԪ��
	 * 
	 * @param tagName
	 *            HTML���
	 * @return ����ֵΪWebElement���󣬼�ҳ��Ԫ�ض���
	 */
	public static WebElement byTagName(String tagName) {
		return getElement(By.tagName(tagName));
	}

	/**
	 * ��λ���Ԫ�صķ���
	 * 
	 * @param by
	 *            By���� ��λ���Ԫ��
	 * @return ����ֵΪList���ϣ��洢��λ��������Ԫ�أ�WebElement��
	 */
	public static List<WebElement> getElements(By by) {
		try {
			return driver.findElements(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Ԫ�ز����ڣ�");
		}
	}

	/**
	 * ͨ��Ԫ�ص�id����ȡԪ�صķ���
	 * 
	 * @param id
	 *            ҳ��Ԫ�ص�id����
	 * @return ����ֵΪList���ϣ���Ÿ���id�����ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byIds(String id) {
		return getElements(By.id(id));
	}

	/**
	 * ͨ��Ԫ�ص�class��������λԪ��
	 * 
	 * @param className
	 *            ҳ��Ԫ�ص�class����
	 * @return ����ֵΪList���ϣ���Ÿ���class�����ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byClassNames(String className) {
		return getElements(By.className(className));

	}

	/**
	 * ͨ��Ԫ�ص�cssѡ��������λԪ��
	 * 
	 * @param cssSelector
	 *            Ԫ�ص�cssѡ����
	 * @return ����ֵΪList���ϣ���Ÿ���cssѡ�����ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byCssSelectors(String cssSelector) {
		return getElements(By.cssSelector(cssSelector));
	}

	/**
	 * ͨ��Ԫ�ص�name��������λ��ȡԪ��
	 * 
	 * @param name
	 *            ҳ��Ԫ�ص�name����
	 * @return ����ֵΪList���ϣ���Ÿ���name�����ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byNames(String name) {
		return getElements(By.name(name));
	}

	/**
	 * ʹ��xpath����λ��ȡԪ��
	 * 
	 * @param xpath
	 *            Ԫ�صĽڵ�·��
	 * @return ����ֵΪList���ϣ���Ÿ���xpath�ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byXpaths(String xpath) {
		return getElements(By.xpath(xpath));
	}

	/**
	 * ͨ������������������λ����Ԫ��
	 * 
	 * @param linkText
	 *            ���ӵ���������
	 * @return ����ֵΪList���ϣ���Ÿ����������������ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byLinkTexts(String linkText) {
		return getElements(By.linkText(linkText));
	}

	/**
	 * ͨ�����ӵĲ���������������λԪ��
	 * 
	 * @param partialText
	 *            ���ӵĲ�����������
	 * @return ����ֵΪList���ϣ���Ÿ������ӵĲ������������ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byPartialTexts(String partialText) {
		return getElements(By.partialLinkText(partialText));
	}

	/**
	 * ʹ��HTMLҳ��������λԪ��
	 * 
	 * @param tagName
	 *            HTML���
	 * @return ����ֵΪList���ϣ���Ÿ���HTML����ҵ�������ҳ��Ԫ�ض���
	 */
	public static List<WebElement> byTagNames(String tagName) {
		return getElements(By.tagName(tagName));
	}

	/**
	 * Ԫ�صĵ������
	 * 
	 * @param by
	 *            By���� ��λ�ɵ��Ԫ��
	 */
	public static void doClick(By by) {
		getElement(by).click();
	}

	/**
	 * ʹ��id��ʽ��λ��Ԫ�صĵ������
	 * 
	 * @param id
	 *            ҳ��Ԫ�ص�id����
	 */
	public static void clickById(String id) {
		doClick(By.id(id));
	}

	/**
	 * ���ͨ��Ԫ��class���Զ�λ��Ԫ��
	 * 
	 * @param className
	 *            ҳ��Ԫ�ص�class����
	 */
	public static void clickByClassName(String className) {
		doClick(By.className(className));

	}

	/**
	 * ���ͨ��Ԫ��cssѡ������λ��Ԫ��
	 * 
	 * @param cssSelector
	 *            Ԫ�ص�cssѡ����
	 */
	public static void clickByCss(String cssSelector) {
		doClick(By.cssSelector(cssSelector));
	}

	/**
	 * ���ͨ��Ԫ��name���Զ�λ��Ԫ��
	 * 
	 * @param name
	 *            ҳ��Ԫ�ص�name����
	 */
	public static void clickByName(String name) {
		doClick(By.name(name));
	}

	/**
	 * ���ͨ��ʹ��xpath����λ��Ԫ��
	 * 
	 * @param xpath
	 *            Ԫ�صĽڵ�·��
	 */
	public static void clickByXpath(String xpath) {
		doClick(By.xpath(xpath));
	}

	/**
	 * ���ͨ������������������λ��������Ԫ��
	 * 
	 * @param linkText
	 *            ���ӵ���������
	 */
	public static void clickByLinkText(String linkText) {
		doClick(By.linkText(linkText));
	}

	/**
	 * ���ͨ�����ӵĲ����������ݶ�λ����Ԫ��
	 * 
	 * @param partialText
	 *            ���ӵĲ�����������
	 */
	public static void clickByPartialText(String partialText) {
		doClick(By.partialLinkText(partialText));
	}

	/**
	 * ���ʹ��HTMLҳ���Ƕ�λ����Ԫ��
	 * 
	 * @param tagName
	 *            HTML���
	 */
	public static void clickByTagName(String tagName) {
		doClick(By.tagName(tagName));
	}

	/**
	 * ����ı��򣬲����������ı�������������
	 * 
	 * @param by
	 *            By���� ��λ�ı���Ԫ��
	 * @param value
	 *            �����������
	 */
	public static void sendKey(By by,String value) {
		WebElement text = getElement(by);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ͨ��id��λ�ı�������������
	 * @param id �ı����id����
	 * @param value �����������
	 */
	public static void sendKeyById(String id,String value) {
		WebElement text = byId(id);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ͨ��name���Զ�λ�ı�������������
	 * @param name �ı����name����
	 * @param value �����������
	 */
	public static void sendKeyBy(String name, String value) {
		WebElement text = byName(name);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ͨ��class���Զ�λ�ı�������������
	 * @param className �ı����class����
	 * @param value �����������
	 */
	public static void sendKeyByClass(String className,String value) {
		WebElement text =byClassName(className);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ͨ��xpath��λ�ı�������������
	 * @param xpath �ı����xpath
	 * @param value �����������
	 */
	public static void sendKeyByXpath(String xpath, String value) {
		WebElement text = byXpath(xpath);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ͨ��cssѡ������λ�ı�������������
	 * @param cssSelector �ı����cssѡ����
	 * @param value �����������
	 */
	public static void sendKeyByCss(String cssSelector, String value) {
		WebElement text = byCssSelector(cssSelector);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * ��ȡ�ı��������
	 * 
	 * @param by
	 *            By���� ��λ�ı���Ԫ��
	 * @return ����ֵΪString���ͣ��ı����е�����
	 */
	public static String getTextValue(By by) {
		return getElement(by).getText();
	}

	/**
	 * ͨ������Ԫ�ؼ��϶�λ����ӦԪ�ز����
	 * 
	 * @param by
	 *            By���� ��λһЩ�ɵ����Ԫ��
	 * @param text
	 *            Ԫ�ذ���������
	 */
	public static void clickContainsText(By by, String text) {
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
	public static String getLinkUrlContainingText(By by, String text) {
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
	 * ͨ��id��������λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param id
	 *            Ԫ�ص�id����
	 */
	public static void toFrameById(String id) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byId(id);
		driver.switchTo().frame(iframe);
	}
	/**
	 * ͨ��name��������λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param name
	 *            Ԫ�ص�name����
	 */
	public static void toFrameByName(String name) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byName(name);
		driver.switchTo().frame(iframe);
	}
	/**
	 * ͨ��cssѡ��������λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param cssSelector
	 *            Ԫ�ص�cssѡ����
	 */
	public static void toFrameByCss(String cssSelector) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byCssSelector(cssSelector);
		driver.switchTo().frame(iframe);
	}
	/**
	 * ͨ��class��������λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param className
	 *            Ԫ�ص�className����
	 */
	public static void toFrameByClass(String className) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byClassName(className);
		driver.switchTo().frame(iframe);
	}
	/**
	 * ͨ��xpath����λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param xpath
	 *            Ԫ�ص�xpath����
	 */
	public static void toFrameByXpath(String xpath) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byXpath(xpath);
		driver.switchTo().frame(iframe);
	}
	/**
	 * ͨ��tagName��ǩ������λ<iframe>��ܣ��л���ĳ��frame��
	 * 
	 * @param tagName
	 *            Ԫ�صı�ǩ��
	 */
	public static void toFrameByTagName(String tagName) {
		// ���ҵ�Ԫ�����ڵ�iframe���л���iframe������ܶ�λ��iframe�е�Ԫ��
		WebElement iframe = byTagName(tagName);
		driver.switchTo().frame(iframe);
	}

	/**
	 * �л���������
	 */
	public static void toParentWindow() {
		driver.switchTo().parentFrame();
	}

	/**
	 * ��ȡ����Ԫ�أ����л��������Ի���
	 * 
	 * @return
	 */
	public static Alert getAlert() {
		return driver.switchTo().alert();
	}

	/**
	 * �ڵ����Ի����е��ȷ�ϰ�ť
	 */
	public static void accept() {
		driver.switchTo().alert().accept();
	}

	/**
	 * �ڵ����Ի����е��ȡ����ť
	 */
	public static void dimiss() {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * ��ȡ�Ի�����ı�����
	 */
	public static void getText() {
		driver.switchTo().alert().getText();
	}

	/**
	 * �ж�ҳ�����Ƿ����ĳ��Ԫ��
	 * 
	 * @param by
	 *            By���� ��λĳ��Ԫ��
	 * @return Boolean���ͣ�true��Ԫ�ش��ڣ�false��Ԫ�ز�����
	 */
	public static Boolean isElementExist(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * ��ȡ�����б����
	 * 
	 * @param by
	 *            By���� ���ڶ�λ<select>��ǩ
	 * @return ����һ��Select����
	 */
	public static Select select(By by) {
		WebElement elem = getElement(by);
		Select select = new Select(elem);
		return select;
	}

	/**
	 * ͨ�������б�Ŀ��ӻ��ı�ѡ�����е�ѡ��
	 * 
	 * @param by
	 *            By���� ���ڶ�λ<select>��ǩ
	 * @param text
	 *            optionѡ��Ŀ��ӻ��ı�����
	 */
	public static void doSelectByText(By by, String text) {
		select(by).selectByVisibleText(text);
	}

	/**
	 * ͨ�������б�ѡ���ֵ��value��ѡ�����е�ѡ��
	 * 
	 * @param by
	 *            By���� ���ڶ�λ<select>��ǩ
	 * @param value
	 *            optionѡ���valueֵ
	 */
	public static void doSelectByValue(By by, String value) {
		select(by).selectByValue(value);
	}

	/**
	 * ͨ�������б�ѡ����±�ѡ�����е�ѡ��
	 * 
	 * @param by
	 *            By���� ���ڶ�λ<select>��ǩ
	 * @param index
	 *            optionѡ����±꣨�±��0��ʼ��
	 */
	public static void doSelectByIndex(By by, Integer index) {
		select(by).selectByIndex(index);
	}

	/**
	 * ����<input>���͵������б��ѡ����
	 * 
	 * @param openBy
	 *            By���󣬶�λ�������б��Ԫ��
	 * @param optionBy
	 *            By���󣬶�λ����ѡ��
	 */
	public static void inputSelect(By openBy, By optionBy) {
		doClick(openBy);
		doClick(optionBy);
	}

	/**
	 * ��������
	 * 
	 * @param prefixName
	 *            -ͼƬ�Ĳ���ǰ׺��
	 */
	public static void getScreenShot(String prefixName) {

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
	 * 
	 * @param selectFileBy
	 *            By���� ���ڶ�λѡ���ļ���input��ǩ
	 * @param uploadBy
	 *            By���� ��λ�ϴ��İ�ť
	 * @param filePath
	 *            ���ϴ��ļ���·��
	 */
	public static void fileUpload(By selectFileBy, By uploadBy, String filePath) {
		WebElement fileElement = getElement(selectFileBy);
		// ѡ���ϴ����ļ�
		File file = new File(filePath);
		fileElement.sendKeys(file.getPath());
		// ����ϴ���ť�����ϴ�
		doClick(uploadBy);
	}

	/**
	 * ���Cookie��org.openqa.selenium.Cookie���ķ���
	 * 
	 * @param key
	 *            Cookie�ļ�����
	 * @param value
	 *            Cookie��ֵ
	 */
	public static void setCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		driver.manage().addCookie(cookie);
	}

	/**
	 * ����Cookie�ļ���ȡ��Ӧ��Cookie
	 * 
	 * @param key
	 *            Cookie�ļ�����
	 * @return ����ֵΪorg.openqa.selenium.Cookie����
	 */
	public static Cookie getCookie(String key) {
		Cookie cookie = driver.manage().getCookieNamed(key);
		return cookie;
	}

	/**
	 * ����Cookie�ļ���ȡ��Ӧ��ֵ
	 * 
	 * @param key
	 *            Cookie�ļ�����
	 * @return ����ֵΪString���ͣ���ʾ����Ӧ��ֵ
	 */
	public static String getCookieValue(String key) {
		Cookie cookie = driver.manage().getCookieNamed(key);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * ��ȡ����Cookie�ķ���
	 * 
	 * @return ����ֵΪSet���ϣ�������е�Cookie
	 */
	public static Set<Cookie> getCookies() {
		Set<Cookie> cookies = driver.manage().getCookies();
		return cookies;
	}

	/**
	 * ����Cookie�ļ�����ɾ��Cookie
	 * 
	 * @param key
	 *            Cookie�ļ���
	 */
	public static void deleteCookie(String key) {
		driver.manage().deleteCookieNamed(key);
	}

	/**
	 * ɾ��ָ����Cookie
	 * 
	 * @param cookie
	 *            org.openqa.selenium.Cookie����
	 */
	public static void deleteCookie(Cookie cookie) {
		driver.manage().deleteCookie(cookie);
	}

	/**
	 * ɾ�����е�Cookie
	 */
	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * �жϹ涨ʱ�����Ƿ��ҵ�ĳԪ�أ�����ҵ��򷵻�true�����򷵻�false
	 * 
	 * @param driver
	 *            -WebDriver����
	 * @param timeout
	 *            -�涨�ĳ�ʱʱ��
	 * @param elementId
	 *            -Ҫ���ҵ�Ԫ�ص�id
	 * @return -����ֵΪBoolean���ͣ�true��ʾ�ҵ�Ԫ�أ�false��ʾû���ҵ�
	 */
	public static Boolean isFound(long timeout, By by) {
		Boolean flag = true;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// ����ʱ���׳��쳣���ʿ����ڴ˲����쳣
		try {
			flag = wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(by).isDisplayed();
				}
			});
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * �л����������
	 * @param windowTitle  ���ڱ���
	 */
	public static void switchWindow(String windowTitle) {
		try {

			// ��ȡ��ǰ���ڵ�handle
			String currentHandle = driver.getWindowHandle();

			// ��ҳ�������е�windowshandle������set���ϵ���

			Set<String> handles = driver.getWindowHandles();

			// ѭ��������������е�Ԫ��
			for (String s : handles) {

				// ����͵�ǰ�Ĵ��ڵ�handleһ������������ѭ���������´�һѭ��
				if (s.equals(currentHandle))
					continue;
				else {
					// �����һ�����л����Ǹ�handle���Ա����ҳ���е�title�Ƿ���������windowtitle������Ǿͷ���true������ѭ���ˡ����title��һ������������ѭ��������ѭ���Աȡ�
					driver.switchTo().window(s);
					if (getTitle().contains(windowTitle)) {
						break;
					} else
						continue;
				}
			}
		} catch (Exception e) {
			System.out.printf("Window: " + windowTitle + " cound not found!", e.fillInStackTrace());
		}

	}

	/**
	 * ��ȡ��ǰҳ���title���Ե�ֵ
	 * @return ����ֵΪString���ͣ���ǰҳ��ı���
	 */
	public static String getTitle() {
		return driver.getTitle();
	}
	/**
	 * ��ȡ��ǰҳ������ҳ���Դ��
	 */
	public static String getPageSource() {
		return driver.getPageSource();
	}
	/**
	 * ����Ԫ����������ȡ��Ӧ������ֵ
	 * @param element WebElementҳ��Ԫ�ض���
	 * @param name Ԫ�ص�������
	 * @return ����ֵΪString���ͣ���Ӧ���Ե�ֵ
	 */
	public static String getAttribute(WebElement element,String name){
		return element.getAttribute(name);
	}
	/**
	 * ����ĳ����ҳ��
	 * @param url
	 */
	public static void toPage(String url) {
		driver.navigate().to(url);
	}
	/**
	 * ͨ����ʷ����ǰ����ҳ��
	 */
	public static void forward() {
		driver.navigate().forward();
	}
	/**
	 * ͨ����ʷ�������ص�ҳ��
	 */
	public static void back() {
		driver.navigate().back();
	}
	/**
	 * ��xpath��λ��ʽ����λ������ĵ�Ԫ�񣬲��ҷ��ص�Ԫ�������
	 * @param tableXpath table��xpath
	 * @param row ��Ԫ�����ڵ���������������ͷ��
	 * @param column ��Ԫ�����ڵ�����
	 * @return ����ֵΪString���ͣ���ʾ��Ԫ�������
	 */
	public static String tableCell(String tableXpath,int row,int column) {
		String text = null;
        //����ȡ����ͷ
        row=row+1;
        String xpath=tableXpath+"/tbody/tr["+row+"]/td["+column+"]";
        WebElement table=driver.findElement(By.xpath(xpath)); 
        text=table.getText();
        return text;
	}
	/**
	 * �ر�������ķ���
	 */
	public static void closeBrowser() {
		driver.close();
	}

	/**
	 * ֹͣ���������ķ���
	 */
	public static void quit() {
		driver.quit();
	}
}
