package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataAccessor {

    private static DataAccessor instance;

    public void savePost(Post post) {
        try {
            File dir = new File("./results");
            if(!dir.exists())
                dir.mkdir();

            String date = "";
            if (!post.getContents().contains("날짜를 찾을 수 없습니다")) {
                Pattern pattern = Pattern.compile("\\[\\|.*\\|\\]");
                Matcher match = pattern.matcher(post.getContents());
                if (match.find())
                    date = match.group(0);

                date = date.replaceAll("[^0-9]*", ".").replace("..", "-");
                date = date.replace(".", "");
                date = date.substring(1, date.length() - 1);

                pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
                match = pattern.matcher(date);
                if (!match.find())
                    date = "20" + date;
            } else {
                date = "[날짜를 찾을 수 없습니다]";
            }

            FileWriter fw = new FileWriter("./results/[" + date + "] " +
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
            bw.write(post.getContents().replace(date, ""));

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
