package hk.edu.polyu.comp.comp2021.cvfs.model.command;

import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.Cris;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.NameSimpleCri;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.SizeSimpleCri;
import hk.edu.polyu.comp.comp2021.cvfs.model.criteria.TypeSimpleCri;
import hk.edu.polyu.comp.comp2021.cvfs.model.fileSystemObject.DocType;

/** Command "newSimpleCri" represents this command can create new simple criterion in file system.*/
public class NewSimpleCri implements Command{
    private Cris cris;
    private String name;
    private String attrName;
    private String op;
    private String val;

    /**
     * @param cris the storage to be added
     * @param name the name
     * @param attrName the attribute name
     * @param op the operator
     * @param val the value
     */
    public NewSimpleCri(Cris cris, String name, String attrName, String op, String val){
        this.cris = cris;
        this.name = name;
        this.attrName = attrName;
        this.op = op;
        this.val = val;
    }

    @Override
    public void execute() {
        createSimpleCri(cris);
    }

    private void createSimpleCri(Cris cris){

        switch (attrName){
            case ("name"):
                createNameSimpleCri(cris);
                break;
            case ("type"):
                createTypeSimpleCri(cris);
                break;
            case ("size"):
                createSizeSimpleCri(cris);
                break;
        }
    }

    private boolean checkAlphabet(String string){
        for (Character character:string.toCharArray()){
            if (!Character.isAlphabetic(character)) return false;
        }
        return true;
    }

    private void createTypeSimpleCri(Cris cris){
        if (!op.equals("equals"))
            throw new IllegalArgumentException();

        String val1 = val.substring(1,val.length()-1).toUpperCase();
        DocType val = DocType.valueOf(val1);

        TypeSimpleCri cri2 = new TypeSimpleCri(name,val);
        cris.addCri(cri2);
    }

    private void createNameSimpleCri(Cris cris){
        if (!op.equals("contains")){
            System.out.println("Invalid operator");
            throw new IllegalArgumentException();
        }
        NameSimpleCri cri1 = new NameSimpleCri(name,val);
        cris.addCri(cri1);
    }

    private void createSizeSimpleCri(Cris cris){
        int val2 = Integer.parseInt(val);
        cris.addCri(new SizeSimpleCri(name,op,val2));
    }

    @Override
    public String toString() {
        return "NewSimpleCri{" +
                "cris=" + cris +
                ", name='" + name + '\'' +
                ", attrName='" + attrName + '\'' +
                ", op='" + op + '\'' +
                ", val='" + val + '\'' +
                '}';
    }
}
