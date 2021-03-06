package com.mlegeb.test;

import java.awt.event.KeyEvent;

import org.junit.Test;

import com.mlegeb.config.GameConfigPoco;
import com.mlegeb.core.Massager;

/**
 * 名称: ConfigPocoTest.java
 * 描述: 单元测试类 
 *
 * @author a_xiang
 * @version v1.0
 * @created 2015年1月21日
 */
public class ConfigPocoTest {

	@Test
	public void savetest() {
		Massager massager = Massager.Instance();
		massager.sleepPc();
	}
	
	@Test
	public void getTest(){
		
		GameConfigPoco config = new GameConfigPoco();
		config.i_A = KeyEvent.VK_I;
		config.i_B = KeyEvent.VK_J;
		config.i_C = KeyEvent.VK_K;
		config.i_D = KeyEvent.VK_L;
		
		config.i_down = KeyEvent.VK_S;
		config.i_up = KeyEvent.VK_W;
		config.i_left = KeyEvent.VK_A;
		config.i_right = KeyEvent.VK_D;
		config.saveConfig();
		
		
	}
	

}
