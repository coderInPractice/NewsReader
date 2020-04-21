package edu.learn.newsreader;

class NewsList {

    private String heading;
    private String timeStamp;
    private String img_url;
    private String url;

    public NewsList(String heading, String timeStamp, String img_url, String url) {
        this.heading = heading;
        this.timeStamp = timeStamp;
        this.img_url = img_url;
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.img_url = url;
    }
}
