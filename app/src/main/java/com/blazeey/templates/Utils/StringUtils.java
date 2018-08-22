package com.blazeey.templates.Utils;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class StringUtils {
    public static final String EMAIL_REGEX = "^[\\w_.]+@[a-zA-Z]+(.[a-zA-Z]+)+$";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
    public static final SimpleDateFormat viewableFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault());
    private final static String NON_THIN = "[^iIl1\\.,']";

    /**
     * Returns the width of the text
     * @param str input string
     * @return width of the string
     */
    private static int textWidth(String str) {
        return str.length() - str.replaceAll(NON_THIN, "").length() / 2;
    }

    /**
     * Ellipsizes the string such that it's length is not more than a specified maximum value
     * @param text input string
     * @param max maximum length of the string
     * @return ellipsized string
     */
    public static String ellipsize(String text, int max) {

        if (textWidth(text) <= max)
            return text;

        // Start by chopping off at the word before max
        // This is an over-approximation due to thin-characters...
        int end = text.lastIndexOf(' ', max - 3);

        // Just one long word. Chop it off.
        if (end == -1)
            return text.substring(0, max - 3) + "...";

        // Step forward as long as textWidth allows.
        int newEnd = end;
        do {
            end = newEnd;
            newEnd = text.indexOf(' ', end + 1);

            // No more spaces.
            if (newEnd == -1)
                newEnd = text.length();

        } while (textWidth(text.substring(0, newEnd) + "...") < max);

        return text.substring(0, end) + "...";
    }

    /**
     * Rounds the number to the specified decimal place
     * @param d input number
     * @param decimalPlace number of digits int the fractional part
     * @return rounded off number
     */
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    /**
     * Sort the words using length
     * @param word the list of words to be sorted
     */
    private static void sortUsingLength(List<String> word) {
        for (int i = 0; i < word.size(); i++) {
            for (int j = 0; j < word.size() - 1 - i; j++) {
                if (word.get(j).length() < word.get(j + 1).length())
                    Collections.swap(word, j, j + 1);
            }
        }
    }

    /**
     * Split the sentence using the pattern specified
     * @param sentence the sentence which is to be split
     * @param pattern the pattern using which the sentence is to be split
     * @return list of strings which is split using the pattern
     */
    private static List<String> splitWithOccurrence(String sentence, String pattern) {
        List<String> split = new ArrayList<>();
        int[] changeArray = new int[sentence.length() + 1];
        int index = 0;
        while (index >= 0) {
            index = indexOf(sentence, pattern, index);
            if (index != -1) {
                changeArray[index]++;
                int nonCharIndex = nonCharIndex(sentence, index + pattern.length());
                changeArray[nonCharIndex]++;
                index = index + pattern.length();
            }
        }
//        Log.v("changeArray", Arrays.toString(changeArray));
        int start = 0;
        for (int i = 1; i < sentence.length(); i++) {
            if (changeArray[i] > 0) {
                split.add(sentence.substring(start, i));
                start = i;
            }
        }
        split.add(sentence.substring(start, sentence.length()));

//        Log.v("Occur", split.toString());
        return split;
    }

    /**
     * Returns the first non character index in the given word
     * @param word the word for which it the first non char is to be found
     * @param position the position from which the word is to be checked
     * @return returns the position of first non char index. If not found returns the length of the word
     */
    private static int nonCharIndex(String word, int position) {
        for (int i = position; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                return i;
            }
        }
        return word.length();
    }

    /**
     * Returns the index of the the word in the sentence from the starting index specified in @{index}
     * @param sentence the sentence where the index is to be found
     * @param word the word for which the index is to be found
     * @param index the starting index from which the word is to be found in the sentence
     * @return returns the index of the word id present. Else returns -1
     */
    public static int indexOf(String sentence, String word, int index) {
        sentence = sentence.toLowerCase();
        word = word.toLowerCase();
        index = sentence.indexOf(word, index);
        if (index == 0)
            return 0;
        else if (index == -1)
            return -1;
        else if (!Character.isAlphabetic(sentence.charAt(index - 1)))
            return index;
        else
            return -1;
    }

    /**
     * Returns if the pattern is found in the string
     * @param word the word
     * @param pattern the pattern to find
     * @return returns true if present else returns false
     */
    public static boolean isFound(String word, String pattern) {
        String tempWord = word.toLowerCase();
        String tempPattern = pattern.toLowerCase();
        return tempWord.startsWith(tempPattern);
    }

    public static boolean contains(String word, String pattern) {
        String tempWord = word.toLowerCase();
        String tempPattern = pattern.toLowerCase();
        if (tempWord.contains(tempPattern)) {
            int index = tempWord.indexOf(tempPattern);
            return index == 0 || !Character.isAlphabetic(tempWord.charAt(index - 1));
        }
        return false;
    }

    public static List<String> getWordsFromJSON(JSONArray array) {

        List<String> wordList = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                wordList.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return wordList;
    }
}
