package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.MSG;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Card;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;
import net.dracolair.games.applestoapples.State;

public class BotCmdChoose extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		ata.m_state = State.CHOOSE;		
		
		responses.add(MSG(msgMap.CHANNEL, "The green card is: hax"));
		
		int cnt = 1;
		for(Card c : ata.m_cards) {
			responses.add(MSG(msgMap.CHANNEL, cnt++ + ". " + c));
		}
		
		responses.add(MSG(msgMap.CHANNEL, ata.m_judge + " must choose a red card!  Type '!choose number'"));
	}

}
