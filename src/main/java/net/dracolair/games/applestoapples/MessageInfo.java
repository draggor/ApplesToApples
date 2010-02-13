package net.dracolair.games.applestoapples;

public final class MessageInfo {
	
	public String ROOM;
	public String NICK;
	public String MESSAGE;

	public MessageInfo(String room,
					   String nick,
					   String message) {
		ROOM = room;
		NICK = nick;
		MESSAGE = message;
	}
	
	public MessageInfo clone() {
		return new MessageInfo(new String(ROOM),
							   new String(NICK),
							   new String(MESSAGE));
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		b.append("{ Channel: ");
		b.append(ROOM);
		b.append(", Nick: ");
		b.append(NICK);
		b.append(", Message: ");
		b.append(MESSAGE);
		b.append(" }");
		
		return b.toString();
	}
}
