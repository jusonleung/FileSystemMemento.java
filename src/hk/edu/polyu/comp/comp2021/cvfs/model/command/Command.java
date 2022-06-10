package hk.edu.polyu.comp.comp2021.cvfs.model.command;

/**
 * CVFS Command
 */
public interface Command {

    /**
     * Execute the command
     */
    void execute();

    String toString();
}
