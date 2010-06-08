package net.dracolair.games.applestoapples;

import java.util.List;

public class NonTemplateTests extends Setup {
	
	public void testEndGame() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
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
		assertMessage("#channel", "grue is:  Absurd ,  Addictive ", responses.get(2));
		assertMessage("#channel", "bob is: ", responses.get(3));
	}
	
	public void testCustomRed() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!customred");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
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
		
		assertMessage("bees", "!botcustomred #channel", responses.get(0));
	}
	
	public void testCustomRedMenu() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!customred");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
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
		List<Message> responses = privCmd("bees", "!botcustomred #channel");
		
		assertMessage("#channel", "It's custom RED card time!  grue needs to make a custom card!", responses.get(0));
		assertMessage("#channel", "Type /msg bees !custom cardname - description", responses.get(1));
	}
	
	public void testCustomRedMake() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!customred");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
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
		privCmd("bees", "!botcustomred #channel");
		List<Message> responses = privCmd("grue", "!custom hurf - durf");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(1, ata.m_customRedCards.size());
		assertMessage("#channel", "Thanks for the card!", responses.get(0));
		assertMessage("bees", "!botplay #channel", responses.get(1));
	}
	
	public void testCustomRedDraw() {
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!customred");
		roomCmd("bob", "!join");
		roomCmd("bob", "!limit 2");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
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
		privCmd("bees", "!botcustomred #channel");
		privCmd("grue", "!custom hurf - durf");
		privCmd("bees", "!botplay #channel");
		List<Message> responses = roomCmd("grue", "!play 1");
		Game ata = gameManager.getGameByChan("#channel");
		
		assertEquals(0, ata.m_customRedCards.size());
		assertMessage("grue", "hurf - durf", responses.get(0));
	}
	
	
	
}
