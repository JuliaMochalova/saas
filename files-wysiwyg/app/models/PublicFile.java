package models;

import play.db.ebean.Model;

import javax.persistence.Id;
import java.net.MalformedURLException;
import java.util.UUID;

import static play.mvc.Controller.request;

/**
 *
 */
public class PublicFile extends Model implements File{
    @Id
    private UUID id;

    private String name;

    private java.io.File file;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setFile(java.io.File file) {
        this.file = file;
    }

    @Override
    public java.io.File getFile() {
        return file;
    }

    @Override
    public String getURL()  {
        return controllers.routes.Assets.at("/upload/"+getName()).absoluteURL(request());
    }
}
