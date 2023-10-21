package interface_adapter.clear_users;

// TODO Complete me

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearOutputData;

import javax.swing.*;
import java.util.ArrayList;

public class ClearPresenter implements ClearOutputBoundary {

    // TODO 25: Create the constructor and attributes for it
    // TODO 25: If you're curious, it's actually constructed once in the SignUpUseCaseFactory and passed into the ClearInteractor, which is passed into the Controller
    private final ClearViewModel clearViewModel;

    public ClearPresenter(ClearViewModel clearViewModel) {
        this.clearViewModel = clearViewModel;
    }

    // TODO 25: Code the prepareSuccessView which in my case really just transforms the output data to a string and returns it to the ClearInteractor
    @Override
    public StringBuilder prepareSuccessView(ClearOutputData clearOutputData) {
        StringBuilder deletedStr = new StringBuilder();
        for (String user : clearOutputData.getDeletedUsers()) {
            deletedStr.append(user + "\n");
        }
        return deletedStr;
    }

    // TODO 26: You can implement the prepareFailView if you did it in TODO 24 ClearOutputBoundary, but like I said, it's not connected to anything anywhere else
    // TODO 26.1: If you do implement this, then you need to go to ClearViewModel and implement getState()
    // TODO 26.2: You would also need to implement ClearState
    @Override
    public void prepareFailView(String error) {
        ClearState clearState = clearViewModel.getState();
        clearState.setError(error);
        clearViewModel.firePropertyChanged();
    }

    // TODO 26.5: I "think" you're maybe done now? idk run it?
}
