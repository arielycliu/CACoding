package use_case.clear_users;

// TODO Complete me

import entity.UserFactory;

import java.util.ArrayList;

public class ClearInteractor implements ClearInputBoundary {
    // TODO 14: This is the implementation of the ClearInputBoundary
    // TODO 14: Remember that we need this to create ClearControllers* in SignupUseCaseFactory
    // TODO 14: Add the constructor for the ClearInteractor and the attributes
    // TODO 14: Mostly just copied from the signup version
    final ClearUserDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary userPresenter;
    final UserFactory userFactory;


    // TODO 14.5: Now we can create the code to construct a Controller, head to ClearController.java
    public ClearInteractor(ClearUserDataAccessInterface clearUserDataAccessInterface,
                           ClearOutputBoundary clearOutputBoundary,
                           UserFactory userFactory) {
        this.userDataAccessObject = clearUserDataAccessInterface;
        this.userPresenter = clearOutputBoundary;
        this.userFactory = userFactory;

    }

    // TODO 17: So we're gonna write the code for the execute function
    // TODO 17: Now notice that in here there's actually a lot of things we have yet to code
    // TODO 17: The exact list I think is - userDataAccessObject.clearall(), ClearOutputData, and ClearOutputBoundary.prepareSuccessView
    // TODO 17.5: Let's start with ClearUserDataAccessInterface userDataAccessObject, head to ClearUserDataAccessInterface

    // TODO 21: Back from FileUserDataAccessObject? Great so step 1 is done, next up is ClearOutputData
    // TODO 21.5: Head to ClearOutputData

    // TODO 23: Two down, one to go -> Head to ClearOutputBoundary
    public StringBuilder execute(ClearInputData clearInputData){
        ArrayList<String> deletedUsers = userDataAccessObject.clearall();

        ClearOutputData clearOutputData = new ClearOutputData(deletedUsers);
        return userPresenter.prepareSuccessView(clearOutputData);
    }
}
