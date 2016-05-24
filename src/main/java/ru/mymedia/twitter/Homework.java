package ru.mymedia.twitter;

import java.util.Collection;
import java.util.Date;

import ru.mymedia.twitter.*;

class Homework
{
	public static void main(String[] argv)
	{
		Collection<Tweet> result = Accessor.search("RealMadrid", new Date(), 10);
		int counter = 0;
		for (Tweet tweet : result) {
			System.out.println(tweet);
			counter++;
		}
		System.out.println(counter);
	}
}
