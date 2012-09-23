package org.oyasunadev.minecraftmania.client.gui;

import org.oyasunadev.minecraftmania.client.util.HTTPUtil;
import org.oyasunadev.minecraftmania.client.util.ParseServers;
import org.oyasunadev.minecraftmania.client.util.Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:09 PM
 */
public class ServersFrame extends JFrame implements MouseListener
{
	public ServersFrame() throws IOException
	{
		setTitle("MinecraftMania");

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		File iconFile = new File("resources/images/icon.png");
		Image iconImage = ImageIO.read(iconFile);

		setIconImage(iconImage);

		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);

		setLayout(layout);

		final JTextField filterField = new JTextField(40);
		JButton filterButton = new JButton("Filter");
		{
			JPanel filterPanel = new JPanel();

			filterPanel.setLayout(new BorderLayout());
			filterPanel.setBorder(new EmptyBorder(0, 10, 0, 10));

			/*final JTextField filterField = new JTextField(40);
			JButton filterButton = new JButton("Filter");*/

			filterPanel.add(new JLabel("Filter:"), BorderLayout.WEST);
			filterPanel.add(filterField, BorderLayout.CENTER);
			filterPanel.add(filterButton, BorderLayout.EAST);

			/*filterButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(filterField.getText().length() > 0)
					{
						sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));
					} else {
						sorter.setRowFilter(null);
					}
				}
			});*/

			add(filterPanel);
		}

		MyTableModel model = new MyTableModel();

		sorter = new TableRowSorter<MyTableModel>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		table.setPreferredScrollableViewportSize(new Dimension(500, 700));
		table.setFillsViewportHeight(true);

		/*sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		sorter.setSortable(2, false);
		sorter.setSortable(3, false);*/

		table.getColumnModel().getColumn(0).setHeaderValue("Server");
		table.getColumnModel().getColumn(1).setHeaderValue("Players");
		table.getColumnModel().getColumn(2).setHeaderValue("Max Players");
		table.getColumnModel().getColumn(3).setHeaderValue("Hash");

		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(0).setWidth(350);

		table.getColumnModel().getColumn(3).setWidth(0);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);

		table.getColumnModel().getColumn(4).setWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane);

		table.addMouseListener(this);

		final TableRowSorter trs = new TableRowSorter(table.getModel());

		for(int i = 1; i < table.getColumnCount() - 1; i++)
		{
			trs.setComparator(i, new Comparator<Object>()
			{
				@Override
				public int compare(Object o1, Object o2)
				{
					Integer i1 = (Integer)o1;
					Integer i2 = (Integer)o2;

					return i1.compareTo(i2);
				}
			});
		}

		table.setRowSorter(trs);

		filterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(filterField.getText().length() > 0)
				{
					trs.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));
				} else {
					trs.setRowFilter(null);
				}
			}
		});

		pack();

		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount() == 2)
		{
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();

			ParseServers.getServers().clear();

			for(int i = 0; i < target.getRowCount(); i++)
			{
				ParseServers.getServers().add(new Server(String.valueOf(target.getValueAt(i, 0)),
						Integer.valueOf(String.valueOf(target.getValueAt(i, 1))),
						Integer.valueOf(String.valueOf(target.getValueAt(i, 2))),
						String.valueOf(target.getValueAt(i, 3)), Integer.valueOf(String.valueOf(target.getValueAt(i, 4)))));
			}

			String serverHASH = ParseServers.getServers().get(row).getHash();
			String play = HTTPUtil.fetchUrl("http://www.minecraft.net/classic/play/" + serverHASH, "", "http://www.minecraft.net/classic/list");

			String username_ = HTTPUtil.getParameterOffPage(play, "username");
			String sessionid = HTTPUtil.getParameterOffPage(play, "sessionid");
			boolean haspaid = Boolean.valueOf(HTTPUtil.getParameterOffPage(play, "haspaid"));
			String server = HTTPUtil.getParameterOffPage(play, "server");
			int port = Integer.valueOf(HTTPUtil.getParameterOffPage(play, "port"));
			String mppass = HTTPUtil.getParameterOffPage(play, "mppass");

			/*for(int i = 0; i < ParseServers.getServers().size(); i++)
			{
				Server server1 = ParseServers.getServers().get(i);

				if(server1.getHash().equals(serverHASH))
				{
					if(server1.getAddress() != null)
					{
						String play1 = HTTPUtil.fetchUrl("http://www.minecraft.net/classic/play/" + "765bc31ca9f317c8da03c446ebee864d", "", "http://www.minecraft.net/classic/list");

						username_ = HTTPUtil.getParameterOffPage(play, "username");
						sessionid = HTTPUtil.getParameterOffPage(play, "sessionid");
						haspaid = Boolean.valueOf(HTTPUtil.getParameterOffPage(play, "haspaid"));
						server = server1.getAddress();
						port = server1.getPort();
						mppass = HTTPUtil.getParameterOffPage(play, "mppass");
					}
				}
			}*/

			MPFrame mpFrame = new MPFrame(username_, sessionid, haspaid, server, port, mppass);

			mpFrame.setVisible(true);

			mpFrame.startMinecraft();
			mpFrame.finish();

			dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	private static JTable table;
	private static TableRowSorter<MyTableModel> sorter;

	public static JTable getTable()
	{
		return table;
	}

	public static TableRowSorter<MyTableModel> getSorter()
	{
		return sorter;
	}

	public class MyTableModel extends AbstractTableModel
	{
		public MyTableModel()
		{
			int row = 0;

			for(Server s : ParseServers.getServers())
			{
				data[row][0] = ParseServers.getServers().get(row).getName();
				data[row][1] = ParseServers.getServers().get(row).getPlayers();
				data[row][2] = ParseServers.getServers().get(row).getMaxplayers();
				data[row][3] = ParseServers.getServers().get(row).getHash();
				data[row][4] = ParseServers.getServers().get(row).getType();

				row++;
			}
		}

		@Override
		public int getRowCount()
		{
			return data.length;
		}

		@Override
		public int getColumnCount()
		{
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex)
		{
			return data[rowIndex][columnIndex];
		}

		private String[] columnNames = {"Name", "Players", "Max Players", "Hash", "Type"};
		private Object[][] data = new Object[ParseServers.getServersTable().size()][5];
	}
}
