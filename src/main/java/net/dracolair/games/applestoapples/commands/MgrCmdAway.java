package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdAway extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		ata.m_warning = false;
		if(ata.m_state == State.PLAY) {
			StringBuilder b = new StringBuilder();
			for(Name name : ata.m_waiting) {
				ata.m_activePlayers.remove(name);
				b.append(name);
				b.append(", ");
			}
			ata.m_waiting.clear();
			b.append("you're being flagged as away.  Use !back to rejoin.");
			responses.add(MSG(msgInfo.MESSAGE, b.toString()));
			ata.m_state = State.LOCK;
			responses.add(MSG(msgInfo.NICK, "!botchoose " + msgInfo.MESSAGE));
		} else {
			responses.add(MSG(msgInfo.MESSAGE, ata.m_judge + ", you're being flagged as away.  Use !back to rejoin."));
			ata.m_state = State.LOCK;
			responses.add(MSG(msgInfo.NICK, "!botcleanup " + msgInfo.MESSAGE));
		}
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean req = (ata.m_state == State.PLAY && !ata.m_waiting.isEmpty()) || ata.m_state == State.CHOOSE;
		
		requirements.add(REQ(req, MSG("", "")));
		requirements.add(REQ(ata.m_warning, MSG("", "")));
	}
	
}
