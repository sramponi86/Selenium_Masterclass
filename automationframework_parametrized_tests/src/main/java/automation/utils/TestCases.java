package automation.utils;

//import jdk.incubator.vector.VectorOperators;

public enum TestCases {
    T1("Testing the authentication");

    private String testName;

    public String getTestName() {
        return testName;
    }

    TestCases(String value) {
        this.testName = value;
    }
}
