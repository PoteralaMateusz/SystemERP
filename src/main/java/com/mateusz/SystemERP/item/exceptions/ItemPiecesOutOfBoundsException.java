package com.mateusz.SystemERP.item.exceptions;

public class ItemPiecesOutOfBoundsException extends RuntimeException {

    public ItemPiecesOutOfBoundsException(String message) {
        super(message);
    }public ItemPiecesOutOfBoundsException(Long itemId, Integer pieces, Integer donePieces) {
        super("Item with id " + itemId + " done pieces out of bounds: pieces: " + pieces + ", done pieces:" + donePieces);
    }
}
