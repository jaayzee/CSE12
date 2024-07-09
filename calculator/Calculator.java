/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: Calculator instructions from Github:
                    https://github.com/CaoAssignments/cse11-sp23-pa3-calculator-starter
                Char Addition:
                    https://stackoverflow.com/questions/8688668/in-java-is-the-result-of-the-addition-of-two-chars-an-int-or-a-char#:~:text=The%20result%20of%20adding%20Java,%2C%20unboxing%20conversion%20(%C2%A75.1.
                    
                
   
  This file is for CSE 12 PA3 in Spring 2023, and contains definitions of 
  Calculator functions for addition and basic multiplication.
*/
//https://howtodoinjava.com/java/string/java-string-replacefirst-example/
public class Calculator {

    // ------------ Number Extraction ------------

    /** 
     * Gets the whole number (left of the decimal) 
     * @param number Double input to be calculated	
     * @return whole number section of the number
     */
    public static String extractWholeNumber(String number) {
        if (number.contains(".")) {
            String[] temp = number.split("[.]");
            return temp[0];
        }
        else { return number; }
        
    }

    /** 
     * Gets the decimal number (right of the decimal) 
     * @param number Double input to be calculated
     * @return decimal section of number, or "" if no decimal
     */
    public static String extractDecimal(String number) {
        if (number.contains(".")) {
            String[] temp = number.split("[.]");
        return temp[1];
        }
        else { return ""; }
    }

    // ------------ Alignment and Formatting ------------

    /** 
     * Prepends zeros to the number (left side)
     * @param number Double input to be calculated	
     * @param numZeros number of zeros to be prepended
     * @return number with prepended zeros, if numZeros = -1, returns unaltered number
     */
    public static String prependZeros(String number, int numZeros) {
        if (numZeros == -1) { return number; }
        else {
            for (int i = 0; i < numZeros; i++) {
                number = "0"+ number;
            }
            return number;
        }
    }

    /** 
     * Appends zeros to the number (right side)
     * @param number Double input to be calculated	
     * @param numZeros number of zeros to be appended
     * @return number with appended zeros, if numZeros = -1, returns unaltered number
     */
    public static String appendZeros(String number, int numZeros) {
        if (numZeros == -1) { return number; }
        else {
            for (int i = 0; i < numZeros; i++) {
                number = number + "0";
            }
            return number;
        }
    }

    /** 
     * Reformats the number, deleting trailing and leading zeros
     * Additionally, adds .0 to values without decimals and 0 to values without whole numbers
     * Ex. 200 -> 200.0, and .12 -> 0.12
     * @param number Double input to be calculated	
     * @return reformatted number usable in calculation
     */
    public static String formatResult(String number) {
        if (number.contains(".")) {
            int leading = 0;
            int trailing = number.length() - 1;
            while (number.charAt(leading) == '0') { leading++; }
            while (number.charAt(trailing) == '0') { trailing--; }
            number = number.substring(leading, trailing + 1);
            if (number.charAt(0) == '.') {
                number = "0" + number;
            }
            if (number.charAt(number.length() - 1) == '.') {
                number = number + "0";
            }
            return number;
            
        }
        else {
            int leading = 0;
            while (number.charAt(leading) == 0) { leading++; }
            number = number.substring(leading);
            return number + ".0";
        }
    }

    // ------------ Single Digit Adder ------------

    /** 
     * Adds digits individually
     * if carryIn is true, +1 is added to the value
     * char values become int in addition, and must be referenced according to '0'
     * @param firstDigit first char to be used in calculation	
     * @param secondDigit second char to be used in calculation
     * @param carryIn determines if the previous addition resulted in a carryover (>= 10)
     * @return char with the values of firstDigit + secondDigit, returns the right digit if >= 10
     */
    public static char addDigits(char firstDigit, char secondDigit, boolean carryIn) {
        int temp = (firstDigit - '0');
        if (carryIn) { temp++; }
        temp += (secondDigit - '0');
        if (temp >= 10) {
            temp -= 10; 
        }
        return (char)(temp + '0');
    }

    /** 
     * Determines if current addition results in a carry over
     * if carryIn is true, +1 is added to the value
     * char values become int in addition, and must be referenced according to '0'
     * @param firstDigit first char to be used in calculation	
     * @param secondDigit second char to be used in calculation
     * @param carryIn determines if the previous addition resulted in a carryover (>= 10)
     * @return boolean true if char value >= 10
     */
    public static boolean carryOut(char firstDigit, char secondDigit, boolean carryIn) {
        int temp = (firstDigit - '0');
        if (carryIn) { temp++; }
        temp += (secondDigit - '0');
        if (temp >= 10) { return true; }
        else { return false; }
    }

    // ------------ Calculation ------------

    /** 
     * Adds the values of firstNumber and secondNumber
     * Strings to be converted into individual char calculations
     * @param firstDigit first String to be used in calculation	
     * @param secondDigit second String to be used in calculation
     * @return String of the added firstNumber and secondNumber
     */
    public static String add(String firstNumber, String secondNumber) {
        firstNumber = formatResult(firstNumber);
        secondNumber = formatResult(secondNumber);
        int wholeNumZeros = extractWholeNumber(firstNumber).length() - extractWholeNumber(secondNumber).length();
        if (wholeNumZeros < 0) { firstNumber = prependZeros(firstNumber, wholeNumZeros * -1); }
        else { secondNumber = prependZeros(secondNumber, wholeNumZeros); }
        int decimalNumZeros = extractDecimal(firstNumber).length() - extractDecimal(secondNumber).length();
        if (decimalNumZeros < 0) { firstNumber = appendZeros(firstNumber, decimalNumZeros * -1); }
        else { secondNumber = appendZeros(secondNumber, decimalNumZeros); }

        String temp = "";
        boolean carry = false;
        for (int i = firstNumber.length()-1; i >= 0; i--){
            if(firstNumber.charAt(i) != '.') {
                temp = addDigits(firstNumber.charAt(i), secondNumber.charAt(i), carry) + temp;
                carry = carryOut(firstNumber.charAt(i), secondNumber.charAt(i), carry);
            }
            else { temp = "." + temp; }
        }
        if(carry == true) {temp = "1"+temp;}
        return formatResult(temp);
    }

    /** 
     * Adds the value of number to itself a certain # of time, effectively multiplying
     * Strings to be added to the total sum numTimes times
     * @param number number used in calculation	
     * @param numTimes number of time added to self, equivalent to (number * numTimes)
     * @return String of the multiplied number with numTimes
     */
    public static String multiply(String number, int numTimes) {
        String temp = number;
        for (int i = 1; i < numTimes; i++) {
            temp = add(temp, number);
        }
        return temp;
    }

    // ------------ Testing ------------
    
    public static void main(String[] args) {
        System.out.println(Calculator.extractWholeNumber("00123.23000")); // should print 00123
        System.out.println(Calculator.extractDecimal("00123.23000")); // should print 23000
        System.out.println(Calculator.formatResult("00123.23000")); // should print 123.23
        System.out.println(Calculator.prependZeros("123",3)); // should print 000123
        System.out.println(Calculator.appendZeros("123",3)); // should print 123000
        System.out.println(Calculator.addDigits('1','4',false)); // should print 5
        System.out.println(Calculator.addDigits('1','4',true)); // should pring 6
        System.out.println(Calculator.addDigits('8','4',false)); // should print 2
        System.out.println(Calculator.carryOut('8','4',false)); // should print true
        System.out.println(Calculator.add("4.02", "0.0050")); // should print 4.025
        System.out.println(Calculator.add("4.02", ".005"));   // should print 4.025
        System.out.println(Calculator.add("100", "200"));     // should print 300.0
        System.out.println(Calculator.add("8.02", "4.0050"));     // should print 12.025
        System.out.println(Calculator.add("4.82", "0.4050"));     // should print 5.225
        System.out.println(Calculator.multiply("100", 3));    // should print 300.0
        System.out.println(Calculator.multiply("2.5", 8));    // should print 20.0
        System.out.println(Calculator.multiply("0.01", 10));    // should print 0.1
    }
}