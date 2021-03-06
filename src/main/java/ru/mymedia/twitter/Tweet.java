package ru.mymedia.twitter;

import java.io.Serializable;
import java.util.Date;

public class Tweet
	implements Serializable
{
	Tweet(String c, Date t, int f, int r, String l)
	{
		content = c;
		timestamp = t;
		favoriteCount = f;
		retweetCount = r;
		lang = l;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Tweet) {
			Tweet tweet = (Tweet)obj;
			return content.equals(tweet.content)
				&& timestamp.equals(tweet.timestamp);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return timestamp.hashCode();
	}

	@Override
	public String toString()
	{
		return String.format("Tweet(\"%s\", Date(\"%s\"), %d, %d, \"%s\")",
		                     content, timestamp, favoriteCount, retweetCount,
		                     lang);
	}

	public String getContent()
	{
		return content;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public int getScore()
	{
		return favoriteCount + 15 * retweetCount;
	}

	public String getLang()
	{
		return lang;
	}

	public int compareByTimestamp(Tweet another)
	{
		return timestamp.compareTo(another.timestamp);
	}

	public int compareByFavoriteCount(Tweet another)
	{
		return Integer.compare(favoriteCount, another.favoriteCount);
	}

	public int compareByRetweetCount(Tweet another)
	{
		return Integer.compare(retweetCount, another.retweetCount);
	}

	private String content;
	private Date timestamp;
	private int favoriteCount;
	private int retweetCount;
	private String lang;
}
