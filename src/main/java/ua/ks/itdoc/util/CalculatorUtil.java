package ua.ks.itdoc.util;

import java.util.*;

public class CalculatorUtil {

    public static String calculator(String stringInput) throws Exception {
        String resCalc = calc(stringInput);
        return Double.toString(calculate(resCalc));

    }

    private static String calc(String stringInput) throws Exception {
        StringBuilder stringBuilderStack = new StringBuilder(""), stringBuilderOut = new StringBuilder("");
        char charIn, charTmp;

        for (int i = 0; i < stringInput.length(); i++) {
            charIn = stringInput.charAt(i);
            if (isOperand(charIn)) {
                if (stringInput.charAt(i) == '-' && i == 0){ stringBuilderOut.append(charIn);}
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

    private static Double calculate(String stringInput) throws Exception {
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
                            doubleA /= doubleB;
                            break;
                        case '*':
                            doubleA *= doubleB;
                            break;
                        case '%':
                            doubleA %= doubleB;
                            break;
                        case '^':
                            doubleA = Math.pow(doubleA, doubleB);
                            break;
                        default:
                            throw new Exception("Illegal operation " + stringTmp);
                    }
                    stack.push(doubleA);
                } else {
                    doubleA = Double.parseDouble(stringTmp);
                    stack.push(doubleA);
                }
            } catch (Exception e) {
                throw new Exception("Illegal symbol in expression");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Number of operators is not equal of operands number");
        }

        return stack.pop();
    }
}