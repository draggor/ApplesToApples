package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

import static net.dracolair.games.applestoapples.Factories.*;
import static net.dracolair.games.applestoapples.MessageMap.*;

public class BotCmdPlay extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		responses.add(MSG(CHANNEL(msgMap), "bob is the judge.  Green card is: hax"));
		responses.add(MSG(CHANNEL(msgMap), "Waiting for players to play cards..."));
	}

}
