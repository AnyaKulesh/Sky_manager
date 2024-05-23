package pl.pingwit.pingwitskymanager.validator;

public enum ValidationUtilConstants {
    BLANK_NAME_ERROR("Name is blank"),
    BLANK_SURNAME_ERROR("Surname is blank"),
    ONLY_LETTERS_NAME_ERROR("Name should have only letters"),
    ONLY_LETTERS_SURNAME_ERROR("Surname should have only letters"),
    EMAIL_PATTERN_ERROR("Email is invalid: ");

    private final String value;

    ValidationUtilConstants(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }
}
