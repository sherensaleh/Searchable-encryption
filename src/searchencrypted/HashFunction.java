/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchencrypted;

/**
 *
 * @author ibraheem
 */
import java.io.Serializable;

import be.tarsos.lsh.Vector;

/**
 * A hash function can hash a vector of arbitrary dimensions to an integer
 * representation. The hash function needs to be locality sensitive to work in
 * the locality sensitive hash scheme. Meaning that vectors that are 'close'
 * according to some metric have a high probability to end up with the same
 * hash.
 * 
 * @author Joren Six
 */
public interface HashFunction extends Serializable {
	/**
	 * Hashes a vector of arbitrary dimensions to an integer. The hash function
	 * needs to be locality sensitive to work in the locality sensitive hash (LSH)
	 * scheme. Meaning that vectors that are 'close' according to some metric
	 * have a high probability to end up with the same hash.
	 * 
	 * @param vector
	 *            The vector to hash. Can have any number of dimensions.
	 * @return A locality sensitive hash (LSH). Vectors that are 'close'
	 *         according to some metric have a high probability to end up with
	 *         the same hash.
	 */
	int hash(Vector vector);
}

