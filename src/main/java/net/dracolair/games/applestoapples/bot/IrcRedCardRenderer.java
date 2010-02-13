package net.dracolair.games.applestoapples.bot;

import org.jibble.pircbot.Colors;

import net.dracolair.games.applestoapples.card.CardRenderer;

public class IrcRedCardRenderer implements CardRenderer {

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
		return Colors.RED + Colors.REVERSE + Colors.BOLD + name + Colors.NORMAL;
	}

}
