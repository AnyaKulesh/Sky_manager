package pl.pingwit.pingwitskymanager.validator;

import java.util.regex.Pattern;

public interface Validator {
    String BLANK_NAME_ERROR = "Name is blank";
    String BLANK_SURNAME_ERROR = "Surname is blank";
    String ONLY_LETTERS_NAME_ERROR = "Name should have only letters";
    String ONLY_LETTERS_SURNAME_ERROR = "Surname should have only letters";
    String EMAIL_PATTERN_ERROR = "Email is invalid: ";
    Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");
}
