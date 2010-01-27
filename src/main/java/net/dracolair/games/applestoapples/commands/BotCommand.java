package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;

public abstract class BotCommand extends Command {
	
	@Override
	public List<Message> execute(Bot bot, MessageMap msgMap) {
		m_responses = new LinkedList<Message>();
		if(bot.getName().equals(msgMap.NICK)) {
			this.run(bot, bot.getGameByChan(msgMap.CHANNEL), msgMap);
		}
		return m_responses;
	}

}
