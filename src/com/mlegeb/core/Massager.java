package com.mlegeb.core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * 数据处理者
 * @author Mine
 */
public class Massager {
	private static Massager massager;
	private static Robot robot;

	private Massager() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.out.println("无法获取键盘");
			e.printStackTrace();
		}
	}

	/**
	 * 单例模式
	 * @return
	 */
	public static Massager Instance() {
		if (massager == null)
			return new Massager();
		return massager;
	}

	/**
	 * 按下
	 * @param key 按键值
	 */
	public void keyDown(int key){
		robot.keyPress(key);
	}

	public void keyUp(int key){
		robot.keyRelease(key);
	}

	public void keyDownAndUp(int key){
		robot.keyPress(key);
		robot.keyRelease(key);
	}

	public void keyWithAlt(int key){
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_ALT);
	}
	
	public void keyWithAlt_Ctrl(int key){
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(key);
		robot.keyRelease(key);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void winChange(int wheelAmt){
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.mouseWheel(wheelAmt);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		
	}
	public void mouseMove(float x, float y){
	}

	public void clickMove(){

	}

	public void shutdownPc(){

		String command = "cmd /c Shutdown -s";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sleepPc(){
		String command = "Shutdown /h";
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}