package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.card.Card;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdDeal7 extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		Name name = gameManager.m_nickToNameMap.get(msgInfo.ROOM);
		Player p = ata.m_players.get(name);
		for(int c = 0; c < 7; c++) {
			Card card = ata.m_redCards.remove(0);
			p.m_redCards.add(card);
			responses.add(MSG(msgInfo.ROOM, card.toFormattedString()));
		}
	}
	
}
