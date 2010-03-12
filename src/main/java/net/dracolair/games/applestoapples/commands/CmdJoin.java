package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdJoin extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		Name n = NAME(msgInfo.NICK);
		gameManager.m_nameToGameMap.put(n, ata);
		gameManager.m_nickToNameMap.put(msgInfo.NICK, n);
		ata.addPlayer(n);
		if(ata.m_state == State.BEGIN) {
			responses.add(MSG(msgInfo.ROOM, msgInfo.NICK + " has joined the game, need "+ (3-ata.m_players.size()) + " more to start."));
		} else {
			responses.add(MSG(msgInfo.ROOM, msgInfo.NICK + " has joined the game!  Cards will be dealt at the start of the next round."));
			responses.add(MSG(gameManager.getName(), "!botdelaycmd " + msgInfo.ROOM + " !botdeal7 " + msgInfo.NICK));
		}
	}

	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean cond = (gameManager.m_nickToNameMap.get(msgInfo.NICK) == null);
		Message msg = MSG(msgInfo.ROOM, msgInfo.NICK + " is already playing.");
		requirements.add(REQ(cond, msg));
	}
}
