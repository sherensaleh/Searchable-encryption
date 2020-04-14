/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

import searchencrypted.HashTable;
import be.tarsos.lsh.Vector;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import searchencrypted.DistanceComparator;
import searchencrypted.DistanceMeasure;
import searchencrypted.HashFamily;
import be.tarsos.lsh.util.FileUtils;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;

/**
 * The index makes it easy to store vectors and lookup queries efficiently. For
 * the moment the index is stored in memory. It holds a number of hash tables,
 * each with a couple of hashes. Together they can be used for efficient lookup
 * of nearest neighbours.
 * 
 * @author Joren Six
 * 
 */
//@State(Scope.Benchmark)
public class Index implements Serializable{
	
	public static final long serialVersionUID = 3757702142917691272L;

	public final static Logger LOG = Logger.getLogger(Index.class.getName()); 

	 EuclidianHashFamily family;
	//public List<HashTable> hashTable = new ArrayList<>();
       // public HashTable hashTabble = new HashTable(5,new EuclidianHashFamily(4, 675));
        //public HashTable hashTabble ;
	public int evaluated;
        ReadData rrr=new ReadData();
    // HashTable htabble= new HashTable(5,new EuclidianHashFamily(4, 675));
	
	/**
	 * Create a new index.
	 * 
	 * @param family
	 *            The family of hash functions to use.
	 * @param numberOfHashes
	 *            The number of hashes that are concatenated in each hash table.
	 *            More concatenated hashes means that less candidates are
	 *            selected for evaluation.
	 * @param numberOfHashTables
	 *            The number of hash tables in use, recall increases with the
	 *            number of hash tables. Memory use also increases. Time needed
	 *            to compute a hash also increases marginally.
	 */
	public Index( EuclidianHashFamily family ,int numberOfHashes, int numberOfHashTables){
           // family=new EuclidianHashFamily(50,675);
		this.family = family;
		//hashTable = new ArrayList<searchencrypted.HashTable>();
		//for(int i = 0 ; i < numberOfHashTables ; i++ ){
			//hashTable.add(new HashTable(numberOfHashes, family));
                        //hashTable.equals(htabble);
		//}
		evaluated = 0;
	}
	
	/**
	 * Add a vector to the current index. The hashes are calculated with the
	 * current hash family and added in the right place.
	 * 
	 * @param vector
	 *            The vector to add.
	 */
	public void index(Vector vector) {
		//for (HashTable table : hashTable) {
			GUI.hashTabble.add(vector,true);
		//}
	}
	
	/**
	 * The number of hash tables used in the current index.
	 * @return The number of hash tables used in the current index.
	 */
	public int getNumberOfHashTables(){
		//return hashTable.size();
                return 1;
	}
	
	/**
	 * The number of hashes used in each hash table in the current index.
	 * @return The number of hashes used in each hash table in the current index.
	 */
	public int getNumberOfHashes(){
		return GUI.hashTabble.getNumberOfHashes();
	}

	/**
	 * Query for the k nearest neighbours in using the current index. The
	 * performance (in computing time and recall/precision) depends mainly on
	 * how the current index is constructed and how the underlying data looks.
	 * 
	 * @param query
	 *            The query vector. The center of the neighbourhood.
	 * @param maxSize
	 *            The maximum number of neighbours to return. Beware, the number
	 *            of neighbours returned lays between zero and the chosen
	 *            maximum.
	 * @return A list of nearest neighbours, the number of neighbours returned
	 *         lays between zero and a chosen maximum.
	 */
	public int[] query(final Vector query,int maxSize){
		//int[] candidateSet = new int[5];
            int[] v= new int[rrr.bf.k];
		//for(HashTable table : hashTable){
//			v = table.query(query);
			 v = GUI.hashTabble.add(query,false);
                         // v = GUI.HashTable.add(query,false);
                	//candidateSet.add(v);
		
               // }
                return v;
}
		//List<Vector>candidates = new ArrayList<Vector>(candidateSet);
		//evaluated += candidates.size();
		//DistanceMeasure measure = family.createDistanceMeasure();
		//DistanceComparator dc = new DistanceComparator(query, measure);
		//Collections.sort(candidates,dc);
		//if(candidates.size() > maxSize){
			//candidates = candidates.subList(0, maxSize);
		//}
		//return candidates;
	//}
	
	/**
	 * The number of near neighbour candidates that are evaluated during the queries on this index. 
	 * Can be used to calculate the average evaluations per query.
	 * @return The number of near neighbour candidates that are evaluated during the queries on this index. 
	 */
	public int getTouched(){
		return evaluated;
	}
	
	
	/**
	 * Serializes the index to disk.
	 * @param index the storage object.
	 */
	public static void serialize(Index index){
		try {
			String serializationFile = serializationName(index);;
			OutputStream file = new FileOutputStream(serializationFile);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {
				output.writeObject(index);
			} finally {
				output.close();
			}
		} catch (IOException ex) {

		}
	}
	
	/**
	 * Return a unique name for a hash table wit a family and number of hashes. 
	 * @param hashtable the hash table.
	 * @return e.g. "be.hogent.tarsos.lsh.CosineHashfamily_16.bin"
	 */
	private static String serializationName(searchencrypted.Index index){
		String name;
            name = index.family.getClass().getName();
		int numberOfHashes = index.getNumberOfHashes();
		int numberOfHashTables = index.getNumberOfHashTables();
		return name + "_" + numberOfHashes + "_" + numberOfHashTables + ".bin";
	}
	

	/**
	 * Deserializes the hash table from disk. If deserialization fails, 
	 * a new hash table object is created.
	 * 
	 * @param family The family.
	 * @param numberOfHashes the number of hashes.
	 * @param numberOfHashTables The number of hash tables
	 * @return a new, or deserialized object.
	 */
	public static Index deserialize(EuclidianHashFamily family,int numberOfHashes,int numberOfHashTables){
		Index index = new Index(family,numberOfHashes,numberOfHashTables);
		String serializationFile = serializationName(index);
		if(FileUtils.exists(serializationFile)){
			try {
				
				InputStream file = new FileInputStream(serializationFile);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);
				try {
					index = (Index) input.readObject();
				} finally {
					input.close();
				}
			} catch (ClassNotFoundException ex) {
				LOG.severe("Could not find class during deserialization: " + ex.getMessage());
			} catch (IOException ex) {
				LOG.severe("IO exeption during during deserialization: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return index;
	}

}



