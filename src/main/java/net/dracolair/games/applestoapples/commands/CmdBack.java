package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdBack extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		Name name = gameManager.m_nickToNameMap.get(msgInfo.NICK);
		ata.m_activePlayers.add(name);
		responses.add(MSG(msgInfo.ROOM, "Welcome back " + name + "!  You'll be in next round."));
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean isPlayer = gameManager.m_nickToNameMap.containsKey(msgInfo.NICK);
		Name name = gameManager.m_nickToNameMap.get(msgInfo.NICK);
		boolean isAway = !ata.m_activePlayers.contains(name);
		requirements.add(REQ(isPlayer, MSG("", "")));
		requirements.add(REQ(isAway, MSG("", "")));
	}
	
}
