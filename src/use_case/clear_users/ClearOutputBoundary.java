package use_case.clear_users;

// TODO Complete me

import use_case.clear_users.ClearOutputData;

public interface ClearOutputBoundary {
    // TODO 24: Code the interface ClearOutputBoundary
    // TODO 24: Honestly, I think you can ignore the prepareFailView I made, I never call it anyways shrug*
    // TODO 24.5: So remember the CA diagram? The ClearOutputBoundary gets implemented by the ClearPresenter - head there next
    StringBuilder prepareSuccessView(ClearOutputData deletedUsers);

    void prepareFailView(String error);
}
