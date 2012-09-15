package org.oyasunadev.minecraftmania.client.util;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 12:58 PM
 */
public class Server
{
	public Server(String name, int players, int maxplayers, String hash, int type)
	{
		this.name = name;
		this.players = players;
		this.maxplayers = maxplayers;
		this.hash = hash;
		this.type = type;

		address = null;
		port = 0;
	}

	public Server(String name, int players, int maxplayers, String hash, int type, String address, int port)
	{
		this.name = name;
		this.players = players;
		this.maxplayers = maxplayers;
		this.hash = hash;
		this.type = type;

		this.address = address;
		this.port = port;
	}

	private String name;
	private int players;
	private int maxplayers;
	private String hash;
	private int type;

	private String address;
	private int port;

	public String getURL()
	{
		return "http://www.minecraft.net/classic/play/" + hash;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPlayers()
	{
		return players;
	}

	public void setPlayers(int players)
	{
		this.players = players;
	}

	public int getMaxplayers()
	{
		return maxplayers;
	}

	public void setMaxplayers(int maxplayers)
	{
		this.maxplayers = maxplayers;
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}
}
