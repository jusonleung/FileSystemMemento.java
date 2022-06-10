package hk.edu.polyu.comp.comp2021.cvfs.model.criteria;

import java.util.ArrayList;

/**
 * A arraylist to store all the existing criteria in a file system.
 */
public class Cris implements java.io.Serializable {

    private final ArrayList<Cri> cris;

    /**
     * Initialize this criteria storage ArrayList, and add a default criterion
     * "IsDocument" to this storage.
     */
    public Cris(){
        cris = new ArrayList<>();
        cris.add(new IsDocument());
    }

    /**
     * Check whether the storage has a criterion of the provided name.
     * @param name name to be checked
     * @return the index of this storage which the corresponding criterion's name
     * is same as the provided name, return -1 if the storage has no existing
     * criterion of the provided name
     */
    public int searchCri(String name){
        for (int i = 0; i < cris.size(); i++){
            if (cris.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Print all criteria in the storage
     */
    public void printAllCri(){
        for (Cri cri : cris) {
            System.out.println(cri.getName() + " " + cri);
        }
    }

    /**
     * Add a criterion to this storage
     * @param cri the criterion to be added
     */
    public void addCri(Cri cri){
        if (cri == null || searchCri(cri.getName()) != -1)
            throw new IllegalArgumentException();

        cris.add(cri);
    }

    /**
     * Get a criterion from the given index of this storage of criteria  .
     * @param index the index of the criterion in storage ArrayList
     * @return the corresponding criterion by the index
     */
    public Cri getCri(int index){
        return cris.get(index);
    }

    /**
     * Get the ArrayList of storage of criteria.
     * @return the ArrayList of storage of criteria
     */
    public ArrayList<Cri> getCris(){
        return cris;
    }

}
