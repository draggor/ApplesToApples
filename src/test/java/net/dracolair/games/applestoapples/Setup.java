package net.dracolair.games.applestoapples;

import static net.dracolair.games.applestoapples.Factories.MSGINFO;

import java.util.List;

import net.dracolair.games.applestoapples.card.CardRenderer;
import net.dracolair.games.applestoapples.card.DefaultCardRenderer;
import junit.framework.TestCase;

public abstract class Setup extends TestCase {
	
	protected GameManager gameManager = null;
	protected CardRenderer cardRenderer = new DefaultCardRenderer();
	
	protected void setUp() {
		gameManager = new GameManager("bees", cardRenderer, cardRenderer);
	}
	
	protected void tearDown() {
		
	}
	
	protected List<Message> roomCmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("#channel", name, command);
		return gameManager.processRoomMessage(msgInfo).execute();
	}
	
	protected List<Message> privCmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("asdf", name, command);
		return gameManager.processPrivMessage(msgInfo).execute();
	}
	
	protected static void assertMessage(String target, String message, Message msg) {
		assertEquals(target, msg.m_target);
		assertEquals(message, msg.m_message);
	}
	
}
