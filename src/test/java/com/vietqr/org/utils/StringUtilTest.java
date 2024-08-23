package com.vietqr.org.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {
    @Test
    void testGenerateRandomStringLength() {
        final int length = 10;
        String randomString = StringUtil.generateRandomString(length);
        assertNotNull(randomString, "Random string should not be null");
        assertEquals(length, randomString.length(), "Random string should have the correct length");
    }

    @Test
    void testGenerateRandomStringContent() {
        int length = 20;
        String randomString = StringUtil.generateRandomString(length);
        assertTrue(randomString.chars().allMatch(
                        ch -> StringUtil.characters.indexOf(ch) >= 0),
                "Random string should contain only valid characters"
        );
    }

    @Test
    void testGenerateRandomStringZeroLength() {
        final int length = 0;
        String randomString = StringUtil.generateRandomString(length);
        assertNotNull(randomString, "Random string should not be null even for zero length");
        assertEquals(length, randomString.length(), "Random string of zero length should be empty");
    }

    @Test
    void testGenerateRandomStringNegativeLength() {
        final int length = -5;
        assertThrows(NegativeArraySizeException.class, () -> {
            StringUtil.generateRandomString(length);
        }, "Negative length should throw an exception");
    }
}
