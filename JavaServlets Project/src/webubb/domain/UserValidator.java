package webubb.domain;

public class UserValidator {
    public static String validate(User entity) {
        StringBuilder errors = new StringBuilder();
        if (entity.getUsername().length()<3) errors.append("Username too short!\n");
        if (entity.getPassword().length()<5) errors.append("Password too short!\n");
        if (!entity.getPassword().matches(".*\\d+.*")) errors.append("Password should contain at least a digit!\n");
        if (entity.getUsername().equals(entity.getPassword())) errors.append("Username and Password shouldn't be equal!\n");
        return errors.toString();
    }
}
