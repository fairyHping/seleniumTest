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
	 * 导航到相应页面的方法
	 * 
	 * @param url
	 *            页面URL
	 */
	public static void openBrowser(String url) {
		driver.get(url);
	}

	/**
	 * 获取定位的页面元素的方法
	 * 
	 * @param by
	 *            By对象，定位元素的方式
	 * @return 返回WebElement对象，即页面元素
	 */
	public static WebElement getElement(By by) {
		try {
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("元素不存在！");

		} catch (ElementNotVisibleException e) {
			throw new RuntimeException("匹配多个元素！");
		}
	}

	/**
	 * 查找元素时设置默认等待时间，若页面找不到元素，则等待一段时间再找，直到超时
	 * 
	 * @param timeout
	 *            等待时间，单位为秒
	 * @param by
	 *            By对象，定位元素的方式
	 * @return
	 */
	public static WebElement getElement(long timeout, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// 若超时则抛出异常，故可以在此捕获异常

		return wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});

	}

	/**
	 * 通过元素的id来获取元素的方法
	 * 
	 * @param id
	 *            页面元素的id属性
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byId(String id) {
		return getElement(By.id(id));
	}

	/**
	 * 通过元素的class属性来定位元素
	 * 
	 * @param className
	 *            页面元素的class属性
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byClassName(String className) {
		return getElement(By.className(className));

	}

	/**
	 * 通过元素的css选择器来定位元素
	 * 
	 * @param cssSelector
	 *            元素的css选择器
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byCssSelector(String cssSelector) {
		return getElement(By.cssSelector(cssSelector));
	}

	/**
	 * 通过元素的name属性来定位获取元素
	 * 
	 * @param name
	 *            页面元素的name属性
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byName(String name) {
		return getElement(By.name(name));
	}

	/**
	 * 使用xpath来定位获取元素
	 * 
	 * @param xpath
	 *            元素的节点路径
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byXpath(String xpath) {
		return getElement(By.xpath(xpath));
	}

	/**
	 * 通过链接文字内容来定位链接元素
	 * 
	 * @param linkText
	 *            链接的文字内容
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byLinkText(String linkText) {
		return getElement(By.linkText(linkText));
	}

	/**
	 * 通过链接的部分文字内容来定位元素
	 * 
	 * @param partialText
	 *            链接的部分文字内容
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byPartialText(String partialText) {
		return getElement(By.partialLinkText(partialText));
	}

	/**
	 * 使用HTML页面标记来定位元素
	 * 
	 * @param tagName
	 *            HTML标记
	 * @return 返回值为WebElement对象，即页面元素对象
	 */
	public static WebElement byTagName(String tagName) {
		return getElement(By.tagName(tagName));
	}

	/**
	 * 定位多个元素的方法
	 * 
	 * @param by
	 *            By对象 定位多个元素
	 * @return 返回值为List集合，存储定位到的所有元素（WebElement）
	 */
	public static List<WebElement> getElements(By by) {
		try {
			return driver.findElements(by);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("元素不存在！");
		}
	}

	/**
	 * 通过元素的id来获取元素的方法
	 * 
	 * @param id
	 *            页面元素的id属性
	 * @return 返回值为List集合，存放根据id属性找到的所有页面元素对象
	 */
	public static List<WebElement> byIds(String id) {
		return getElements(By.id(id));
	}

	/**
	 * 通过元素的class属性来定位元素
	 * 
	 * @param className
	 *            页面元素的class属性
	 * @return 返回值为List集合，存放根据class属性找到的所有页面元素对象
	 */
	public static List<WebElement> byClassNames(String className) {
		return getElements(By.className(className));

	}

	/**
	 * 通过元素的css选择器来定位元素
	 * 
	 * @param cssSelector
	 *            元素的css选择器
	 * @return 返回值为List集合，存放根据css选择器找到的所有页面元素对象
	 */
	public static List<WebElement> byCssSelectors(String cssSelector) {
		return getElements(By.cssSelector(cssSelector));
	}

	/**
	 * 通过元素的name属性来定位获取元素
	 * 
	 * @param name
	 *            页面元素的name属性
	 * @return 返回值为List集合，存放根据name属性找到的所有页面元素对象
	 */
	public static List<WebElement> byNames(String name) {
		return getElements(By.name(name));
	}

	/**
	 * 使用xpath来定位获取元素
	 * 
	 * @param xpath
	 *            元素的节点路径
	 * @return 返回值为List集合，存放根据xpath找到的所有页面元素对象
	 */
	public static List<WebElement> byXpaths(String xpath) {
		return getElements(By.xpath(xpath));
	}

	/**
	 * 通过链接文字内容来定位链接元素
	 * 
	 * @param linkText
	 *            链接的文字内容
	 * @return 返回值为List集合，存放根据链接文字内容找到的所有页面元素对象
	 */
	public static List<WebElement> byLinkTexts(String linkText) {
		return getElements(By.linkText(linkText));
	}

	/**
	 * 通过链接的部分文字内容来定位元素
	 * 
	 * @param partialText
	 *            链接的部分文字内容
	 * @return 返回值为List集合，存放根据链接的部分文字内容找到的所有页面元素对象
	 */
	public static List<WebElement> byPartialTexts(String partialText) {
		return getElements(By.partialLinkText(partialText));
	}

	/**
	 * 使用HTML页面标记来定位元素
	 * 
	 * @param tagName
	 *            HTML标记
	 * @return 返回值为List集合，存放根据HTML标记找到的所有页面元素对象
	 */
	public static List<WebElement> byTagNames(String tagName) {
		return getElements(By.tagName(tagName));
	}

	/**
	 * 元素的点击操作
	 * 
	 * @param by
	 *            By对象 定位可点击元素
	 */
	public static void doClick(By by) {
		getElement(by).click();
	}

	/**
	 * 使用id方式定位的元素的点击操作
	 * 
	 * @param id
	 *            页面元素的id属性
	 */
	public static void clickById(String id) {
		doClick(By.id(id));
	}

	/**
	 * 点击通过元素class属性定位的元素
	 * 
	 * @param className
	 *            页面元素的class属性
	 */
	public static void clickByClassName(String className) {
		doClick(By.className(className));

	}

	/**
	 * 点击通过元素css选择器定位的元素
	 * 
	 * @param cssSelector
	 *            元素的css选择器
	 */
	public static void clickByCss(String cssSelector) {
		doClick(By.cssSelector(cssSelector));
	}

	/**
	 * 点击通过元素name属性定位的元素
	 * 
	 * @param name
	 *            页面元素的name属性
	 */
	public static void clickByName(String name) {
		doClick(By.name(name));
	}

	/**
	 * 点击通过使用xpath来定位的元素
	 * 
	 * @param xpath
	 *            元素的节点路径
	 */
	public static void clickByXpath(String xpath) {
		doClick(By.xpath(xpath));
	}

	/**
	 * 点击通过链接文字内容来定位到的链接元素
	 * 
	 * @param linkText
	 *            链接的文字内容
	 */
	public static void clickByLinkText(String linkText) {
		doClick(By.linkText(linkText));
	}

	/**
	 * 点击通过链接的部分文字内容定位到的元素
	 * 
	 * @param partialText
	 *            链接的部分文字内容
	 */
	public static void clickByPartialText(String partialText) {
		doClick(By.partialLinkText(partialText));
	}

	/**
	 * 点击使用HTML页面标记定位到的元素
	 * 
	 * @param tagName
	 *            HTML标记
	 */
	public static void clickByTagName(String tagName) {
		doClick(By.tagName(tagName));
	}

	/**
	 * 清空文本框，并且在输入文本框中输入内容
	 * 
	 * @param by
	 *            By对象 定位文本框元素
	 * @param value
	 *            待输入的内容
	 */
	public static void sendKey(By by,String value) {
		WebElement text = getElement(by);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 通过id定位文本框且输入内容
	 * @param id 文本框的id属性
	 * @param value 待输入的内容
	 */
	public static void sendKeyById(String id,String value) {
		WebElement text = byId(id);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 通过name属性定位文本框且输入内容
	 * @param name 文本框的name属性
	 * @param value 待输入的内容
	 */
	public static void sendKeyBy(String name, String value) {
		WebElement text = byName(name);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 通过class属性定位文本框且输入内容
	 * @param className 文本框的class属性
	 * @param value 待输入的内容
	 */
	public static void sendKeyByClass(String className,String value) {
		WebElement text =byClassName(className);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 通过xpath定位文本框且输入内容
	 * @param xpath 文本框的xpath
	 * @param value 待输入的内容
	 */
	public static void sendKeyByXpath(String xpath, String value) {
		WebElement text = byXpath(xpath);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 通过css选择器定位文本框且输入内容
	 * @param cssSelector 文本框的css选择器
	 * @param value 待输入的内容
	 */
	public static void sendKeyByCss(String cssSelector, String value) {
		WebElement text = byCssSelector(cssSelector);
		text.clear();
		text.sendKeys(value);
	}
	/**
	 * 获取文本框的内容
	 * 
	 * @param by
	 *            By对象 定位文本框元素
	 * @return 返回值为String类型，文本框中的内容
	 */
	public static String getTextValue(By by) {
		return getElement(by).getText();
	}

	/**
	 * 通过遍历元素集合定位到相应元素并点击
	 * 
	 * @param by
	 *            By对象 定位一些可点击的元素
	 * @param text
	 *            元素包含的内容
	 */
	public static void clickContainsText(By by, String text) {
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
	 * 通过id属性来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param id
	 *            元素的id属性
	 */
	public static void toFrameById(String id) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byId(id);
		driver.switchTo().frame(iframe);
	}
	/**
	 * 通过name属性来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param name
	 *            元素的name属性
	 */
	public static void toFrameByName(String name) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byName(name);
		driver.switchTo().frame(iframe);
	}
	/**
	 * 通过css选择器来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param cssSelector
	 *            元素的css选择器
	 */
	public static void toFrameByCss(String cssSelector) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byCssSelector(cssSelector);
		driver.switchTo().frame(iframe);
	}
	/**
	 * 通过class属性来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param className
	 *            元素的className属性
	 */
	public static void toFrameByClass(String className) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byClassName(className);
		driver.switchTo().frame(iframe);
	}
	/**
	 * 通过xpath来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param xpath
	 *            元素的xpath属性
	 */
	public static void toFrameByXpath(String xpath) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byXpath(xpath);
		driver.switchTo().frame(iframe);
	}
	/**
	 * 通过tagName标签名来定位<iframe>框架，切换到某个frame中
	 * 
	 * @param tagName
	 *            元素的标签名
	 */
	public static void toFrameByTagName(String tagName) {
		// 先找到元素所在的iframe，切换到iframe里面才能定位到iframe中的元素
		WebElement iframe = byTagName(tagName);
		driver.switchTo().frame(iframe);
	}

	/**
	 * 切换到父窗口
	 */
	public static void toParentWindow() {
		driver.switchTo().parentFrame();
	}

	/**
	 * 获取弹窗元素，即切换到弹出对话框
	 * 
	 * @return
	 */
	public static Alert getAlert() {
		return driver.switchTo().alert();
	}

	/**
	 * 在弹出对话框中点击确认按钮
	 */
	public static void accept() {
		driver.switchTo().alert().accept();
	}

	/**
	 * 在弹出对话框中点击取消按钮
	 */
	public static void dimiss() {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * 获取对话框的文本内容
	 */
	public static void getText() {
		driver.switchTo().alert().getText();
	}

	/**
	 * 判断页面上是否存在某个元素
	 * 
	 * @param by
	 *            By对象 定位某个元素
	 * @return Boolean类型，true：元素存在；false：元素不存在
	 */
	public static Boolean isElementExist(By by) {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * 获取下拉列表对象
	 * 
	 * @param by
	 *            By对象 用于定位<select>标签
	 * @return 返回一个Select对象
	 */
	public static Select select(By by) {
		WebElement elem = getElement(by);
		Select select = new Select(elem);
		return select;
	}

	/**
	 * 通过下拉列表的可视化文本选择其中的选项
	 * 
	 * @param by
	 *            By对象 用于定位<select>标签
	 * @param text
	 *            option选项的可视化文本内容
	 */
	public static void doSelectByText(By by, String text) {
		select(by).selectByVisibleText(text);
	}

	/**
	 * 通过下拉列表选项的值（value）选择其中的选项
	 * 
	 * @param by
	 *            By对象 用于定位<select>标签
	 * @param value
	 *            option选项的value值
	 */
	public static void doSelectByValue(By by, String value) {
		select(by).selectByValue(value);
	}

	/**
	 * 通过下拉列表选项的下标选择其中的选项
	 * 
	 * @param by
	 *            By对象 用于定位<select>标签
	 * @param index
	 *            option选项的下标（下标从0开始）
	 */
	public static void doSelectByIndex(By by, Integer index) {
		select(by).selectByIndex(index);
	}

	/**
	 * 对于<input>类型的下拉列表的选择处理
	 * 
	 * @param openBy
	 *            By对象，定位打开下拉列表的元素
	 * @param optionBy
	 *            By对象，定位具体选项
	 */
	public static void inputSelect(By openBy, By optionBy) {
		doClick(openBy);
		doClick(optionBy);
	}

	/**
	 * 截屏操作
	 * 
	 * @param prefixName
	 *            -图片的部分前缀名
	 */
	public static void getScreenShot(String prefixName) {

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
	 * 
	 * @param selectFileBy
	 *            By对象 用于定位选择文件的input标签
	 * @param uploadBy
	 *            By对象 定位上传的按钮
	 * @param filePath
	 *            待上传文件的路径
	 */
	public static void fileUpload(By selectFileBy, By uploadBy, String filePath) {
		WebElement fileElement = getElement(selectFileBy);
		// 选择上传的文件
		File file = new File(filePath);
		fileElement.sendKeys(file.getPath());
		// 点击上传按钮进行上传
		doClick(uploadBy);
	}

	/**
	 * 添加Cookie（org.openqa.selenium.Cookie）的方法
	 * 
	 * @param key
	 *            Cookie的键名称
	 * @param value
	 *            Cookie的值
	 */
	public static void setCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		driver.manage().addCookie(cookie);
	}

	/**
	 * 根据Cookie的键获取对应的Cookie
	 * 
	 * @param key
	 *            Cookie的键名称
	 * @return 返回值为org.openqa.selenium.Cookie对象
	 */
	public static Cookie getCookie(String key) {
		Cookie cookie = driver.manage().getCookieNamed(key);
		return cookie;
	}

	/**
	 * 根据Cookie的键获取对应的值
	 * 
	 * @param key
	 *            Cookie的键名称
	 * @return 返回值为String类型，表示键对应的值
	 */
	public static String getCookieValue(String key) {
		Cookie cookie = driver.manage().getCookieNamed(key);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 获取所有Cookie的方法
	 * 
	 * @return 返回值为Set集合，存放所有的Cookie
	 */
	public static Set<Cookie> getCookies() {
		Set<Cookie> cookies = driver.manage().getCookies();
		return cookies;
	}

	/**
	 * 根据Cookie的键名称删除Cookie
	 * 
	 * @param key
	 *            Cookie的键名
	 */
	public static void deleteCookie(String key) {
		driver.manage().deleteCookieNamed(key);
	}

	/**
	 * 删除指定的Cookie
	 * 
	 * @param cookie
	 *            org.openqa.selenium.Cookie对象
	 */
	public static void deleteCookie(Cookie cookie) {
		driver.manage().deleteCookie(cookie);
	}

	/**
	 * 删除所有的Cookie
	 */
	public static void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	/**
	 * 判断规定时间内是否找到某元素，如果找到则返回true，否则返回false
	 * 
	 * @param driver
	 *            -WebDriver对象
	 * @param timeout
	 *            -规定的超时时间
	 * @param elementId
	 *            -要查找的元素的id
	 * @return -返回值为Boolean类型，true表示找到元素，false表示没有找到
	 */
	public static Boolean isFound(long timeout, By by) {
		Boolean flag = true;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		// 若超时则抛出异常，故可以在此捕获异常
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
	 * 切换浏览器窗口
	 * @param windowTitle  窗口标题
	 */
	public static void switchWindow(String windowTitle) {
		try {

			// 获取当前窗口的handle
			String currentHandle = driver.getWindowHandle();

			// 将页面上所有的windowshandle放在入set集合当中

			Set<String> handles = driver.getWindowHandles();

			// 循环遍历这个集合中的元素
			for (String s : handles) {

				// 如果和当前的窗口的handle一样，跳出本次循环，继续下次一循环
				if (s.equals(currentHandle))
					continue;
				else {
					// 如果不一样就切换到那个handle，对比这个页面中的title是否包含传入的windowtitle。如果是就返回true，跳出循环了。如果title不一样就跳出本次循环，继续循环对比。
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
	 * 获取当前页面的title属性的值
	 * @return 返回值为String类型，当前页面的标题
	 */
	public static String getTitle() {
		return driver.getTitle();
	}
	/**
	 * 获取当前页面整个页面的源码
	 */
	public static String getPageSource() {
		return driver.getPageSource();
	}
	/**
	 * 根据元素属性名获取对应的属性值
	 * @param element WebElement页面元素对象
	 * @param name 元素的属性名
	 * @return 返回值为String类型，对应属性的值
	 */
	public static String getAttribute(WebElement element,String name){
		return element.getAttribute(name);
	}
	/**
	 * 进入某个新页面
	 * @param url
	 */
	public static void toPage(String url) {
		driver.navigate().to(url);
	}
	/**
	 * 通过历史导航前进到页面
	 */
	public static void forward() {
		driver.navigate().forward();
	}
	/**
	 * 通过历史导航返回到页面
	 */
	public static void back() {
		driver.navigate().back();
	}
	/**
	 * 用xpath定位方式来定位到具体的单元格，并且返回单元格的内容
	 * @param tableXpath table的xpath
	 * @param row 单元格所在的行数（不包括表头）
	 * @param column 单元格所在的列数
	 * @return 返回值为String类型，表示单元格的内容
	 */
	public static String tableCell(String tableXpath,int row,int column) {
		String text = null;
        //避免取到表头
        row=row+1;
        String xpath=tableXpath+"/tbody/tr["+row+"]/td["+column+"]";
        WebElement table=driver.findElement(By.xpath(xpath)); 
        text=table.getText();
        return text;
	}
	/**
	 * 关闭浏览器的方法
	 */
	public static void closeBrowser() {
		driver.close();
	}

	/**
	 * 停止继续操作的方法
	 */
	public static void quit() {
		driver.quit();
	}
}
