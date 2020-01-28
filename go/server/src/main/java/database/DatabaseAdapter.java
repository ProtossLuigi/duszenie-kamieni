package database;

public interface DatabaseAdapter {
    int newGame();
    void move(int game,String player,int x,int y);
    void pass(int game,String player);
    void finishGame(int id,String status);
}
