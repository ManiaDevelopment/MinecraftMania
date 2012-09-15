package org.oyasunadev.minecraftmania.client.gui;

import org.oyasunadev.minecraftmania.client.util.HTTPUtil;
import org.oyasunadev.minecraftmania.client.util.ParseServers;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 12:48 PM
 */
public class LoginFrame extends JFrame implements ActionListener, KeyListener
{
	public LoginFrame() throws IOException
	{
		setTitle("MinecraftMania");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		File iconFile = new File("resources/images/icon.png");
		Image iconImage = ImageIO.read(iconFile);

		setIconImage(iconImage);

		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);

		setLayout(layout);

		{
			JPanel panel = new JPanel();
			File logoFile = new File("resources/images/logo.png");
			Image logoImage = ImageIO.read(logoFile);
			Icon logoIcon = new ImageIcon(logoImage);
			JLabel logoLabel = new JLabel(logoIcon);

			{
				panel.add(logoLabel);
			}

			{
				add(panel);
			}
		}

		{
			add(Box.createVerticalStrut(10));
		}

		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Use your minecraft.net login...");

			{
				panel.add(label);
			}

			{
				add(panel);
			}
		}

		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Username:");

			{
				panel.add(label);
				panel.add(Box.createHorizontalStrut(10));
				panel.add(usernameField = new JTextField(20));
			}

			{
				add(panel);
			}
		}

		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Password:");

			{
				panel.add(label);
				panel.add(Box.createHorizontalStrut(10));
				panel.add(passwordField = new JPasswordField(20));
			}

			{
				add(panel);
			}
		}

		{
			JPanel panel = new JPanel();
			JLabel labelWidth = new JLabel("Width:");
			JLabel labelHeight = new JLabel("Height:");
			//Font font = new Font("Arial", Font.BOLD, 12);
			//labelWidth.setFont(font);
			//labelHeight.setFont(font);

			{
				panel.setOpaque(false);
			}

			{
				panel.add(labelWidth);
				panel.add(Box.createHorizontalStrut(28));
				panel.add(widthField = new JTextField(5));
				panel.add(Box.createHorizontalStrut(14));
				panel.add(labelHeight);
				panel.add(Box.createHorizontalStrut(5));
				panel.add(heightField = new JTextField(5));
			}

			{
				widthField.addKeyListener(this);
				heightField.addKeyListener(this);
			}

			{
				add(panel);
			}
		}

		{
			JPanel panel = new JPanel();

			{
				panel.add(rememberCheck = new JCheckBox("Remember login?"));
				panel.add(Box.createHorizontalStrut(10));
				panel.add(loginButton = new JButton("Login!"));
			}

			{
				add(panel);
			}
		}

		{
			loginButton.addActionListener(this);
		}

		properties = new Properties();
		File propertiesFile = new File("settings.properties");
		InputStream is = new FileInputStream(propertiesFile);

		properties.load(is);

		String propertiesUsername = properties.getProperty("username");
		String propertiesPassword = properties.getProperty("password");
		String propertiesWidth = properties.getProperty("width");
		String propertiesHeight = properties.getProperty("height");

		if(propertiesUsername != null)
		{
			usernameField.setText(propertiesUsername);
		}

		if(propertiesPassword != null)
		{
			passwordField.setText(propertiesPassword);
		}

		if(propertiesWidth != null)
		{
			widthField.setText(propertiesWidth);
		}

		if(propertiesHeight != null)
		{
			heightField.setText(propertiesHeight);
		}

		if(propertiesUsername != null && propertiesPassword != null && propertiesWidth != null && propertiesHeight != null)// && properties.getProperty("lastserver") != null)
		{
			rememberCheck.setSelected(true);
		}

		pack();
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Login!"))
		{
			String username = usernameField.getText();
			String password = new String(passwordField.getPassword());
			int width = Integer.valueOf(widthField.getText());
			int height = Integer.valueOf(heightField.getText());

			if(username.length() != 0)
			{
				if(password.length() != 0)
				{
					if(width >= 854)
					{
						if(height >= 480)
						{
							try {
								if(ParseServers.login(username, password, width, height, rememberCheck, properties))
								{
									System.out.println(username + ", " + password);

									ParseServers.parseServers(HTTPUtil.rawFetchUrl("http://www.minecraft.net/classic/list", "", "http://www.minecraft.net"));

									System.out.println(ParseServers.getServersTable().size() + " servers.");

									ServersFrame serversFrame = new ServersFrame();
									serversFrame.setVisible(true);

									JTable table = ServersFrame.getTable();

									/*for(int i = 0; i < table.getRowCount(); i++)
									{
										TableCellRenderer renderer = table.getCellRenderer(i, 0);
										Component comp = table.prepareRenderer(renderer, i, 0);

										if(ServersFrame.getSorter().getModel().getValueAt(i, 4) == (Object)0)
										{
											comp.setBackground(new Color(0, 255, 0));
										} else if(ServersFrame.getSorter().getModel().getValueAt(i, 4) == (Object)1) {
											comp.setBackground(new Color(255, 0, 0));
										}
									}*/

									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Unable to login to minecraft.net.");
								}
							} catch (URISyntaxException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "The height must be equal to or greater than 480.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "The width must be equal to or greater than 854.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You must type in a password.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "You must type in a username.");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	private Properties properties;

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField widthField;
	private JTextField heightField;
	private JCheckBox rememberCheck;
	private JButton loginButton;
}
