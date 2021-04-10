package com.willjsporter.service;

import com.willjsporter.model.Pair;

public enum Direction {
    N {
        @Override
        public Pair moveInDirection(Pair currentPosition, Pair roomSize) {
            Pair potentialNextMove = Pair.of(currentPosition.getX(), currentPosition.getY() + 1);
            return getNextPosition(currentPosition, potentialNextMove, roomSize);
        }
    },
    E {
        @Override
        public Pair moveInDirection(Pair currentPosition, Pair roomSize) {
            Pair potentialNextMove = Pair.of(currentPosition.getX() + 1, currentPosition.getY());
            return getNextPosition(currentPosition, potentialNextMove, roomSize);
        }
    },
    S {
        @Override
        public Pair moveInDirection(Pair currentPosition, Pair roomSize) {
            Pair potentialNextMove = Pair.of(currentPosition.getX(), currentPosition.getY() - 1);
            return getNextPosition(currentPosition, potentialNextMove, roomSize);
        }
    },
    W {
        @Override
        public Pair moveInDirection(Pair currentPosition, Pair roomSize) {
            Pair potentialNextMove = Pair.of(currentPosition.getX() - 1, currentPosition.getY());
            return getNextPosition(currentPosition, potentialNextMove, roomSize);
        }
    };

    public abstract Pair moveInDirection(Pair currentPosition, Pair roomSize);

    private static Pair getNextPosition(Pair currentPosition, Pair potentialNextMove, Pair roomSize) {
        final boolean hasValidXCoords = potentialNextMove.getX() < roomSize.getX() && potentialNextMove.getX() >= 0;
        final boolean hasValidYCoords = potentialNextMove.getY() < roomSize.getY() && potentialNextMove.getY() >= 0;

        return hasValidXCoords && hasValidYCoords
            ? potentialNextMove
            : currentPosition;
    }
}
