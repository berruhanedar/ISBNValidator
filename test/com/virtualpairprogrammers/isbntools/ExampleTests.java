package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ExampleTests {

    @Test
    public void exampleTests() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN(140449116);
        assertTrue(result);

    }
}
