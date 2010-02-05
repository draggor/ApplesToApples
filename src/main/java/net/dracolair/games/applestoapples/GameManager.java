package net.dracolair.games.applestoapples;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.dracolair.games.applestoapples.commands.MgrCmdChoose;
import net.dracolair.games.applestoapples.commands.MgrCmdCleanup;
import net.dracolair.games.applestoapples.commands.MgrCmdDeal7;
import net.dracolair.games.applestoapples.commands.MgrCmdEndGame;
import net.dracolair.games.applestoapples.commands.MgrCmdPlay;
import net.dracolair.games.applestoapples.commands.CmdChoose;
import net.dracolair.games.applestoapples.commands.CmdJoin;
import net.dracolair.games.applestoapples.commands.CmdList;
import net.dracolair.games.applestoapples.commands.CmdPlay;
import net.dracolair.games.applestoapples.commands.CmdStart;
import net.dracolair.games.applestoapples.commands.Command;

import static net.dracolair.games.applestoapples.FileParser.*;

public class GameManager {

	public static List<Card>	RED = null;
	public static List<Card>	GREEN = null;
	
	public Map<String, Game>	m_roomToGameMap = new LinkedHashMap<String, Game>();
	public Map<Name, Game>		m_nameToGameMap = new LinkedHashMap<Name, Game>();
	public Map<String, Command> m_commands = new LinkedHashMap<String, Command>();
	public Map<String, Name> 	m_nickToNameMap = new LinkedHashMap<String, Name>();
	public String				m_name;
	
	public GameManager(String name) {
		m_name = name;
		
		if(RED == null) {
			RED = loadCardsFromFile("src/main/resources/red.txt");
		}
		if(GREEN == null) {
			GREEN = loadCardsFromFile("src/main/resources/green.txt");
		}
		
		m_commands.put("join", new CmdJoin());
		m_commands.put("list", new CmdList());
		m_commands.put("start", new CmdStart());
		m_commands.put("play", new CmdPlay());
		m_commands.put("choose", new CmdChoose());
		m_commands.put("botplay", new MgrCmdPlay());
		m_commands.put("botchoose", new MgrCmdChoose());
		m_commands.put("botdeal7", new MgrCmdDeal7());
		m_commands.put("botcleanup", new MgrCmdCleanup());
		m_commands.put("botendgame", new MgrCmdEndGame());
	}
	
	public List<Message> processRoomMessage(MessageInfo msgInfo) {
		List<Message> responses = new LinkedList<Message>();
		if(msgInfo.MESSAGE.charAt(0) == '!') {
			String[] parsedMessage = msgInfo.MESSAGE.split(" ", 2);
			String cmdKey = parsedMessage[0].substring(1);
			MessageInfo modMsgInfo = msgInfo.clone();
			if (parsedMessage.length < 2) {
				modMsgInfo.MESSAGE = "";
			} else {
				modMsgInfo.MESSAGE = parsedMessage[1];
			}
			
			Command cmd = m_commands.get(cmdKey);
			Game ata = getGameByChan(modMsgInfo.ROOM);
			
			if (cmd != null) {
				responses = cmd.execute(this, ata, modMsgInfo);
			}
		}
		return responses;
	}
	
	public List<Message> processPrivMessage(MessageInfo msgInfo) {
		List<Message> responses = new LinkedList<Message>();
		String[] parsedMessage = msgInfo.MESSAGE.split(" ", 2);
		String cmdKey = parsedMessage[0];
		MessageInfo modMsgInfo = msgInfo.clone();
		if (parsedMessage.length < 2) {
			modMsgInfo.MESSAGE = "";
		} else {
			modMsgInfo.MESSAGE = parsedMessage[1];
		}
		
		Command cmd = m_commands.get(cmdKey);
		Game ata = getGameByNick(modMsgInfo.NICK);
		
		if (cmd != null) {
			responses = cmd.execute(this, ata, modMsgInfo);
		}

		return responses;
	}

	public Game getGameByChan(String channel) {
		Game ata = m_roomToGameMap.get(channel);
		if(ata == null) {
			ata = new Game(RED, GREEN);
			m_roomToGameMap.put(channel, ata);
		}
		return ata;
	}
	
	public Game getGameByNick(String nick) {
		return m_nameToGameMap.get(m_nickToNameMap.get(nick));
	}
	
	public String getName() {
		return m_name;
	}
}
