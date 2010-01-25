package net.dracolair.games.applestoapples;

public class Card {
	String m_name = "";
	String m_desc = "";
	
	public Card(String name, String desc) {
		m_name = name;
		m_desc = desc;
	}
	
	@Override
	public String toString() {
		return m_name + " - " + m_desc;
	}
}
