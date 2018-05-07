package se.hitta.dsl.executor;

import se.hitta.dsl.instruction.InstructionNode;

import java.io.IOException;

public interface Executor {
    void execute(InstructionNode instruction, ExecutorContext context) throws IOException;
}