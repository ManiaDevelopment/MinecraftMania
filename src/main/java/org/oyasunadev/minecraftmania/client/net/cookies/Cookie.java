package org.oyasunadev.minecraftmania.client.net.cookies;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:02 PM
 */
public class Cookie
{
	public Cookie(URI uri, String header)
	{
		String[] attributes = header.split(";");
		String nameValue = attributes[0].trim();

		this.uri = uri;
		this.name = nameValue.substring(0, nameValue.indexOf('='));
		this.value = nameValue.substring(nameValue.indexOf('=') + 1);
		this.path = "/";
		this.domain = uri.getHost();

		for(int i = 1; i < attributes.length; i++)
		{
			nameValue = attributes[i].trim();

			int equals = nameValue.indexOf('=');
			if(equals == -1)
			{
				continue;
			}

			String name = nameValue.substring(0, equals);
			String value = nameValue.substring(equals + 1);

			if(name.equalsIgnoreCase("domain"))
			{
				String uriDomain = uri.getHost();
				if(uriDomain.equals(value))
				{
					this.domain = value;
				} else {
					if(!value.startsWith("."))
					{
						value = "." + value;
					}

					uriDomain = uriDomain.substring(uriDomain.indexOf('.'));
					if(!uriDomain.equals(value))
					{
						throw new IllegalArgumentException("Trying to set foreign cookie");
					}

					this.domain = value;
				}
			} else if (name.equalsIgnoreCase("path")) {
				this.path = value;
			} else if (name.equalsIgnoreCase("expires")) {
				try {
					this.expires = expiresFormat1.parse(value);
				} catch (ParseException e) {
					try {
						this.expires = expiresFormat2.parse(value);
					} catch (ParseException e2) {
						throw new IllegalArgumentException("Bad date format in header: " + value);
					}
				}
			}
		}
	}

	private final String name;
	private final String value;
	private final URI uri;
	private String domain;
	private Date expires;
	private String path;

	private static final DateFormat expiresFormat1 = new SimpleDateFormat("E, dd MMM yyyy k:m:s 'GMT'", Locale.US);
	private static final DateFormat expiresFormat2 = new SimpleDateFormat("E, dd-MMM-yyyy k:m:s 'GMT'", Locale.US);

	public boolean hasExpired()
	{
		if(expires == null)
		{
			return false;
		}

		Date now = new Date();

		return now.after(expires);
	}

	public boolean matches(URI uri)
	{
		if(hasExpired())
		{
			return false;
		}

		String path = uri.getPath();

		if(path == null)
		{
			path = "/";
		}

		return path.startsWith(this.path);
	}

	public boolean matchesEx(URI uri, String name)
	{
		if(hasExpired())
		{
			return false;
		}

		String domain = uri.getHost();

		if(domain.equalsIgnoreCase(this.domain) || domain.endsWith("." + this.domain))
		{
			String path = uri.getPath();

			if(path == null || path.length() == 0)
			{
				path = "/";
			}

			if(path.equals(this.path) || path.startsWith(this.path))
			{
				return this.name.equals(name);
			}
		}

		return false;
	}

	public String toString()
	{
		StringBuilder result = new StringBuilder(name);

		result.append("=");
		result.append(value);

		return result.toString();
	}

	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}

	public URI getUri()
	{
		return uri;
	}

	public String getDomain()
	{
		return domain;
	}

	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	public Date getExpires()
	{
		return expires;
	}

	public void setExpires(Date expires)
	{
		this.expires = expires;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public static DateFormat getExpiresFormat1()
	{
		return expiresFormat1;
	}

	public static DateFormat getExpiresFormat2()
	{
		return expiresFormat2;
	}
}
