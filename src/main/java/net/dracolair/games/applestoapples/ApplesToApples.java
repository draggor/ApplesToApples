package net.dracolair.games.applestoapples;

public class ApplesToApples {
	
	public static void main(String[] args) throws Exception {
		int port;
		try {
			port = Integer.parseInt(System.getProperty("port"));
		} catch (Exception e) {
			port = 6667;
		}
		String password = System.getProperty("password");
		
		String name = args[0];
		String server = args[1];
		
		Bot bot = new Bot(name);
		
		if(password != null) {
			bot.connect(server, port, password);
		} else {
			bot.connect(server, port);
		}
		
		for(int i = 2; i < args.length; i++) {
			String key = System.getProperty("key" + (i-1));
			if(key != null) {
				bot.joinChannel(args[i], key);
			} else {
				bot.joinChannel(args[i]);
			}
		}
	}
	
}
