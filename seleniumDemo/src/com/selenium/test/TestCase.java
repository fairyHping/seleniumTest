package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.selenium.test.base.SeleniumUtil;

public class TestCase {
	WebDriver driver=null;
	
	public static void waitTime(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �����Ӧ��ϵͳ
	 */
	@BeforeSuite
	public void getdriver() {
		driver=SeleniumUtil.getDriver("chrome",".//chromedriver.exe");
		SeleniumUtil.openBrowser("http://localhost:8080/CRM/");
	}
	
	/**
	 * ��¼����
	 */
	@Test
	public void loginCRM() {
		SeleniumUtil.sendKeyByCss("input[name='username']", "zhangsan001");
		SeleniumUtil.sendKeyByCss("input[name='password']", "zhangsan001");
		SeleniumUtil.clickByCss("#loginForm > p.submit > input[type=\"submit\"]");
	}
	
	/**
	 * �����û�����
	 */
	@Test(dependsOnMethods="loginCRM")
	public void userManage() {
		waitTime(2000);
		SeleniumUtil.clickByXpath("//*[@id='_easyui_tree_1']/span[1]");
		waitTime(2000);
		SeleniumUtil.clickByXpath("//*[@id=\"_easyui_tree_10\"]/span[4]");	//�л��������û�����ҳ��
		SeleniumUtil.toFrameByXpath("//*[@id=\"tabs\"]/div[2]/div[2]/div/iframe");
		
	}
	
	/**
	 * ���н�ɫ�������
	 */
	@Test(dependsOnMethods="userManage")
	public void assignRole() {
		SeleniumUtil.clickByXpath("//*[@id=\"datagrid-row-r1-2-5\"]");
		SeleniumUtil.clickByXpath("//*[@id=\"assignButton\"]/span");
		//�л��������ɫ��������
		SeleniumUtil.toParentWindow();
		SeleniumUtil.toFrameByXpath("//*[@id=\"topWindow\"]/iframe");
		SeleniumUtil.clickByXpath("//*[@id=\"datagrid-row-r1-2-2\"]/td[1]/div/input");
		SeleniumUtil.doClick(By.cssSelector("body > div:nth-child(3) > a"));
	}

}
