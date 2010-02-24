package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.*;

import java.util.Collections;
import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.State;

public class MgrCmdChoose extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		ata.m_state = State.CHOOSE;		
		
		if(ata.m_isRandom) {
			Collections.shuffle(ata.m_cards);
		}
		
		responses.add(MSG(msgInfo.MESSAGE, "The green card is: " + ata.m_greenCard.toFormattedString()));
		
		for(int cnt = 0; cnt < ata.m_cards.size(); cnt++) {
			responses.add(MSG(msgInfo.MESSAGE, (cnt + 1) + ". " + ata.m_cards.get(cnt).toFormattedString()));
		}
		
		responses.add(MSG(msgInfo.MESSAGE, ata.m_judge + " must choose a red card!  Type '!choose number'"));
		ata.m_time = System.currentTimeMillis();
	}
	
}
