package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdCustomRed extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		ata.m_state = State.CUSTOMRED;
		responses.add(MSG(msgInfo.ROOM, "It's custom RED card time!  " + ata.m_winnerRed.m_playedBy + " needs to make a custom card!"));
		responses.add(MSG(msgInfo.ROOM, "Type /msg bees !custom cardname - description"));
	}

}
