package models;

import controllers.Auth;
import play.data.validation.Constraints;

/**
 * Форма изменения пароля
 */
public class ChangePassword{
    @Constraints.Required(message = "Введите текущий пароль")
    public String password;

    @Constraints.Required(message = "Введите новый пароль")
    public String newPassword;


    // Валидация
    public String validate() {
        String email = Auth.currentUserEmail();
        if (email == null) return "Сперва необходимо аутентифицироваться";

        return User.authenticate(email, password);
    }
}
