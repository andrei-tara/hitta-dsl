package se.hitta.dsl;

import se.hitta.dsl.executor.ExecutorContext;
import se.hitta.dsl.executor.Parser;
import se.hitta.dsl.instruction.InstructionNode;

import java.io.IOException;
import java.util.List;

public class ScriptRunner {




    public static void run(String script) throws IOException {
        List<InstructionNode> instructions = Parser.parse(script);
        execute(instructions);
    }

    private static void execute(List<InstructionNode> instructions) throws IOException {

        ExecutorContext context = new ExecutorContext();
        for (InstructionNode instruction : instructions) {
            instruction.type.executor.execute(instruction, context);
        }
    }
}
