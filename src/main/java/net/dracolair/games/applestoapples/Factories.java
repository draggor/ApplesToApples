package net.dracolair.games.applestoapples;

import java.util.List;

import net.dracolair.games.applestoapples.card.Card;
import net.dracolair.games.applestoapples.card.CardRenderer;

public final class Factories {
	
	private Factories() {}
	
	public static Card CARD(String name, String desc, CardRenderer cardRenderer) {
		return new Card(name, desc, cardRenderer);
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
	
	public static Requirement REQ(boolean condition, Message message) {
		return new Requirement(condition, message);
	}
	
	public static Game GAME(List<Card> red, List<Card> green, boolean isRandom) {
		return new Game(red, green, isRandom);
	}
}
