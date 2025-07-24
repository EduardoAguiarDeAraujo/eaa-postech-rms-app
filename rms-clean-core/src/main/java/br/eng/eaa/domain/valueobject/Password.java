package br.eng.eaa.domain.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    private String value;

    public Password(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.value = value;
    }

    private boolean isValid(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }

        if (value.length() < 8) {
            return false;
        }

        Pattern upperCasePattern = Pattern.compile(".*[A-Z].*");
        Matcher upperCaseMatcher = upperCasePattern.matcher(value);
        if (!upperCaseMatcher.matches()) {
            return false;
        }

        Pattern lowerCasePattern = Pattern.compile(".*[a-z].*");
        Matcher lowerCaseMatcher = lowerCasePattern.matcher(value);
        if (!lowerCaseMatcher.matches()) {
            return false;
        }

        Pattern digitPattern = Pattern.compile(".*[\\d].*");
        Matcher digitMatter = digitPattern.matcher(value);
        if (!digitMatter.matches()) {
            return false;
        }

        Pattern specialCharPattern = Pattern.compile(".*[\\W].*");
        Matcher specialCharMatcher = specialCharPattern.matcher(value);
        if (!specialCharMatcher.matches()){
            return false;
        } else {
            return true;
        }
    }

    public String getValue()    {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
