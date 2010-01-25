package net.dracolair.games.applestoapples;

public final class Factories {
	
	private Factories() {}
	
	public static Card CARD(String name, String desc) {
		return new Card(name, desc);
	}
	
	public static Message MSG(String target, String message) {
		return new Message(target, message);
	}
}
