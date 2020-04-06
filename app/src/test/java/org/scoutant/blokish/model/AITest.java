package org.scoutant.blokish.model;

import android.util.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AITest {

    private static final String tag = "ai";
    private int color = 0;
    private Piece L4 = new Piece(color, 3, "L4", 4, 2).add(0, -1).add(0, 0).add(0, 1).add(1, 1);
    private Piece P5 = new Piece(color, 3, "P5", 4, 2).add(0, -1).add(0, 0).add(0, 1).add(1, -1).add(1, 0);
    private Piece I3 = new Piece(color, 3, "I3", 2, 1).add(0, -1).add(0, 0).add(0, 1);
    private AI ai;
    private Game game;
    private Board board;
    private List<Piece> pieces;
    private boolean valid = false;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before
    public void setUp() {
        game = new Game();
        board = game.boards.get(color);
        ai = new AI(game);
        pieces = board.pieces;
        pieces.clear();

//        L4 = board.findPieceByType("L4");
//        P5 = board.findPieceByType("P5");
//        I3 = board.findPieceByType("I3");
    }

    @Test
    public void testThinkEmptyBoard() {
        pieces.add(L4);
        assertNotNull(ai.think(0, 0));
    }

    @Test
    public void testThinkWithL4() {
        pieces.add(L4);
        List<Move> moves = ai.thinkUpToNMoves(0, 0);
        assertEquals(6, moves.size());
    }

    @Test
    public void testThinkWithP5() {
        pieces.add(P5);
        List<Move> moves = ai.thinkUpToNMoves(0, 3);
        assertEquals(6, moves.size());
    }

    @Test
    public void testThinkWithP5AndL4() {
        Move move = new Move(P5, 0, 1);
        game.play(move);
        assertEquals(2, board.seeds().size());
        pieces.add(L4);
        List<Move> moves = ai.thinkUpToNMoves(0, 3);
        assertEquals(17, moves.size());
        game.play(new Move(L4, 1, 4));
        Log.d(tag, "" + board);
        assertEquals(4, board.seeds().size());
    }

    @Test
    public void testThinkWithP5L4I3() {
        game.play(new Move(P5, 0, 1));
        game.play(new Move(L4, 1, 4));
        pieces.add(I3);
        List<Move> moves = ai.thinkUpToNMoves(0, 3);
        assertEquals(4, board.seeds().size());
        Log.d(tag, "#moves : " + moves.size());
        assertEquals(6, moves.size());
        I3.rotate(1);
        valid = game.play(new Move(I3, 4, 6));
        assertTrue(valid);
        Log.d(tag, "" + board);
        assertEquals(6, board.seeds().size());
    }

    @Test
    public void testThinkWithP5L4I3P5() {
        game.play(new Move(P5, 0, 1));
        game.play(new Move(L4, 1, 4));
        pieces.add(I3);
        I3.rotate(1);
        valid = game.play(new Move(I3, 4, 6));
        assertTrue(valid);
        assertEquals(6, board.seeds().size());
        pieces.add(P5);
        List<Move> moves = ai.thinkUpToNMoves(0, 3);
        System.out.println("move : " + moves.size());
        assertEquals(37, moves.size());
    }

}
