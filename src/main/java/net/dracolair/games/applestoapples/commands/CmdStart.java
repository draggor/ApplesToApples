package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

public class CmdStart extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		responses.add(msgMap[0]);
		if(ata == null || ata.m_players.size() < 3) {
			responses.add("bob is fail, needs " + (ata==null?3:(3-ata.m_players.size())) + " to play.");
		}
	}

}
