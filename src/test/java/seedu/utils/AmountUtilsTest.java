package seedu.utils;

import org.junit.jupiter.api.Test;
import seedu.message.ErrorMessages;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AmountUtilsTest {

    @Test
    void parseAmount_invalidAmountFormat_throwsException() {
        try {
            AmountUtils.parseAmount("test");
        } catch (IllegalArgumentException e) {
            assertEquals(ErrorMessages.INVALID_AMOUNT_FORMAT + "test"
                    + ErrorMessages.INVALID_AMOUNT_GUIDE, e.getMessage());
        }
    }

    @Test
    void parseAmount_negativeAmount_throwsException() {
        try {
            AmountUtils.parseAmount("-1");
        } catch (IllegalArgumentException e) {
            assertEquals(ErrorMessages.INVALID_AMOUNT_FORMAT + "-1"
                    + ErrorMessages.INVALID_AMOUNT_GUIDE , e.getMessage());
        }
    }

    @Test
    void parseAmount_validAmount_success() {
        assertEquals(AmountUtils.parseAmount("10"), 10);
    }
}
