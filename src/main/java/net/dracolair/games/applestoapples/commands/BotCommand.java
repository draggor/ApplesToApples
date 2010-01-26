package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;

import static net.dracolair.games.applestoapples.MessageMap.*;

public abstract class BotCommand extends Command {
	
	@Override
	public List<Message> execute(Bot bot, String[] msgMap) {
		responses = new LinkedList<Message>();
		if(bot.getName() == NAME(msgMap)) {
			this.run(bot, bot.getGame(CHANNEL(msgMap)), msgMap);
		}
		return responses;
	}

}
