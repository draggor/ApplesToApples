package net.dracolair.games.applestoapples;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.dracolair.games.applestoapples.card.Card;
import net.dracolair.games.applestoapples.card.CardRenderer;
import net.dracolair.games.applestoapples.commands.CommandFactory;
import net.dracolair.games.applestoapples.commands.Command;

import static net.dracolair.games.applestoapples.FileParser.*;
import static net.dracolair.games.applestoapples.Factories.*;

public class GameManager {

	public static List<Card>	RED = null;
	public static List<Card>	GREEN = null;
	
	public Map<String, Game>	m_roomToGameMap = new LinkedHashMap<String, Game>();
	public Map<Name, Game>		m_nameToGameMap = new LinkedHashMap<Name, Game>();
	public Map<String, Name> 	m_nickToNameMap = new LinkedHashMap<String, Name>();
	public String				m_name;
	public CommandFactory		m_cmdFactory = new CommandFactory();
	
	public GameManager(String name, CardRenderer redCardRenderer, CardRenderer greenCardRenderer) {
		m_name = name;
		
		if(RED == null) {
			RED = loadCardsFromFile(this.getClass().getResourceAsStream("/red.txt"), redCardRenderer);
		}
		if(GREEN == null) {
			GREEN = loadCardsFromFile(this.getClass().getResourceAsStream("/green.txt"), greenCardRenderer);
		}
	}
	
	/**
	 * TODO: Modify processRoomMessage and processPrivMessage to return Command objects instead of a List<Message>
	 *       That way, when the bot goes to send a message, it will evaluate the command at that point, and not create timers
	 *       and then sit on it while it waits to send.
	 *       Note that in the bot, there will need to be some way to stagger the adding of messages.  Could SendRawMessage and
	 *       use a delay timer of my own.
	 *       Ideally, instead of adding a String to a queue, we would add the command itself to delay evaluation that way.
	 * @param msgInfo
	 * @return
	 */
	public Command processRoomMessage(MessageInfo msgInfo) {
		System.out.println("processRoomMessage: " + msgInfo.MESSAGE);
		if(msgInfo.MESSAGE.charAt(0) == '!') {
			String[] parsedMessage = msgInfo.MESSAGE.split(" ", 2);
			String cmdKey = parsedMessage[0].substring(1);
			MessageInfo modMsgInfo = msgInfo.clone();
			if (parsedMessage.length < 2) {
				modMsgInfo.MESSAGE = "";
			} else {
				modMsgInfo.MESSAGE = parsedMessage[1];
			}
			
			Command cmd = m_cmdFactory.create(cmdKey);
			Game ata = getGameByChan(modMsgInfo.ROOM);
			
			if (cmd != null) {
				cmd.ata = ata;
				cmd.gameManager = this;
				cmd.msgInfo = modMsgInfo;
			}
			return cmd;
		} else {
			return null;
		}
	}
	
	public Command processPrivMessage(MessageInfo msgInfo) {
		System.out.println("processPrivMessage: " + msgInfo.MESSAGE);
		String[] parsedMessage = msgInfo.MESSAGE.split(" ", 3);
		String cmdKey = parsedMessage[0].substring(1);
		MessageInfo modMsgInfo = msgInfo.clone();
		if (parsedMessage.length < 2) {
			modMsgInfo.MESSAGE = "";
		} else if (parsedMessage.length < 3) {
			modMsgInfo.MESSAGE = parsedMessage[1];
			modMsgInfo.ROOM = parsedMessage[1];
		} else {
			modMsgInfo.MESSAGE = parsedMessage[2];
			modMsgInfo.ROOM = parsedMessage[1];
		}
 
		Command cmd = m_cmdFactory.create(cmdKey);
		Game ata = getGameByChan(modMsgInfo.ROOM);
		if(ata == null) {
			ata = getGameByNick(modMsgInfo.ROOM);
		}
 
		if (cmd != null) {
			cmd.ata = ata;
			cmd.gameManager = this;
			cmd.msgInfo = modMsgInfo;
		}
 
		return cmd;
	}

	public Game getGameByChan(String channel) {
		return m_roomToGameMap.get(channel);
	}
	
	public Game getGameByNick(String nick) {
		return m_nameToGameMap.get(m_nickToNameMap.get(nick));
	}
	
	public String getName() {
		return m_name;
	}

	public void reset(String room) {
		Game ata = getGameByChan(room);
		for(Name n : ata.m_players.keySet()) {
			m_nameToGameMap.remove(n);
			m_nickToNameMap.remove(n.toString());
		}
		m_roomToGameMap.put(room, GAME(RED, GREEN, ata.m_isRandom));
	}

	public void changeNick(String oldNick, String newNick) {
		Name n = m_nickToNameMap.get(oldNick);
		if(n != null) {
			n.m_name = newNick;
			m_nickToNameMap.put(newNick, n);
			m_nickToNameMap.remove(oldNick);
		}
	}
}
