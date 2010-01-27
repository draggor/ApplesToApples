package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.State;

public class BotCmdState extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap) {
		ata.m_state = State.valueOf(msgMap.MESSAGE);
	}

}
