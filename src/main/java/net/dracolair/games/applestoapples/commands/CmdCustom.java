package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.card.Card;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdCustom extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		String[] sp = msgInfo.MESSAGE.split("-", 2);
		Card customCard = CARD(sp[0].trim(), sp[1].trim(), gameManager.m_redCardRenderer);
		ata.m_customRedCards.add(customCard);
		
		responses.add(MSG(msgInfo.ROOM, "Thanks for the card!"));
		responses.add(MSG(gameManager.m_name, "!botplay " + msgInfo.ROOM));
	}

}
