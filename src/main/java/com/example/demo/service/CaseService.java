package com.example.demo.service;

import com.example.demo.model.MinesweeperRequest;
import com.example.demo.model.MinesweeperResponse;
import com.example.demo.model.RpnExpressionRequest;
import com.example.demo.model.RpnExpressionResponse;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class CaseService {

    public RpnExpressionResponse calculatorResult(RpnExpressionRequest expression) {
        RpnExpressionResponse result = new RpnExpressionResponse();
        result.setResult(getLast(expression));
        return result;
    }

    private Double getLast(RpnExpressionRequest expression) {
        Stack myStack = new Stack();

        String seperator[] = expression.getExpression().split(" ");
        for (int unit = 0; unit < seperator.length; unit++) {
            if (!isOperator(seperator[unit])) {
                myStack.push(seperator[unit]);
            } else {
                double operant1 = Double.parseDouble(String.valueOf(myStack.pop()));
                double operant2 = Double.parseDouble(String.valueOf(myStack.pop()));

                if (seperator[unit].equals("+"))
                    myStack.push(String.valueOf(operant2 + operant1));
                else if (seperator[unit].equals("-"))
                    myStack.push(String.valueOf(operant2 - operant1));
                else if (seperator[unit].equals("*"))
                    myStack.push(String.valueOf(operant2 * operant1));
                else if (seperator[unit].equals("/"))
                    myStack.push(String.valueOf(operant2 / operant1));
            }
        }
        return Double.parseDouble((String) myStack.pop());
    }

    private boolean isOperator(String op) {
        return (op.equals("+") ||
                op.equals("*") ||
                op.equals("/") ||
                op.equals("-")
        );
    }

    public MinesweeperResponse minesweeperResult(MinesweeperRequest square) {

        int mapX = square.getSquare()[0].split("").length;
        int mapY = square.getSquare().length;
        String[][] hintMap = new String[mapY][mapX];
        String[] hintResult = new String[mapY];
        String getRow = "";

        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                if (!(String.valueOf(square.getSquare()[y].charAt(x)).equals("*"))) {
                    hintMap[y][x] = String.valueOf(checkAdjacent(square, y, x, mapX, mapY));
                } else {
                    hintMap[y][x] = "*";
                }
                getRow += hintMap[y][x];
            }
            hintResult[y] = getRow;
            getRow = "";
        }

        MinesweeperResponse hintsResponse = new MinesweeperResponse();
        hintsResponse.setHints(hintResult);
        return hintsResponse;
    }

    private boolean outOfBounds(int x, int y, int mapX, int mapY) {
        return (x < 0 || x >= mapX || y < 0 || y >= mapY);
    }

    private int checkAdjacent(MinesweeperRequest square, int posY, int posX, int mapX, int mapY) {
        int counter = 0;
        for (int y = posY - 1; y <= posY + 1; y++) {
            for (int x = posX - 1; x <= posX + 1; x++) {
                if (
                        !outOfBounds(x, y, mapX, mapY)  //Is selected cell out of array?
                                && !(x == posX && y == posY)    //Is selected cell, actually the base cell?
                                && (String.valueOf(square.getSquare()[y].charAt(x)).equals("*"))) {     //Is selected cell bomb?
                    counter++;
                }
            }
        }
        return counter;
    }
}