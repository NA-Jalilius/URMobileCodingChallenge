package abdeljalil.nadif.urmobilecodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor @Data @NoArgsConstructor


/**
 * POJO that'll contain all the repos of one page (a list of repos)
 */
public class Repos {

    @Expose
    @SerializedName("items")
    private List<Repo> repos;

    public Repos() {
    }

    public Repos(List<Repo> repos) {
        this.repos = repos;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public String toString() {
        return "Repos{" +
                "repos=" + repos +
                '}';
    }
}

