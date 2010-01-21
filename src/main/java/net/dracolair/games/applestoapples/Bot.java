package net.dracolair.games.applestoapples;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.dracolair.games.applestoapples.commands.CmdJoin;
import net.dracolair.games.applestoapples.commands.CmdList;
import net.dracolair.games.applestoapples.commands.CmdStart;
import net.dracolair.games.applestoapples.commands.Command;

import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {

	public Map<String, ApplesToApples>	m_gameList = new HashMap<String, ApplesToApples>();
	public Map<String, ApplesToApples>	m_playerList = new HashMap<String, ApplesToApples>();
	public Map<String, Command> 		m_chanCommands = new HashMap<String, Command>();
	public Map<String, Command> 		m_privCommands = new HashMap<String, Command>();

	public Bot(String name) {
		this.setName(name);
		
		m_chanCommands.put("join", new CmdJoin());
		m_chanCommands.put("list", new CmdList());
		m_chanCommands.put("start", new CmdStart());
	}
	
	public void onMessage(String channel, 
						  String sender, 
						  String login, 
						  String hostname, 
						  String message) {
		
	}

	public List<String> handleChanMessage(String[] msgMap) {
		List<String> responses = new LinkedList<String>();
		String[] parsedMessage = msgMap[4].split(" ", 2);
		String cmdKey = parsedMessage[0].substring(1);
		String[] modMsgMap = msgMap.clone();
		if (parsedMessage.length < 2) {
			modMsgMap[4] = "";
		} else {
			modMsgMap[4] = parsedMessage[1];
		}
		Command cmd = m_chanCommands.get(cmdKey);
		
		if (cmd != null) {
			responses = cmd.execute(this, modMsgMap);
		}

		return responses;
	}

	public ApplesToApples getGame(String channel) {
		return m_gameList.get(channel);
	}
	
}
