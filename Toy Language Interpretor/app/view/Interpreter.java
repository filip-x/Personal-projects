package app.view;

import app.controller.Controller;
import app.exception.MyInterpreterException;
import app.model.dictionary.MyDictionary;
import app.model.dictionary.MyHeap;
import app.model.expresion.*;
import app.model.list.MyList;
import app.model.programstate.ProgramState;
import app.model.stack.MyStack;
import app.model.statement.*;
import app.model.type.BoolType;
import app.model.type.IntType;
import app.model.type.ReferenceType;
import app.model.type.StringType;
import app.model.value.BoolValue;
import app.model.value.IntValue;
import app.model.value.InterfaceValue;
import app.model.value.StringValue;
import app.repository.InterfaceRepository;
import app.repository.Repository;

public class Interpreter {

    public static void main(String[]args) throws MyInterpreterException {
        InterfaceStatement ex1 = new CompoundStatement(
            new VariableDeclarationStatement(
                    "v",
                    new IntType()
            ),
            new CompoundStatement(
                    new AssignmentStatement(
                            "v",
                            new ValueExpression(new IntValue(5))
                    ),
                    new PrintStatement(
                            new VariableExpression("v")
                    )
            )
        );
        ProgramState prg1 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex1,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo1 =new Repository(prg1,"log1.txt");
        Controller ctr1 = new Controller(repo1);

        InterfaceStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement(
                                        "a",
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5)),
                                                        3
                                                ),
                                                1
                                        )
                                ),
                                new CompoundStatement(
                                        new AssignmentStatement(
                                                "b",
                                                new ArithmeticExpression(
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)),
                                                        1
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("b")
                                        )
                                )
                        )
                )
        );

        ProgramState prg2 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex2,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo2 =new Repository(prg2,"log2.txt");
        Controller ctr2 = new Controller(repo2);



        InterfaceStatement ex3 = new CompoundStatement(
            new VariableDeclarationStatement("a",new BoolType()),
            new CompoundStatement(
                    new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(
                            new AssignmentStatement(
                                    "a",
                                    new ValueExpression(new BoolValue(true))),
                            new CompoundStatement(
                                    new IfStatement(
                                            new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(-5)),"<"),
                                            new AssignmentStatement(
                                                    "v",
                                                    new ValueExpression(new IntValue(2))
                                            ),
                                            new AssignmentStatement(
                                                    "v",
                                                    new ValueExpression(new IntValue(3))
                                            )
                                    ),
                                    new PrintStatement(new VariableExpression("v"))
                            )
                    )
            )
    );

        ProgramState prg3 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex3,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo3 =new Repository(prg3,"log3.txt");
        Controller ctr3 = new Controller(repo3);

        InterfaceStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf",new StringType()),
                new CompoundStatement(new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFile(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc",new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"),"varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CloseReadFile(new VariableExpression("varf"))))))))));


        ProgramState prg4 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex4,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo4 =new Repository(prg4,"log4.txt");
        Controller ctr4 = new Controller(repo4);


        InterfaceStatement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocation("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocation("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );

        ProgramState prg5 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex5,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo5 =new Repository(prg5,"log5.txt");
        Controller ctr5 = new Controller(repo5);

        InterfaceStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocation("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocation("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new HeapReading(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(
                                                        new HeapReading(new HeapReading(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)),
                                                        1)
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState prg6 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex6,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo6 =new Repository(prg6,"log6.txt");
        Controller ctr6 = new Controller(repo6);

        InterfaceStatement ex7 =new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocation("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReading(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new HeapWriting("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        new HeapReading(new VariableExpression("v")),
                                                        new ValueExpression(new IntValue(5)),
                                                        1
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState prg7 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex7,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo7 =new Repository(prg7,"log7.txt");
        Controller ctr7 = new Controller(repo7);


        InterfaceStatement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new HeapAllocation("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new HeapAllocation("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new HeapReading(new VariableExpression("v"))),
                                                new PrintStatement(
                                                        new HeapReading(new HeapReading(new VariableExpression("a"))))
                                        )
                                )
                        )
                )
        );

        ProgramState prg8 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex8,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo8 =new Repository(prg8,"log8.txt");
        Controller ctr8 = new Controller(repo8);

        InterfaceStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement("v",new ArithmeticExpression(new VariableExpression("v"),new ValueExpression(new IntValue(1)),2)))),
                                        new PrintStatement(new VariableExpression("v")))));

        ProgramState prg9 = new ProgramState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex9,new MyDictionary<>(),new MyHeap<>());
        InterfaceRepository repo9 =new Repository(prg9,"log9.txt");
        Controller ctr9 = new Controller(repo9);


        TextMenu menu = new TextMenu();
        menu.add_command(new ExitCommand("0","exit"));
        menu.add_command(new RunExample("1",ex1.toString(),ctr1));
        menu.add_command(new RunExample("2",ex2.toString(),ctr2));
        menu.add_command(new RunExample("3",ex3.toString(),ctr3));
        menu.add_command(new RunExample("4",ex4.toString(),ctr4));
        menu.add_command(new RunExample("5",ex5.toString(),ctr5));
        menu.add_command(new RunExample("6",ex6.toString(),ctr6));
        menu.add_command(new RunExample("7",ex7.toString(),ctr7));
        menu.add_command(new RunExample("8",ex8.toString(),ctr8));
        menu.add_command(new RunExample("9",ex9.toString(),ctr9));

        menu.show();



    }
}
