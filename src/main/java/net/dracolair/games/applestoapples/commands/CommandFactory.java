package net.dracolair.games.applestoapples.commands;

import java.util.HashMap;
import java.util.Map;


public class CommandFactory {
	
	public Map<String, Class<? extends Command>> m_commands = new HashMap<String, Class<? extends Command>>();
	
	public CommandFactory() {
		m_commands.put("join", CmdJoin.class);
		m_commands.put("list", CmdList.class);
		m_commands.put("start", CmdStart.class);
		m_commands.put("play", CmdPlay.class);
		m_commands.put("choose", CmdChoose.class);
		m_commands.put("limit", CmdLimit.class);
		m_commands.put("away", CmdAway.class);
		m_commands.put("back", CmdBack.class);
		m_commands.put("botplay", MgrCmdPlay.class);
		m_commands.put("botchoose", MgrCmdChoose.class);
		m_commands.put("botdeal7", MgrCmdDeal7.class);
		m_commands.put("botcleanup", MgrCmdCleanup.class);
		m_commands.put("botendgame", MgrCmdEndGame.class);
		m_commands.put("botcreategame", MgrCmdCreateGame.class);
		m_commands.put("botwarning", MgrCmdWarning.class);
		m_commands.put("botaway", MgrCmdAway.class);
		m_commands.put("botdelaycmd", MgrCmdDelay.class);
	}
	
	public Command create(String cmd) {
		Class<? extends Command> clazz = m_commands.get(cmd);
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
}
