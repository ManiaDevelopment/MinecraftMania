package org.oyasunadev.minecraftmania.client.net.cookies;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:02 PM
 */
public class CookieList extends CookieHandler
{
	public CookieList()
	{
	}

	@Override
	public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException
	{
		StringBuilder cookies = new StringBuilder();
		Object object = cookieJars.iterator();

		do
		{
			if(!((Iterator) object).hasNext())
			{
				break;
			}

			Cookie cookie = (Cookie)((Iterator) object).next();
			if(cookie.hasExpired())
			{
				((Iterator) object).remove();
			} else if (cookie.matches(uri)) {
				if(cookies.length() > 0)
				{
					cookies.append(", ");
				}

				cookies.append(cookie.toString());
			}
		} while(true);

		object = new HashMap(requestHeaders);

		if(cookies.length() > 0)
		{
			List<String> list = Collections.singletonList(cookies.toString());

			((Map) object).put("Cookie", list);
		}

		//System.out.println("CookieMap: " + object);

		return Collections.unmodifiableMap((Map) object);
	}

	@Override
	public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException
	{
		List<String> setCookieList = responseHeaders.get("Set-Cookie");

		if(setCookieList != null)
		{
			for(final String item : setCookieList)
			{
				Cookie cookie = new Cookie(uri, item);
				for(Cookie existingCookie : cookieJars)
				{
					if((cookie.getUri().equals(existingCookie.getUri())) && (cookie.getName().equals(existingCookie.getName())))
					{
						cookieJars.remove(existingCookie);

						break;
					}
				}

				cookieJars.add(cookie);
			}
		}
	}

	private final List<Cookie> cookieJars = new LinkedList<Cookie>();

	public Cookie getCookie(URI uri, String name)
	{
		for (Cookie cookie : cookieJars)
		{
			if(cookie.matchesEx(uri, name))
			{
				return cookie;
			}
		}
		return null;
	}
}
