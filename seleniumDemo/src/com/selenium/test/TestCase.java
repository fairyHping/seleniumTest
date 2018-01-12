package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.selenium.test.base.InitDriver;
import com.selenium.test.base.SeleniumUtil;

public class TestCase {
	WebDriver driver=null;
	SeleniumUtil util=null;
	
	/**
	 * 进入对应的系统
	 */
	@BeforeSuite
	public void getdriver() {
		driver=InitDriver.getDriver("chrome");
		util=new SeleniumUtil(driver);
		util.openBrowser("http://localhost:8080/CRM/");
	}
	
	/**
	 * 登录操作
	 */
	@Test
	public void loginCRM() {
		util.sendValue(By.cssSelector("input[name='username']"), "zhangsan001");
		util.sendValue(By.cssSelector("input[name='password']"), "zhangsan001");
		util.doClick(By.cssSelector("#loginForm > p.submit > input[type=\"submit\"]"));
	}
	
	/**
	 * 进入用户管理
	 */
	@Test(dependsOnMethods="loginCRM")
	public void userManage() {
		util.doClick(By.xpath("//*[@id='_easyui_tree_1']/span[1]"));
		util.doClick(By.xpath("//*[@id=\"_easyui_tree_12\"]/span[4]"));	//切换窗口至用户管理页面
		util.changeFrame(By.xpath("//*[@id=\"tabs\"]/div[2]/div[2]/div/iframe"));
		
	}
	
	/**
	 * 进行角色分配操作
	 */
	@Test(dependsOnMethods="userManage")
	public void assignRole() {
		util.doClick(By.xpath("//*[@id=\"datagrid-row-r1-2-5\"]"));
		util.doClick(By.xpath("//*[@id=\"assignButton\"]/span"));
		//切换至分配角色弹出窗口
		util.toParentWindow();
		util.changeFrame(By.xpath("//*[@id=\"topWindow\"]/iframe"));
		util.doClick(By.xpath("//*[@id=\"datagrid-row-r1-2-2\"]/td[1]/div/input"));
		util.doClick(By.cssSelector("body > div:nth-child(3) > a"));
	}

}
