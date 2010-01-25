package net.dracolair.games.applestoapples;

public class Message {
	public String m_target;
	public String m_message;
	
	public Message(String target, String message) {
		m_target = target;
		m_message = message;
	}
	
	public String toString() {
		return m_target + ": " + m_message;
	}
}
