package webubb.domain;

public class ImageTile
{
    private int id;
    private int userid;
    private String img_src;
    private int i;
    private int j;
    private boolean empty;

    public ImageTile(int id, int userid, String img_src, int i, int j, boolean is_empty) {
        this.id = id;
        this.userid = userid;
        this.img_src = img_src;
        this.i = i;
        this.j = j;
        this.empty = is_empty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setIsempty(boolean is_empty) {
        this.empty = is_empty;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getImg_src() {
        return img_src;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isEmpty() {
        return empty;
    }




}
