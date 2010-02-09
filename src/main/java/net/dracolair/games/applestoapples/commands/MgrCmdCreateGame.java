package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdCreateGame extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		boolean isRandom = Boolean.parseBoolean(msgInfo.MESSAGE);
		if(msgInfo.MESSAGE.equals("")) {
			isRandom = true;
		}
		ata = GAME(GameManager.RED, GameManager.GREEN, isRandom);
		gameManager.m_roomToGameMap.put(msgInfo.ROOM, ata);
	}
	
}
