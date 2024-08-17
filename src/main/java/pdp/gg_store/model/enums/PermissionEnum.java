package pdp.gg_store.model.enums;

import java.util.List;

public enum PermissionEnum {

    //FOR USER ROLE
    CREATE,//
    VIEW_OWN,
    EDIT_OWN,
    DELETE_OWN,


    //FOR ADMIN ROLE
    VIEW_ANY,
    EDIT_ANY,
    DELETE_ANY,

    //ROLE
    VIEW_ROLE,
    CREATE_ROLE,
    EDIT_ROLE,
    DELETE_ROLE,

    //USER
    VIEW_USERS,
    BLOCK_USER,


    ;

    public static List<PermissionEnum> userPermissions() {
        return List.of(
                CREATE,
                VIEW_OWN,
                EDIT_OWN,
                DELETE_OWN
        );
    }
}
