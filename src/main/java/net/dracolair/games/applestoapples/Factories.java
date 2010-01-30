package net.dracolair.games.applestoapples;

public final class Factories {
	
	private Factories() {}
	
	public static Card CARD(String name, String desc) {
		return new Card(name, desc);
	}
	
	public static Message MSG(String target, String message) {
		return new Message(target, message);
	}
	
	public static Name NAME(String name) {
		return new Name(name);
	}
	
	public static MessageInfo MSGINFO(String room,
					  				  String nick,
					  				  String message) {
		return new MessageInfo(room, nick, message);
	}
}
