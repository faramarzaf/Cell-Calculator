package com.faramarz.tictacdev.cellcalculator.Normal;

public class DoublingComputerTime {
    public double initialConcentration, finalConcentration, duration;

    public DoublingComputerTime() {
    }

    public double ComputeDoubling(double initialConcentration, double finalConcentration, double duration) {
        double soorateKasr = duration * Math.log(2);
        double makhrajeKasr = (Math.log(finalConcentration)) - (Math.log(initialConcentration));

        double doublingTime = (soorateKasr) / (makhrajeKasr);

        return doublingTime;
    }
}
