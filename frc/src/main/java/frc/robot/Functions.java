/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Various functions that may be useful throughout the project.
 */
public class Functions {

    /**
     * Maps a value within a range to the equivalent in a different range.
     * 
     * @param val       the value to map
     * @param oldMin    the minimum of the old range
     * @param oldMax    the maximum of the old range 
     * @param newMin    the minimum of the new range
     * @param newMax    the maximum of the new range
     * @return          the corresponding value on the new range
     */
    public static double map(double val, double oldMin, double oldMax, double newMin, double newMax) {
        return (val - oldMin) / (oldMax - oldMin) * (newMax - newMin) + newMin;
    }
    
    /**
     * Returns the provided value only if it falls outside of the minimum magnitudes.
     * 
     * @param val   the value to check
     * @return      val if greater than 0.2 or less than -0.2, otherwise 0
     */
    public static double deadband(double val) {//made public to steal for testInit - Allison
        return val = (val < -0.2 ? val : (val > 0.2 ? val : (val = 0)));
    }

}
