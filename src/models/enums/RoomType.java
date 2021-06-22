package models.enums;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    private int id;

    RoomType(final int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
