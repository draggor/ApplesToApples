package net.dracolair.games.applestoapples.commands;

import net.dracolair.games.applestoapples.Message;


public class Requirement {
	
	public boolean m_condition;
	public Message m_message;
	
	public Requirement (boolean condition, Message message) {
		m_condition = condition;
		m_message = message;
	}
	
	public String toString() {
		return "[Cond: " + m_condition + ", Msg: " + m_message + "]";
	}
}
