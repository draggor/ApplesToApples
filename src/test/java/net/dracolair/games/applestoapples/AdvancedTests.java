package net.dracolair.games.applestoapples;

import java.util.LinkedList;
import java.util.List;

public class AdvancedTests extends Level2 {

	public void testAllPlayersPlayCards() {
		roomCmd("neel", "!play 5");
		List<Message> responses = roomCmd("grue", "!play 4");
		
		assertMessage("grue", " Adolph Hitler  - 1889-1945, turned Germany into a militarized dictatorship, caused the slaughter of millions and launched World War II.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	public void testPlayerChangesCard() {
		roomCmd("neel", "!play 5");
		roomCmd("neel", "!play 4");
		Game ata = gameManager.getGameByChan("#channel");
		Name n = gameManager.m_nickToNameMap.get("neel");
		Player p = ata.m_players.get(n);
		
		assertEquals(1, ata.m_cards.size());
		assertEquals(7, p.m_redCards.size());
	}
	
	public void testPlayerJoinsMidGame() {
		roomCmd("neel", "!play 5");
		List<Message> responses = roomCmd("id10t", "!join");
		
		assertMessage("#channel", "id10t has joined the game!  Cards will be dealt at the start of the next round.", responses.get(0));
		assertMessage("bees", "!botdelaycmd #channel !botdeal7 id10t", responses.get(1));
	}
	
	public void testDelayCmd() {
		roomCmd("neel", "!play 5");
		roomCmd("id10t", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 id10t");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(1, ata.m_delayedCommands.size());
	}
	
	public void testRunDelayedCmd() {
		roomCmd("neel", "!play 5");
		roomCmd("id10t", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 id10t");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		List<Message> responses = privCmd("bees", "!botplay #channel");
		
		assertMessage("bees", "!botdeal7 id10t", responses.get(0));
	}
	
	public void testOnePlayerGetsWarning() {
		roomCmd("neel", "!play 5");
		Game ata = gameManager.getGameByChan("#channel");
		assertFalse(ata.m_warning);
		List<Message> responses = privCmd("bees", "!botwarning #channel");
		
		assertTrue(ata.m_warning);
		assertMessage("#channel", "grue, play a card or be marked as away!", responses.get(0));
	}
	
	public void testOnePlayerGetsMarkedAway() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 3");
		privCmd("bees", "!botwarning #channel");
		
		Game ata = gameManager.getGameByChan("#channel");
		assertTrue(ata.m_warning);
		List<Message> responses = privCmd("bees", "!botaway #channel");
		
		assertFalse(ata.m_warning);
		assertEquals(0, ata.m_waiting.size());
		assertEquals(3, ata.m_activePlayers.size());
		assertMessage("#channel", "derp, you're being flagged as away.  Use !back to rejoin.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	public void testPlayerBack() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		roomCmd("id10t", "!away");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		privCmd("bees", "!botplay #channel");
		List<Message> responses = roomCmd("id10t", "!back");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(4, ata.m_activePlayers.size());
		assertMessage("#channel", "Welcome back id10t!  You'll be in next round.", responses.get(0));
	}
	
	public void testChooseMenu() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		List<Message> responses = privCmd("bees", "!botchoose #channel");
		
		assertMessage("#channel", "The green card is:  Absurd  - ridiculous, senseless, foolish", responses.get(0));
		assertMessage("#channel", "1.  A Locker Room  - Steamy atmosphere ... bawdy humor ... sweaty bodies ... HEY! Sounds like Cable TV!", responses.get(1));
		assertMessage("#channel", "2.  A Sunset  - The sun never set on the British Empire ... because God didn't trust the English in the dark.", responses.get(2));
		assertMessage("#channel", "bob must choose a red card!  Type '!choose number'", responses.get(3));
	}
	
	public void testJudgeWarning() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		
		Game ata = gameManager.getGameByChan("#channel");
		assertFalse(ata.m_warning);
		List<Message> responses = privCmd("bees", "!botwarning #channel");
		
		assertTrue(ata.m_warning);
		assertMessage("#channel", "bob, choose a card or be marked away!", responses.get(0));
	}
	
	public void testJudgeAway() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		privCmd("bees", "!botwarning #channel");
		
		Game ata = gameManager.getGameByChan("#channel");
		assertTrue(ata.m_warning);
		List<Message> responses = privCmd("bees", "!botaway #channel");
		
		assertFalse(ata.m_warning);
		assertMessage("#channel", "bob, you're being flagged as away.  Use !back to rejoin.", responses.get(0));
		assertMessage("bees", "!botcleanup #channel", responses.get(1));
	}
	
	public void testAllPlayersPlayCardsAndJudgePicks() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		List<Message> responses = roomCmd("bob", "!choose 2");
		
		assertMessage("#channel", "The winner is grue:  A Sunset !", responses.get(0));
		assertMessage("#channel", "Scores: bob:0 neel:0 grue:1 ", responses.get(1));
		assertMessage("bees", "!botcleanup #channel", responses.get(2));
	}
	
	public void testCleanup() {
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
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		privCmd("bees", "!botchoose #channel");
		roomCmd("bob", "!choose 2");
		privCmd("bees", "!botcleanup #channel");
		List<Message> responses = privCmd("bees", "!botplay #channel");
		
		assertMessage("#channel", "neel is the judge.  Green card is:  Addictive  - obsessive, consuming, captivating", responses.get(0));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(1));
		assertMessage("bees", "!botshowplayercards #channel", responses.get(2));
		assertMessage("bees", "!botsettime #channel", responses.get(3));
	}
	
	/**
	 * TODO: add more checks to this!
	 */
	public void testChooseLastWinner() {
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
	
	
	
	
	
}
