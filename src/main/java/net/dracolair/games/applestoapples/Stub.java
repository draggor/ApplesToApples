package net.dracolair.games.applestoapples;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class Stub {
	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		Bot bot = new Bot("Bees");
		bot.connect("irc.freenode.net");
		bot.joinChannel("#applestoapples");
		for(;;) {
			
		}
	}
}
