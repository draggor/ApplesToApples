package net.dracolair.games.applestoapples;

import java.util.List;

import junit.framework.*;

public class ApplesToApplesTests extends TestCase{
	
	private Bot b = null;
	
	protected void setUp() {
		b = new Bot("bees");
	}
	
	protected void tearDown() {
		
	}
	
	public void testStartupNoGamesRunning() {
		assertTrue(b.m_gameList.isEmpty());
		assertTrue(b.m_playerList.isEmpty());
	}
	
	public void testCmdIsRunningFalseForBob() {
		List<String> responses = cmd("bob", "!list");
		assertEquals("bob: No game is running.", responses.get(1));
	}
	
	public void testCmdIsRunningTrueForBob() {
		cmd("Neel", "!join");
		List<String> responses = cmd("bob", "!list");
		
		assertEquals("bob: A game is already running, type !join to play!", responses.get(1));
		assertEquals("bob: List of players:", responses.get(2));
		assertEquals("bob: 1) Neel", responses.get(3));
	}
	
	public void testFirstPlayerJoins() {
		List<String> responses = cmd("bob", "!join");
		
		ApplesToApples ataFromGList = b.m_gameList.get("#channel");
		ApplesToApples ataFromPList = b.m_playerList.get("bob");
		
		assertEquals(1, b.m_gameList.size());
		assertNotNull(ataFromGList);
		assertEquals(1, b.m_playerList.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertEquals("bob has joined the game.", responses.get(1));
		assertEquals("Need 2 more to start.", responses.get(2));
	}
	
	public void testBobCantJoinTwice() {
		cmd("bob", "!join");
		List<String> responses = cmd("bob", "!join");
		
		ApplesToApples ataFromGList = b.m_gameList.get("#channel");
		ApplesToApples ataFromPList = b.m_playerList.get("bob");
		
		assertEquals(1, b.m_gameList.size());
		assertNotNull(ataFromGList);
		assertEquals(1, b.m_playerList.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertEquals("bob is already playing.", responses.get(1));
	}
	
	public void testBobCantJoinTwiceFromAnotherChannel() {
		cmd("bob", "!join");
		String[] msgMap = {"#bees", "bob", "login", "hostname", "!join"};
		List<String> responses = b.handleChanMessage(msgMap);
		
		ApplesToApples ataFromGList = b.m_gameList.get("#channel");
		ApplesToApples ataFromPList = b.m_playerList.get("bob");
		
		assertEquals(1, b.m_gameList.size());
		assertNotNull(ataFromGList);
		assertEquals(1, b.m_playerList.size());
		assertNotNull(ataFromPList);
		assertEquals(ataFromGList, ataFromPList);
		assertEquals(1, ataFromGList.m_players.size());
		assertEquals("bob is already playing.", responses.get(1));
	}
	
	public void testStartGameFail() {
		List<String> responses = cmd("bob", "!start");
		
		assertEquals("bob is fail, needs 3 to play.", responses.get(1));
	}
	
	public void testStartGameNeeds2More() {
		cmd("bob", "!join");
		List<String> responses = cmd("bob", "!start");
		
		assertEquals("bob is fail, needs 2 to play.", responses.get(1));
	}
	
	public List<String> cmd(String name, String command) {
		String[] msgMap = {"#channel", name, "login", "hostname", command};
		return b.handleChanMessage(msgMap);
	}
}
