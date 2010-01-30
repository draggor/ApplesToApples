package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

public abstract class ManagerCommand extends Command {
	
	@Override
	public List<Message> execute(GameManager gameManager, MessageInfo msgMap) {
		List<Message> responses = new LinkedList<Message>();
		if(gameManager.getName().equals(msgMap.NICK)) {
			this.run(gameManager, gameManager.getGameByChan(msgMap.ROOM), msgMap, responses);
		}
		return responses;
	}

}
