package de.koelle.christian.common.ui.filter;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import de.koelle.christian.common.constants.Rglob;

public class DecimalNumberInputUtil {

    private final char localizedDelimiter;
    private final char unintendedDelimiter;
    private final DecimalNumberInputPatternMatcher amountInputPatternMatcher;

    public DecimalNumberInputUtil(Locale locale) {
        this.localizedDelimiter = determineDecimalSeparator(locale);
        this.unintendedDelimiter = getDelimiterInversion(localizedDelimiter);
        this.amountInputPatternMatcher = new DecimalNumberInputPatternMatcher();
    }

    private char determineDecimalSeparator(final Locale locale) {
        char decimalSeparator = new
                DecimalFormatSymbols(locale).getDecimalSeparator();
        if (!(decimalSeparator == ',' || decimalSeparator == '.')) {
            decimalSeparator = '.';
        }
        return decimalSeparator;
    }

    public char getLocalizedDelimiter() {
        return localizedDelimiter;
    }

    public String fixInputString(String input) {
        return input.replace(unintendedDelimiter, localizedDelimiter);
    }

    private char getDelimiterInversion(char delimiter) {
        return (delimiter == Rglob.DECIMAL_DEL_COMMA) ? Rglob.DECIMAL_DEL_DOT : Rglob.DECIMAL_DEL_COMMA;
    }

    public DecimalNumberInputPatternMatcher getInputPatternMatcher() {
        return amountInputPatternMatcher;
    }

}
