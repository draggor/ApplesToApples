package net.dracolair.games.applestoapples;

import static net.dracolair.games.applestoapples.Factories.*;

import java.util.List;

import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	
	public GameManager m_gameManager;
	
	public Bot(String name) {
		this.setName(name);
		this.setLogin(name);
		m_gameManager = new GameManager(name);
	}
	
	public void processResponses(List<Message> responses) {
		for(Message response : responses) {
			sendMessage(response.m_target, response.m_message);
		}
	}
	
	@Override
	public void onMessage(String channel, 
						  String sender, 
						  String login, 
						  String hostname, 
						  String message) {
		processResponses(m_gameManager.processRoomMessage(MSGINFO(channel, sender, message)));
	}
	
	@Override
	public void onPrivateMessage(String sender,
								 String login,
								 String hostname,
								 String message) {
		processResponses(m_gameManager.processPrivMessage(MSGINFO(sender, sender, message)));
	}
	
	@Override
	public void onJoin(String channel,
					   String sender,
					   String login,
					   String hostname) {
		if(sender.equals(m_gameManager.getName()) && m_gameManager.getGameByChan(channel) == null) {
			processResponses(m_gameManager.processRoomMessage(MSGINFO(channel, sender, "!botcreategame")));
		}
	}
	
}
