package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;
import static net.dracolair.games.applestoapples.MessageMap.*;

public class CmdPlay extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		try {
			int num = Integer.parseInt(MESSAGE(msgMap));
			
			if(num < 8 && num > 0) {
				Player p = ata.m_waiting.remove(NAME(msgMap));
				Card c = p.m_cards.remove(num);
				ata.m_cards.add(c);
				responses.add(MSG(NAME(msgMap), "Card - 8"));

				if(ata.m_waiting.isEmpty()) {
					responses.add(MSG(bot.getName(), "!botchoose"));
//					responses.add(MSG("#channel", "The green card is: hax"));
//					responses.add(MSG("#channel", "1. Card 5"));
//					responses.add(MSG("#channel", "2. Card 4"));
//					responses.add(MSG("#channel", "bob must choose a red card!  Type '!choose number'"));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
