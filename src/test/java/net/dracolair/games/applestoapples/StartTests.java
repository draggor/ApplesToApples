package net.dracolair.games.applestoapples;

import java.util.List;

public class StartTests extends Level1 {
	
	public void testStartGameWorks() {
		List<Message> responses = roomCmd("bob", "!start");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(3, ata.m_activePlayers.size());
		assertEquals("bob", ata.m_activePlayers.get(0).toString());
		assertEquals("neel", ata.m_activePlayers.get(1).toString());
		assertEquals("grue", ata.m_activePlayers.get(2).toString());
		
		assertMessage("#channel", "We have >=3 players, the game will begin!", responses.get(0));
		assertMessage("#channel", "Dealing out cards...", responses.get(1));
		assertMessage("bees", "!botplay #channel", responses.get(2));
	}
	
	public void testDeal7() {
		roomCmd("bob", "!start");
		privCmd("bees", "!botplay #channel");
		List<Message> responses = privCmd("bees", "!botdeal7 bob");
		
		assertEquals(7, responses.size());
/*		for(int i = 0; i < 7; i++) {
			assertMessage("bob", "Card - " + (i + 1), responses.get(i));
		}
*/		
		responses = privCmd("bees", "!botdeal7 neel");
		
		assertEquals(7, responses.size());
/*		for(int i = 0; i < 7; i++) {
			assertMessage("neel", "Card - " + (i + 8), responses.get(i));
		}
*/		
		responses = privCmd("bees", "!botdeal7 grue");
		
		assertEquals(7, responses.size());
/*		for(int i = 0; i < 7; i++) {
			assertMessage("grue", "Card - " + (i + 15), responses.get(i));
		}
*/	}
	
	public void testNewRound() {
		roomCmd("bob", "!start");
		List<Message> responses = privCmd("bees", "!botplay #channel");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(2, ata.m_waiting.size());
		assertEquals("bob", ata.m_judge.toString());
		assertEquals(7, responses.size());
		
		assertMessage("bees", "!botdeal7 bob", responses.get(0));
		assertMessage("bees", "!botdeal7 neel", responses.get(1));
		assertMessage("bees", "!botdeal7 grue", responses.get(2));
		assertMessage("#channel", "bob is the judge.  Green card is:  Absurd  - ridiculous, senseless, foolish", responses.get(3));
		assertMessage("#channel", "Waiting for players to play cards...", responses.get(4));
		assertMessage("bees", "!botshowplayercards #channel", responses.get(5));
		assertMessage("bees", "!botsettime #channel", responses.get(6));
	}
	
}
