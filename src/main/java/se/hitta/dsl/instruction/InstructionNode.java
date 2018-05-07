package se.hitta.dsl.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionNode {
    public InstructionType type;
    public String arg1;
    public String arg2;

    public List<InstructionNode> children = new ArrayList<>();

    public InstructionNode(InstructionType type, String arg1, String arg2) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public String toString() {
        return "InstructionNode{" +
                "type=" + type +
                ", arg1='" + arg1 + '\'' +
                ", arg2='" + arg2 + '\'' +
                '}';
    }

    public static InstructionNode parse(String input) {

        for (InstructionType type : InstructionType.values()) {
            // Create a Pattern object
            Pattern r = Pattern.compile(type.pattern);
            // Now create matcher object.
            Matcher m = r.matcher(input);
            if (m.find()) {
                return new InstructionNode(type, m.group(2), m.group(3));
            }
        }

        return null;
    }
}