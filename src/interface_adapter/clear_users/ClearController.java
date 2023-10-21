package interface_adapter.clear_users;

import use_case.clear_users.ClearInputBoundary;
import use_case.clear_users.ClearInputData;

import java.util.ArrayList;

// TODO Complete me
public class ClearController {
    // TODO 15: Add the code for the attributes and to construct a new ClearController
    final ClearInputBoundary clearUseCaseInteractor;

    public ClearController(ClearInputBoundary clearUseCaseInteractor) {
        this.clearUseCaseInteractor = clearUseCaseInteractor;
    }

    // TODO 16: Now, I want you to remember the stuff in SignupView where we called "clearController.execute()"
    // TODO 16: We have to implement execute which is really just passing in a random input data to the clearUseCaseInteractor/InputBoundary
    // TODO 16.optional: Ctrl-click on ClearInputData and create it's constructor if you want to have input data
    // TODO 16.5: Now notice that clearUseCaseInteractor is of the type ClearInputBoundary, which if you head there you'll notice
    // TODO 16.5: is actually implemented by ClearInteractor - so yeah head to ClearInteractor

    public StringBuilder execute(){
        ClearInputData clearInputData = new ClearInputData();
        return clearUseCaseInteractor.execute(clearInputData);
    };
}
