package com.example.moksleivis.knygalaboras.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by moksleivis on 2018-01-19.
 */

public class Validation {

    private static final String VALID_BOOKNAME_ADD_REGEX ="^[A-Za-z0-9_ ąčęėįšųĄČĘĖĮŠŲŪ]{1,100}$";
    private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z0-9.-_ąčęėįšųĄČĘĖĮŠŲŪ ]{5,30}$";
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    private static final String VALID_YEAR = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
    private static final String VALID_PAGES = "^[0-9]{1,4}$";
    private static final String VALID_AUTHORNAME = "^[A-Za-z ąčęėįšųĄČĘĖĮŠŲŪ]{1,1000}$";

    public static boolean isValidCredentials(String credentials){
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }

    public static boolean isValidEmail(String email){
        Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    public static boolean isValidYear(String year){
        Pattern yearPattern = Pattern.compile(VALID_YEAR);
        Matcher yearMatcher = yearPattern.matcher(year);
        return yearMatcher.find();
    }
    public static boolean isValidBookNameForAdd(String Pokemon){
        Pattern PokemonNamePattern = Pattern.compile(VALID_BOOKNAME_ADD_REGEX);
        Matcher PokemonNameMatcher = PokemonNamePattern.matcher(Pokemon);
        return PokemonNameMatcher.find();
    }
    public static boolean isValidAuthor(String author) {
        Pattern authorNamePattern = Pattern.compile(VALID_AUTHORNAME);
        Matcher authorNameMatcher = authorNamePattern.matcher(author);
        return authorNameMatcher.find();
    }
    public static boolean isValidPages(String pages) {
        Pattern pagesPattern = Pattern.compile(VALID_PAGES);
        Matcher pagesMatcher = pagesPattern.matcher(pages);
        return pagesMatcher.find();
    }
}
