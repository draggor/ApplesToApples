package net.dracolair.games.applestoapples;

import java.util.HashMap;
import java.util.Map;

public class ApplesToApples {
	
	public Map<String, Player> m_players = new HashMap<String, Player>();
	
	public boolean isRunning() {
		return true;
	}
	
	public void addPlayer(String name) {
		m_players.put(name, new Player());
	}
	
}
