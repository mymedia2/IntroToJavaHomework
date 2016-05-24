package ru.mymedia.twitter;

import java.util.Date;

import ru.mymedia.twitter.*;

class Homework
{
	public static void main(String[] argv)
	{
		for (Tweet tweet : Accessor.search("RealMadrid", new Date(), 10)) {
			System.out.println(tweet);
		}
	}
}
