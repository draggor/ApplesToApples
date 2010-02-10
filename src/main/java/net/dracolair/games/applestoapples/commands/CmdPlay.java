package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.Requirement;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.card.Card;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdPlay extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		try {
			int cardIndex = Integer.parseInt(msgInfo.MESSAGE) - 1;
			
			if(cardIndex < 8 && cardIndex >= 0) {
				Name n = gameManager.m_nickToNameMap.get(msgInfo.NICK);
				ata.m_waiting.remove(n);
				Player p = ata.m_players.get(n);
				Card c = p.m_redCards.remove(cardIndex);
				Card newCard = ata.m_redCards.remove(0);
				p.m_redCards.add(newCard);
				c.m_playedBy = n;
				ata.m_cards.add(c);
				responses.add(MSG(msgInfo.NICK, newCard.toString()));
				if(ata.m_waiting.isEmpty()) {
					responses.add(MSG(gameManager.getName(), "!botchoose " + msgInfo.ROOM));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		requirements.add(REQ(ata.m_state == State.PLAY, MSG("", "")));
		requirements.add(REQ(ata.m_judge != gameManager.m_nickToNameMap.get(msgInfo.NICK), MSG("", "")));
	}
}
