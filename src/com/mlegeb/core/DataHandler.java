package com.mlegeb.core;

import com.mlegeb.frame.IndexFrame;
import com.mlegeb.state.*;
import com.mlegeb.udpservice.UdpManager;


/**
 * 数据控制器-负责数据分发
 * @author Mine
 */
public class DataHandler{
	
	public final int INIT_STATE     = 0;  //初始化
	public final int GAME_STATE     = 1;  //手柄模式
	public final int INPUT_STATE    = 2;  //输入模式
	public final int MOUSE_STATE    = 3;  //鼠标模式
	public final int MUSIC_STATE    = 4;  //播放器模式
	public final int PPT_STATE      = 5;  //PPT模式
	public final int SHUTDOWN_STATE = 6;  //关机模式
	public final int WINDOW_STATE   = 7;  //窗口模式
	

	private IndexFrame indexPanel;
	private StateFactory stateFactory;
	
	private AppState appState;
	private GameState  gameState;
	private InputState inputState;
	private MouseState mouseState;
	private MusicState musicState;
	private PPTState pptState;
	private ShutdownState shutdownState;
	private SleepState sleepState;
//	private WaittingState waittingState;
	private WindowState windowState;
	
	
	public DataHandler(IndexFrame indexPanel){
		this.indexPanel = indexPanel;
		stateFactory = new StateFactory();
		
		gameState = stateFactory.getGameState();
		inputState = stateFactory.getInputState();
		mouseState = stateFactory.getMouseState();
		musicState = stateFactory.getMusicState();
		pptState = stateFactory.getPPTState();
		shutdownState = stateFactory.getShutdownState();
		sleepState = stateFactory.getSleepState(this);
//		waittingState = stateFactory.getWaittingState();
		windowState = stateFactory.getWindowState();
		
		appState = sleepState;                                                   //初始化为睡眠状态
	}
	
	/**
	 * 数据处理
	 * @param msg 获取的数据
	 */
	public void procesData(String msg, UdpManager udpManager){
		if(msg.startsWith("state")){
			appState = sleepState;	
		}
		else if(msg.startsWith("link")){                                        //收到“link” 表示连接操作。
			indexPanel.stateText.setText("已连接");
			udpManager.send("Successful");                           //返回successful 表示连接成功
		}
		else{
			changeState(Integer.parseInt(msg.substring(0, 1)));
			appState.AcceptData(msg.substring(2));
		}
		indexPanel.clientIPText.setText(udpManager.getAddresssIP());
		
		
	}
	
	/**
	 * 状态转变
	 * @param state 状态值
	 */
	public void changeState(int state){
		switch (state) {
		case INIT_STATE:
			appState = sleepState;
			indexPanel.stateText.setText("已连接");
			break;
		case GAME_STATE:
			appState = gameState;
			indexPanel.stateText.setText("手柄模式");
			break;
		case INPUT_STATE:
			appState = inputState;
			indexPanel.stateText.setText("键盘模式");
			break;
		case MOUSE_STATE:
			appState = mouseState;
			indexPanel.stateText.setText("鼠标模式");
			break;
		case MUSIC_STATE:
			appState = musicState;
			indexPanel.stateText.setText("播放器模式");
			break;
		case PPT_STATE:
			appState = pptState;
			indexPanel.stateText.setText("PPT模式");
			break;
		case SHUTDOWN_STATE:
			appState = shutdownState;
			indexPanel.stateText.setText("CMD模式");
			break;
		case WINDOW_STATE:
			appState = windowState;
			indexPanel.stateText.setText("窗口模式");
			break;
		default:
			appState = sleepState;
			indexPanel.stateText.setText("等待连接");
			break;
		}
	}

}
