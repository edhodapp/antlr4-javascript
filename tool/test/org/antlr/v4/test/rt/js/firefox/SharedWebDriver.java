package org.antlr.v4.test.rt.js.firefox;

import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SharedWebDriver {

	static WebDriver driver;
	static Timer timer;
	
	public static WebDriver init() {
		if(driver==null) {
			driver = new FirefoxDriver();
		} else if(timer!=null) {
			timer.cancel();
			timer = null;
		}
			
		return driver;
	}

	public static void close() {
		if(driver!=null) {
			if(timer!=null) {
				timer.cancel();
				timer = null;
			}
			timer = new Timer();
			timer.schedule(new TimerTask() { 
				@Override public void run() {
					driver.quit();
					driver = null;
				}
			}, 2000); // close with delay to allow next Test to start
		}
	}

}
