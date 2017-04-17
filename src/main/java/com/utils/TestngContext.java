package com.utils;

import org.testng.ITestContext;

public class TestngContext {

    private static ITestContext _context;

    public TestngContext() {}

    public static void setContext(ITestContext context) {
        _context = context;
    }

    public static ITestContext getContext() {
        return _context;
    }

}
