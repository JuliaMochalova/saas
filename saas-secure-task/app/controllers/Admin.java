package controllers;

import models.*;
import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.*;
import util.Secured;
import views.html.*;

import javax.persistence.Id;

@Security.Authenticated(Secured.class)
public class Admin extends Controller {

    public static Result profile() {
        return ok(profile.render(Form.form(ChangePassword.class)));
    }

    public static Result changePassword() {
        Form<ChangePassword> form = Form.form(ChangePassword.class).bindFromRequest();
        if (form.hasErrors()) return badRequest(profile.render(form));
        else {
            ChangePassword reg = form.get();
            User user = Auth.currentUser();
            user.setPassword(reg.newPassword);
            user.save();
            flash("success", "Пароль успешно изменен");
            return redirect(routes.Admin.profile());
        }
    }
}