package LunchVote.util.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static LunchVote.util.DateTimeUtil.parseLocalDate;


/**
 * gkislin
 * 25.10.2016
 */
public class DateFormatter {
    public static class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String text, Locale locale) throws ParseException {
            return parseLocalDate(text);
        }

        @Override
        public String print(LocalDate lt, Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
}
