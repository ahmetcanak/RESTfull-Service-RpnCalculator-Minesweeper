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

    /*public String makeOperation() {

    }*/

    public boolean isOperator(String op) {
        return (op.equals("+") ||
                op.equals("*") ||
                op.equals("/") ||
                op.equals("-")
        );
    }

    public MinesweeperResponse minesweeperResult(MinesweeperRequest square) {

        int mapX = square.getSquare()[0].split("").length;
        int mapY = square.getSquare().length;
        String[][] matrixMap = new String[mapY][mapX];
        String[][] hintMap = new String[mapY][mapX];

        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                matrixMap[y][x] = String.valueOf(square.getSquare()[y].charAt(x));
            }
        }

        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                if (!(matrixMap[y][x].equals("*"))) {
                    hintMap[y][x] = String.valueOf(checkAdjacent(matrixMap, y, x, mapX, mapY));
                } else {
                    hintMap[y][x] = "*";
                }
            }
        }

        String[] hintResult = new String[mapY];
        String getRow = "";

        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                getRow += hintMap[y][x];
            }
            hintResult[y] = getRow;
            getRow = "";
        }

        MinesweeperResponse hintsResponse = new MinesweeperResponse();
        hintsResponse.setHints(hintResult);
        return hintsResponse;
    }

    public boolean outOfBounds(int x, int y, int mapX, int mapY) {
        if (x < 0 || x >= mapX || y < 0 || y >= mapY) {
            return true;
        } else
            return false;
    }

    public int checkAdjacent(String[][] map, int posY, int posX, int mapX, int mapY) {
        int counter = 0;
        for (int y = posY - 1; y <= posY + 1; y++) {
            for (int x = posX - 1; x <= posX + 1; x++) {
                if (!outOfBounds(x, y, mapX, mapY) && !(x == posX && y == posY) && map[y][x].equals("*")) {
                    counter++;
                }
            }
        }
        return counter;
    }
}