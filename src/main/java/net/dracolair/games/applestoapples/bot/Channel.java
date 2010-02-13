package net.dracolair.games.applestoapples.bot;

public class Channel {
	public String m_channel;
	public String m_pass = null;
	
	public Channel(String channel) {
		m_channel = channel;
	}
	
	public Channel(String channel, String pass) {
		m_channel = channel;
		m_pass = pass;
	}
}
