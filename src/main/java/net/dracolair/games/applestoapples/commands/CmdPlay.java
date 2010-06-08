package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.card.Card;

import static net.dracolair.games.applestoapples.Factories.*;
import static net.dracolair.games.applestoapples.Lookup.*;

public class CmdPlay extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		try {
			int cardIndex = Integer.parseInt(msgInfo.MESSAGE) - 1;
			
			if(cardIndex < 8 && cardIndex >= 0) {
				Name n = gameManager.m_nickToNameMap.get(msgInfo.NICK);
				ata.m_waiting.remove(n);
				Player p = ata.m_players.get(n);
				Card oldCard = getCard(ata, n);
				Card c = p.m_redCards.remove(cardIndex);
				if(oldCard == null) {
					Card newCard = ata.getNextRed(); //m_redCards.remove(0);
					p.m_redCards.add(newCard);
					responses.add(MSG(msgInfo.NICK, newCard.toFormattedString()));
				} else {
					p.m_redCards.add(oldCard);
				}
				c.m_playedBy = n;
				ata.m_cards.add(c);
				if(ata.m_waiting.isEmpty()) {
					ata.m_state = State.LOCK;
					responses.add(MSG(gameManager.getName(), "!botchoose " + msgInfo.ROOM));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Card getCard(Game ata, Name nick) {
		Card c = null;
		
		for(Card card : ata.m_cards) {
			if(card.m_playedBy.equals(nick)) {
				c = card;
			}
		}
		
		if(c != null) {
			ata.m_cards.remove(c);
		}
		
		return c;
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		requirements.add(REQ(ata.m_state == State.PLAY, BLANK_MESSAGE));
		requirements.add(REQ(ata.m_judge != gameManager.m_nickToNameMap.get(msgInfo.NICK), BLANK_MESSAGE));
		requirements.add(REQ(ata.m_waiting.contains(gameManager.m_nickToNameMap.get(msgInfo.NICK)), BLANK_MESSAGE));
	}
}
