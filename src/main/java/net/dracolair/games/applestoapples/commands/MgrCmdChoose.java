package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.*;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.State;

public class MgrCmdChoose extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgMap, List<Message> responses) {
		ata.m_state = State.CHOOSE;		
		
		responses.add(MSG(msgMap.ROOM, "The green card is: hax"));
		
		for(int cnt = 0; cnt < ata.m_cards.size(); cnt++) {
			responses.add(MSG(msgMap.ROOM, (cnt + 1) + ". " + ata.m_cards.get(cnt)));
		}
		
		responses.add(MSG(msgMap.ROOM, ata.m_judge + " must choose a red card!  Type '!choose number'"));
	}
	
}
