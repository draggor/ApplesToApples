package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.MessageMap.*;

public class CmdState extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		if(bot.getName() == NAME(msgMap)) {
			ata.m_state = State.valueOf(MESSAGE(msgMap));
		}
	}

}
