package main;

public class GameParameters {

    public boolean pvp;
    public int[] boardSize;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GameParameters))
            return false;
        if (((GameParameters) obj).pvp != pvp)
            return false;
        if (((GameParameters) obj).boardSize.length != 2)
            return false;
        for (int i=0; i < 2; i++){
            if (((GameParameters) obj).boardSize[i] != boardSize[i])
                return false;
        }
        return true;
    }
}