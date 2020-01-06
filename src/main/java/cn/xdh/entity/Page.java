package cn.xdh.entity;

public class Page {
    private String username;
    private int pageNum;

    public Page(String username, int pageNum) {
        this.username = username;
        this.pageNum = pageNum;
    }

    public Page() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
