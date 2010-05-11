package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdHelp extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		if(msgInfo.MESSAGE.equals("")) {
			responses.add(MSG(msgInfo.NICK, "Use !help <command>, available commands: away back choose join limit list play start"));
		} else {
			
		}
	}
	
	
	
}
