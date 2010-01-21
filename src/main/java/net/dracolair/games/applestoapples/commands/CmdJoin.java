package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

public class CmdJoin extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		responses.add(msgMap[0]);
		if(ata == null) {
			ata = new ApplesToApples();
		}
		if(bot.m_playerList.get(msgMap[1]) != null) {
			responses.add("bob is already playing.");
		} else {
			bot.m_gameList.put(msgMap[0], ata);
			bot.m_playerList.put(msgMap[1], ata);
			ata.m_players.put(msgMap[1], null);
			
			responses.add("bob has joined the game.");
			responses.add("Need 2 more to start.");
		}
	}

}
