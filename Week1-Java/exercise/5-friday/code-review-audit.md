# StringProcessor Code Review Audit

## Friday Exercise 1

### Review Questions

- Did the AI identify a genuine logical error or reference vulnerability?
- Did it suggest clean additions, like adding `@Override` annotations or `final` declarations?
- Crucial check: Did it change your class behavior or introduce syntax errors?
- Paste the AI's modified code into a separate file and verify it compiles without warnings.

## Feedback on AI Changes

Yes, the AI identified ways to fix genuine logical errors. The original code did not check for uppercase vowels.

The AI also made the code shorter by removing a redundant character array assignment. The string already has a built-in `toCharArray()` method, so this should be a one-line assignment instead of a two-line assignment.

The additions were clean. I rejected some changes because they did not affect the functionality, such as renaming `checkNullInput` to `isNull`. I also rejected changing the class to `final` because I am fine with other classes extending the `StringProcessor` class.

## Changes List

### Change 1: Remove redundant array allocation

Status: Applied

The original `reverse` method created an empty character array and immediately replaced it with `input.toCharArray()`. The first allocation was unnecessary.

```java
// Before
char[] myCharArray = new char[input.length()];
myCharArray = input.toCharArray();

// After
char[] myCharArray = input.toCharArray();
```

### Change 2: Replace repeated String concatenation with StringBuilder

Status: Applied

The current `reverse` method appends one character at a time using `reverse += myCharArray[i]`. Since `String` is immutable, each append creates a new `String`. `StringBuilder` avoids repeated allocations.

```java
// Before
String reverse = "";
if(checkNullInput(input)) return reverse;
char[] myCharArray = input.toCharArray();
for(int i = myCharArray.length-1; i>=0; i--)
    reverse+=myCharArray[i];
return reverse;

// After
if(checkNullInput(input)) return "";
return new StringBuilder(input).reverse().toString();
```

### Change 3: Count uppercase vowels

Status: Applied

The current `countVowels` method only counts lowercase vowels. Converting each character to lowercase before comparison allows inputs like `"Apple"` to count both `A` and `e`.

```java
// Before
for(char c :  input.toCharArray())
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
        vowelCount++;

// After
for(char c : input.toCharArray()) {
    char normalized = Character.toLowerCase(c);
    if(normalized == 'a' || normalized == 'e' || normalized == 'i' || normalized == 'o' || normalized == 'u')
        vowelCount++;
}
```

### Change 4: Rename null-check helper for clearer intent

Status: Rejected

`checkNullInput` is understandable, but `isNull` reads more naturally as a boolean method name. This change was rejected because it does not affect functionality.

```java
// Before
public static boolean checkNullInput(String input){
    return input == null;
}

// After
public static boolean isNull(String input){
    return input == null;
}
```

All call sites would also be updated:

```java
// Before
if(checkNullInput(input)) return "";

// After
if(isNull(input)) return "";
```

### Change 5: Make the class a non-instantiable utility class

Status: Rejected

`StringProcessor` only contains static methods. Marking it `final` and adding a private constructor would make that design explicit. This change was rejected because extending `StringProcessor` is acceptable for this exercise.

```java
// Before
public class StringProcessor {

// After
public final class StringProcessor {

    private StringProcessor() {
        // Utility class; prevent instantiation.
    }
```
