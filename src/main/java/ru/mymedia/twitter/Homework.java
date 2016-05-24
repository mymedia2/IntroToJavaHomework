package ru.mymedia.twitter;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Date;

import ru.mymedia.twitter.*;

class Homework
{
	public static void main(String[] argv)
	{
		Collection<Tweet> result = Accessor.search("RealMadrid", new GregorianCalendar(2016, 4, 24, 21, 16, 51).getTime(), 10);
		int counter = 0;
		for (Tweet tweet : result) {
			System.out.println(tweet);
			counter++;
		}
		System.out.println(counter);
	}
}
