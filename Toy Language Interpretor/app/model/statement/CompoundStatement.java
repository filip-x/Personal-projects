package app.model.statement;

import app.exception.MyInterpreterException;
import app.model.expresion.InterfaceExpression;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
//keeps the input statements in a tree-view(binary tree)

public class CompoundStatement implements  InterfaceStatement {
    InterfaceStatement first;
    InterfaceStatement second;

    public CompoundStatement(InterfaceStatement first,InterfaceStatement second)
    {
        this.first = first;
        this.second = second;
    }

    public ProgramState execute(ProgramState state) throws MyInterpreterException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    public String recursiveToString(InterfaceStatement statement){
        StringBuilder str = new StringBuilder();
        if (statement instanceof CompoundStatement)
        {// this is compound statement
            CompoundStatement statement1 = (CompoundStatement) statement;
            str.append(recursiveToString(statement1.getFirst()));
            str.append(recursiveToString(statement1.getSecond()));
        }
        else{
            str.append(statement.toString()+"\n");//this is normal
        }
        return  str.toString();
    }
    public String toString()
    {

        return recursiveToString(this);
    }


    public InterfaceStatement getFirst(){
        return this.first;
    }
    public InterfaceStatement getSecond(){
        return this.second;
    }
}
