package use_case.clear_users;

// TODO Complete me

import java.util.ArrayList;

public class ClearOutputData {

    // TODO 22: This is just an object to wrap our ArrayList<String> of deletedUsers in
    // TODO 22: Write the attributes, create a constructor, and create a getter which I think I use in ClearPresenter
    // TODO 22.5: When you're done, head back to ClearInteractor
    private final ArrayList<String> deletedUsers;

    public ClearOutputData(ArrayList<String> deletedUsers) {
        this.deletedUsers = deletedUsers;
    }

    public ArrayList<String> getDeletedUsers() {
        return deletedUsers;
    }
}
