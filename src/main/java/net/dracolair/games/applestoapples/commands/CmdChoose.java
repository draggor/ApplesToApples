package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.Requirement;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdChoose extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgMap, List<Message> responses) {
		int cardIndex = Integer.parseInt(msgMap.MESSAGE) - 1;
		
		if(cardIndex >= 0 && cardIndex < ata.m_cards.size()) {
			Card winner = ata.m_cards.get(cardIndex);
			Player p = ata.m_players.get(winner.m_playedBy);
			p.m_score++;
			responses.add(MSG(msgMap.ROOM, "The winner is " + winner.m_playedBy + ": " + winner + "!"));
			responses.add(MSG(msgMap.ROOM, "Scores: " + ata.playersNscores()));
			responses.add(MSG(gameManager.getName(), "!botcleanup"));
		}
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgMap, List<Requirement> requirements) {
		requirements.add(REQ(ata.m_state == State.CHOOSE, MSG("", "")));
	}
	
}
