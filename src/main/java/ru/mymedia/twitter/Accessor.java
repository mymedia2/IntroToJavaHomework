package ru.mymedia.twitter;

import java.util.Date;
import java.text.SimpleDateFormat;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import ru.mymedia.twitter.TweetsSet;
import ru.mymedia.twitter.TweetsContainer;

public class Accessor
{
	/**
	 * @param query - строка поиска, передаваемая в Twitter4j
	 * @param since - дата, с которой начинается поиск (достаточно указать день, месяц и год)
	 * @param querySize - количество твитов в результирующей выборке
	 */
	public static TweetsContainer<Tweet> search(String query, Date since, int querySize)
	{
		assert querySize > 0;
		TweetsContainer<Tweet> result = new TweetsSet();
		try {
			Query request = new Query(query + " since:" + new SimpleDateFormat("yyyy-MM-dd").format(since));
			QueryResult response;
			int counter = 0;
			outerLoop: do {
				response = twitter.search(request);
				for (Status tweet : response.getTweets()) {
					result.add(new Tweet(tweet.getText(), tweet.getCreatedAt(),
										 tweet.getFavoriteCount(), tweet.getRetweetCount(),
										 tweet.getLang()));
					if (++counter >= querySize) {
						break outerLoop;
					}
				}
			} while ((request = response.nextQuery()) != null);
		} catch (TwitterException te) {
			te.printStackTrace();
		}
		return result;
	}

	private static Twitter twitter;

	static {
		// почему-то файл twitter4j.properties и переменные окружения не работают
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("3nVuSoBZnx6U4vzUxf5w")	// Twitter for Android
			.setOAuthConsumerSecret("Bcs59EFbbsdF6Sl9Ng71smgStWEGwXXKSjYvPVt7qys")
			.setOAuthAccessToken("704702959792353282-Bk7Z3Q0hpYGYKUNgcg4KNdjVTfWTvTq")
			.setOAuthAccessTokenSecret("dYL88iwgg4QuV0diZWTUma1SiHVPBdqzsUOKwZqAyeovg");
		twitter = new TwitterFactory(cb.build()).getInstance();
	}
}
