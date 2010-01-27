package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.MessageMap.*;

public class BotCmdState extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		ata.m_state = State.valueOf(MESSAGE(msgMap));
	}

}
