package net.dracolair.games.applestoapples;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static net.dracolair.games.applestoapples.Factories.*;

public class Game {
	
	public Map<Name, Player>	m_players = 		new LinkedHashMap<Name, Player>();
	public List<Card> 			m_cards = 			new LinkedList<Card>();
	public List<Name>			m_waiting = 		new LinkedList<Name>();
	public State				m_state = 			State.BEGIN;
	public List<Name>			m_activePlayers =	new LinkedList<Name>();
	public Name					m_judge = 			null;
	
	public void addPlayer(Name name) {
		m_players.put(name, new Player());
		m_activePlayers.add(name);
	}
	
	public String playersNscores() {
		StringBuilder builder = new StringBuilder();
		
		for(Entry<Name, Player> e : m_players.entrySet()) {
			builder.append(e.getKey());
			builder.append(":");
			builder.append(e.getValue().m_score);
			builder.append(" ");
		}
		
		return builder.toString();
	}
	
	int cardcnt = 1;
	
	public Card getRandomCard() {
		return CARD("Card", String.valueOf(cardcnt++));
	}
	
	public void rotatePlayers() {
		List<Name> names = new LinkedList<Name>(m_players.keySet());
		Player temp = m_players.remove(names.get(0));
		m_players.put(names.get(0), temp);
		Name name = m_activePlayers.remove(0);
		m_activePlayers.add(name);
	}
}
