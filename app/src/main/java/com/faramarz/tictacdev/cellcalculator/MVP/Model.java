package com.faramarz.tictacdev.cellcalculator.MVP;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Model implements Contract.Model {
    Contract.Presenter presenter;

    @Override
    public void attachPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getDoublingTime(double initialConcentration, double finalConcentration, double duration) {

        double soorateKasr = duration * Math.log(2);
        double makhrajeKasr = (Math.log(finalConcentration)) - (Math.log(initialConcentration));
        double doublingTime = (soorateKasr) / (makhrajeKasr);
        presenter.onDoublingTimeReceived(doublingTime);

    }




}
