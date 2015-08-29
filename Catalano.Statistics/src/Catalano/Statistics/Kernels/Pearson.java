// Catalano Statistics Library
// The Catalano Framework
//
// Copyright © Diego Catalano, 2015
// diego.catalano at live.com
//
// Copyright © César Souza, 2009-2013
// cesarsouza at gmail.com
//
//    This library is free software; you can redistribute it and/or
//    modify it under the terms of the GNU Lesser General Public
//    License as published by the Free Software Foundation; either
//    version 2.1 of the License, or (at your option) any later version.
//
//    This library is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//    Lesser General Public License for more details.
//
//    You should have received a copy of the GNU Lesser General Public
//    License along with this library; if not, write to the Free Software
//    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
//

package Catalano.Statistics.Kernels;

/**
 * Pearson VII universal kernel (PUK).
 * @author Diego Catalano
 */
public class Pearson implements IMercerKernel<double[]>{
    
    private double omega;
    private double sigma;
    private double constant;

    /**
     * Get the omega parameter.
     * @return Omega parameter.
     */
    public double getOmega() {
        return omega;
    }

    /**
     * Set the omega parameter.
     * @param omega Omega parameter.
     */
    public void setOmega(double omega) {
        this.omega = omega;
        this.constant = 2 * Math.sqrt(Math.pow(2, (1 / omega)) - 1) / sigma;
    }

    /**
     * Get the sigma parameter.
     * @return Sigma parameter.
     */
    public double getSigma() {
        return sigma;
    }

    /**
     * Set the sigma parameter.
     * @param sigma Sigma parameter.
     */
    public void setSigma(double sigma) {
        this.sigma = sigma;
        this.constant = 2 * Math.sqrt(Math.pow(2, (1 / omega)) - 1) / sigma;
    }
    
    /**
     * Constructs a new Pearson VII universal kernel.
     */
    public Pearson() {
        this(1,1);
    }

    /**
     * Constructs a new Pearson VII universal kernel.
     * @param omega The Pearson's omega parameter w. Default is 1.
     * @param sigma The Pearson's sigma parameter s. Default is 1.
     */
    public Pearson(double omega, double sigma){
        setOmega(omega);
        setSigma(sigma);
    }

    @Override
    public double Function(double[] x, double[] y) {
        double xx = 0;
        double yy = 0;
        double xy = 0;
        for (int i = 0; i < x.length; i++){
            double u = x[i] * x[i];
            double v = y[i] * y[i];
            double uv = x[i] * y[i];
            xx += u;
            yy += v;
            xy += uv;
        }
        
        double sd = -2.0 * xy + xx + yy;
        double m = constant * Math.sqrt(sd);

        return 1.0 / Math.pow(1.0 + m * m, omega);
    }   
}