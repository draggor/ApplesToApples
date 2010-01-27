package net.dracolair.games.applestoapples;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.dracolair.games.applestoapples.commands.BotCmdChoose;
import net.dracolair.games.applestoapples.commands.BotCmdPlay;
import net.dracolair.games.applestoapples.commands.CmdChoose;
import net.dracolair.games.applestoapples.commands.CmdJoin;
import net.dracolair.games.applestoapples.commands.CmdList;
import net.dracolair.games.applestoapples.commands.CmdPlay;
import net.dracolair.games.applestoapples.commands.CmdStart;
import net.dracolair.games.applestoapples.commands.Command;

import org.jibble.pircbot.PircBot;

import static net.dracolair.games.applestoapples.Factories.*;

public class Bot extends PircBot {

	public Map<String, ApplesToApples>	m_chanToGameMap = new LinkedHashMap<String, ApplesToApples>();
	public Map<Name, ApplesToApples>	m_nameToGameMap = new LinkedHashMap<Name, ApplesToApples>();
	public Map<String, Command> 		m_chanCommands = new LinkedHashMap<String, Command>();
	public Map<String, Command> 		m_privCommands = new LinkedHashMap<String, Command>();
	public Map<String, Name> 			m_nickToNameMap = new LinkedHashMap<String, Name>();

	public Bot(String name) {
		this.setName(name);
		
		m_chanCommands.put("join", new CmdJoin());
		m_chanCommands.put("list", new CmdList());
		m_chanCommands.put("start", new CmdStart());
		m_chanCommands.put("play", new CmdPlay());
		m_chanCommands.put("choose", new CmdChoose());
		m_chanCommands.put("botplay", new BotCmdPlay());
		m_chanCommands.put("botchoose", new BotCmdChoose());
	}
	
	public void onMessage(String channel, 
						  String sender, 
						  String login, 
						  String hostname, 
						  String message) {
		MessageMap msgMap = MSGMAP(channel, sender, login, hostname, message);
		for(Message response : handleChanMessage(msgMap)) {
			sendMessage(response.m_target, response.m_message);
		}
	}

	public List<Message> handleChanMessage(MessageMap msgMap) {
		List<Message> responses = new LinkedList<Message>();
		String[] parsedMessage = msgMap.MESSAGE.split(" ", 2);
		String cmdKey = parsedMessage[0].substring(1);
		MessageMap modMsgMap = msgMap.clone();
		if (parsedMessage.length < 2) {
			modMsgMap.MESSAGE = "";
		} else {
			modMsgMap.MESSAGE = parsedMessage[1];
		}
		Command cmd = m_chanCommands.get(cmdKey);
		
		if (cmd != null) {
			responses = cmd.execute(this, modMsgMap);
		}

		return responses;
	}

	public ApplesToApples getGameByChan(String channel) {
		return m_chanToGameMap.get(channel);
	}
	
	public ApplesToApples getGameByNick(String nick) {
		return m_nameToGameMap.get(m_nickToNameMap.get(nick));
	}
	
}
