package net.dracolair.games.applestoapples.commands;

import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.MessageMap;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdStart extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		if(ata == null || ata.m_players.size() < 3) {
			responses.add(MSG(msgMap.CHANNEL, msgMap.NICK + 
					                          " is fail, needs " + 
					                          (ata==null?3:(3-ata.m_players.size())) + 
					                          " to play."));
		} else {
			responses.add(MSG(msgMap.CHANNEL, "We have >=3 players, the game will begin!"));
			responses.add(MSG(msgMap.CHANNEL, "Dealing out cards..."));
			for(Entry<Name, Player> e : ata.m_players.entrySet()) {
				ata.m_activePlayers.add(e.getKey());
				responses.add(MSG(bot.getName(), "!botdeal7 " + e.getKey()));
			}
			ata.m_state = State.PLAY;
			responses.add(MSG(bot.getName(), "!botplay"));
		}
	}

}
