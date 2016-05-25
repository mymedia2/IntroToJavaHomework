package ru.mymedia.twitter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import ru.mymedia.twitter.*;

class Homework
{
	public static void main(String[] argv)
	{
		Calendar fromDate = new GregorianCalendar();
		fromDate.add(Calendar.MONTH, -1);
		printData(Accessor.search("RealMadrid", fromDate.getTime(), 10).groupByLang(),
		          Accessor.search("AtleticoMadrid", fromDate.getTime(), 10).groupByLang());
	}

	private static void printData(Map<String, Collection<Tweet>> real,
	                              Map<String, Collection<Tweet>> atletico)
	{
		System.out.println("lang\treal\tatletico\treal_proportion\tatletico_proportion");
		Set<String> allLangs = new HashSet<String>(real.keySet());
		allLangs.addAll(atletico.keySet());
		for (String lang : allLangs) {
			// в JDK 1.7 пока нельзя для словаря указать значение по умолчанию
			Collection<Tweet> realForLang = real.get(lang);
			Collection<Tweet> atleticoForLang = atletico.get(lang);
			int realRating = realForLang != null ? realForLang.size() : 0;
			int atleticoRating = atleticoForLang != null ? atleticoForLang.size() : 0;
			System.out.printf("%s\t%d\t%d\t%f\t%f\n", lang, realRating, atleticoRating,
			                  (double)realRating / (realRating + atleticoRating),
			                  (double)atleticoRating / (realRating + atleticoRating));
		}
	}
}
