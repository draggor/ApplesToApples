package net.dracolair.games.applestoapples.commands;

import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdEndGame extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		Name winner = ata.getWinner();
		
		responses.add(MSG("#channel", "GAME OVER!  The winner is " + winner));
		for(Entry<Name, Player> e : ata.m_players.entrySet()) {
			responses.add(MSG("#channel", e.getKey() + " is: " + e.getValue().greenCards()));
		}
		
		ata = null;
	}

}
