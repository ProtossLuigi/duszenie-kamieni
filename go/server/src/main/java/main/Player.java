package main;

import matchmaking.GameRoom;

public interface Player {

    void pawnPlaced(int x,int y,int color);
    void setWaitingRoom(GameRoom waitingRoom);
}
