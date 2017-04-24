import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Farima on 4/23/2017.
 */
public class Getter {
    public static void main(String[] args) {
        Getter g=new Getter();
        //ArrayList<FileFeature> clonePairs=new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String s = in.next();
        while (!s.equals("#")){
            FileFeature query=g.new FileFeature(s.split(":")[0],s.split(":")[1]);
            FileFeature candidate=g.new FileFeature(s.split(":")[2],s.split(":")[3]);
            ClonePair cp=g.new ClonePair();
            s = in.next();
            try {
                String inputFileName = new File(Paths.get("./input").toString()).listFiles()[0].getName();
                String eachFile = "";
                BufferedReader bf = new BufferedReader(new FileReader(Paths.get("./input").toString() + "/" + inputFileName));
                while ((eachFile = bf.readLine()) != null) {
                    String[] separatedLineFromMeta = eachFile.split("@#@");
                    String projId=separatedLineFromMeta[0].split(",")[0];
                    String fileId=separatedLineFromMeta[0].split(",")[1];
                    if (query.equals(g.new FileFeature(projId,fileId))){
                        cp.setPair1(query);
                        cp.setUniqueTokensPair1(separatedLineFromMeta[0].split(",")[3]);
                        cp.setCharsPair1(separatedLineFromMeta[0].split(",")[5]);
                        if (cp.allFeaturesFilled()){
                            System.out.println(cp.toString());
                        }
                        continue;
                    }
                    if(candidate.equals(g.new FileFeature(projId,fileId))){
                        cp.setPair2(candidate);
                        cp.setUniqueTokensPair2(separatedLineFromMeta[0].split(",")[3]);
                        cp.setCharsPair2(separatedLineFromMeta[0].split(",")[5]);
                        if (cp.allFeaturesFilled()){
                            System.out.println(cp.toString());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private class FileFeature{
        String projId;
        String fileId;


        public FileFeature(String pair1ProjId, String pair1FileId) {
            this.projId = pair1ProjId;
            this.fileId = pair1FileId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FileFeature clonePair = (FileFeature) o;

            if (!projId.equals(clonePair.projId)) return false;
            return fileId.equals(clonePair.fileId);

        }

        @Override
        public int hashCode() {
            int result = projId.hashCode();
            result = 31 * result + fileId.hashCode();
            return result;
        }

    }

    private class ClonePair{
        FileFeature pair1;
        FileFeature pair2;
        String uniqueTokensPair1="";
        String uniqueTokensPair2="";
        String charsPair1="";
        String charsPair2="";

        public FileFeature getPair1() {
            return pair1;
        }

        public void setPair1(FileFeature pair1) {
            this.pair1 = pair1;
        }

        public FileFeature getPair2() {
            return pair2;
        }

        public void setPair2(FileFeature pair2) {
            this.pair2 = pair2;
        }

        public String getUniqueTokensPair1() {
            return uniqueTokensPair1;
        }

        public void setUniqueTokensPair1(String uniqueTokensPair1) {
            this.uniqueTokensPair1 = uniqueTokensPair1;
        }

        public String getUniqueTokensPair2() {
            return uniqueTokensPair2;
        }

        public void setUniqueTokensPair2(String uniqueTokensPair2) {
            this.uniqueTokensPair2 = uniqueTokensPair2;
        }

        public String getCharsPair1() {
            return charsPair1;
        }

        public void setCharsPair1(String charsPair1) {
            this.charsPair1 = charsPair1;
        }

        public String getCharsPair2() {
            return charsPair2;
        }

        public void setCharsPair2(String charsPair2) {
            this.charsPair2 = charsPair2;
        }

        public boolean allFeaturesFilled(){
            if (uniqueTokensPair1.length()>0&&uniqueTokensPair2.length()>0&&charsPair1.length()>0&&charsPair2.length()>0)
                return true;
            return false;
        }

        @Override
        public String toString() {
            return  pair1.projId+":"+pair1.fileId+":"+
                    pair2.projId+":"+pair2.fileId+","+
                    uniqueTokensPair1 +","+
                    uniqueTokensPair2 + "," +
                    charsPair1 + "," +
                    charsPair2;
        }
    }

}
