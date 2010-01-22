package net.dracolair.games.applestoapples.commands;

import java.util.Map.Entry;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Message.*;
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
				for(int i = 1; i < 8; i++) {
					responses.add(MSG(e.getKey(), "Card " + i));
				}
			}
			responses.add(MSG(CHANNEL(msgMap), "bob is the judge.  Green card is hax"));
			responses.add(MSG(CHANNEL(msgMap), "Waiting for players to play cards..."));
		}
	}

}
