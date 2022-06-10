package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.command.Command;

import java.util.ArrayList;

/**
 * The Comp Virtual File System
 */
public class CVFS {
    private CommandMaker commandMaker;
    private FileSystem fileSystem;
    private final Undoer undoer;
    private ArrayList<Command> history;
    private CmdType cmdType;

    /**
     * Initialize the CVFS by creating a new FileSystem
     * and the storage of the image of FileSystem for undo and redo
     */
    public CVFS(){
        fileSystem = new FileSystem();
        undoer = new Undoer();
        commandMaker = new CommandMaker();
        history = new ArrayList<>();
    }

    /**
     * Run the CVFS
     */
    public void run(){
        boolean flag = true;
        System.out.println("Welcome to the Comp Virtual File System!");
        while(flag){
            try {
                if (!fileSystem.isEmptyDisk())
                    System.out.print(fileSystem.getwDir().toString());
                System.out.print("> ");
                commandMaker.scan(System.in);
                cmdType = commandMaker.getCmdType();
                flag = cmdType != CmdType.stop;
                execute();
            }catch (Exception e){
                System.out.println("Unknown command or Invalid argument(s)");
            }
        }
    }

    private void execute(){
        try {
            if (cmdType.getNeedMemo()) {
                undoer.addUndo(fileSystem.createMemento());
                undoer.redoStackClear();
            }
            if (cmdType == CmdType.undo) undoer.addRedo(fileSystem.createMemento());
            if (cmdType == CmdType.redo) undoer.addUndo(fileSystem.createMemento());
            Command command = commandMaker.makeCommand(fileSystem, undoer, this);
            command.execute();
            history.add(command);
        }catch (Exception e){
            System.out.println("Invalid argument(s).");
            if (commandMaker.getCmdType().getNeedMemo()) undoer.undoStackPop();
            if (commandMaker.getCmdType() == CmdType.undo) undoer.redoStackPop();
        }
    }

    /**
     * Testing the input command
     * @param input a String of command
     */
    public void testParse(String input){
        try {
            commandMaker.scan(input);
            cmdType = commandMaker.getCmdType();
            execute();
        }catch (Exception e){
            System.out.println("Unknown command or Invalid argument(s)");
        }
    }

    /**
     * @return current command
     */
    public Command getCmd(){
        return history.get(history.size()-1);
    }

    /**
     * @return current FileSystem
     */
    public FileSystem getFileSystem(){
        return fileSystem;
    }

    /**
     * Set the CVFS to a new FileSystem
     * @param fileSystem a new FileSystem
     */
    public void setFileSystem(FileSystem fileSystem){
        this.fileSystem = fileSystem;
    }

}