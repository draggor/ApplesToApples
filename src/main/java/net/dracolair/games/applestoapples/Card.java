package net.dracolair.games.applestoapples;

public class Card {
	public String m_name = "";
	public String m_desc = "";
	public Name m_playedBy;
	
	public Card(String name, String desc) {
		m_name = name;
		m_desc = desc;
	}
	
	@Override
	public String toString() {
		return m_name + " - " + m_desc;
	}
}
