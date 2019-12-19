package rules;

import main.Player;
import rules.board.GameState;
import rules.board.GameStatus;
import rules.point.Direction;
import rules.point.Point;
import rules.point.PointState;
import rules.territory.OwnerTerritory;
import rules.territory.Territory;

import java.util.ArrayList;

public class Logic {

    GameState gameState;
    boolean PassBefore = false;


    Boolean isPointInBounds(Point point) {

        return (
                point.getX() >= 0 && point.getX() < gameState.boardWidth &&
                        point.getY() >= 0 && point.getY() < gameState.boardHeight
        );
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
            if (isPointInBounds(nPoint)) {
                PointState nState = gameState.getPointState(nPoint);

                if (nState == PointState.EMPTY) {
                    if (!nPoint.isInArray(territory.points)) {
                        getTerritory(nPoint, territory);
                    }
                } else if (territory.owner != OwnerTerritory.NEUTRAL) {

                    if (territory.owner == OwnerTerritory.EMPTY) {
                        territory.owner = OwnerTerritory.valueOf(nState.toString());
                    } else if (territory.owner != OwnerTerritory.valueOf(nState.toString())) {
                        territory.owner = OwnerTerritory.NEUTRAL;

                    }
                }
            }
        }

        if (isRoot && territory.owner == OwnerTerritory.EMPTY) {
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

            if (isPointInBounds(nPoint)) {
                PointState nState = gameState.getPointState(nPoint);

                if (pState == nState) {
                    if (!nPoint.isInArray(chainPoints)) {
                        getChainPoints(nPoint, chainPoints);
                    }
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
            if (isPointInBounds(nPoint)) {
                PointState pState = gameState.getPointState(point);
                PointState nState = gameState.getPointState(nPoint);

                if (nState != pState && nState != PointState.EMPTY) {
                    if (!nPoint.isInArray(capPoints) && getLibertyPoints(nPoint, null, null).size() == 0) {
                        capPoints.addAll(getChainPoints(nPoint, null));
                    }

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
            if (isPointInBounds(nPoint)) {
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
        }


        return libPoints;
    }


    boolean isValidMove(Point point) {
        PassBefore = false;
        if (gameState.getPointState(point) != PointState.EMPTY) {
            gameState.moveError = gameState.MoveError("OCCUPIED");

            return false;
        }
        boolean isValid = true;
        ArrayList<ArrayList<PointState>> backupBoard = gameState.getBoardCopy();

        gameState.setPointState(point, gameState.getPlayerColor());

        ArrayList<Point> captures = getCapturedPoints(point);

        if (captures.size() > 0) {
            for (Point p :
                    captures) {
                gameState.setPointState(p, PointState.EMPTY);

            }

            if (!gameState.isUniqueBoard()) {
                gameState.moveError = gameState.MoveError("REPEAT");
                gameState.setPointState(point, PointState.EMPTY);

                isValid = false;
            }


        } else if (getLibertyPoints(point, null, null).size() == 0) {
            gameState.moveError = gameState.MoveError("SUICIDE");
            gameState.setPointState(point, PointState.EMPTY);

            isValid = false;
        }
        gameState.setBoardCopy(backupBoard);
        return isValid;
    }

    MoveResult move(int x, int y, Player player) {

        if (gameState.getStatus() == GameStatus.ACTIVE) {
            Point point = new Point(x, y);

            if (player == gameState.current) {
                ArrayList<Point> capturedPoints;

                gameState.moveError = "";

                if (!isValidMove(point)) {
                    player.sendChatMessage(gameState.moveError);

                    return null;
                } else {

                    gameState.setPreviousBoard(gameState.getBoardCopy());

                    gameState.setPointState(point, gameState.getPlayerColor());

                    capturedPoints = getCapturedPoints(point);

                    for (Point p :
                            capturedPoints) {
                        gameState.setPointState(p, PointState.EMPTY);
                    }
                    swapAndNotifyPlayers(player);


                    return new MoveResult(player, point, capturedPoints);
                }
            } else {
                return null;
            }
        }
        return null;
    }

    void pressPass(Player player) {

        if (gameState.getStatus() == GameStatus.ACTIVE) {
            if (PassBefore) {
                setScores(player);
            } else {

                PassBefore = !PassBefore;

                swapAndNotifyPlayers(player);
            }
        }
    }

    ArrayList<ArrayList<PointState>> getMarkedBoard() {
        ArrayList<ArrayList<PointState>> markedBoard = gameState.getBoardCopy();

        for (int x = 0; x < gameState.boardWidth; x++) {
            for (int y = 0; y < gameState.boardHeight; y++) {
                if (markedBoard.get(x).get(y) == PointState.EMPTY) {
                    Territory territory = getTerritory(new Point(x, y), null);
                    for (Point p : territory.points) {
                        OwnerTerritory territory1 = territory.owner;
                        if (territory1 == OwnerTerritory.NEUTRAL) {
                            territory1 = OwnerTerritory.EMPTY;
                        }
                        markedBoard.get(p.getX()).set(p.getY(), PointState.valueOf((territory1).toString()));

                    }
                }
            }
        }


        return markedBoard;
    }

    void swapAndNotifyPlayers(Player player) {
        gameState.current = (player == gameState.playerBlack) ? gameState.playerWhite : gameState.playerBlack;
        player.opponentTurn();
        gameState.current.yourTurn();

    }

    void setScores(Player player) {
        ArrayList<ArrayList<PointState>> markedBoard = getMarkedBoard();
        int p1 = 0;
        int p2 = 0;


        for (int x = 0; x < gameState.boardWidth; x++) {
            for (int y = 0; y < gameState.boardHeight; y++) {
                PointState pState = markedBoard.get(x).get(y);

                if (pState == PointState.BLACK) {
                    p1++;
                } else if (pState == PointState.WHITE) {
                    p2++;
                }

                player.setScore(p1, p2);

                if (p1 > p2) {
                    gameState.playerBlack.notifWin();
                    gameState.playerWhite.notifLoss();

                } else if ( p2< p1) {
                    gameState.playerBlack.notifLoss();
                    gameState.playerWhite.notifWin();
                }
                else if (p1 == p2) {
                    //TODO tu remis
                    //gameState.playerBlack.notifREMIS();
                   // gameState.playerWhite.notifREMIS();
                }
            }
        }

        gameState.setStatus(GameStatus.ENDED);
    }

    void newGame(int width, int height, Player player1, Player player2) {
        gameState = new GameState(width, height, player1, player2, null);
        swapAndNotifyPlayers(player1);
    }


}
