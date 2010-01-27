package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.MSG;
import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.State;

public class BotCmdChoose extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap) {
		ata.m_state = State.CHOOSE;		
		
		m_responses.add(MSG("#channel", "The green card is: hax"));
		
		int cnt = 1;
		for(Card c : ata.m_cards) {
			m_responses.add(MSG("#channel", cnt++ + ". " + c));
		}
		
		m_responses.add(MSG("#channel", ata.m_judge + " must choose a red card!  Type '!choose number'"));
	}

}
