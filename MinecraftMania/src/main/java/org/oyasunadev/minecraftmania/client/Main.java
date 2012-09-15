package org.oyasunadev.minecraftmania.client;

import org.oyasunadev.minecraftmania.client.gui.LoginFrame;
import org.oyasunadev.minecraftmania.client.net.cookies.CookieList;

import javax.swing.*;
import java.io.IOException;
import java.net.CookieHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:02 PM
 */
public class Main
{
	public static void main(String[] args)
	{
		try {
			new Main();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Main() throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException, IOException
	{
		main = this;

		cookieList = new CookieList();
		CookieHandler.setDefault(cookieList);

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		LoginFrame loginFrame = new LoginFrame();

		loginFrame.setVisible(true);
	}

	private static Main main;

	private static CookieList cookieList;

	public static Main getMain()
	{
		return main;
	}

	public static CookieList getCookieList()
	{
		return Main.cookieList;
	}
}
