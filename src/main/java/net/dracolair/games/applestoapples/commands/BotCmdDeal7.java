package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class BotCmdDeal7 extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		Player p = ata.m_players.get(bot.m_nickToNameMap.get(msgMap.MESSAGE));
		for(int c = 0; c < 7; c++) {
			Card card = ata.getRandomCard();
			p.m_cards.add(card);
			responses.add(MSG(msgMap.MESSAGE, card.toString()));
		}
	}

}
