/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import be.tarsos.lsh.Vector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;

import searchencrypted.HashFamily;
import searchencrypted.EuclidianHashFamily;
//import be.tarsos.lsh.families.HashFunction;

/**
 * An index contains one or more locality sensitive hash tables. These hash
 * tables contain the mapping between a combination of a number of hashes
 * (encoded using an integer) and a list of possible nearest neighbours.
 *
 * @author Joren Six
 */
//@State(Scope.Benchmark)
class HashTable implements Serializable {

    public static final long serialVersionUID = -5410017645908038641L;
    /**
     * Contains the mapping between a combination of a number of hashes (encoded
     * using an integer) and a list of possible nearest neighbours
     */
  //  ReadData r=new ReadData();
    public HashMap<Integer, List<Vector>> hashTable;
     EuclideanHash [] hashFunctions ;
    
     EuclidianHashFamily family=new EuclidianHashFamily(25,675);
    int[] combinedHash;
    static List<int[]> listOfCombinedHash ;

    /**
     * Initialize a new hash table, it needs a hash family and a number of hash
     * functions that should be used.
     *
     * @param numberOfHashes The number of hash functions that should be used.
     * @param family The hash function family knows how to create new hash
     * functions, and is used therefore.
     */
    public HashTable(int numberOfHashes, EuclidianHashFamily family) {
        listOfCombinedHash = new ArrayList<int[]>();
        hashTable = new HashMap<Integer, List<Vector>>();
        // this.hashFunctions[] =  new hashFunctions[numberOfHashes];
       this.hashFunctions =  new EuclideanHash[numberOfHashes];
        for (int i = 0; i < numberOfHashes; i++) {
           // hashFunctions[i]=new EuclideanHash(675,10);
            hashFunctions[i] =  (EuclideanHash) family.createHashFunction();
        }
        this.family = family;
    }

    /**
     * Query the hash table for a vector. It calculates the hash for the vector,
     * and does a lookup in the hash table. If no candidates are found, an empty
     * list is returned, otherwise, the list of candidates is returned.
     *
     * @param query The query vector.
     * @return Does a lookup in the table for a query using its hash. If no
     * candidates are found, an empty list is returned, otherwise, the list of
     * candidates is returned.
     */
    public int[] query(Vector query) {
        int[] combinedHash = hashs(query);
        //if(hashTable.containsKey(combinedHash))
        //return hashTable.get(combinedHash);
        //else
        //return new ArrayList<Vector>();
        return combinedHash;
    }

    /**
     * Add a vector to the index.
     *
     * @param vector
     */
    public int[] add(Vector vector,boolean flag) {
        combinedHash = hashs(vector);
        if (flag)
        //  listOfCombinedHash  =new ArrayList<int[]>();
        {
        listOfCombinedHash.add(combinedHash);
        for (int i = 0; i < combinedHash.length; i++) {
            if (!hashTable.containsKey(combinedHash[i])) {
                hashTable.put(combinedHash[i], new ArrayList<Vector>());
            } else {
                hashTable.get(combinedHash[i]).add(vector);
            }
        }
        }
        return combinedHash;
    }

    /**
     * Calculate the combined hash for a vector.
     *
     * @param vector The vector to calculate the combined hash for.
     * @return An integer representing a combined hash.
     */
    public int[] hashs(Vector vector) {
        //int hashes[] = new int[7];
        int hashes[] = new int[hashFunctions.length];
        for (int i = 0; i < hashFunctions.length; i++) {
            //hashFunctions[i] = new EuclidianHashFamily(2,675).createHashFunction();
            hashes[i]=this.hashFunctions[i].hash(vector);
           // hashes[i] = new EuclideanHash(675, 2).hash(vector);
        }
        //Integer combinedHash = family.combine(hashes);
        return hashes;
    }

    /**
     * Return the number of hash functions used in the hash table.
     *
     * @return The number of hash functions used in the hash table.
     */
    public int getNumberOfHashes() {
        return hashFunctions.length;
    }
}
