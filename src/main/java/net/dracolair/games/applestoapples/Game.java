package net.dracolair.games.applestoapples;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import net.dracolair.games.applestoapples.card.Card;

public class Game {
	
	public Map<Name, Player>	m_players = 		new LinkedHashMap<Name, Player>();
	public List<Card> 			m_cards = 			new LinkedList<Card>();
	public List<Name>			m_waiting = 		new LinkedList<Name>();
	public State				m_state = 			State.BEGIN;
	public List<Name>			m_activePlayers =	new LinkedList<Name>();
	public Name					m_judge = 			null;
	public int					m_limit = 			7;
	public Card					m_greenCard = 		null;
	public List<Card>			m_greenCards = 		null;
	public List<Card>			m_redCards = 		null;
	public boolean				m_isRandom = 		true;
	public Random				m_random = 			new Random();
	public long 				m_time = 			0;
	public boolean 				m_warning = 		false;
	
	public Game(List<Card> red, List<Card> green, boolean isRandom) {
		m_redCards = new LinkedList<Card>(red);
		m_greenCards = new LinkedList<Card>(green);
		m_isRandom = isRandom;
		if(m_isRandom) {
			Collections.shuffle(m_redCards);
			Collections.shuffle(m_greenCards);
		}
	}
	
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
	
	public void rotatePlayers() {
		List<Name> names = new LinkedList<Name>(m_players.keySet());
		Player temp = m_players.remove(names.get(0));
		m_players.put(names.get(0), temp);
		Name name = m_activePlayers.remove(0);
		m_activePlayers.add(name);
	}
	
	public Name getWinner() {
		Player p = new Player();
		Name n = null;
		p.m_score = -1;
		for(Entry<Name, Player> e : m_players.entrySet()) {
			if(e.getValue().m_score > p.m_score) {
				p = e.getValue();
				n = e.getKey();
			}
		}
		return n;
	}
	
}
