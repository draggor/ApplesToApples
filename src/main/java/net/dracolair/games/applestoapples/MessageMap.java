package net.dracolair.games.applestoapples;

public final class MessageMap {
	
	public String CHANNEL;
	public String NICK;
	public String LOGIN;
	public String HOSTNAME;
	public String MESSAGE;

	public MessageMap(String channel,
					  String nick,
					  String login,
					  String hostname,
					  String message) {
		CHANNEL = channel;
		NICK = nick;
		LOGIN = login;
		HOSTNAME = hostname;
		MESSAGE = message;
	}
	
	public MessageMap clone() {
		return new MessageMap(new String(CHANNEL),
							  new String(NICK),
							  new String(LOGIN),
							  new String(HOSTNAME),
							  new String(MESSAGE));
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		
		b.append("{ Channel: ");
		b.append(CHANNEL);
		b.append(", Nick: ");
		b.append(NICK);
		b.append(", Login: ");
		b.append(LOGIN);
		b.append(", Hostname: ");
		b.append(HOSTNAME);
		b.append(", Message: ");
		b.append(MESSAGE);
		b.append(" }");
		
		return b.toString();
	}
}
