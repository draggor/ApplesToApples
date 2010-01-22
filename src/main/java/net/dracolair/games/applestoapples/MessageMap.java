package net.dracolair.games.applestoapples;

public final class MessageMap {
	
	private static final int m_CHANNEL = 0;
	private static final int m_NAME = 1;
	private static final int m_LOGIN = 2;
	private static final int m_HOSTNAME = 3;
	private static final int m_MESSAGE = 4;
	
	public static String CHANNEL(String[] msgMap) {
		return msgMap[m_CHANNEL];
	}
	
	public static String NAME(String[] msgMap) {
		return msgMap[m_NAME];
	}
	
	public static String LOGIN(String[] msgMap) {
		return msgMap[m_LOGIN];
	}
	
	public static String HOSTNAME(String[] msgMap) {
		return msgMap[m_HOSTNAME];
	}
	
	public static String MESSAGE(String[] msgMap) {
		return msgMap[m_MESSAGE];
	}
}
