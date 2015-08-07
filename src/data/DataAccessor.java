package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class DataAccessor {

    private static DataAccessor instance;

    public void savePost(Post post) {
        try {
            File dir = new File("./results");
            if(!dir.exists())
                dir.mkdir();

            FileWriter fw = new FileWriter("./results/" +
                    (post.getContents().equals("기사 내용을 가져올 수 없습니다.") ? "_" : "") + post.getTitle());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[Title]");
            bw.newLine();
            bw.write(post.getTitle());
            bw.newLine();
            bw.newLine();
            bw.write("[Link]");
            bw.newLine();
            bw.write(post.getLink());
            bw.newLine();
            bw.newLine();
            bw.write("[Contents]");
            bw.newLine();
            bw.write(post.getContents());

            bw.close();

            System.out.println(post.getTitle() + " 기사를 저장했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataAccessor getInstance() {
        return instance;
    }

    static {
        instance = new DataAccessor();
    }

}
