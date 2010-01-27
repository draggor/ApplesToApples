package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Name;

import static net.dracolair.games.applestoapples.MessageMap.*;
import static net.dracolair.games.applestoapples.Factories.*;

public class CmdJoin extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		if(ata == null) {
			ata = new ApplesToApples();
		}
		if(bot.m_nickToNameMap.get(NICK(msgMap)) != null) {
			m_responses.add(MSG(CHANNEL(msgMap), NICK(msgMap) + " is already playing."));
		} else {
			Name n = NAME(NICK(msgMap));
			bot.m_chanToGameMap.put(CHANNEL(msgMap), ata);
			bot.m_nameToGameMap.put(n, ata);
			bot.m_nickToNameMap.put(NICK(msgMap), n);
			ata.addPlayer(n);
			
			m_responses.add(MSG(CHANNEL(msgMap), NICK(msgMap) + " has joined the game, need 2 more to start."));
		}
	}

}
