package net.dracolair.games.applestoapples;

import static net.dracolair.games.applestoapples.Factories.MSGINFO;

import java.util.List;

import net.dracolair.games.applestoapples.card.CardRenderer;
import net.dracolair.games.applestoapples.card.DefaultCardRenderer;

public class BasicTests extends Setup {
	
	private GameManager gameManager = null;
	private CardRenderer cardRenderer = new DefaultCardRenderer();
	
	protected void setUp() {
		gameManager = new GameManager("bees", cardRenderer, cardRenderer);
	}
	
	protected void tearDown() {
		
	}
	
	public void testStartupNoGamesRunning() {
		assertTrue(gameManager.m_roomToGameMap.isEmpty());
		assertTrue(gameManager.m_nameToGameMap.isEmpty());
	}
	
	public void testHelpNoArgs() {
		List<Message> responses = roomCmd("bob", "!help");
		
		assertMessage("bob", "Use !help <command>, available commands: away back choose join limit list play start", responses.get(0));
	}
	
	public void testCmdIsRunningFalseForBob() {
		roomCmd("bees", "!botcreategame false");
		List<Message> responses = roomCmd("bob", "!list");
		assertMessage("bob", "List of players: ", responses.get(0));
	}
	
	public void testCmdIsRunningTrueForBob1Player() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("Neel", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		List<Message> responses = roomCmd("bob", "!list");
		Game ata = gameManager.getGameByChan("#channel");
		Name neel = gameManager.m_nickToNameMap.get("Neel");
		
		assertEquals(1, ata.m_players.size());
		assertTrue(ata.m_players.containsKey(neel));
		assertMessage("bob", "List of players: Neel:0 ", responses.get(0));
	}
	
	public void testCmdIsRunningTrueForBob2Players() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("Neel", "!join");
		roomCmd("Grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
		List<Message> responses = roomCmd("bob", "!list");
		Game ata = gameManager.getGameByChan("#channel");
		Name neel = gameManager.m_nickToNameMap.get("Neel");
		Name grue = gameManager.m_nickToNameMap.get("Grue");
		
		assertEquals(2, ata.m_players.size());
		assertTrue(ata.m_players.containsKey(neel));
		assertTrue(ata.m_players.containsKey(grue));
		assertMessage("bob", "List of players: Neel:0 Grue:0 ", responses.get(0));
	}
	
	public void testFirstPlayerJoins() {
		roomCmd("bees", "!botcreategame false");
		List<Message> responses = roomCmd("bob", "!join");
		
		Game ataFromGList = gameManager.m_roomToGameMap.get("#channel");
		Game ataFromPList = gameManager.getGameByNick("bob");
		
		assertEquals(1, gameManager.m_roomToGameMap.size());
		assertNotNull(ataFromGList);
		assertEquals(1, gameManager.m_nameToGameMap.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertMessage("#channel", "bob has joined the game, need 2 more to start.", responses.get(0));
	}
	
	public void testBobCantJoinTwice() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		List<Message> responses = roomCmd("bob", "!join");
		
		Game ataFromGList = gameManager.m_roomToGameMap.get("#channel");
		Game ataFromPList = gameManager.getGameByNick("bob");
		
		assertEquals(1, gameManager.m_roomToGameMap.size());
		assertNotNull(ataFromGList);
		assertEquals(1, gameManager.m_nameToGameMap.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertMessage("#channel", "bob is already playing.", responses.get(0));
	}
	
	public void testBobCantJoinTwiceFromAnotherChannel() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		MessageInfo msgMap1 = MSGINFO("#bees", "bees", "!botcreategame false");
		gameManager.processRoomMessage(msgMap1).execute();
		MessageInfo msgMap2 = MSGINFO("#bees", "bob", "!join");
		List<Message> responses = gameManager.processRoomMessage(msgMap2).execute();
		
		Game ataFromGList = gameManager.m_roomToGameMap.get("#channel");
		Game ataFromPList = gameManager.getGameByNick("bob");
		
		assertEquals(2, gameManager.m_roomToGameMap.size());
		assertNotNull(ataFromGList);
		assertEquals(1, gameManager.m_nameToGameMap.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertMessage("#bees", "bob is already playing.", responses.get(0));
	}
	
	/**
	 * TODO: Review this, maybe make changes on how delayed cmds are stored
	 */
	public void testChangeNick() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		gameManager.changeNick("bob", "newhart");
		List<Message> responses = roomCmd("newhart", "!list");
		
		assertMessage("newhart", "List of players: newhart:0 ", responses.get(0));
	}
	
	public void testStartGameFail() {
		roomCmd("bees", "!botcreategame false");
		List<Message> responses = roomCmd("bob", "!start");
		
		assertMessage("#channel", "bob is fail, needs 3 to play.", responses.get(0));
	}
	
	public void testStartGameNeeds2More() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		List<Message> responses = roomCmd("bob", "!start");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(1, ata.m_delayedCommands.size());
		
		assertMessage("#channel", "bob is fail, needs 2 to play.", responses.get(0));
	}
	
	public void testSetCustomRedGame() {
		roomCmd("bees", "!botcreategame false");
		List<Message> responses = roomCmd("bob", "!customred");
		
		assertMessage("#channel", "Winner makes custom red card: true", responses.get(0));
		
		responses = roomCmd("bob", "!customred");
		
		assertMessage("#channel", "Winner makes custom red card: false", responses.get(0));
	}
	
}
