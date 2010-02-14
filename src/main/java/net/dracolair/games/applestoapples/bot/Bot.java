package net.dracolair.games.applestoapples.bot;

import static net.dracolair.games.applestoapples.Factories.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.card.CardRenderer;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot implements Runnable {
	
	public GameManager m_gameManager;
	public List<Channel> m_channels = new LinkedList<Channel>();
	public String m_server;
	public Thread m_thread;
	
	public Bot(String name) {
		this.setName(name);
		this.setLogin(name);
		CardRenderer redCardRenderer = new IrcRedCardRenderer();
		CardRenderer greenCardRenderer = new IrcGreenCardRenderer();
		m_gameManager = new GameManager(name, redCardRenderer, greenCardRenderer);
		m_thread = new Thread(this);
		m_thread.start();
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
			rejoinChannels();
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

	public void connect() {
		try {
			connect(m_server);
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
	
	public void join(Channel channel) {
		if(channel.m_pass != null) {
			joinChannel(channel.m_channel, channel.m_pass);
		} else {
			joinChannel(channel.m_channel);
		}
	}
	
	public void rejoinChannels() {
		for(Channel channel : m_channels) {
			join(channel);
		}
	}

	public void joinAndSaveChannel(Channel channel) {
		m_channels.add(channel);
		join(channel);
	}

	@Override
	public void run() {
		for(;;) {
			for(Entry<String, Game> e : m_gameManager.m_roomToGameMap.entrySet()) {
				Game ata = e.getValue();
				String channel = e.getKey();
				long wait = System.currentTimeMillis() - ata.m_time;
				if(ata.m_state.equals(State.PLAY) || ata.m_state.equals(State.CHOOSE)) {
					if(wait > 30000 && !ata.m_warning) {
						m_gameManager.processPrivMessage(MSGINFO(m_gameManager.getName(), m_gameManager.getName(), "!botwarning " + channel));
					} else if(wait > 60000 && ata.m_warning) {
						m_gameManager.processPrivMessage(MSGINFO(m_gameManager.getName(), m_gameManager.getName(), "!botaway " + channel));
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
