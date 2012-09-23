package org.oyasunadev.minecraftmania.client.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 12:58 PM
 */
public class HTTPUtil
{
	public static String fetchUrl(String url, String params)
	{
		return fetchUrl(url, params, url);
	}

	public static String fetchUrl(String url, String params, String referrer)
	{
		try {
			URLConnection conn = makeConnection(url, params, referrer, true);

			if(conn == null)
			{
				return "ERROR!";
			}

			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String line;

			while((line = reader.readLine()) != null)
			{
				buffer.append(line);
				buffer.append("\n");
			}

			reader.close();

			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String rawFetchUrl(String url, String params, String referrer)
	{
		try {
			URLConnection conn = makeConnection(url, params, referrer, true);
			InputStream in = getInputStream(conn);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String str;

			while((str = reader.readLine()) != null)
			{
				buffer.append(str);
				buffer.append("\n");
			}

			reader.close();

			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	private static InputStream getInputStream(URLConnection paramURLConnection) throws IOException
	{
		InputStream stream = paramURLConnection.getInputStream();
		String encoding = paramURLConnection.getContentEncoding();

		if(encoding != null)
		{
			encoding = encoding.toLowerCase();

			if(encoding.contains("gzip"))
			{
				stream = new GZIPInputStream(stream);
			} else if (encoding.contains("deflate")) {
				stream = new InflaterInputStream(stream);
			}
		}

		return stream;
	}

	private static URLConnection makeConnection(String url, String params, String referrer, boolean paramBoolean) throws IOException
	{
		URLConnection conn = new URL(url).openConnection();

		conn.addRequestProperty("Referer", referrer);

		conn.setReadTimeout(40000);
		conn.setConnectTimeout(15000);
		conn.setDoInput(true);

		conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.addRequestProperty("Accept-Language", "en-us,en;q=0.5");
		conn.addRequestProperty("Connection", "keep-alive");

		if(params.length() > 0)
		{
			conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.addRequestProperty("Content-Length", Integer.toString(params.length()));
			conn.setDoOutput(true);

			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());

			//System.out.println(params);

			out.write(params);
			out.flush();
			out.close();
		}

		conn.connect();

		return conn;
	}

	public static String getParameterOffPage(String page, String param)
	{
		String str = "param name=\"" + param + "\" value=\"";
		int index = page.indexOf(str);

		if(index > 0)
		{
			index += str.length();

			int index2 = page.indexOf("\"", index);

			if(index2 > 0)
			{
				return page.substring(index, index2);
			}
		}

		return "";
	}
}
