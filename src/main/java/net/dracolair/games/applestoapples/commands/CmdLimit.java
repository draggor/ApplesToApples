package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

public class CmdLimit extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		ata.m_limit = Integer.parseInt(msgInfo.MESSAGE);
	}

}
