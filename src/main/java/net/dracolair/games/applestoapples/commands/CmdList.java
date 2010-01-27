package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.MessageMap;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdList extends Command {
	
	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap) {
		if(ata != null) {
			m_responses.add(MSG(msgMap.NICK, msgMap.NICK + ": List of players: " + ata.playersNscores()));
		} else {
			m_responses.add(MSG(msgMap.NICK, msgMap.NICK + ": No game is running."));
		}
	}

}
