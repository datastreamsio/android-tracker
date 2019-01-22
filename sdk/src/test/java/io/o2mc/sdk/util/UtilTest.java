package io.o2mc.sdk.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilTest {

  @Test
  public void isValidEndpoint() {
    // List of valid endpoints
    List<String> valid = new ArrayList<>();
    valid.add("http://google.com"); // web endpoint
    valid.add("http://www.google.com"); // web endpoint
    valid.add("http://test.google.com"); // web endpoint
    valid.add("http://test.google.com:5000"); // web endpoint
    valid.add("http://test.google.com:5000/events"); // web endpoint
    valid.add("http://test.google.com:5000/events?key=value"); // web endpoint
    valid.add("http://127.0.0.1"); // local endpoint
    valid.add("http://192.168.1.1:5000/"); // local endpoint
    valid.add("http://10.0.2.2:5000/events"); // local endpoint

    for (String s : valid) {
      assertTrue(String.format("Endpoint '%s' should be valid. It's considered invalid.", s),
          Util.isValidEndpoint(s)); // should be true
    }

    // List of invalid endpoints -- mistyped the valid ones on deliberately
    List<String> invalid = new ArrayList<>();
    invalid.add("http://googlecom"); // web endpoint
    invalid.add("http:/www.google.com"); // web endpoint
    invalid.add("htp://test.google.com:5000"); // web endpoint
    invalid.add("http:test.google.com:5000/events"); // web endpoint
    invalid.add("http://testgooglecom:5000/events?key=value"); // web endpoint
    invalid.add("http://127.0.1"); // local endpoint
    invalid.add("http://.168.1.1:5000/"); // local endpoint
    invalid.add("http//0.0.2.2:5000/events"); // local endpoint

    for (String s : invalid) {
      assertFalse(String.format("Endpoint '%s' should be invalid. It's considered valid.", s),
          Util.isValidEndpoint(s)); // should be false
    }
  }

  @Test
  public void isValidDispatchInterval() {
    // Test valid dispatch intervals (anywhere between 1 second and 1 hour)
    int second = 1;
    assertTrue(Util.isValidDispatchInterval(second));

    int minute = 60;
    assertTrue(Util.isValidDispatchInterval(minute));

    int fiveMinutes = 60 * 5;
    assertTrue(Util.isValidDispatchInterval(fiveMinutes));

    int fiftyFiveMinutes = 60 * 55;
    assertTrue(Util.isValidDispatchInterval(fiftyFiveMinutes));

    // Test invalid dispatch intervals (anywhere above 1 hour and below 1 second)
    int hourAndSecond = 60 * 60 + 1;
    assertFalse(Util.isValidDispatchInterval(hourAndSecond));

    int twoHours = 60 * 60 * 2;
    assertFalse(Util.isValidDispatchInterval(twoHours));

    int negativeNumber = -10;
    assertFalse(Util.isValidDispatchInterval(negativeNumber));
  }

  @Test
  public void isHttps() {
    String httpEndpoint = "http://google.com";
    assertFalse(Util.isHttps(httpEndpoint));

    String httpsEndpoint = "https://google.com";
    assertTrue(Util.isHttps(httpsEndpoint));
  }

  @Test
  public void isValidMaxRetries() {
    List<Integer> validRetries = new ArrayList<>();
    validRetries.add(1);
    validRetries.add(2);
    validRetries.add(3);
    validRetries.add(99);
    validRetries.add(100);
    validRetries.add(101);

    for (int i : validRetries) {
      assertTrue(Util.isValidMaxRetries(i));
    }

    List<Integer> invalidRetries = new ArrayList<>();
    invalidRetries.add(0);
    invalidRetries.add(-1);
    invalidRetries.add(-2);
    for (int i : invalidRetries) {
      assertFalse(Util.isValidMaxRetries(i));
    }
  }

  @Test
  public void isValidEventName() {
    List<String> validEventNames = new ArrayList<>();
    validEventNames.add("hello");
    validEventNames.add("hello_world");
    validEventNames.add("helloWorld");
    validEventNames.add("1337");
    validEventNames.add("a/b/c");
    validEventNames.add("aa-b-cc");
    validEventNames.add("aa.b.cc");
    validEventNames.add("aa:b:cc");
    validEventNames.add("aa-/-cc.dd:ee");

    for (String eventName : validEventNames) {
      assertTrue(Util.isValidEventName(eventName));
    }

    List<String> invalidEventNames = new ArrayList<>();
    invalidEventNames.add(null);
    invalidEventNames.add("hello World");
    invalidEventNames.add(" "); // space
    invalidEventNames.add("      "); // multiple spaces
    invalidEventNames.add("     "); // tables
    invalidEventNames.add("^&##s");


    for (String eventName : invalidEventNames) {
      assertFalse(Util.isValidEventName(eventName));
    }
  }

  @Test
  public void isValidEventValue() {
    List<Object> validEventValues = new ArrayList<>();

    int[] intArray = {9000, 1000, 1337};
    String stringArray[] = {"Edwin", "Juan", "Nicky"};
    class TestObj {  public String name = "Josh"; }

    validEventValues.add("string"); // string
    validEventValues.add(true); // bool
    validEventValues.add(1.123f); // float
    validEventValues.add(0.000002); // double
    validEventValues.add(1337); // int
    validEventValues.add(31337L); // long
    validEventValues.add('c'); // char
    validEventValues.add("bytes".getBytes()); // bytes
    validEventValues.add(intArray); // int array
    validEventValues.add(stringArray); // string array
    validEventValues.add(new TestObj()); // object

    for (Object eventValue : validEventValues) {
      assertTrue(Util.isValidEventValue(eventValue));
    }

    List<String> invalidEventValues = new ArrayList<>();
    invalidEventValues.add(null);

    for (String eventValue : invalidEventValues) {
      assertFalse(Util.isValidEventValue(eventValue));
    }
  }
}