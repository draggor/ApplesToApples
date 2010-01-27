package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdChoose extends Command {

	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		m_responses.add(MSG("#channel", "The winner is grue: Card 4!"));
		m_responses.add(MSG("#channel", "Scores: bob:0 neel:0 grue:1 "));
		m_responses.add(MSG("#channel", "neel is the judge.  Green card is: hax"));
		m_responses.add(MSG("#channel", "Waiting for players to play cards..."));
	}
	
}
