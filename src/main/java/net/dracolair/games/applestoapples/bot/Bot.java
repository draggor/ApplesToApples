package net.dracolair.games.applestoapples.bot;

import static net.dracolair.games.applestoapples.Factories.*;

import java.io.IOException;
import java.util.List;

import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.card.CardRenderer;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	
	public GameManager m_gameManager;
	
	public Bot(String name) {
		this.setName(name);
		this.setLogin(name);
		CardRenderer redCardRenderer = new IrcRedCardRenderer();
		CardRenderer greenCardRenderer = new IrcGreenCardRenderer();
		m_gameManager = new GameManager(name, redCardRenderer, greenCardRenderer);
	}
	
	public void processResponses(List<Message> responses) {
		System.out.println(responses);
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
		if(sender.equals("Draggor") && hostname.equals("216-80-120-145.alc-bsr1.chi-alc.il.static.cable.rcn.com")) {
			String[] sp = message.split(" ", 3);
			if(sp[0].equals("!c")) {
				processResponses(m_gameManager.processRoomMessage(MSGINFO(channel, sp[1], sp[2])));
			} else {
				processResponses(m_gameManager.processRoomMessage(MSGINFO(channel, sender, message)));
			}
		} else {
			processResponses(m_gameManager.processRoomMessage(MSGINFO(channel, sender, message)));
		}
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
	
	@Override
	public void onNotice(String sourceNick, 
						 String sourceLogin, 
						 String sourceHostname, 
						 String target, 
						 String notice) {
		System.out.println("NOTICE: " + notice);
	}
	
	@Override
	public void onNickChange(String oldNick, 
							 String login, 
							 String hostname, 
							 String newNick) {
		m_gameManager.changeNick(oldNick, newNick);
	}
	
	@Override
	public void onDisconnect() {
		try {
			reconnect();
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}