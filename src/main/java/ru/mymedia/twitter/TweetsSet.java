package ru.mymedia.twitter;

import java.util.*;

import ru.mymedia.twitter.Tweet;
import ru.mymedia.twitter.TweetsContainer;

public class TweetsSet
	extends HashSet<Tweet>
	implements TweetsContainer<Tweet>
{
	@Override
    public Tweet getOldest()
	{
		Tweet max = iterator().next();
		for (Tweet t : this) {
			if (t.getTimestamp().after(max.getTimestamp())) {
				max = t;
			}
		}
		return max;
	}

	@Override
    public Tweet getTopRated()
	{
		Tweet max = iterator().next();
		for (Tweet t : this) {
			if (t.getScore() > max.getScore()) {
				max = t;
			}
		}
		return max;
	}

	@Override
	public boolean remove(Tweet t)
	{
		// почему-то в HashSet есть только remove, принимающий аргумент типа Object
		return super.remove(t);
	}

	@Override
    public void sort(Comparator<Tweet> comparator)
	{
		// непонятно, как сортировать HashSet на месте
		Tweet[] allTweets = toArray(new Tweet[0]);
		Arrays.sort(allTweets, comparator);
		throw new UnsupportedOperationException();
	}

	@Override
    public Map<String, Collection<Tweet>> groupByLang()
	{
		Map<String, Collection<Tweet>> result = new HashMap<String, Collection<Tweet>>();
		for (Tweet tweet : this) {
			Collection<Tweet> byLang = result.get(tweet.getLang());
			if (byLang == null) {
				result.put(tweet.getLang(), byLang = new TweetsSet());
			}
			byLang.add(tweet);
		}
		return result;
	}

	@Override
    public Map<String, Double> getTagCloud(String lang)
	{
		Map<String, Integer> tags = new HashMap<String, Integer>();
		for (Tweet tweet : groupByLang().get(lang)) {
			for (String word : tweet.getContent().toLowerCase().split("\\W")) {
				if (word.length() > 3) {
					Integer counter = tags.get(word);
					if (counter == null) {
						tags.put(word, 1);
					} else {
						counter++;
					}
				}
			}
		}
		return normalize(tags);
	}

	private Map<String, Double> normalize(Map<String, Integer> raw)
	{
		int sum = 0;
		for (Integer count : raw.values()) {
			sum += count;
		}
		Map<String, Double> result = new HashMap<String, Double>();
		for (Map.Entry<String, Integer> el : raw.entrySet()) {
			result.put(el.getKey(), el.getValue() / (double)sum);
		}
		return result;
	}
}
