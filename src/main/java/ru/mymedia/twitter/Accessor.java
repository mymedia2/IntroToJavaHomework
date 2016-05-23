package ru.mymedia.twitter;

import java.util.*;
import twitter4j.*;

import ru.mymedia.twitter.*;

public class Accessor
{
	/**
	 * @param query - строка поиска, передаваемая в Twitter4j
	 * @param since - дата, с которой начинается поиск (достаточно указать день, месяц и год)
	 * @param querySize - количество твитов в результирующей выборке
	 */
	public static Collection<Tweet> search(String query, Date since, int querySize)
		throws TwitterException
	{
		TweetsContainer<Tweet> result = new TweetsSet();
		Twitter twitter = new TwitterFactory().getInstance();
		Query request = new Query(query);
		QueryResult response;
		do { // TODO: избавиться от do … while
			response = twitter.search(request);
			for (Status tweet : response.getTweets()) {
				result.add(new Tweet(tweet.getText(), tweet.getCreatedAt(),
				                     tweet.getFavoriteCount(), tweet.getRetweetCount(),
				                     tweet.getLang()));
			}
		} while ((request = response.nextQuery()) != null);
		return result;
	}
}
