package cz2002;

import java.util.ArrayList;
/**
 * This is the base interface that all other IO managers will implement
 * @author xingwei1
 *
 */
public interface BaseIOMGR {
	/**
	 * This is the method to read from the IO managers respective files
	 * @return return type is the specific ArrayList of the objects that the IO managers are responsible for
	 */
	public abstract ArrayList readFromFile();
}
