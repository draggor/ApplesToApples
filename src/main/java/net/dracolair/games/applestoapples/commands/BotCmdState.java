package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.State;

public class BotCmdState extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		ata.m_state = State.valueOf(msgMap.MESSAGE);
	}

}
