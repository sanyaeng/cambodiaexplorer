/***/
package com.ce.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author mak sophea
 * 
 */
public class StringUtils {
	public static final String SPACE = " ";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String PIPE = "|";
	public static final String UTF8 = "UTF-8";
	public static final String BLANK = "";
	public static final String MINUS = "-";
	public static final String NBSP = "&nbsp;";
	public static final String NEW_LINE = "\n";
	public static final String DELIMITER = PIPE;

	public static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0x0100 + (bytes[i] & 0x00FF))
					.substring(1);

			if (hex.length() < 2) {
				sb.append("0");
			}

			sb.append(hex);
		}

		return sb.toString();
	}

	public static int count(String s, String text) {
		if ((s == null) || (text == null)) {
			return 0;
		}

		int count = 0;

		int pos = s.indexOf(text);

		while (pos != -1) {
			pos = s.indexOf(text, pos + text.length());

			count++;
		}

		return count;
	}

	public static boolean endsWith(String s, char end) {
		return endsWith(s, (new Character(end)).toString());
	}

	public static boolean endsWith(String s, String end) {
		if ((s == null) || (end == null)) {
			return false;
		}

		if (end.length() > s.length()) {
			return false;
		}

		String temp = s.substring(s.length() - end.length(), s.length());

		if (temp.equalsIgnoreCase(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static String lowerCase(String s) {
		if (s == null) {
			return null;
		} else {
			return s.toLowerCase();
		}
	}

	// public static String randomize(String s) {
	// return Randomizer.getInstance().randomize(s);
	// }

	public static String read(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line).append('\n');
		}

		br.close();

		return sb.toString().trim();
	}

	public static String replace(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}
		return s.replace(oldSub, newSub);
	}

	public static String replace(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		// The number 5 is arbitrary and is used as extra padding to reduce
		// buffer expansion

		StringBuilder sb = new StringBuilder(s.length() + 5 * newSub.length());

		char[] charArray = s.toCharArray();

		for (char c : charArray) {
			if (c == oldSub) {
				sb.append(newSub);
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String replace(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		int y = s.indexOf(oldSub);

		if (y >= 0) {

			// The number 5 is arbitrary and is used as extra padding to reduce
			// buffer expansion

			StringBuilder sb = new StringBuilder(s.length() + 5
					* newSub.length());

			int length = oldSub.length();
			int x = 0;

			while (x <= y) {
				sb.append(s.substring(x, y));
				sb.append(newSub);

				x = y + length;
				y = s.indexOf(oldSub, x);
			}

			sb.append(s.substring(x));

			return sb.toString();
		} else {
			return s;
		}
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs) {
		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replace(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	public static String replace(String s, String[] oldSubs, String[] newSubs,
			boolean exactMatch) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		if (!exactMatch) {
			replace(s, oldSubs, newSubs);
		} else {
			for (int i = 0; i < oldSubs.length; i++) {
				s = s.replaceAll("\\b" + oldSubs[i] + "\\b", newSubs[i]);
			}
		}

		return s;
	}

	public static String replaceFirst(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), String.valueOf(newSub));
	}

	public static String replaceFirst(String s, char oldSub, String newSub) {
		if ((s == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(String.valueOf(oldSub), newSub);
	}

	public static String replaceFirst(String s, String oldSub, String newSub) {
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		return s.replaceFirst(oldSub, newSub);
	}

	public static String replaceFirst(String s, String[] oldSubs,
			String[] newSubs) {

		if ((s == null) || (oldSubs == null) || (newSubs == null)) {
			return null;
		}

		if (oldSubs.length != newSubs.length) {
			return s;
		}

		for (int i = 0; i < oldSubs.length; i++) {
			s = replaceFirst(s, oldSubs[i], newSubs[i]);
		}

		return s;
	}

	/**
	 * Returns a string with replaced values. This method will replace all text
	 * in the given string, between the beginning and ending delimiter, with new
	 * values found in the given map. For example, if the string contained the
	 * text <code>[$HELLO$]</code>, and the beginning delimiter was
	 * <code>[$]</code>, and the ending delimiter was <code>$]</code>, and the
	 * values map had a key of <code>HELLO</code> that mapped to
	 * <code>WORLD</code>, then the replaced string will contain the text
	 * <code>[$WORLD$]</code>.
	 * 
	 * @param s
	 *            the original string
	 * @param begin
	 *            the beginning delimiter
	 * @param end
	 *            the ending delimiter
	 * @param values
	 *            a map of old and new values
	 * @return a string with replaced values
	 */
	public static String replaceValues(String s, String begin, String end,
			Map<String, String> values) {

		if ((s == null) || (begin == null) || (end == null) || (values == null)
				|| (values.size() == 0)) {

			return s;
		}

		StringBuilder sb = new StringBuilder(s.length());

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos, s.length()));

				break;
			} else {
				sb.append(s.substring(pos, x + begin.length()));

				String oldValue = s.substring(x + begin.length(), y);

				String newValue = values.get(oldValue);

				if (newValue == null) {
					newValue = oldValue;
				}

				sb.append(newValue);

				pos = y;
			}
		}

		return sb.toString();
	}

	public static String reverse(String s) {
		if (s == null) {
			return null;
		}

		char[] c = s.toCharArray();
		char[] reverse = new char[c.length];

		for (int i = 0; i < c.length; i++) {
			reverse[i] = c[c.length - i - 1];
		}

		return new String(reverse);
	}

	public static String shorten(String s) {
		return shorten(s, 20);
	}

	public static String shorten(String s, int length) {
		return shorten(s, length, "...");
	}

	public static String shorten(String s, String suffix) {
		return shorten(s, 20, suffix);
	}

	public static String shorten(String s, int length, String suffix) {
		if ((s == null) || (suffix == null)) {
			return null;
		}

		if (s.length() > length) {
			for (int j = length; j >= 0; j--) {
				if (Character.isWhitespace(s.charAt(j))) {
					length = j;

					break;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append(s.substring(0, length));
			sb.append(suffix);

			s = sb.toString();
		}

		return s;
	}

	public static String[] split(String s, String delimiter) {
		if (s == null || delimiter == null || isEmpty(s)) {
			return new String[] {};
		}
		s = s.trim();
		if (!s.endsWith(delimiter)) {
			StringBuilder sb = new StringBuilder();

			sb.append(s);
			sb.append(delimiter);

			s = sb.toString();
		}

		if (s.equals(delimiter)) {
			return new String[0];
		}

		List<String> nodeValues = new ArrayList<String>();

		if (delimiter.equals("\n") || delimiter.equals("\r")) {
			try {
				BufferedReader br = new BufferedReader(new StringReader(s));

				String line = null;

				while ((line = br.readLine()) != null) {
					nodeValues.add(line);
				}

				br.close();
			} catch (IOException ioe) {
				_log.error(ioe.getMessage());
			}
		} else {
			int offset = 0;
			int pos = s.indexOf(delimiter, offset);

			while (pos != -1) {
				nodeValues.add(new String(s.substring(offset, pos)));

				offset = pos + delimiter.length();
				pos = s.indexOf(delimiter, offset);
			}
		}

		return nodeValues.toArray(new String[nodeValues.size()]);
	}

	public static boolean[] split(String s, boolean x) {
		return split(s, COMMA, x);
	}

	public static boolean[] split(String s, String delimiter, boolean x) {
		String[] array = split(s, delimiter);
		boolean[] newArray = new boolean[array.length];

		for (int i = 0; i < array.length; i++) {
			boolean value = x;

			try {
				value = Boolean.valueOf(array[i]).booleanValue();
			} catch (Exception e) {
			}
			newArray[i] = value;
		}

		return newArray;
	}

	public static double[] split(String s, double x) {
		return split(s, COMMA, x);
	}

	public static double[] split(String s, String delimiter, double x) {
		String[] array = split(s, delimiter);
		double[] newArray = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			double value = x;

			try {
				value = Double.parseDouble(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static float[] split(String s, float x) {
		return split(s, COMMA, x);
	}

	public static float[] split(String s, String delimiter, float x) {
		String[] array = split(s, delimiter);
		float[] newArray = new float[array.length];

		for (int i = 0; i < array.length; i++) {
			float value = x;

			try {
				value = Float.parseFloat(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static int[] split(String s, int x) {
		return split(s, COMMA, x);
	}

	public static int[] split(String s, String delimiter, int x) {
		String[] array = split(s, delimiter);
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int value = x;

			try {
				value = Integer.parseInt(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static long[] split(String s, long x) {
		return split(s, COMMA, x);
	}

	public static long[] split(String s, String delimiter, long x) {
		String[] array = split(s, delimiter);
		long[] newArray = new long[array.length];

		for (int i = 0; i < array.length; i++) {
			long value = x;

			try {
				value = Long.parseLong(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static short[] split(String s, short x) {
		return split(s, COMMA, x);
	}

	public static short[] split(String s, String delimiter, short x) {
		String[] array = split(s, delimiter);
		short[] newArray = new short[array.length];

		for (int i = 0; i < array.length; i++) {
			short value = x;

			try {
				value = Short.parseShort(array[i]);
			} catch (Exception e) {
			}

			newArray[i] = value;
		}

		return newArray;
	}

	public static boolean startsWith(String s, char begin) {
		return startsWith(s, (new Character(begin)).toString());
	}

	public static boolean startsWith(String s, String start) {
		if ((s == null) || (start == null)) {
			return false;
		}

		if (start.length() > s.length()) {
			return false;
		}

		String temp = s.substring(0, start.length());

		if (temp.equalsIgnoreCase(start)) {
			return true;
		} else {
			return false;
		}
	}

	public static String upperCase(String s) {
		if (s == null) {
			return null;
		} else {
			return s.toUpperCase();
		}
	}

	public static String upperCaseFirstLetter(String s) {
		char[] chars = s.toCharArray();

		if ((chars[0] >= 97) && (chars[0] <= 122)) {
			chars[0] = (char) (chars[0] - 32);
		}

		return new String(chars);
	}

	public static String valueOf(Object obj) {
		return String.valueOf(obj);
	}

	public static String wrap(String text) {
		return wrap(text, 80, NEW_LINE);
	}

	public static String wrap(String text, int width, String lineSeparator) {
		if (text == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new StringReader(text));

			String s = BLANK;

			while ((s = br.readLine()) != null) {
				if (s.length() == 0) {
					sb.append(lineSeparator);
				} else {
					String[] tokens = s.split(SPACE);
					boolean firstWord = true;
					int curLineLength = 0;

					for (int i = 0; i < tokens.length; i++) {
						if (!firstWord) {
							sb.append(SPACE);
							curLineLength++;
						}

						if (firstWord) {
							sb.append(lineSeparator);
						}

						sb.append(tokens[i]);

						curLineLength += tokens[i].length();

						if (curLineLength >= width) {
							firstWord = true;
							curLineLength = 0;
						} else {
							firstWord = false;
						}
					}
				}
			}
		} catch (IOException ioe) {
			_log.error(ioe.getMessage());
		}

		return sb.toString();
	}

	public static String limit(String value, int size) {
		if (value != null && value.length() > size) {
			return value.substring(0, size);
		}

		return value;
	}

	public static boolean isNullAndBlank(String text) {
		return text == null || text.trim().equals("");
	}

	/**
	 * Returns <code>true</code> if the provided String is either
	 * <code>null</code> or the empty String <code>""</code>.
	 * <p>
	 * 
	 * @param value
	 *            the value to check
	 * 
	 * @return true, if the provided value is null or the empty String, false
	 *         otherwise
	 */
	public static boolean isEmpty(String value) {

		return (value == null) || (value.length() == 0);
	}

	/**
	 * Returns <code>true</code> if the provided String is either
	 * <code>null</code> or contains only white spaces.
	 * <p>
	 * 
	 * @param value
	 *            the value to check
	 * 
	 * @return true, if the provided value is null or contains only white
	 *         spaces, false otherwise
	 */
	public static boolean isEmptyOrWhitespaceOnly(String value) {

		return isEmpty(value) || (value.trim().length() == 0);
	}

	/**
	 * Returns <code>true</code> if the provided Objects are either both
	 * <code>null</code> or equal according to {@link Object#equals(Object)}.
	 * <p>
	 * 
	 * @param value1
	 *            the first object to compare
	 * @param value2
	 *            the second object to compare
	 * 
	 * @return <code>true</code> if the provided Objects are either both
	 *         <code>null</code> or equal according to
	 *         {@link Object#equals(Object)}
	 */
	public static boolean isEqual(Object value1, Object value2) {

		if (value1 == null) {
			return (value2 == null);
		}
		return value1.equals(value2);
	}

	/**
	 * Returns <code>true</code> if the provided String is neither
	 * <code>null</code> nor the empty String <code>""</code>.
	 * <p>
	 * 
	 * @param value
	 *            the value to check
	 * 
	 * @return true, if the provided value is not null and not the empty String,
	 *         false otherwise
	 */
	public static boolean isNotEmpty(String value) {

		return (value != null) && (value.length() != 0);
	}

	/**
	 * Returns <code>true</code> if the provided String is neither
	 * <code>null</code> nor contains only white spaces.
	 * <p>
	 * 
	 * @param value
	 *            the value to check
	 * 
	 * @return <code>true</code>, if the provided value is <code>null</code> or
	 *         contains only white spaces, <code>false</code> otherwise
	 */
	public static boolean isNotEmptyOrWhitespaceOnly(String value) {

		return (value != null) && (value.trim().length() > 0);
	}

	private static Log _log = LogFactory.getLog(StringUtils.class);

}
