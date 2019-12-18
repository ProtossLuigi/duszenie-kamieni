package main;

import matchmaking.GameRoom;
import rules.point.PointState;

public interface Player {

    void pawnPlaced(int x,int y,int color);
    void setWaitingRoom(GameRoom waitingRoom);

    void notifWin();

    void setScore(int yourScore,int opponentScore);

    void notifLoss();
    void sendChatMessage(String line);
    void yourTurn();
    void opponentTurn();
    void startGame();
    void waiting();
}
