package use_case.clear_users;

// TODO Complete me

import java.util.ArrayList;

public interface ClearUserDataAccessInterface {

    // TODO 18: So uh yeah add the clearall* function declaration
    // TODO 18: it's public bc I couldn't figure out this one error okay...
    // TODO 18.5: So FileUserDataAccessObject implements SignupUserDataAccessInterface and LoginUserDataAccessInterface
    // TODO 18.5: I have decided it is also going to implement ClearUserDataAccessInterface, head to FileUserDataAccessObject next

    boolean existsByName(String identifier);
    public ArrayList<String> clearall();
}
