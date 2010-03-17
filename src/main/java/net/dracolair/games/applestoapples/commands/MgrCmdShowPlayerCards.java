package net.dracolair.games.applestoapples.commands;

import static net.dracolair.games.applestoapples.Factories.MSG;

import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;

public class MgrCmdShowPlayerCards extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo,	List<Message> responses) {
		for(Entry<Name, Player> e : ata.m_players.entrySet()) {
			StringBuilder b = new StringBuilder();
			b.append("Red Cards:");
			for(int i = 0; i < 7; i++) {
				b.append(" ");
				b.append((i+1));
				b.append(". ");
				b.append(e.getValue().m_redCards.get(i).getFormattedName());
			}
			responses.add(MSG(e.getKey().toString(), b.toString()));
		}
	}

}
