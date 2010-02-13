package net.dracolair.games.applestoapples;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.card.CardRenderer;
import net.dracolair.games.applestoapples.card.DefaultCardRenderer;

import junit.framework.*;

import static net.dracolair.games.applestoapples.Factories.*;

public class ApplesToApplesTest extends TestCase{
	
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
	
	public void testCmdIsRunningFalseForBob() {
		roomCmd("bees", "!botcreategame false");
		List<Message> responses = roomCmd("bob", "!list");
		assertMessage("bob", "List of players: ", responses.get(0));
	}
	
	public void testCmdIsRunningTrueForBob1Player() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("Neel", "!join");
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
		MessageInfo msgMap1 = MSGINFO("#bees", "bees", "!botcreategame false");
		gameManager.processRoomMessage(msgMap1);
		MessageInfo msgMap2 = MSGINFO("#bees", "bob", "!join");
		List<Message> responses = gameManager.processRoomMessage(msgMap2);
		
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
	
	public void testChangeNick() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
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
		List<Message> responses = roomCmd("bob", "!start");
		
		assertMessage("#channel", "bob is fail, needs 2 to play.", responses.get(0));
	}
	
	public void testStartGameWorks() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		List<Message> responses = roomCmd("bob", "!start");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(3, ata.m_activePlayers.size());
		assertEquals("bob", ata.m_activePlayers.get(0).toString());
		assertEquals("neel", ata.m_activePlayers.get(1).toString());
		assertEquals("grue", ata.m_activePlayers.get(2).toString());
		
		assertMessage("#channel", "We have >=3 players, the game will begin!", responses.get(0));
		assertMessage("#channel", "Dealing out cards...", responses.get(1));
		assertMessage("bees", "!botdeal7 bob", responses.get(2));
		assertMessage("bees", "!botdeal7 neel", responses.get(3));
		assertMessage("bees", "!botdeal7 grue", responses.get(4));
		assertMessage("bees", "!botplay #channel", responses.get(5));
	}
	
	public void testDeal7() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		List<Message> responses = privCmd("bees", "!botdeal7 bob");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("bob", "Card - " + (i + 1), responses.get(i));
		}
*/		
		responses = privCmd("bees", "!botdeal7 neel");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("neel", "Card - " + (i + 8), responses.get(i));
		}
*/		
		responses = privCmd("bees", "!botdeal7 grue");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("grue", "Card - " + (i + 15), responses.get(i));
		}
*/	}
	
	public void testNewRound() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		List<Message> responses = privCmd("bees", "!botplay #channel");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(2, ata.m_waiting.size());
		assertEquals("bob", ata.m_judge.toString());
		
		assertMessage("#channel", "bob is the judge.  Green card is: Absurd - ridiculous, senseless, foolish", responses.get(0));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(1));
	}
	
	public void testAllPlayersPlayCards() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		List<Message> responses = roomCmd("grue", "!play 4");
		
		assertMessage("grue", "Adolph Hitler - 1889-1945, turned Germany into a militarized dictatorship, caused the slaughter of millions and launched World War II.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	public void testOnePlayerGetsWarning() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		List<Message> responses = privCmd("bees", "!botwarning #channel");
		
		assertMessage("#channel", "grue, play a card or be marked as away!", responses.get(0));
	}
	
	public void testOnePlayerGetsMarkedAway() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("derp", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botdeal7 derp");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 3");
		List<Message> responses = privCmd("bees", "!botaway #channel");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(0, ata.m_waiting.size());
		assertEquals(3, ata.m_activePlayers.size());
		assertMessage("#channel", "derp, you're being flagged as away.  Use !back to rejoin.", responses.get(0));
	}
	
	public void testChooseMenu() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		List<Message> responses = privCmd("bees", "!botchoose #channel");
		
		assertMessage("#channel", "The green card is: Absurd - ridiculous, senseless, foolish", responses.get(0));
		assertMessage("#channel", "1. A Locker Room - Steamy atmosphere ... bawdy humor ... sweaty bodies ... HEY! Sounds like Cable TV!", responses.get(1));
		assertMessage("#channel", "2. A Sunset - The sun never set on the British Empire ... because God didn't trust the English in the dark.", responses.get(2));
		assertMessage("#channel", "bob must choose a red card!  Type '!choose number'", responses.get(3));
	}
	
	public void testJudgeWarning() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		List<Message> responses = privCmd("bees", "!botwarning #channel");
		
		assertMessage("#channel", "bob, choose a card or be marked away!", responses.get(0));
	}
	
	public void testJudgeAway() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		List<Message> responses = privCmd("bees", "!botaway #channel");
		
		assertMessage("#channel", "bob, you're being flagged as away.  Use !back to rejoin.", responses.get(0));
		
	}
	
	public void testAllPlayersPlayCardsAndJudgePicks() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		List<Message> responses = roomCmd("bob", "!choose 2");
		
		assertMessage("#channel", "The winner is grue: A Sunset!", responses.get(0));
		assertMessage("#channel", "Scores: bob:0 neel:0 grue:1 ", responses.get(1));
		assertMessage("bees", "!botcleanup #channel", responses.get(2));
	}
	
	public void testCleanup() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		List<Message> responses = privCmd("bees", "!botcleanup #channel");
		Game ata = gameManager.getGameByChan("#channel");
		List<Name> names = new LinkedList<Name>(ata.m_players.keySet());
		
		assertEquals("neel", names.get(0).toString());
		assertEquals("grue", names.get(1).toString());
		assertEquals("bob", names.get(2).toString());
		assertMessage("bees", "!botplay #channel", responses.get(0));
	}
	
	public void testNextRound() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		List<Message> responses = privCmd("bees", "!botplay #channel");
		
		assertMessage("#channel", "neel is the judge.  Green card is: Addictive - obsessive, consuming, captivating", responses.get(0));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(1));
	}
	
	public void testChooseLastWinner() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		privCmd("bees", "!botplay #channel");
		roomCmd("grue", "!play 1");
		roomCmd("bob", "!play 3");
		privCmd("bees", "!botchoose #channel");
		List<Message> responses = roomCmd("neel", "!choose 1");
		
		assertMessage("bees", "!botendgame #channel", responses.get(0));
	}
	
	public void testEndGame() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("bob", "!start");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botplay #channel");
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		privCmd("bees", "!botplay #channel");
		roomCmd("grue", "!play 6");
		roomCmd("bob", "!play 3");
		privCmd("bees", "!botchoose #channel");
		roomCmd("neel", "!choose 1");
		List<Message> responses = privCmd("bees", "!botendgame #channel");
		
		assertMessage("#channel", "GAME OVER!  The winner is grue", responses.get(0));
		assertMessage("#channel", "neel is: ", responses.get(1));
		assertMessage("#channel", "grue is: Absurd, Addictive", responses.get(2));
		assertMessage("#channel", "bob is: ", responses.get(3));
	}
	
	public List<Message> roomCmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("#channel", name, command);
		return gameManager.processRoomMessage(msgInfo);
	}
	
	public List<Message> privCmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("#channel", name, command);
		return gameManager.processPrivMessage(msgInfo);
	}
	
	public static void assertMessage(String target, String message, Message msg) {
		assertEquals(target, msg.m_target);
		assertEquals(message, msg.m_message);
	}
}
