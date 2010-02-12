package net.dracolair.games.applestoapples.commands;

import java.util.List;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.Name;
import net.dracolair.games.applestoapples.Player;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdPlay extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		ata.m_state = State.PLAY;
		ata.m_waiting.addAll(ata.m_activePlayers);
		ata.m_judge = ata.m_waiting.remove(0);
		
		ata.m_greenCard = ata.m_greenCards.remove(0);
		
		responses.add(MSG(msgInfo.MESSAGE, ata.m_judge + " is the judge.  Green card is: " + ata.m_greenCard.toFormattedString()));
		responses.add(MSG(msgInfo.MESSAGE, "Waiting for players to play cards..."));
		
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
