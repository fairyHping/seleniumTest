package com.selenium.test.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestCase {

	public static void main(String[] args) {
		WebDriver driver = InitDriver.getDriver("chrome");
		SeleniumUtil util = new SeleniumUtil(driver);
		util.openBrowser("http://localhost:8080/CRM/");
		// �����¼��Ϣ
		util.sendValue(By.cssSelector("input[name='username']"), "zhangsan001");
		util.sendValue(By.cssSelector("input[name='password']"), "zhangsan001");
		// �ύ��¼
		util.doClick(By.cssSelector("input[value='��¼']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ��������
		// ���ñ���·����user.dir��ϵͳ��ȫ�����ԣ���ʾ����Ŀ��Ŀ¼��
		String path = System.getProperty("user.dir") + "/screenshots/";
		// ���ý�ͼ�ļ����ƣ�1��ʹ������� 2����ǰʱ�� 3ģ������ŵ�
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
		String date = sf.format(new Date());
		String name = "user_" + date + ".png";
		TakesScreenshot take = (TakesScreenshot) driver;
		// �ص����������ڴ���
		File file = take.getScreenshotAs(OutputType.FILE);
		// ���ص������ݸ��Ƴ���
		try {
			FileUtils.copyFile(file, new File(path + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
