package net.dracolair.games.applestoapples;

public class Level3 extends Setup {
	
	protected void setUp() {
		super.setUp();
		roomCmd("bees", "!botcreategame false");
		roomCmd("bob", "!join");
		roomCmd("neel", "!join");
		roomCmd("grue", "!join");
		roomCmd("id10t", "!join");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 bob");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 neel");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 grue");
		privCmd("bees", "!botdelaycmd #channel !botdeal7 id10t");
		roomCmd("bob", "!start");
		privCmd("bees", "!botplay #channel");
		privCmd("bees", "!botdeal7 bob");
		privCmd("bees", "!botdeal7 neel");
		privCmd("bees", "!botdeal7 grue");
		privCmd("bees", "!botdeal7 id10t");
		privCmd("bees", "!botshowplayercards #channel");
		privCmd("bees", "!botsettime #channel");
	}
	
}
