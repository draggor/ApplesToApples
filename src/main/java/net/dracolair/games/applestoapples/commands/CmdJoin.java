package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Requirement;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdJoin extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		Name n = NAME(msgInfo.NICK);
		gameManager.m_nameToGameMap.put(n, ata);
		gameManager.m_nickToNameMap.put(msgInfo.NICK, n);
		ata.addPlayer(n);
		
		responses.add(MSG(msgInfo.ROOM, msgInfo.NICK + " has joined the game, need 2 more to start."));
	}

	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean cond = (gameManager.m_nickToNameMap.get(msgInfo.NICK) == null);
		Message msg = MSG(msgInfo.ROOM, msgInfo.NICK + " is already playing.");
		requirements.add(REQ(cond, msg));
	}
}
