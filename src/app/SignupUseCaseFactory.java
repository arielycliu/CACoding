package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearPresenter;
import interface_adapter.clear_users.ClearViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInteractor;
import use_case.clear_users.ClearOutputBoundary;
import use_case.clear_users.ClearUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
            SignupViewModel signupViewModel, FileUserDataAccessObject userDataAccessObject,
            ClearViewModel clearViewModel) {
            // TODO 3: Change SignupUserDataAccessInterface to FileUserDataAccessObject, we want it to be more general
            // TODO 3: so that we can cast it into both SignupUserDataAccessInterface and ClearUserDataAccessInterface*
            // TODO 3: this way we can use it in both createUserSignupUseCase and createClearSignupUseCase*
            // TODO 4: Add "ClearViewModel clearViewModel" to the parameters

        try {

            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel,
                                                                        loginViewModel, userDataAccessObject);
            // TODO 5: Create a new instance of ClearController using the createClearSignupUseCase* function which we will create
            ClearController clearController = createClearSignupUseCase(clearViewModel, userDataAccessObject);
            // TODO 5.5: call the SignupView but pass in the just created clearController*, and the clearViewModel created in Main
            return new SignupView(signupController, signupViewModel, clearController, clearViewModel);
            // TODO 6.5: control-click into SignupView
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    // TODO 6: Create the createClearSignupUseCase function which we just called above
    // TODO 6: Takes in the clearViewModel* and clearUserDataAccessInterface* (userDataAccessObject) as parameters
    // TODO 6: This functions creates a clearInteractor* (basically the input boundary) which is an argument we need to create the Controller - which we return
    // TODO 6: Don't worry, we will define the ClearInteractor next next - let's head to SignupView first!
    private static ClearController createClearSignupUseCase(ClearViewModel clearViewModel, ClearUserDataAccessInterface clearUserDataAccessInterface) throws IOException {

        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(clearViewModel);

        UserFactory userFactory = new CommonUserFactory();

        ClearInputBoundary clearInteractor = new ClearInteractor(
                clearUserDataAccessInterface, clearOutputBoundary, userFactory);
        return new ClearController(clearInteractor);
    }


}
