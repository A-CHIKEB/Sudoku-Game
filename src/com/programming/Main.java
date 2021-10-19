package com.programming;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // 9x9
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {7,0,2,0,5,0,6,0,0},
                {0,0,0,0,0,3,0,0,0},
                {1,0,0,0,0,9,5,0,0},
                {8,0,0,0,0,0,0,9,0},
                {0,4,3,0,0,0,7,5,0},
                {0,9,0,0,0,0,0,0,8},
                {0,0,9,7,0,0,0,0,5},
                {0,0,0,2,0,0,0,0,0},
                {0,0,7,0,4,0,2,0,3}
        };

        if(solveBoard(board)){
            System.out.println("Sudoku Solved Successfully");
        }else{
            System.out.println("Unsolved Board :(");
        }
        printBoard(board);


    }


    /**
     * If Number Enter in parameter(2) Exist In Row
     * @param board
     * @param number
     * @param row
     * @return boolean
     */
    public static boolean isNumberExistInRow(int[][] board,int number,int row){
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i]==number) return true;
        }
        return false;
    }


    /**
     * If Number Enter in parameter(2) Exist In Column
     * @param board
     * @param number
     * @param column
     * @return boolean
     */
    public static boolean isNumberExistInColumn(int[][] board,int number,int column){
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[i][column]==number) return true;
        }
        return false;
    }


    /**
     * If Number Enter in parameter(2) Exist In The Box
     * Box = 3x3
     * @param board
     * @param number
     * @param row
     * @param column
     * @return boolean
     */
    public static boolean isNumberExistInBox(int[][] board,int number,int row,int column){
        /*
         * 1-4-7
         * 2-5-8
         * 3-6-9
         */
        List<Integer> firstBox = new ArrayList<>(List.of(0,3,6));
        List<Integer> secondBox = new ArrayList<>(List.of(1,4,7));
        List<Integer> thirdBox = new ArrayList<>(List.of(2,5,8));

        int beginRow = firstBox.contains(row) ? row : secondBox.contains(row) ? row - 1 : row - 2;
        int beginColumn = firstBox.contains(column) ? column : secondBox.contains(column) ? column - 1 : column - 2;


        /*
         * int beginRow = row - row % 3;
         * int beginColumn = column - column % 3;
         */

        for (int i = beginRow; i < beginRow+3; i++) {
            for (int j = beginColumn; j < beginColumn+3; j++) {
                if(board[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if Number is in the valid place (isNumberExistInRow, isNumberExistInColumn,isNumberExistInBox)
     * @param board
     * @param number
     * @param row
     * @param column
     * @return boolean;
     */

    public static boolean isValidPlacement(int[][] board,int number,int row,int column){
        return  !isNumberExistInRow(board,number,row) &&
                !isNumberExistInColumn(board,number,column) &&
                !isNumberExistInBox(board,number,row,column);
    }

    /**
     * Solve Board Problem
     * @param board
     * @return boolean
     */
    public static boolean solveBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if(board[row][column]==0){

                    //Try Number 1->9

                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if(isValidPlacement(board,number,row,column)){
                            board[row][column] = number;

                            /**
                             * if we can solved the rest of the board with this number return is valid number
                             * else make it 0
                             */

                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Print Sudoku Board
     * @return like sudoku table in console
     */
    public static void printBoard(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.print("|");
            for (int column = 0; column < GRID_SIZE; column++) {
                System.out.print(board[row][column]);
                System.out.print((column+1)%3==0 ? "|" : "");
            }
            System.out.println();
            if((row+1)%3==0){
                System.out.println("-------------");
            }
        }
    }

}
