package com.faramarz.tictacdev.cellcalculator.MVP;

public interface Contract {

    interface View {
        void onDoublingTimeReceived(double result);
    }

    interface Presenter {
        void getDoublingTime(double initialConcentration, double finalConcentration, double duration);

        void onDoublingTimeReceived(double result);

        void attachView(View view);

    }

    interface Model {
        void attachPresenter(Presenter presenter);

        void getDoublingTime(double initialConcentration, double finalConcentration, double duration);


    }


}
