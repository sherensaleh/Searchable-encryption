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
import be.tarsos.lsh.Vector;

/**
 * A distance measure defines how distance is calculated, measured as it were, between two vectors.
 * Each hash family has a corresponding distance measure which is abstracted using this interface.
 * @author Joren Six
 */
public interface DistanceMeasure {
	
	/**
	 * Calculate the distance between two vectors. From one to two.
	 * @param one The first vector.
	 * @param other The other vector
	 * @return A value representing the distance between two vectors.
	 */
	double distance(Vector one, Vector other);
}

