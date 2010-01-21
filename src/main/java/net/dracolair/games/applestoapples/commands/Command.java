package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

public abstract class Command {
	
	protected List<String> responses = null;
	
	public final List<String> execute(Bot bot, String[] msgMap) {
		responses = new LinkedList<String>();
		this.run(bot, bot.getGame(msgMap[0]), msgMap);
		return responses;
	}
	
	public abstract void run(Bot bot, ApplesToApples ata, String[] msgMap);
}
