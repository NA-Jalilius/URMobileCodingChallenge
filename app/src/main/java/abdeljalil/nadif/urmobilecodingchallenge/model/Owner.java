package abdeljalil.nadif.urmobilecodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO of the owner of the repo
 */

public class Owner {
    @Expose
    @SerializedName("login")
    private String name;
    @Expose
    @SerializedName("avatar_url")
    private String avatar;

    public Owner() {
    }

    public Owner(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
