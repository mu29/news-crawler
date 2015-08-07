package data;

import java.util.ArrayList;

public class DataAccessor {

    private ArrayList<Post> postList;
    private static DataAccessor instance;

    private DataAccessor() {
        postList = new ArrayList<>();
    }

    public void addPost(Post post) {
        postList.add(post);
    }

    public void save() {
        
    }

    public static DataAccessor getInstance() {
        return instance;
    }

    static {
        instance = new DataAccessor();
    }

}
