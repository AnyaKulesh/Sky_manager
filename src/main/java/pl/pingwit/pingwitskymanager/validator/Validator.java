package pl.pingwit.pingwitskymanager.validator;

import java.util.regex.Pattern;

public interface Validator {
    Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]*$");
}
