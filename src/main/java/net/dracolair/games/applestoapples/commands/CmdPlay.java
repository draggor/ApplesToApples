package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdPlay extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		try {
			int cardIndex = Integer.parseInt(msgMap.MESSAGE) - 1;
			
			if(cardIndex < 8 && cardIndex > 0) {
				Name n = bot.m_nickToNameMap.get(msgMap.NICK);
				ata.m_waiting.remove(n);
				Player p = ata.m_players.get(n);
				Card c = p.m_cards.remove(cardIndex);
				c.m_playedBy = n;
				ata.m_cards.add(c);
				responses.add(MSG(msgMap.NICK, "Card - 8"));

				if(ata.m_waiting.isEmpty()) {
					responses.add(MSG(bot.getName(), "!botchoose"));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
