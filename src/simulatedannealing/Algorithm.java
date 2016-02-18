/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatedannealing;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Gerald Alba
 */
public class Algorithm {
    public static int []C;
    public int []cPrima;
    public Random random = new Random();
    public double shortestDistance =0;
    
    public double getTotalEnergy(int []order){
        double distance = 0;
        
        for (int i = 0; i < order.length; i++){
            if(order.length - 1  == i){
                distance += ReadExcel.matriz[order[i]][order[0]];
            }
            else{
                distance += ReadExcel.matriz[order[i]][order[i + 1]];
            }
        }
        
        return distance;
    }
    
    public int []getNextArrangement(int []c, int []newOrder){
        for (int i = 0; i < C.length; i++)
            newOrder[i] = C[i];
        
        int length = C.length - 1;
        int firstRandomCityIndex = random.nextInt(length - 1) + 1;
        int secondRandomCityIndex = random.nextInt(length - 1) + 1;

        for( ;firstRandomCityIndex == secondRandomCityIndex; ){
            firstRandomCityIndex = random.nextInt(length - 1) + 1;
            secondRandomCityIndex = random.nextInt(length - 1) + 1;        
        }
        
        int dummy = newOrder[firstRandomCityIndex];
        newOrder[firstRandomCityIndex] =  newOrder[secondRandomCityIndex];
        newOrder[secondRandomCityIndex] = dummy;
        
        return newOrder;
    }
    
    public double coolingTemperature(double t){
        double T = 0;
        double T0 = 1000;
        double tmax = 100;
        double Tf = 1.0;
        double aux = Math.pow((Tf / T0), (t / tmax));
        T = aux * T0;
        
        return T;    
    }
    
    public void Anneal(){
        int iteration = -1;

        double temperature = 400.0;
        double energiaPrima = 0;

        double p = 0;
        double q = 0;
        double energia = getTotalEnergy(C);
        int []next = new int[C.length];
        double deltaE = 0;

        while (temperature > .0001){
            if(iteration > 40000){
                break;
            }
            cPrima = getNextArrangement(C, next);
            energiaPrima = getTotalEnergy(cPrima);
            deltaE = energiaPrima - energia;

            q = Math.min(1, Math.exp(-deltaE / temperature));
            p = Math.random();

            if (p < q){
                for (int i = 0; i < C.length; i++){
                   C[i] = cPrima[i];
                }
                energia = getTotalEnergy(cPrima);
            }

            //cool down the temperature
            temperature = coolingTemperature(iteration);
            //temperature *= .999;
            iteration++;
        }

        shortestDistance = energia;
        System.out.println("Distancia óptima: " + NumberFormat.getNumberInstance(Locale.US).format(shortestDistance));
        System.out.println("Camino: " + Arrays.toString(C));
        System.out.println("Iteraciones: " + NumberFormat.getNumberInstance(Locale.US).format(iteration));
    }
      
}
