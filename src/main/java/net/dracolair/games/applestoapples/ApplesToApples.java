package net.dracolair.games.applestoapples;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ApplesToApples {
	
	public Map<String, Player> m_players = new HashMap<String, Player>();
	public List<Card> m_cards = new LinkedList<Card>();
	public Map<String, Player> m_waiting = new HashMap<String, Player>();
	public State m_state = State.BEGIN;
	
	public void addPlayer(String name) {
		m_players.put(name, new Player());
	}
	
}
