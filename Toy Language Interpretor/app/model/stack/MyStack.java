package app.model.stack;

import app.exception.MyInterpreterException;

import java.util.Stack;

public class MyStack<dataType> implements InterfaceMyStack<dataType> {

    Stack <dataType> stack;
    public MyStack()
    {
        stack= new Stack<dataType>();
    }

    public dataType pop() throws MyInterpreterException {//Exception later on if it is empty
        if(stack.isEmpty())
            throw new MyInterpreterException("the stack is empty");
        return  stack.pop();

    }

    public void push(dataType value) {
        stack.push(value);
    }

    public boolean isEmpty() {
        if(stack.isEmpty())//if stack.isEmpty() == true
            return true;
        return false;
    }

    public String printIterationOfTheStack() {
        String str ="";
        for(dataType item:stack)
        {
            str += item.toString()+"\n";
        }
        return str;
    }

    public dataType peek() {
        return stack.peek();
    }
    public String toString(){
        String str="";
        for(dataType item :stack)
            str +=item.toString()+", ";
        return str;
    }
}
