package net.dracolair.games.applestoapples.card;

public interface CardRenderer {
	
	public String render(String name, String desc);

	public String renderDesc(String desc);

	public String renderName(String name);
	
}
