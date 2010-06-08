package net.dracolair.games.applestoapples;

import java.util.List;

public class ComplexTests extends Level3 {
	
	public void testUserSetAway() {
		roomCmd("neel", "!play 5");
		List<Message> responses = roomCmd("id10t", "!away");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(3, ata.m_activePlayers.size());
		assertEquals(1, ata.m_waiting.size());
		assertEquals(1, responses.size());
		assertMessage("#channel", "id10t has been marked as away.  Use !back to rejoin.", responses.get(0));
	}
	
	public void testUserSetAwayThenChoose() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		List<Message> responses = roomCmd("id10t", "!away");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(3, ata.m_activePlayers.size());
		assertEquals(0, ata.m_waiting.size());
		assertMessage("#channel", "id10t has been marked as away.  Use !back to rejoin.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	public void testUserSetAwayThenCleanup() {
		roomCmd("neel", "!play 5");
		roomCmd("grue", "!play 4");
		List<Message> responses = roomCmd("id10t", "!away");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(3, ata.m_activePlayers.size());
		assertEquals(0, ata.m_waiting.size());
		assertMessage("#channel", "id10t has been marked as away.  Use !back to rejoin.", responses.get(0));
		assertMessage("bees", "!botchoose #channel", responses.get(1));
	}
	
	

}
