package net.dracolair.games.applestoapples.commands;

import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.Requirement;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdStart extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgMap, List<Message> responses) {
		responses.add(MSG(msgMap.ROOM, "We have >=3 players, the game will begin!"));
		responses.add(MSG(msgMap.ROOM, "Dealing out cards..."));
		for(Entry<Name, Player> e : ata.m_players.entrySet()) {
			responses.add(MSG(gameManager.getName(), "!botdeal7 " + e.getKey()));
		}
		ata.m_state = State.PLAY;
		responses.add(MSG(gameManager.getName(), "!botplay"));
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgMap, List<Requirement> requirements) {
		boolean cond1 = ata.m_state == State.BEGIN;
		Message msg1 = MSG(msgMap.ROOM, "A game has already begun!");
		
		boolean cond2 = ata.m_players.size() >= 3;
		Message msg2 = MSG(msgMap.ROOM, msgMap.NICK + 
					                    " is fail, needs " + 
					                    (3-ata.m_players.size()) + 
					                    " to play.");
		
		requirements.add(REQ(cond1, msg1));
		requirements.add(REQ(cond2, msg2));
	}

}
