package net.dracolair.games.applestoapples.commands;

import java.util.Map.Entry;

import net.dracolair.games.applestoapples.ApplesToApples;
import net.dracolair.games.applestoapples.Bot;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.MessageMap.*;
import static net.dracolair.games.applestoapples.Message.*;

public class CmdList extends Command {
	
	@Override
	public void run(Bot bot, ApplesToApples ata, String[] msgMap) {
		if(ata != null) {
			StringBuilder builder = new StringBuilder();
			for(Entry<String, Player> e : ata.m_players.entrySet()) {
				builder.append(e.getKey());
				builder.append(":");
				builder.append(e.getValue().m_score);
				builder.append(" ");
			}
			responses.add(MSG(NAME(msgMap), NAME(msgMap) + ": List of players: " + builder.toString()));
		} else {
			responses.add(MSG(NAME(msgMap), NAME(msgMap) + ": No game is running."));
		}
	}

}
