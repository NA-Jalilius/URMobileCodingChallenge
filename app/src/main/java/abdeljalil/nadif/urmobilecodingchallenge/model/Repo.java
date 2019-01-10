package abdeljalil.nadif.urmobilecodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor @Data @NoArgsConstructor

/**
 * POJO of one Repo
 */

public class Repo {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("stargazers_count")
    private int numberStars;
    @Expose
    @SerializedName("owner")
    private Owner owner;

    public Repo() {
    }

    public Repo(String name, String description, int numberStars/*, Owner owner*/) {
        this.name = name;
        this.description = description;
        this.numberStars = numberStars;
        //this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(int numberStars) {
        this.numberStars = numberStars;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberStars=" + numberStars +
                ", owner=" + owner +
                '}';
    }


}