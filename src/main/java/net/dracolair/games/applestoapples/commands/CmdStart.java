package net.dracolair.games.applestoapples.commands;

import java.util.Map.Entry;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;
import static net.dracolair.games.applestoapples.MessageMap.*;

public class CmdStart extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		if(ata == null || ata.m_players.size() < 3) {
			responses.add(MSG(CHANNEL(msgMap), NAME(msgMap) + 
					                          " is fail, needs " + 
					                          (ata==null?3:(3-ata.m_players.size())) + 
					                          " to play."));
		} else {
			responses.add(MSG(CHANNEL(msgMap), "We have >=3 players, the game will begin!"));
			responses.add(MSG(CHANNEL(msgMap), "Dealing out cards..."));
			for(Entry<String, Player> e : ata.m_players.entrySet()) {
				ata.m_waiting.put(e.getKey(), e.getValue());
				for(int i = 1; i < 8; i++) {
					Card c = CARD("Card", String.valueOf(i));
					e.getValue().m_cards.add(c);
					responses.add(MSG(e.getKey(), c.toString()));
				}
			}
			ata.m_waiting.remove("bob");
			ata.m_state = State.PLAY;
			responses.add(MSG(bot.getName(), "!botplay"));
//			responses.add(MSG(CHANNEL(msgMap), "bob is the judge.  Green card is: hax"));
//			responses.add(MSG(CHANNEL(msgMap), "Waiting for players to play cards..."));
		}
	}

}
