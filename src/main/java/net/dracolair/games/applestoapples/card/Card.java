package net.dracolair.games.applestoapples.card;

import net.dracolair.games.applestoapples.Name;

public class Card {
	public String m_name = "";
	public String m_desc = "";
	public Name m_playedBy;
	public CardRenderer m_renderer = new DefaultCardRenderer();
	
	public Card(String name, String desc, CardRenderer cardRenderer) {
		m_name = name;
		m_desc = desc;
		m_renderer = cardRenderer;
	}
	
	@Override
	public String toString() {
		return m_name + " - " + m_desc;
	}
	
	public String toFormattedString() {
		return m_renderer.render(m_name, m_desc);
	}
	
	public String getFormattedName() {
		return m_renderer.renderName(m_name);
	}
	
	public String getFormattedDesc() {
		return m_renderer.renderDesc(m_desc);
	}
}
