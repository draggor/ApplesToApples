package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Requirement;

import static net.dracolair.games.applestoapples.Factories.*;

public abstract class ManagerCommand extends Command {

	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		super.getRequirements(gameManager, ata, msgInfo, requirements);
		requirements.add(REQ(gameManager.getName().equals(msgInfo.NICK), MSG(msgInfo.NICK, "You're not authorized to run this command")));
	}
	
}