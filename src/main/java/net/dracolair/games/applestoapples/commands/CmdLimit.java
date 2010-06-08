package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdLimit extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		ata.m_limit = Integer.parseInt(msgInfo.MESSAGE);
		responses.add(MSG(msgInfo.ROOM, "Number of green cards to win: " + ata.m_limit));
	}

}
