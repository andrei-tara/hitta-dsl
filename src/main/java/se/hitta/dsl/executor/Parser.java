package se.hitta.dsl.executor;

import se.hitta.dsl.instruction.InstructionNode;
import se.hitta.dsl.util.TextUtil;

import java.util.*;

public class Parser {

    public static List<InstructionNode> parse(String script) {

        String[] lines = script.split("\n");

        List<InstructionNode> tokens = new ArrayList<>();

        Stack<List<InstructionNode>> stack = new Stack<>();
        stack.push(tokens);
        int ident = 0;

        for (String line : lines) {

            InstructionNode token = InstructionNode.parse(line);

            int _ident = TextUtil.getIdentSpacing(line);
            if (_ident != ident) {

                if (_ident > ident) {
                    InstructionNode _token = TextUtil.last(stack.peek());
                    stack.push(_token.children);
                }

                if (_ident < ident) {
                    stack.pop();
                }

            }
            ident = _ident;
            stack.peek().add(token);

        }

        return tokens;
    }


}
