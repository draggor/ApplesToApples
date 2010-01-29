package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageMap;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdList extends Command {
	
	@Override
	public void run(Bot bot, ApplesToApples ata, MessageMap msgMap, List<Message> responses) {
		if(ata != null) {
			responses.add(MSG(msgMap.NICK, msgMap.NICK + ": List of players: " + ata.playersNscores()));
		} else {
			responses.add(MSG(msgMap.NICK, msgMap.NICK + ": No game is running."));
		}
	}

}
