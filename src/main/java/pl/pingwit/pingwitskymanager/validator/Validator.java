package pl.pingwit.pingwitskymanager.validator;

import java.util.regex.Pattern;

public interface Validator {  //Круто, что вынесла общие константы в одно место, но их лучше хранить в енаме ValidationUtilConstants например назвать
    Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");
}
