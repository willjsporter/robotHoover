package com.willjsporter.service;

import com.willjsporter.model.Pair;

public enum Direction {
    N {
        @Override
        public Pair move(Pair currentPosition) {
            return Pair.of(currentPosition.getX(), currentPosition.getY() + 1);
        }
    },
    E {
        @Override
        public Pair move(Pair currentPosition) {
            return Pair.of(currentPosition.getX() + 1, currentPosition.getY());
        }
    },
    S {
        @Override
        public Pair move(Pair currentPosition) {
            return Pair.of(currentPosition.getX(), currentPosition.getY() - 1);
        }
    },
    W {
        @Override
        public Pair move(Pair currentPosition) {
            return Pair.of(currentPosition.getX() - 1, currentPosition.getY());
        }
    };

    public abstract Pair move(Pair currentPosition);
}
