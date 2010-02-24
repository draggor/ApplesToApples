package net.dracolair.games.applestoapples;

import java.io.IOException;

import net.dracolair.games.applestoapples.bot.Bot;
import net.dracolair.games.applestoapples.bot.Channel;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class Stub {
	public static void main(String[] args) throws NickAlreadyInUseException, IOException, IrcException {
		Bot bot = new Bot("Bees");
		bot.connect("irc.freenode.net");
		bot.joinAndSaveChannel(new Channel("#applestoapples"));
		for(;;) {
			
		}
	}
}
