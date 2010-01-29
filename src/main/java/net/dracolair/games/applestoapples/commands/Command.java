package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;

public abstract class Command {
	
	public List<Message> execute(Bot bot, MessageMap msgMap) {
		List<Message> responses = new LinkedList<Message>();
		this.run(bot, bot.getGameByChan(msgMap.CHANNEL), msgMap, responses);
		return responses;
	}
	
	public abstract void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses);
}
