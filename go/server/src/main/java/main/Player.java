package main;

import matchmaking.GameRoom;
import rules.Game;
import rules.point.PointState;

public interface Player {

    void pawnPlaced(int x,int y,int color);
    void setWaitingRoom(GameRoom waitingRoom);
    void setGame(Game game);

    void notifWin();

    void setScore(int yourScore,int opponentScore);

    void notifLoss();
    void notifDraw();
    void sendChatMessage(String line);
    void yourTurn();
    void opponentTurn();
    void startGame(int width,int height,int color);
    void waiting();
    void attemptPlacePawn(int x,int y);
    void pass();
}
