package database;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "move")
public class MoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game")
    private Integer game;

    @Column(name = "player")
    private String player;

    @Column(name = "type")
    private String type;

    @Column(name = "x")
    private Integer x;

    @Column(name = "y")
    private Integer y;

    @Column(name = "time")
    private Timestamp time;

    public MoveEntity(){}

    public MoveEntity(int game,String player,String type,int x,int y){
        setGame(game);
        setPlayer(player);
        setType(type);
        setX(x);
        setY(y);
        setTime(new Timestamp(System.currentTimeMillis()));
    }

    public MoveEntity(int game,String player,String type){
        setGame(game);
        setPlayer(player);
        setType(type);
        setTime(new Timestamp(System.currentTimeMillis()));
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
