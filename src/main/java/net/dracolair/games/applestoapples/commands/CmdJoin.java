package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.Name;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdJoin extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		if(ata == null) {
			ata = new ApplesToApples();
		}
		if(bot.m_nickToNameMap.get(msgMap.NICK) != null) {
			responses.add(MSG(msgMap.CHANNEL, msgMap.NICK + " is already playing."));
		} else {
			Name n = NAME(msgMap.NICK);
			bot.m_chanToGameMap.put(msgMap.CHANNEL, ata);
			bot.m_nameToGameMap.put(n, ata);
			bot.m_nickToNameMap.put(msgMap.NICK, n);
			ata.addPlayer(n);
			
			responses.add(MSG(msgMap.CHANNEL, msgMap.NICK + " has joined the game, need 2 more to start."));
		}
	}

}
