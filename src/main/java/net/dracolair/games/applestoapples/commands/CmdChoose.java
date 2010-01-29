package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdChoose extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		int cardIndex = Integer.parseInt(msgMap.MESSAGE) - 1;
		
		if(cardIndex >= 0 && cardIndex < ata.m_cards.size()) {
			Card winner = ata.m_cards.get(cardIndex);
			Player p = ata.m_players.get(winner.m_playedBy);
			p.m_score++;
			responses.add(MSG(msgMap.CHANNEL, "The winner is " + winner.m_playedBy + ": " + winner + "!"));
			responses.add(MSG(msgMap.CHANNEL, "Scores: " + ata.playersNscores()));
			responses.add(MSG(bot.getName(), "!botplay"));
		}
	}
	
}
