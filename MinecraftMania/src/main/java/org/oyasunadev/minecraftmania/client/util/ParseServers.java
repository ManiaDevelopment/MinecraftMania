package org.oyasunadev.minecraftmania.client.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.oyasunadev.minecraftmania.client.Main;
import org.oyasunadev.minecraftmania.client.gui.ServersFrame;
import org.oyasunadev.minecraftmania.client.net.cookies.Cookie;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 12:57 PM
 */
public class ParseServers
{
	private static List<Server> servers = new ArrayList<Server>();
	private static List<String[]> serversTable = new ArrayList<String[]>();

	public static int width = 854;
	public static int height = 480;

	public static boolean login(String username, String password, int width, int height, JCheckBox rememberCheck, Properties properties) throws URISyntaxException, IOException
	{
		ParseServers.width = width;
		ParseServers.height = height;

		String result = "";

		result = HTTPUtil.fetchUrl("https://www.minecraft.net/login", "", "https://www.minecraft.net");

		if(!result.equals(""))
		{
			String[] responseParameters = result.split(":");

			HTTPUtil.fetchUrl("https://www.minecraft.net/login", "", "https://www.minecraft.net");

			try {
				result = HTTPUtil.fetchUrl("https://www.minecraft.net/login", "username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8"), "http://www.minecraft.net");
			} catch (UnsupportedEncodingException e) {
				JOptionPane.showMessageDialog(null, "UTF-8 not supported.");

				e.printStackTrace();

				return false;
			}

			Cookie cookie = Main.getCookieList().getCookie(new URI("https://www.minecraft.net"), "PLAY_SESSION");

			System.out.println("1");

			if(cookie != null)
			{
				result = HTTPUtil.fetchUrl("http://www.minecraft.net", "", "https://www.minecraft.net/login");

				System.out.println("2");

				if(result.contains("Logged in as"))
				{
					System.out.println("3");

					if(rememberCheck.isSelected())
					{
						File propertiesFile = new File("settings.properties");
						OutputStream os = new FileOutputStream(propertiesFile);

						properties.setProperty("username", username);
						properties.setProperty("password", password);
						properties.setProperty("width", String.valueOf(ParseServers.width));
						properties.setProperty("height", String.valueOf(ParseServers.height));

						properties.store(os, null);
					}

					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Your username and/or password is incorrect. Try again...");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Cannot find the cookie: " + "PLAY_SESSION.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Unable to connect to minecraft.net...");
		}

		return false;
	}

	public static void parseServers(String source)
	{
		for(int i = source.indexOf("<a href=\""); i != -1;)
		{
			i = source.indexOf("classic/play/", i);
			if(i == -1)
				break;
			String hash = source.substring(i + 13, source.indexOf("\"", i)); // hash
			i = source.indexOf(">", i) + 1;
			String name = source.substring(i, source.indexOf("</a>", i)); // name
			i = source.indexOf("<td>", i) + 4;
			String players = source.substring(i, source.indexOf("</td>", i)); // players
			i = source.indexOf("<td>", i) + 4;
			String maxplayers = source.substring(i, source.indexOf("</td>", i)); // max players
			//i = source.indexOf("<td>", i) + 4;
			//String uptime = source.substring(i, source.indexOf("</td>", i)); // uptime
			//i = source.indexOf(">", i) + 4;
			//String flag = source.substring(i, source.indexOf("</div>", i)); // flag

			name = decodeServerName(name);

			//System.out.println(flag);

			/*System.out.println(name + ":");
			System.out.println(" - Hash: " + hash);
			System.out.println(" - Players: " + players);
			System.out.println(" - Max Players: " + maxplayers);*/

			Server server = new Server(name, Integer.valueOf(players), Integer.valueOf(maxplayers), hash, 0);
			String[] serverForTable = {server.getName(), String.valueOf(server.getPlayers()), String.valueOf(server.getMaxplayers())};

			servers.add(server);
			serversTable.add(serverForTable);
		}

		//parseWoMDirect(HTTPUtil.rawFetchUrl("http://direct.worldofminecraft.com/classic.txt", "", ""));
	}

	public static void parseWoMDirect(String source)
	{
		String[] servers1 = source.split("\n");

		String hash = "";
		int players = 0;
		int maxplayers = 0;
		String name = "";

		String address = "";
		int port = 0;

		String[] strings; // = new String[8];

		for(String s : servers1)
		{
			strings = s.split("\t");

			boolean add = true;

			// TODO: Doesn't get every server.

			if(strings.length == 8 && !strings[5].equals(""))
			{
				hash = strings[0];
				players = Integer.valueOf(strings[1]);
				maxplayers = Integer.valueOf(strings[2]);
				name = strings[7];

				address = strings[4];
				port = Integer.valueOf(strings[5]);

				Server server = new Server(name, players, maxplayers, hash, 1, address, port);
				String[] serverForTable = {server.getName(), String.valueOf(server.getPlayers()), String.valueOf(server.getMaxplayers())};

				for(int i = 0; i < servers.size(); i++)
				{
					if(servers.get(i).getHash().equals(server.getHash()))
					{
						add = false;
					}
				}

				if(add)
				{
					servers.add(server);
					serversTable.add(serverForTable);
				}
			}

			//JTable table = ServersFrame.getTable();
			//int row = serversTable.indexOf(serverForTable);
			//TableCellRenderer renderer = table.getCellRenderer(row, 0);
			//Component comp = table.prepareRenderer(renderer, row, 0);

			//comp.setBackground(new Color(255, 0, 0));
		}
	}

	private static String decodeServerName(String name)
	{
		String name1 = name;

		String[] unscape = {"lt", "gt", "amp"};

		/*name1 = name1.replaceAll("&lt;", "&");
		name1 = name1.replaceAll("&gt;", "&");
		name1 = name1.replaceAll("&amp;", "&");*/

		String s1;

		for(String s : unscape)
		{
			s1 = "&" + s + ";";

			name1 = name1.replaceAll(s1, "&");
		}

		return StringEscapeUtils.unescapeHtml(name1);
	}

	public static List<Server> getServers()
	{
		return servers;
	}

	public static void setServers(List<Server> servers)
	{
		ParseServers.servers = servers;
	}

	public static List<String[]> getServersTable()
	{
		return serversTable;
	}

	public static void setServersTable(List<String[]> serversTable)
	{
		ParseServers.serversTable = serversTable;
	}
}
