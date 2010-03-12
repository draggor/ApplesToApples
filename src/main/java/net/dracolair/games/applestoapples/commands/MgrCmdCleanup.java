package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdCleanup extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		ata.rotatePlayers();
		ata.m_cards.clear();
		for(String delayCmd : ata.m_delayedCommands) {
			responses.add(MSG(gameManager.getName(), delayCmd));
		}
		ata.m_delayedCommands.clear();
		responses.add(MSG(gameManager.getName(), "!botplay " + msgInfo.MESSAGE));
	}

}
