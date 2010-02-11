package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.card.Card;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdChoose extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		int cardIndex = Integer.parseInt(msgInfo.MESSAGE) - 1;
		
		if(cardIndex >= 0 && cardIndex < ata.m_cards.size()) {
			Card winner = ata.m_cards.get(cardIndex);
			ata.m_cards.clear();
			Player p = ata.m_players.get(winner.m_playedBy);
			p.m_greenCards.add(ata.m_greenCard);
			
			if(++p.m_score == ata.m_limit) {
				responses.add(MSG(gameManager.getName(), "!botendgame " + msgInfo.ROOM));
			} else {
				responses.add(MSG(msgInfo.ROOM, "The winner is " + winner.m_playedBy + ": " + winner.m_name + "!"));
				responses.add(MSG(msgInfo.ROOM, "Scores: " + ata.playersNscores()));
				responses.add(MSG(gameManager.getName(), "!botcleanup " + msgInfo.ROOM));
			}
		}
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		requirements.add(REQ(ata.m_state == State.CHOOSE, MSG("", "")));
	}
	
}
