package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.MSG;
import static net.dracolair.games.applestoapples.Factories.REQ;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

public class CmdAway extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		ata.m_activePlayers.remove(gameManager.m_nickToNameMap.get(msgInfo.NICK));
		
		responses.add(MSG(msgInfo.ROOM, msgInfo.NICK + " has been marked as away.  Use !back to rejoin."));
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		super.getRequirements(gameManager, ata, msgInfo, requirements);
		boolean req1 = gameManager.m_nickToNameMap.containsKey(msgInfo.NICK);
		Message msg1 = MSG(msgInfo.ROOM, msgInfo.NICK + ": You're not playing!");
		boolean req2 = ata.m_activePlayers.contains(gameManager.m_nickToNameMap.get(msgInfo.NICK));
		Message msg2 = MSG(msgInfo.ROOM, msgInfo.NICK + ": You're already away!");
		
		requirements.add(REQ(req1, msg1));
		requirements.add(REQ(req2, msg2));
	}
	
}
