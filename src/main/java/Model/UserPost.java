package Model;

import lombok.Data;
import lombok.Getter;

public @Data
class UserPost {
    public UserPost(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public UserPost(){
        this.id = -1;
        this.userId = -1;
        this.title = null;
        this.body = null;
    };
    @Getter
    private int userId;
    @Getter
    private int id;
    @Getter
    private String title;
    @Getter
    private String body;
}
