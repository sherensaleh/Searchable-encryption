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
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
import searchencrypted.DistanceMeasure;

/**
 * Calculates the <a
 * href="http://en.wikipedia.org/wiki/Euclidean_distance">Euclidean distance</a>
 * between two vectors. Sometimes this is also called the L<sub>2</sub>
 * distance.
 * 
 * @author Joren Six
 */
//@State(Scope.Benchmark)
public class EuclideanDistance implements DistanceMeasure {
	
	/* (non-Javadoc)
	 * @see be.hogent.tarsos.lsh.families.DistanceMeasure#distance(be.hogent.tarsos.lsh.Vector, be.hogent.tarsos.lsh.Vector)
	 */
	@Override
	public double distance(Vector one, Vector other) {
		double sum = 0.0;
		for(int d = 0 ; d < one.getDimensions() ; d++) {
			//double delta = one.get(d) * other.get(d);
                        double delta = one.get(d) - other.get(d);
			sum += delta * delta;
		}
              //  if(sum==2)
		//return Math.sqrt(sum);
                //return Math.sqrt(sum);
                return (sum);
	}
}
