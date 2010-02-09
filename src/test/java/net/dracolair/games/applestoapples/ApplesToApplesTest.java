package net.dracolair.games.applestoapples;

import java.util.LinkedList;
import java.util.List;

import junit.framework.*;

import static net.dracolair.games.applestoapples.Factories.*;

public class ApplesToApplesTest extends TestCase{
	
	private GameManager gameManager = null;
	
	protected void setUp() {
		gameManager = new GameManager("bees");
	}
	
	protected void tearDown() {
		
	}
	
	public void testStartupNoGamesRunning() {
		assertTrue(gameManager.m_roomToGameMap.isEmpty());
		assertTrue(gameManager.m_nameToGameMap.isEmpty());
	}
	
	public void testCmdIsRunningFalseForBob() {
		cmd("bees", "!botcreategame false");
		List<Message> responses = cmd("bob", "!list");
		assertMessage("bob", "List of players: ", responses.get(0));
	}
	
	public void testCmdIsRunningTrueForBob1Player() {
		cmd("bees", "!botcreategame false");
		cmd("Neel", "!join");
		List<Message> responses = cmd("bob", "!list");
		Game ata = gameManager.getGameByChan("#channel");
		Name neel = gameManager.m_nickToNameMap.get("Neel");
		
		assertEquals(1, ata.m_players.size());
		assertTrue(ata.m_players.containsKey(neel));
		assertMessage("bob", "List of players: Neel:0 ", responses.get(0));
	}
	
	public void testCmdIsRunningTrueForBob2Players() {
		cmd("bees", "!botcreategame false");
		cmd("Neel", "!join");
		cmd("Grue", "!join");
		List<Message> responses = cmd("bob", "!list");
		Game ata = gameManager.getGameByChan("#channel");
		Name neel = gameManager.m_nickToNameMap.get("Neel");
		Name grue = gameManager.m_nickToNameMap.get("Grue");
		
		assertEquals(2, ata.m_players.size());
		assertTrue(ata.m_players.containsKey(neel));
		assertTrue(ata.m_players.containsKey(grue));
		assertMessage("bob", "List of players: Neel:0 Grue:0 ", responses.get(0));
	}
	
	public void testFirstPlayerJoins() {
		cmd("bees", "!botcreategame false");
		List<Message> responses = cmd("bob", "!join");
		
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
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		List<Message> responses = cmd("bob", "!join");
		
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
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
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
	
	public void testStartGameFail() {
		cmd("bees", "!botcreategame false");
		List<Message> responses = cmd("bob", "!start");
		
		assertMessage("#channel", "bob is fail, needs 3 to play.", responses.get(0));
	}
	
	public void testStartGameNeeds2More() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		List<Message> responses = cmd("bob", "!start");
		
		assertMessage("#channel", "bob is fail, needs 2 to play.", responses.get(0));
	}
	
	public void testStartGameWorks() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		List<Message> responses = cmd("bob", "!start");
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
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		List<Message> responses = privcmd("bees", "!botdeal7 bob");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("bob", "Card - " + (i + 1), responses.get(i));
		}
*/		
		responses = privcmd("bees", "!botdeal7 neel");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("neel", "Card - " + (i + 8), responses.get(i));
		}
*/		
		responses = privcmd("bees", "!botdeal7 grue");
		
/*		for(int i = 0; i < 7; i++) {
			assertMessage("grue", "Card - " + (i + 15), responses.get(i));
		}
*/	}
	
	public void testNewRound() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		List<Message> responses = privcmd("bees", "!botplay #channel");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(2, ata.m_waiting.size());
		assertEquals("bob", ata.m_judge.toString());
		
		assertMessage("#channel", "bob is the judge.  Green card is: Absurd - ridiculous, senseless, foolish", responses.get(0));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(1));
	}
	
	public void testAllPlayersPlayCards() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		List<Message> responses = cmd("grue", "!play 4");
		
		assertMessage("grue", "Adolph Hitler - 1889-1945, turned Germany into a militarized dictatorship, caused the slaughter of millions and launched World War II.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	public void testChooseMenu() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		List<Message> responses = privcmd("bees", "!botchoose #channel");
		
		assertMessage("#channel", "The green card is: hax", responses.get(0));
		assertMessage("#channel", "1. A Locker Room - Steamy atmosphere ... bawdy humor ... sweaty bodies ... HEY! Sounds like Cable TV!", responses.get(1));
		assertMessage("#channel", "2. A Sunset - The sun never set on the British Empire ... because God didn't trust the English in the dark.", responses.get(2));
		assertMessage("#channel", "bob must choose a red card!  Type '!choose number'", responses.get(3));
	}
	
	public void testAllPlayersPlayCardsAndJudgePicks() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		privcmd("bees", "!botchoose #channel");
		List<Message> responses = cmd("bob", "!choose 2");
		
		assertMessage("#channel", "The winner is grue: A Sunset!", responses.get(0));
		assertMessage("#channel", "Scores: bob:0 neel:0 grue:1 ", responses.get(1));
		assertMessage("bees", "!botcleanup #channel", responses.get(2));
	}
	
	public void testCleanup() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		privcmd("bees", "!botchoose #channel");
		cmd("bob", "!choose 2");
		List<Message> responses = privcmd("bees", "!botcleanup #channel");
		Game ata = gameManager.getGameByChan("#channel");
		List<Name> names = new LinkedList<Name>(ata.m_players.keySet());
		
		assertEquals("neel", names.get(0).toString());
		assertEquals("grue", names.get(1).toString());
		assertEquals("bob", names.get(2).toString());
		assertMessage("bees", "!botplay #channel", responses.get(0));
	}
	
	public void testNextRound() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		privcmd("bees", "!botchoose #channel");
		cmd("bob", "!choose 2");
		privcmd("bees", "!botcleanup #channel");
		List<Message> responses = privcmd("bees", "!botplay #channel");
		
		assertMessage("#channel", "neel is the judge.  Green card is: Addictive - obsessive, consuming, captivating", responses.get(0));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(1));
	}
	
	public void testChooseLastWinner() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		privcmd("bees", "!botchoose #channel");
		cmd("bob", "!choose 2");
		privcmd("bees", "!botcleanup #channel");
		privcmd("bees", "!botplay #channel");
		cmd("grue", "!play 1");
		cmd("bob", "!play 3");
		privcmd("bees", "!botchoose #channel");
		List<Message> responses = cmd("neel", "!choose 1");
		
		assertMessage("bees", "!botendgame #channel", responses.get(0));
	}
	
	public void testEndGame() {
		cmd("bees", "!botcreategame false");
		cmd("bob", "!join");
		cmd("neel", "!join");
		cmd("grue", "!join");
		cmd("bob", "!start");
		privcmd("bees", "!botdeal7 bob");
		privcmd("bees", "!botdeal7 neel");
		privcmd("bees", "!botdeal7 grue");
		privcmd("bees", "!botplay #channel");
		cmd("neel", "!play 5");
		cmd("grue", "!play 4");
		privcmd("bees", "!botchoose #channel");
		cmd("bob", "!choose 2");
		privcmd("bees", "!botcleanup #channel");
		privcmd("bees", "!botplay #channel");
		cmd("grue", "!play 6");
		cmd("bob", "!play 3");
		privcmd("bees", "!botchoose #channel");
		cmd("neel", "!choose 1");
		List<Message> responses = privcmd("bees", "!botendgame #channel");
		
		assertMessage("#channel", "GAME OVER!  The winner is grue", responses.get(0));
		assertMessage("#channel", "neel is: ", responses.get(1));
		assertMessage("#channel", "grue is: Absurd, Addictive", responses.get(2));
		assertMessage("#channel", "bob is: ", responses.get(3));
	}
	
	public List<Message> cmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("#channel", name, command);
		return gameManager.processRoomMessage(msgInfo);
	}
	
	public List<Message> privcmd(String name, String command) {
		MessageInfo msgInfo = MSGINFO("#channel", name, command);
		return gameManager.processPrivMessage(msgInfo);
	}
	
	public static void assertMessage(String target, String message, Message msg) {
		assertEquals(target, msg.m_target);
		assertEquals(message, msg.m_message);
	}
}
