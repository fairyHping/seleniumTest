package com.selenium.test.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitDriver {
	public static WebDriver driver=null;
	/**
	 * ��ʼ�����������
	 * @param browser -String����  ���������
	 * @return -����WebDriver����
	 */
	public static WebDriver getDriver(String browser) {
		
		//FireFox��������
		if ("firefox".equals(browser.toLowerCase())) {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			 driver.manage().window().maximize();

		} else if ("ie".equals(browser.toLowerCase())) {//IE�����
			// �رձ���ģʽ
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability("ignoreProtectedModeSettings", true);
			// ָ������λ�ã�����������
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capability);
			driver.manage().window().maximize();
		} else if ("chrome".equals(browser.toLowerCase())) {//Google Chrome�����
            //ָ������λ�ã�����������
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else{
            System.out.println("�����ָ�����󣡣�������֧��IE��Firefox��Chrome��");
        }
		return driver;
	}
}