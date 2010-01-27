package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdChoose extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap) {
		int cardIndex = Integer.parseInt(msgMap.MESSAGE) - 1;
		
		if(cardIndex >= 0 && cardIndex < ata.m_cards.size()) {
			Card winner = ata.m_cards.get(cardIndex);
			Player p = ata.m_players.get(winner.m_playedBy);
			p.m_score++;
			m_responses.add(MSG("#channel", "The winner is " + winner.m_playedBy + ": " + winner + "!"));
			m_responses.add(MSG("#channel", "Scores: " + ata.playersNscores()));
			m_responses.add(MSG("bees", "!botplay"));
		}
	}
	
}
