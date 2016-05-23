package ru.mymedia.twitter;

import java.util.HashSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

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
		throw new UnsupportedOperationException();
	}

	@Override
    public Map<String, Collection<Tweet>> groupByLang()
	{
		throw new UnsupportedOperationException();
	}

	@Override
    public Map<String, Double> getTagCloud(String lang)
	{
		throw new UnsupportedOperationException();
	}
}
