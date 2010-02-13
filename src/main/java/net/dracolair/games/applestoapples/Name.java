package net.dracolair.games.applestoapples;

public class Name implements Comparable<Name> {
	
	public String m_name = null;
	
	public Name(String name)  {
		m_name = name;
	}
	
	public String toString() {
		return m_name;
	}
	
	public boolean equals(String name) {
		return m_name.equals(name);
	}
	
	public boolean equals(Name name) {
		return m_name.equals(name.m_name);
	}
	
	public int compareTo(Name name) {
		return m_name.compareTo(name.m_name);
	}
	
}
