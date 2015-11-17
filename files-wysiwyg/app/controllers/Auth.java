package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Auth extends Controller {

    // Страница формы для аутентификации
    public static Result login() {
        if (session("email") != null) return redirect(routes.Application.index());
        else return ok(login.render(Form.form(Login.class)));
    }

    // Обработка формы аутентификации
    public static Result auth() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors())
            return badRequest(login.render(loginForm));
        else {
            session("email", loginForm.get().email);
            flash("success","Вы успешно аутентифицировались. Добро пожаловать!");
            return redirect(routes.Application.index());
        }
    }

    // Выход и очистка сессии
    public static Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }

    // Страница регистрации
    public static Result signup() {
        if (session("email") != null) return redirect(routes.Application.index());
        else return ok(register.render(Form.form(Register.class)));
    }

    // Регистрация
    public static Result register() {
        Form<Register> regForm = Form.form(Register.class).bindFromRequest();
        if (regForm.hasErrors()) return badRequest(register.render(regForm));
        else {
            Register reg = regForm.get();
            User user = new User(reg.email, reg.password);
            user.save();
            session("email", reg.email);
            return redirect(routes.Application.index());
        }
    }

    public static String currentUserEmail() {
        return session("email");
    }

    public static User currentUser(){
        return User.find.byId(currentUserEmail());
    }
}
