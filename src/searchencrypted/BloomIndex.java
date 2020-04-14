/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;


import be.tarsos.lsh.Vector;
import java.util.List;
import java.util.Set;

//import com.skjegstad.utils.BloomFilter;


public  interface BloomIndex<E> {
    public int deleteFromIndex(int id, InsDelUpdateStatistics stat);

    /**
     * Return the size - number of bits in a Bloom Filter indexed by this
     * index
     *
     * @return
     */
    public int getBloomFilterSize();

    public int getHeight();

    public Set<Integer> getIDs();

    public boolean getIsRootAllOne();

    public int getNbChildrenRoot();

    /**
     * Return the number of nodes in this Bloom Index
     *
     * @return
     */
    public int getSize();

    public void insertBloomFilter(BloomFilter<E> bf,InsDelUpdateStatistics stat);

    /**
     * Return matching ids
     */
    public List<Integer> search(Integer[] o, SearchStatistics stat);

   
    public  int updateIndex(BloomFilter<E> newBloomFilter,InsDelUpdateStatistics stat);

}
