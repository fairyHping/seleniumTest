package com.selenium.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitDriver {
	public static WebDriver driver=null;
	/**
	 * 初始化浏览器驱动
	 * @param browser -String类型  浏览器名称
	 * @return -返回WebDriver对象
	 */
	public static WebDriver getDriver(String browser) {
		
		//FireFox火狐浏览器
		if ("firefox".equals(browser.toLowerCase())) {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			 driver.manage().window().maximize();

		} else if ("ie".equals(browser.toLowerCase())) {//IE浏览器
			// 关闭保护模式
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability("ignoreProtectedModeSettings", true);
			// 指定驱动位置，并加载驱动
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capability);
			driver.manage().window().maximize();
		} else if ("chrome".equals(browser.toLowerCase())) {//Google Chrome浏览器
            //指定驱动位置，并加载驱动
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else{
            System.out.println("浏览器指定错误！！！（仅支持IE、Firefox、Chrome）");
        }
		return driver;
	}
}
