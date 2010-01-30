package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdDeal7 extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgMap, List<Message> responses) {
		Player p = ata.m_players.get(gameManager.m_nickToNameMap.get(msgMap.MESSAGE));
		for(int c = 0; c < 7; c++) {
			Card card = ata.getRandomCard();
			p.m_cards.add(card);
			responses.add(MSG(msgMap.MESSAGE, card.toString()));
		}
	}

}
