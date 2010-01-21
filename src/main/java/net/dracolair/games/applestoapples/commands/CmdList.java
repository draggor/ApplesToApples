package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

public class CmdList extends Command {
	
	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		responses.add(msgMap[1]);
		if(ata != null) {
			responses.add("bob: A game is already running, type !join to play!");
			responses.add("bob: List of players:");
			responses.add("bob: 1) Neel");
		} else {
			responses.add("bob: No game is running.");
		}
	}

}
