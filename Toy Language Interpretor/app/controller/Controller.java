package app.controller;

import app.exception.MyInterpreterException;
import app.model.dictionary.InterfaceMyHeap;
import app.model.list.InterfaceMyList;
import app.model.list.MyList;
import app.model.programstate.ProgramState;
import app.model.stack.InterfaceMyStack;
import app.model.statement.InterfaceStatement;
import app.model.value.InterfaceValue;
import app.model.value.ReferenceValue;
import app.repository.InterfaceRepository;
import com.sun.jdi.Value;

import java.util.*;
import java.util.concurrent.*;

public class Controller {

    InterfaceRepository repository;
    ExecutorService executor;


    public Controller(InterfaceRepository repository){
        this.repository = repository;
    }


    public void allStep() throws MyInterpreterException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompleteProgram(repository.getProgramStateList());
        while (programList.size() > 0)
        {
            programList.get(0).getHeap().setContent((HashMap)conservativeGarbageCollector(getAdressesFromTables(programList,programList.get(0).getHeap().getContent()),programList.get(0).getHeap().getContent()));

            oneStepForAllPrograms(programList);
            programList = removeCompleteProgram(repository.getProgramStateList());
        }
        executor.shutdownNow();
        repository.setProgramStateList(programList);
    }

    public Map<Integer, InterfaceValue> conservativeGarbageCollector(List<Integer>symbolTableAddress,Map<Integer,InterfaceValue> heap)
    {
        HashMap<Integer,InterfaceValue> newHeap = new HashMap<>();
        for(Map.Entry<Integer,InterfaceValue> pair: heap.entrySet())// we take every pair(key,value) from heap
        {
            if(symbolTableAddress.contains(pair.getKey()))
            {
                newHeap.put(pair.getKey(),pair.getValue());
            }
        }
        return newHeap;
    }

    public List<Integer> getAdressesFromTables(List<ProgramState> programList,Map<Integer,InterfaceValue> heap)
    {
        List<Integer> addresses= new LinkedList<>();

        for (ProgramState index: programList)
        {
            addresses.addAll(getAddressFromSymbolTable(index.getSymbolTable().getContent().values()));
        }
        for(InterfaceValue index: heap.values())
        {
            if(index instanceof ReferenceValue)
            {
                ReferenceValue indexRef = (ReferenceValue) index;
                addresses.add(indexRef.getAddress());
            }
        }
        return addresses;
    }

    public List<Integer> getAddressFromSymbolTable(Collection<InterfaceValue> symbolTableValue)
    {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for(InterfaceValue index: symbolTableValue)// index will have every value from symbolTableValue
        {
            if(index instanceof ReferenceValue)
            {
                ReferenceValue indexRef = (ReferenceValue) index;
                linkedList.add(indexRef.getAddress());
            }
        }

        return linkedList;
    }

    public List<ProgramState> removeCompleteProgram(List<ProgramState>inProgramList)
    {
        List<ProgramState> removeList =  new LinkedList<>();
        for(ProgramState index: inProgramList)
        {
            if(index.isNotCompleted()!= false)
            {
                removeList.add(index);
            }
        }
        return removeList;
    }

    public void oneStepForAllPrograms(List<ProgramState> programList) throws MyInterpreterException, InterruptedException {
        System.out.println("3");
        for (ProgramState index: programList)
        {
            System.out.println("1");
            repository.logProgramStateExecution(index);
        }

        List<Callable<ProgramState>> callList = new LinkedList<>();

        for(ProgramState index2: programList)
        {

            callList.add(index2::oneStep);// we get the function into a list
        }

        List<ProgramState> newProgramList = new LinkedList<>();
        for(Future<ProgramState> promise: executor.invokeAll(callList))
        {
            try {
                ProgramState varPromise=promise.get();
                 if(varPromise != null ) {
                     newProgramList.add(varPromise);
                 }
            } catch (ExecutionException e) {
                System.out.println("Error has occured!! "+e.getMessage());
            }
        }
        programList.addAll(newProgramList);

        for (ProgramState index: programList)
        {
            System.out.println("2");
            repository.logProgramStateExecution(index);
        }

        repository.setProgramStateList(programList);

    }

}

