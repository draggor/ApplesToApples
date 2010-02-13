package net.dracolair.games.applestoapples.card;

public class DefaultCardRenderer implements CardRenderer {

	@Override
	public String render(String name, String desc) {
		return renderName(name) + " - " + renderDesc(desc);
	}

	@Override
	public String renderDesc(String desc) {
		return desc;
	}

	@Override
	public String renderName(String name) {
		return name;
	}

}
