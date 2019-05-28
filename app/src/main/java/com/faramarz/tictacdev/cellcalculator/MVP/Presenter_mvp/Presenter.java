package com.faramarz.tictacdev.cellcalculator.MVP.Presenter_mvp;

import com.faramarz.tictacdev.cellcalculator.MVP.Contract;
import com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.Model;

public class Presenter implements Contract.Presenter {

    Contract.Model model = new Model();
    Contract.View view;

    @Override
    public void getDoublingTime(double initialConcentration, double finalConcentration, double duration) {
        model.getDoublingTime(initialConcentration,finalConcentration,duration);

    }

    @Override
    public void onDoublingTimeReceived(double result) {
        view.onDoublingTimeReceived(result);
    }

    @Override
    public void attachView(Contract.View view) {
        this.view = view;
        model.attachPresenter(this);
    }


}
