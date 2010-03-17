package net.dracolair.games.applestoapples.commands;

import java.util.LinkedList;
import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;

public abstract class Command {
	
	public GameManager gameManager;
	public Game ata;
	public MessageInfo msgInfo;
	
	public List<Message> execute() {
		List<Message> responses = new LinkedList<Message>();
		List<Requirement> requirements = new LinkedList<Requirement>();
		
		getRequirements(gameManager, ata, msgInfo, requirements);
		
		boolean runCmd = true;
		for(Requirement requirement : requirements) {
			if(!requirement.m_condition) {
				runCmd = false;
				responses.add(requirement.m_message);
				break;
			}
		}
		
		if(runCmd) {
			this.run(gameManager, ata, msgInfo, responses);
		}
		
		return responses;
	}
	
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		
	}
	
	public abstract void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses);
	
}
