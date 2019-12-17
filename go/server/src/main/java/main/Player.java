package main;

import matchmaking.GameRoom;
import rules.point.PointState;

public interface Player {

    void pawnPlaced(int x,int y,int color);
    void setWaitingRoom(GameRoom waitingRoom);

    void setColor();
    void setPointState();
    void setScore();
    PointState pointState = null;


}
