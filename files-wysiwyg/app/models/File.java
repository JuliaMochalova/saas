package models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * Created by User on 17.11.2015.
 */
public interface File {
    UUID getId();
    void setName(String name);
    String getName();
    void setFile(java.io.File file);
    java.io.File getFile();
    String getURL();
    void save();
    void delete();
}
