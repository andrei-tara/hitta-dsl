package se.hitta.dsl.executor;

import se.hitta.dsl.instruction.InstructionNode;
import se.hitta.dsl.util.ConnectionUtil;
import se.hitta.dsl.util.ExecutionUtil;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Executors {
    public static Executor FETCH_EXECUTOR = new Executor() {
        @Override
        public void execute(InstructionNode instruction, ExecutorContext context) throws IOException {

            Map<String, Object> memory = context.memory;
            String url = ExecutionUtil.replaceVars(memory, instruction.arg2, true);

            String json = ConnectionUtil.fetch(url);
            context.memory.put(instruction.arg1, json);
        }


    };

    public static Executor FOREACH_EXECUTOR = new Executor() {
        @Override
        public void execute(InstructionNode instruction, ExecutorContext context) throws IOException {

            getIterator(instruction, context);

            List<?> list = ExecutionUtil.readVariable(context.memory, instruction.arg2);

            for (Object elem : list) {
                for (InstructionNode subinstruction : instruction.children) {
                    String subObject = Configuration.defaultConfiguration().jsonProvider().toJson(elem);
                    context.memory.put(instruction.arg1, subObject);
                    Executor executor = subinstruction.type.executor;
                    executor.execute(subinstruction, context);
                }

            }
        }

        private List<?> getIterator(InstructionNode instruction, ExecutorContext context) {
            String[] tokens = instruction.arg2.split("\\.", 2);
            String json = (String) context.memory.get(tokens[0]);
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
            return JsonPath.read(document, "$." + tokens[1]);
        }
    };

    public static Executor DOWNLOAD_EXECUTOR = new Executor() {
        @Override
        public void execute(InstructionNode instruction, ExecutorContext context) throws IOException {

            Map<String, Object> memory = context.memory;
            String url = ExecutionUtil.replaceVars(memory, instruction.arg1, false);
            String location = ExecutionUtil.replaceVars(memory, instruction.arg2, false);

            ConnectionUtil.download(url, location);
        }
    };
}
