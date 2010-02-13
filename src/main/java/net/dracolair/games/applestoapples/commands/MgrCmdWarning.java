package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdWarning extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		StringBuilder b = new StringBuilder();
		for(Name name : ata.m_waiting) {
			b.append(name);
			b.append(", ");
		}
		b.append("play a card or be marked as away!");
		responses.add(MSG(msgInfo.MESSAGE, b.toString()));
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		requirements.add(REQ(!ata.m_waiting.isEmpty(), MSG("", "")));
	}

}
