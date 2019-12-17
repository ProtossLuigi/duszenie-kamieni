package rules;

import main.Player;
import rules.board.GameState;
import rules.point.Direction;
import rules.point.Point;
import rules.point.PointState;
import rules.territory.OwnerTerritory;
import rules.territory.Territory;

import java.util.ArrayList;

public class Logic {

    GameState gameState;

    public Logic() {


    }


    Territory getTerritory(Point point, Territory territory) {

        PointState pState = gameState.getPointState(point);
        if (pState != PointState.EMPTY) {
            return new Territory(getChainPoints(point, null), OwnerTerritory.valueOf(pState.toString()));
        }
        boolean isRoot = false;

        if (territory == null) {
            territory = new Territory(null, null);
            isRoot = true;
        }
        territory.points.add(point);

        for (Direction side : Direction.values()
        ) {
            Point nPoint = point.getNeighbor(side);
            PointState nState = gameState.getPointState(nPoint);

            if (nState == PointState.EMPTY) {
                if (!nPoint.isInArray(territory.points)) {
                    getTerritory(nPoint, territory);
                }
            } else if (territory.owner != OwnerTerritory.NEUTRAL) {

                if (territory.owner == OwnerTerritory.UNKNOWN) {
                    territory.owner = OwnerTerritory.valueOf(nState.toString());
                } else if (territory.owner != OwnerTerritory.valueOf(nState.toString())) {
                    territory.owner = OwnerTerritory.NEUTRAL;

                }
            }
        }

        if (isRoot && territory.owner == OwnerTerritory.UNKNOWN) {
            territory.owner = OwnerTerritory.NEUTRAL;
        }

        return territory;

    }

    ArrayList<Point> getChainPoints(Point point, ArrayList<Point> chainPoints) {

        PointState pState = gameState.getPointState(point);

        if (pState == PointState.EMPTY) {
            return new ArrayList<>();
        }
        if (chainPoints == null) {
            chainPoints = new ArrayList<>();
        }
        chainPoints.add(point);

        for (Direction side :
                Direction.values()
        ) {
            Point nPoint = point.getNeighbor(side);


            PointState nState = gameState.getPointState(nPoint);

            if (pState == nState) {
                if (!nPoint.isInArray(chainPoints)) {
                    getChainPoints(nPoint, chainPoints);
                }
            }

        }
        return chainPoints;

    }

    ArrayList<Point> getCapturedPoints(Point point) {

        ArrayList<Point> capPoints = new ArrayList<>();

        for (Direction side :
                Direction.values()
        ) {
            Point nPoint = point.getNeighbor(side);
            PointState pState = gameState.getPointState(point);
            PointState nState = gameState.getPointState(nPoint);

            if (nState != pState && nState != PointState.EMPTY) {
                if (!nPoint.isInArray(capPoints) && getLibertyPoints(nPoint, null, null).size() == 0) {
                    capPoints.addAll(getChainPoints(nPoint, null));
                }

            }

        }

        return capPoints;
    }

    ArrayList<Point> getLibertyPoints(Point point, ArrayList<Point> chainPoints, ArrayList<Point> libPoints) {

        if (chainPoints == null) {
            chainPoints = new ArrayList<>();
        }
        if (libPoints == null) {
            libPoints = new ArrayList<>();
        }
        for (Direction side :
                Direction.values()) {
            Point nPoint = point.getNeighbor(side);
            PointState pState = gameState.getPointState(point);
            PointState nState = gameState.getPointState(nPoint);
            if (pState == nState) {
                chainPoints.add(point);
                if (!nPoint.isInArray(chainPoints)) {
                    getLibertyPoints(nPoint, chainPoints, libPoints);
                }
            } else if (nState == PointState.EMPTY) {
                if (!nPoint.isInArray(libPoints)) {
                    libPoints.add(nPoint);
                }
            }

        }


        return libPoints;
    }


    boolean isValidMove(Point point, Player player) {
        if (gameState.getPointState(point) != PointState.EMPTY) {
            gameState.moveError = gameState.MoveError("OCCUPIED");
            return false;
        }
        boolean isValid = true;
        ArrayList<ArrayList<PointState>> backupBoard = gameState.getBoardCopy();

        gameState.setPointState(point, player.pointState);

        ArrayList<Point> captures = getCapturedPoints(point);

        if (captures.size() > 0) {
            for (Point p :
                    captures) {
                gameState.setPointState(p, PointState.EMPTY);

            }
        } else if (getLibertyPoints(point, null, null).size() == 0) {
            gameState.moveError = gameState.MoveError("SUICIDE");
            isValid = false;
        }
        gameState.setBoardCopy(backupBoard);
        return isValid;
    }

    MoveResult move(int x, int y) {
        Point point = new Point(x, y);
        var player = gameState.current;
        ArrayList<Point> capturedPoints;

        gameState.moveError = "";

        if (!isValidMove(point, player)) {
            return null;
        }
        gameState.setPointState(point, player.pointState);

        capturedPoints = getCapturedPoints(point);

        for (Point p :
                capturedPoints) {
            gameState.setPointState(p, PointState.EMPTY);

            gameState.current = (player == gameState.player1) ? gameState.player2 : gameState.player1;

        }
        return new MoveResult(player, point, capturedPoints);

    }

    ArrayList<ArrayList<PointState>> getMarkedBoard() {
        ArrayList<ArrayList<PointState>> markedBoard = gameState.getBoardCopy();

        for (int x = 0; x < gameState.boardWidth; x++) {
            for (int y = 0; y < gameState.boardHeight; y++) {
                if (markedBoard.get(x).get(y) == PointState.EMPTY) {
                    Territory territory = getTerritory(new Point(x, y), null);
                    for (Point p : territory.points) {
                        markedBoard.get(p.getX()).set(p.getY(), PointState.valueOf((territory.owner).toString()));

                    }
                }
            }
        }


        return markedBoard;
    }


    void setScores() {
        ArrayList<ArrayList<PointState>> markedBoard = getMarkedBoard();
        Player p1 = gameState.player1;
        Player p2 = gameState.player2;

        p1.setScore(); /// TODO: 17.12.2019 setScore(0);
        p2.setScore(); /// Todo setScore(0)

        for (int x = 0; x < gameState.boardWidth; x++) {
            for (int y = 0; y < gameState.boardHeight; y++) {
                PointState pState = markedBoard.get(x).get(y);

                if (pState == p1.pointState) {
                    p1.setScore(); /// todo p1.score ++
                } else if (pState == p2.pointState) {
                    p2.setScore(); /// todo p2.score ++
                }
            }
        }
    }

    void newGame(int width, int height) {
        gameState = GameState(width, height, new Player(), new Player(), null);
    }


}
