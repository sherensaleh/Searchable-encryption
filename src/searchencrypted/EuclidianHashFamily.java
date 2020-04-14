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
import searchencrypted.DistanceMeasure;
import searchencrypted.HashFunction;
import java.util.Arrays;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;
//@State(Scope.Benchmark)

public class EuclidianHashFamily implements HashFamily {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3406464542795652263L;
	private final int dimensions;
	private int w;
		
	public EuclidianHashFamily(int w,int dimensions){
		this.dimensions = dimensions;
		this.w=w;
	}
	
	@Override
	public HashFunction createHashFunction(){
		return new EuclideanHash(dimensions, w);
	}
	
	@Override
	public Integer combine(int[] hashes){
		return Arrays.hashCode(hashes);
	}

	@Override
	public DistanceMeasure createDistanceMeasure() {
		return new EuclideanDistance();
	}
}

