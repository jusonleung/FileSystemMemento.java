package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;


import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.FileObject;

/**
 * SizeSimpleCri is a simple criterion that selects the files that their file size
 * meets certain requirement.
 */
public class SizeSimpleCri extends Cri {
    private final String op;
    private final int val;

    /**
     * Create a new simple criterion with size attribute
     * @param name criterion's name
     * @param op criterion's relational and equality operator
     * @param val criterion's value
     * @throws IllegalArgumentException if the operator is invalid
     */
    public SizeSimpleCri(String name, String op, int val){
        super(name);
        if (!isValid(op)) throw new IllegalArgumentException();
        this.op = op;
        this.val = val;
    }

    @Override
    public boolean fitCri(FileObject fileObject) {
        switch (op){
            case ">":
                return fileObject.getSize() > val;
            case "<":
                return fileObject.getSize() < val;
            case ">=":
                return fileObject.getSize() >= val;
            case "<=":
                return fileObject.getSize() <= val;
            case "==":
                return fileObject.getSize() == val;
            case "!=":
                return fileObject.getSize() != val;
        }
        return false;
    }

    /**
     * Check whether the simple criterion with size attribute is valid or not
     * @param op criterion's relational and equality operator
     * @return true if valid, false if invalid
     */
    private boolean isValid(String op) {
        switch (op){
            case ">":
            case "<":
            case ">=":
            case "<=":
            case "==":
            case "!=":
                return true;
            default:
                System.out.print("Invalid op - ");
        }
        return false;
    }

    public String toString(){
        return "size " + op +  " " + val;
    }
}
