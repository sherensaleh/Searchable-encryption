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
import java.util.Comparator;

import be.tarsos.lsh.Vector;

/**
 * This comparator can be used to sort candidate neighbours according to their
 * distance to a query vector. Either for linear search or to sort the LSH
 * candidates found in colliding hash bins.
 * 
 * @author Joren Six
 */
public class DistanceComparator implements Comparator<Vector>{
	
	private final Vector query;
	private final DistanceMeasure distanceMeasure;
	
	/**
	 * 
	 * @param query
	 * @param distanceMeasure
	 */
	public DistanceComparator(Vector query,DistanceMeasure distanceMeasure){
		this.query = query;
		this.distanceMeasure = distanceMeasure;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Vector one, Vector other) {
		Double oneDistance = distanceMeasure.distance(query,one);
		Double otherDistance = distanceMeasure.distance(query,other);
		return oneDistance.compareTo(otherDistance);
	}
}

