package se.hitta.dsl.instruction;

import se.hitta.dsl.executor.Executor;
import se.hitta.dsl.executor.Executors;

public enum InstructionType {

    FETCH("(FETCH|fetch) (.*) (.*)", Executors.FETCH_EXECUTOR),
    FOREACH("(FOREACH|foreach) (.*) (.*)", Executors.FOREACH_EXECUTOR),
    DOWNLOAD("(DOWNLOAD|download) (.*) (.*)", Executors.DOWNLOAD_EXECUTOR),;

    public String pattern;
    public Executor executor;

    InstructionType(String pattern, Executor executor) {
        this.pattern = pattern;
        this.executor = executor;
    }
}
