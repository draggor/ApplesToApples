package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;
import static net.dracolair.games.applestoapples.Lookup.*;

public class MgrCmdWarning extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		ata.m_warning = true;
		if(ata.m_state == State.PLAY) {
			StringBuilder b = new StringBuilder();
			for(Name name : ata.m_waiting) {
				b.append(name);
				b.append(", ");
			}
			b.append("play a card or be marked as away!");
			responses.add(MSG(msgInfo.MESSAGE, b.toString()));
		} else {
			responses.add(MSG(msgInfo.MESSAGE, ata.m_judge + ", choose a card or be marked away!"));
		}
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean req = (ata.m_state == State.PLAY && !ata.m_waiting.isEmpty()) || ata.m_state == State.CHOOSE;
		
		requirements.add(REQ(req, BLANK_MESSAGE));
		requirements.add(REQ(!ata.m_warning, BLANK_MESSAGE));
	}

}
