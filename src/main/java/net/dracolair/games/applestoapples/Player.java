package net.dracolair.games.applestoapples;

import java.util.LinkedList;
import java.util.List;

public class Player {
	public int m_score = 0;
	public List<Card> m_redCards = new LinkedList<Card>();
	public List<Card> m_greenCards = new LinkedList<Card>();
	
	public String toString() {
		return "[Score: " + m_score + ", Cards: " + m_redCards + "]";
	}

	public String greenCards() {
		StringBuilder builder = new StringBuilder();
		
		for(Card c : m_greenCards) {
			builder.append(c.m_name);
			builder.append(", ");
		}
		
		if(builder.length() >= 2) {
			builder.delete(builder.length() - 2, builder.length());
		}
		
		return builder.toString();
	}
}
