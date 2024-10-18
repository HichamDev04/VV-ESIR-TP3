package fr.istic.vv;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } 
            else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                return (top == '{' && ch == '}') || (top == '[' && ch == ']') || (top == '(' && ch == ')');
            }
        }
        return stack.isEmpty();
    }
}
