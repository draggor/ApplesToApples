package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.MSG;
import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

public class BotCmdChoose extends BotCommand {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		m_responses.add(MSG("#channel", "The green card is: hax"));
		m_responses.add(MSG("#channel", "1. Card 5"));
		m_responses.add(MSG("#channel", "2. Card 4"));
		m_responses.add(MSG("#channel", "bob must choose a red card!  Type '!choose number'"));
	}

}
