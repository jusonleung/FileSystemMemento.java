package hk.edu.polyu.comp.comp2021.cvfs;

import hk.edu.polyu.comp.comp2021.cvfs.model.CVFS;

/**
 * Starting the program
 */
public class Application {

    /**
     * @param args running the program
     */
    public static void main(String[] args){
        CVFS cvfs = new CVFS();

        // Initialize and utilize the system
        cvfs.run();
    }
}
