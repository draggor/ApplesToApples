package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

import static net.dracolair.games.applestoapples.MessageMap.*;
import static net.dracolair.games.applestoapples.Message.*;

public class CmdJoin extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		if(ata == null) {
			ata = new ApplesToApples();
		}
		if(bot.m_playerList.get(NAME(msgMap)) != null) {
			responses.add(MSG(CHANNEL(msgMap), NAME(msgMap) + " is already playing."));
		} else {
			bot.m_gameList.put(CHANNEL(msgMap), ata);
			bot.m_playerList.put(NAME(msgMap), ata);
			ata.addPlayer(NAME(msgMap));
			
			responses.add(MSG(CHANNEL(msgMap), NAME(msgMap) + " has joined the game, need 2 more to start."));
		}
	}

}
