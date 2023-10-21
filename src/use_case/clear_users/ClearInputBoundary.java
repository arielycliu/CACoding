package use_case.clear_users;

// TODO Complete me


public interface ClearInputBoundary {

    // TODO 13: Add execute function with ClearInteractor* which we will need to implement
    // TODO 13: Execute returns a StringBuilder which goes from the DAO -> Interactor -> Controller -> SignupView
    // TODO 13: I know it's illegal, if there's a better method I would suggest you do it
    StringBuilder execute(ClearInputData clearInputData);
    // TODO 13.5: Head to ClearInteractor.java next
}
