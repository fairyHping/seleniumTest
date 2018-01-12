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
		// 输入登录信息
		util.sendValue(By.cssSelector("input[name='username']"), "zhangsan001");
		util.sendValue(By.cssSelector("input[name='password']"), "zhangsan001");
		// 提交登录
		util.doClick(By.cssSelector("input[value='登录']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 截屏操作
		// 设置保存路径：user.dir是系统的全局属性，表示在项目根目录下
		String path = System.getProperty("user.dir") + "/screenshots/";
		// 设置截图文件名称：1、使用随机数 2、当前时间 3模块名编号等
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
		String date = sf.format(new Date());
		String name = "user_" + date + ".png";
		TakesScreenshot take = (TakesScreenshot) driver;
		// 截到的内容在内存中
		File file = take.getScreenshotAs(OutputType.FILE);
		// 将截到的内容复制出来
		try {
			FileUtils.copyFile(file, new File(path + name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
