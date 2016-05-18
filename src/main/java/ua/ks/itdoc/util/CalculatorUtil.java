package ua.ks.itdoc.util;

import org.apache.log4j.Logger;

import java.util.*;

public class CalculatorUtil {

    //Logger initialization
    private static final Logger log = Logger.getLogger(CalculatorUtil.class);

    public static String calculateString(String stringInput) throws Exception {

        if (stringInput.isEmpty()) {
            throw new Exception("Expression is empty");
        } else {
            return Double.toString(calculateFromReversePolishNotation(parsingToReversePolishNotation(stringInput)));
        }
    }

    private static String parsingToReversePolishNotation(String stringInput) throws Exception {

        log.info("parsing string " + stringInput + " to Reverse Polish Notation!");

        StringBuilder stringBuilderStack = new StringBuilder(""), stringBuilderOut = new StringBuilder("");
        char charIn, charTmp;

        for (int i = 0; i < stringInput.length(); i++) {
            charIn = stringInput.charAt(i);
            if (isOperand(charIn)) {
                while (stringBuilderStack.length() > 0) {
                    charTmp = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                    if (isOperand(charTmp) && (operandPriority(charIn) <= operandPriority(charTmp))) {
                        stringBuilderOut.append(" ").append(charTmp).append(" ");
                        stringBuilderStack.setLength(stringBuilderStack.length() - 1);
                    } else {
                        stringBuilderOut.append(" ");
                        break;
                    }
                }
                stringBuilderOut.append(" ");
                if ('-' == stringInput.charAt(i) && 0 == i) {
                    stringBuilderOut.append(charIn);
                } else
                    stringBuilderStack.append(charIn);
            } else if ('(' == charIn) {
                stringBuilderStack.append(charIn);
            } else if (')' == charIn) {
                charTmp = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                while ('(' != charTmp) {
                    if (stringBuilderStack.length() < 1) {
                        throw new Exception("Error brackets. Check the expression");
                    }
                    stringBuilderOut.append(" ").append(charTmp);
                    stringBuilderStack.setLength(stringBuilderStack.length() - 1);
                    charTmp = stringBuilderStack.substring(stringBuilderStack.length() - 1).charAt(0);
                }
                stringBuilderStack.setLength(stringBuilderStack.length() - 1);
            } else {
                stringBuilderOut.append(charIn);
            }
        }

        while (stringBuilderStack.length() > 0) {
            stringBuilderOut.append(" ").append(stringBuilderStack.substring(stringBuilderStack.length() - 1));
            stringBuilderStack.setLength(stringBuilderStack.length() - 1);
        }

        log.info("parsing is OK. Result: " + stringBuilderOut.toString());

        return stringBuilderOut.toString();
    }

    private static boolean isOperand(char ch) {
        switch (ch) {
            case '-':
            case '+':
            case '*':
            case '/':
            case '^':
                return true;
        }
        return false;
    }

    private static byte operandPriority(char operand) {
        switch (operand) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return 1; //  + & -
    }

    private static Double calculateFromReversePolishNotation(String stringInput) throws Exception {

        log.info("Start of calculation from Reverse Polish Notation " + stringInput);

        double doubleA, doubleB;
        String stringTmp;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(stringInput);
        while (st.hasMoreTokens()) {
            try {
                stringTmp = st.nextToken().trim();
                if (1 == stringTmp.length() && isOperand(stringTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Wrong number of data in stack for operation " + stringTmp);
                    }
                    doubleB = stack.pop();
                    doubleA = stack.pop();
                    switch (stringTmp.charAt(0)) {
                        case '+':
                            doubleA += doubleB;
                            break;
                        case '-':
                            doubleA -= doubleB;
                            break;
                        case '/':
                            if (0 != doubleB) {
                                doubleA /= doubleB;
                                break;
                            } else {
                                throw new Exception("division by zero is forbidden");
                            }
                        case '*':
                            doubleA *= doubleB;
                            break;
                        case '%':
                            doubleA %= doubleB;
                            break;
                        case '^':
                            doubleA = Math.pow(doubleA, doubleB);
                            if (doubleA > Double.MAX_VALUE) {
                                throw new Exception("Calculation result exceeds the allowable maximum value");
                            } else if (doubleA < Double.MIN_VALUE) {
                                throw new Exception("Calculation result exceeds the allowable  minimum value");
                            } else {
                                break;
                            }
                        default:
                            throw new Exception("Illegal operation " + stringTmp);
                    }
                    stack.push(doubleA);
                } else {
                    doubleA = Double.parseDouble(stringTmp);
                    stack.push(doubleA);
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Illegal symbol in expression");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Number of operators is not equal of operands number");
        }

        Double result = stack.pop();
        return result;
    }
}