package net.dracolair.games.applestoapples;

import static net.dracolair.games.applestoapples.Factories.*;

import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	
	public GameManager m_gameManager;
	
	public Bot(String name) {
		this.setName(name);
		this.setLogin(name);
		m_gameManager = new GameManager(name);
	}
	
	public void onMessage(String channel, 
			  String sender, 
			  String login, 
			  String hostname, 
			  String message) {
		MessageInfo msgMap = MSGINFO(channel, sender, message);
		for(Message response : m_gameManager.processRoomMessage(msgMap)) {
			sendMessage(response.m_target, response.m_message);
		}
	}
	
}
