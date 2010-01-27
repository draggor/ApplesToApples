package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;

public abstract class Command {
	
	protected List<Message> m_responses = null;
	
	public List<Message> execute(Bot bot, MessageMap msgMap) {
		m_responses = new LinkedList<Message>();
		this.run(bot, bot.getGameByChan(msgMap.CHANNEL), msgMap);
		return m_responses;
	}
	
	public abstract void run(Bot bot, ApplesToApples ata, MessageMap msgMap);
}
