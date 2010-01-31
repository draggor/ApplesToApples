package net.dracolair.games.applestoapples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import static net.dracolair.games.applestoapples.Factories.*;

public class FileParser {
	
	public static List<Card> loadCardsFromFile(String path) {
		List<Card> cards = new LinkedList<Card>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			String line;
			while((line=reader.readLine()) != null) {
				String[] sp = line.split(" - ", 2);
				cards.add(CARD(sp[0], sp[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cards;
	}
	
}
