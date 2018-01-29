package com.example.moksleivis.knygalaboras;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by moksleivis on 2018-01-19.
 */

public class Validation {

    private static final String VALID_ID_REGEX ="^[0-9]{1,7}$";
    private static final String VALID_POKEMONNAME_ADD_REGEX ="^[A-Za-z ]{1,1000}$";
    private static final String VALID_POKEMONNAME_SEARCH_REGEX ="^[A-Za-z]{1,1000}$";
    private static final String VALID_stats_REGEX ="^[0-9.]{1,7}$";
    private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z0-9.-]{5,13}$";
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";


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
    public static boolean isValidID(String ID){
        Pattern IDPattern = Pattern.compile(VALID_ID_REGEX);
        Matcher IDMatcher = IDPattern.matcher(ID);
        return IDMatcher.find();
    }


    public static boolean isValidPokemonNameForAdd(String Pokemon){
        Pattern PokemonNamePattern = Pattern.compile(VALID_POKEMONNAME_ADD_REGEX);
        Matcher PokemonNameMatcher = PokemonNamePattern.matcher(Pokemon);
        return PokemonNameMatcher.find();
    }

    public static boolean isValidstats(String stats){
        Pattern statsPattern = Pattern.compile(VALID_stats_REGEX);
        Matcher statsMatcher = statsPattern.matcher(stats);
        return statsMatcher.find();
    }

    public static boolean isValidPokemonNameForSearch(String Pokemon){
        Pattern PokemonNamePattern = Pattern.compile(VALID_POKEMONNAME_SEARCH_REGEX);
        Matcher PokemonNameMatcher = PokemonNamePattern.matcher(Pokemon);
        return PokemonNameMatcher.find();
    }
}
