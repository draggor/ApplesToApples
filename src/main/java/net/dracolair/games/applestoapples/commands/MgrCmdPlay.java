package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdPlay extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgMap, List<Message> responses) {
		ata.m_waiting.addAll(ata.m_activePlayers);
		ata.m_judge = ata.m_waiting.remove(0);
		
		responses.add(MSG(msgMap.ROOM, ata.m_judge + " is the judge.  Green card is: hax"));
		responses.add(MSG(msgMap.ROOM, "Waiting for players to play cards..."));
	}

}
