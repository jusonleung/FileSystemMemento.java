package hk.edu.polyu.comp.comp2021.cvfs.model;

import hk.edu.polyu.comp.comp2021.cvfs.model.command.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.*;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.DocType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/** A class define the user input command */
public  class CommandMaker {

    private String[] cmds;
    private CmdType cmdType;


    /** Construct a command by the user input */
    public CommandMaker(){
    }

    /**
     * @return the trimmed command without command type
     */
    public String[] getCmds(){return cmds;}

    /**
     * @return the type of command
     */
    public CmdType getCmdType(){
        return cmdType;
    }

    /**
     * @return String of the command type of this command
     */
    public String toString(){
        return cmdType.toString();
    }

    /**
     * Scan the user input to build a command
     * @param inputStream user input
     */
    public void scan(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        build(scanner.nextLine());
    }


    /**
     * Scan the string to build a command
     * @param string String to be built
     */
    public void scan(String string){
        build(string);
    }

    /**
     * Read this command to perform actions to a fileSystem.
     * @param fileSystem the current fileSystem
     * @param undoer the object that contains images of fileSystem for undo or redo
     * @param cvfs this CVFS System
     * @return a command
     */
    public Command makeCommand(FileSystem fileSystem, Undoer undoer, CVFS cvfs){
        if (fileSystem == null)
            throw new IllegalArgumentException();

        switch (cmdType){
            case newDisk:
                return newDiskCmd(fileSystem);
            case newDoc:
                return newDocCmd(fileSystem);
            case newDir:
                return newDirCmd(fileSystem);
            case delete:
                return deleteDocCmd(fileSystem);
            case rename:
                return renameCmd(fileSystem);
            case changeDir:
                return changeDirCmd(fileSystem);
            case list:
                return listCmd(fileSystem);
            case rList:
                return rListCmd(fileSystem);
            case newSimpleCri:
                return newSimpleCriCmd(fileSystem);
            case newNegation:
                return newNegationCmd(fileSystem);
            case newBinaryCri:
                return newBinaryCriCmd(fileSystem);
            case printAllCriteria:
                return printAllCriteriaCmd(fileSystem);
            case search:
                return searchCmd(fileSystem);
            case rSearch:
                return rSearchCmd(fileSystem);
            case undo:
                return undoCmd(fileSystem, undoer);
            case redo:
                return redoCmd(fileSystem, undoer);
            case store:
                return storeCmd(fileSystem);
            case load:
                return loadCmd(cvfs);
            case stop:
                return new Stop();
            default:
                throw new IllegalArgumentException();
        }
    }

    private void build(String cmd){
        cmd = cmd.trim();
        char[] chars = cmd.toCharArray();
        ArrayList<String> strings = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        Stack<String> stringStack = new Stack<>();

        for(char i:chars){
            switch (i){
                case('"'):
                    if (stringStack.isEmpty()){
                        stringStack.push("\"");
                        temp.append(i);
                        break;
                    }
                    if (stringStack.peek().equals("\"")){
                        temp.append(i);
                        stringStack.pop();
                    }
                    break;
                case(' '):
                    if (stringStack.isEmpty()){
                        strings.add(temp.toString());
                        temp = new StringBuilder();
                    }else{
                        temp.append(i);
                    }
                    break;

                default:
                    temp.append(i);
            }
        }
        if (temp.length() > 0){
            strings.add(temp.toString());
        }
        cmdType = CmdType.valueOf(strings.get(0));
        strings.remove(0);


        cmds = strings.toArray(new String[strings.size()]);

    }

    // File-related command
    private Command newDiskCmd(FileSystem fileSystem){
        if (cmds.length != 1)
            throw new IllegalArgumentException();
        int size = Integer.parseInt(cmds[0]);

        return new NewDisk(fileSystem, size);

    }

    private Command newDocCmd(FileSystem fileSystem) {
        if (cmds.length != 3 || cmds[0].length() > 10 || cmds[0].matches("\\W+"))
            throw new IllegalArgumentException();

        DocType docType;
        try {
            docType = DocType.valueOf(cmds[1].toUpperCase());
        }catch (Exception e){
            System.out.println("Invalid document type");
            return null;
        }

        return new NewDoc(fileSystem, cmds[0], docType, cmds[2]);
    }

    private Command newDirCmd(FileSystem fileSystem) {
        if (cmds.length != 1 || cmds[0].length() > 10 || cmds[0].matches("\\W+"))
            throw new IllegalArgumentException();

        return new NewDir(fileSystem,cmds[0]);
    }

    private Command deleteDocCmd(FileSystem fileSystem) {
        if (cmds.length != 1)
            throw new IllegalArgumentException();

        return new Delete(fileSystem.getDir(), cmds[0]);
    }

    private Command renameCmd(FileSystem fileSystem){
        if (cmds.length != 2 || cmds[1].matches("\\W+") || cmds[1].length() > 10)
            throw new IllegalArgumentException();

        return new Rename(fileSystem.getDir(), cmds[0], cmds[1]);
    }

    private Command changeDirCmd(FileSystem fileSystem) {
        if (cmds.length != 1)
            throw new IllegalArgumentException();

        return new ChangeDir(fileSystem,cmds[0]);
    }

    private Command listCmd(FileSystem fileSystem) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new List(fileSystem.getDir());
    }

    private Command rListCmd(FileSystem fileSystem) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();


        return new RList(fileSystem.getDir());
    }

    // Criteria-related command

    private Command newSimpleCriCmd(FileSystem fileSystem) {
        if (cmds.length != 4 ||
                !cmds[0].matches("^[a-zA-Z]*$") ||
                cmds[0].length() != 2
        )
            throw new IllegalArgumentException();
        if (!(cmds[1].equals("name") || cmds[1].equals("type") || cmds[1].equals("size"))){
            System.out.print("Invalid attribute name - ");
            throw new IllegalArgumentException();
        }

        return new NewSimpleCri(fileSystem.getCris(), cmds[0], cmds[1], cmds[2], cmds[3]);
    }

    private Command newNegationCmd(FileSystem fileSystem) {
        if (cmds.length != 2 || !cmds[0].matches("^[a-zA-Z]*$") || cmds[0].length() != 2)
            throw new IllegalArgumentException();

        Cris cris = fileSystem.getCris();
        return new NewNegation(cris, cmds[0], cmds[1]);

    }

    private Command newBinaryCriCmd(FileSystem fileSystem) {
        if (cmds.length != 4 || !cmds[0].matches("^[a-zA-Z]*$") || cmds[0].length() != 2)
            throw new IllegalArgumentException();

        return new NewBinaryCri(fileSystem.getCris(), cmds[0], cmds[1], cmds[2], cmds[3]) ;
    }

    private Command printAllCriteriaCmd(FileSystem fileSystem) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new PrintAllCriteria(fileSystem.getCris());
    }

    private Command searchCmd(FileSystem fileSystem) {
        if (cmds.length != 1)
            throw new IllegalArgumentException();

        return new Search(fileSystem.getDir(), fileSystem.getCris(), cmds[0]);
    }

    private Command rSearchCmd(FileSystem fileSystem) {
        if (cmds.length != 1)
            throw new IllegalArgumentException();

        return new RSearch(fileSystem.getDir(),fileSystem.getCris(),cmds[0]);
    }

    // File system related command

    private Command undoCmd(FileSystem fileSystem, Undoer undoer) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new Undo(fileSystem, undoer);
    }

    private Command redoCmd(FileSystem fileSystem, Undoer undoer) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new Redo(fileSystem, undoer);
    }

    private Command storeCmd(FileSystem fileSystem) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new Store(fileSystem);
    }

    private Command loadCmd(CVFS cvfs) {
        if (cmds.length != 0)
            throw new IllegalArgumentException();

        return new Load(cvfs, cvfs.getFileSystem());
    }


}
