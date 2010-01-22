package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;

import static net.dracolair.games.applestoapples.MessageMap.*;

public abstract class Command {
	
	protected List<Message> responses = null;
	
	public final List<Message> execute(Bot bot, String[] msgMap) {
		responses = new LinkedList<Message>();
		this.run(bot, bot.getGame(CHANNEL(msgMap)), msgMap);
		return responses;
	}
	
	public abstract void run(Bot bot, ApplesToApples ata, String[] msgMap);
}
