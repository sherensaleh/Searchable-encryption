/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;

//@State(Scope.Benchmark)

public class SearchStatistics {

    /** Number of BloomFilters checked for matches */
    public int nbBFChecks;

    public SearchStatistics() {
        nbBFChecks = 0;
    }

    /**
     * Reset the statistics to 0
     */
    public void clear() {
        nbBFChecks = 0;
    }
}

