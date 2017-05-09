package ru.sereske;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import static ru.sereske.Quiz.Direction.*;

/**
 * Created by Sergey on 07.05.2017.
 */
public class Quiz {
    public static void main(String[] args) {
        //System.out.println(factorial(0));
        /*
        int [] result = mergeArrays(new int[] {0, 2, 2}, new int[] {1, 3});
        for (int i : result) {
            System.out.println(i);
        }
        */
        Robot robot = new Robot(0, 0, UP);
        moveRobot(robot, 3, 3);
    }

    private static BigInteger factorial(int value) {
        BigInteger result = new BigInteger("1");
        for (int i = 2; i < value + 1; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        return result;
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int [] result = new int[a1.length + a2.length];
        int length = Math.min(a1.length, a2.length);
        boolean hasEnd1 = false;
        boolean hasEnd2 = false;
        for (int i = 0, k1 = 0, k2 = 0; i < a1.length + a2.length; i++) {
            if (a1[k1] < a2[k2] && !hasEnd1) {
                if (k1 == a1.length - 1) {
                    hasEnd1 = true;
                }
                result[i] = a1[k1];
                if (k1 + 1 < a1.length) {
                    k1++;
                }
            } else if (a1[k1] >= a2[k2] && !hasEnd2) {
                if (a2[k2] == a2.length - 1) {
                    hasEnd2 = true;
                }
                result[i] = a2[k2];
                if (k2 + 1 < a2.length) {
                    k2++;
                }
            }
        }
        return result; // your implementation here
    }

    private String printTextPerRole(String[] roles, String[] textLines) {

        return "";
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        int deltaX = toX - robot.getX();
        int deltaY = toY - robot.getY();
        Direction initialDirection = robot.getDirection();
        Direction dirX;
        if (deltaX >= 0) {
            switch (initialDirection) {
                case UP:
                    robot.turnRight();
                    break;
                case RIGHT:
                    break;
                case DOWN:
                    robot.turnLeft();
                    break;
                case LEFT:
                    robot.turnRight();
                    robot.turnRight();
                    break;
            }
        } else {
            switch (initialDirection) {
                case UP:
                    robot.turnLeft();
                    break;
                case RIGHT:
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                case DOWN:
                    robot.turnRight();
                    break;
                case LEFT:
                    break;
            }
        }
        for (int i = 0; i < Math.abs(deltaX); i++) {
            System.out.println("x: " + robot.x + ", y:" + robot.y);
            robot.stepForward();
        }
        initialDirection = robot.getDirection();
        Direction dirY;
        if (deltaY >= 0) {
            switch (initialDirection) {
                case UP:
                    break;
                case RIGHT:
                    robot.turnLeft();
                    break;
                case DOWN:
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                case LEFT:
                    robot.turnRight();
                    break;
            }
        } else {
            switch (initialDirection) {
                case UP:
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                case RIGHT:
                    robot.turnRight();
                    break;
                case DOWN:
                    break;
                case LEFT:
                    robot.turnLeft();
                    break;
            }
        }
        for (int i = 0; i < Math.abs(deltaY); i++) {
            robot.stepForward();
            System.out.println("x: " + robot.x + ", y:" + robot.y);
        }
    }

    public static class Robot {

        private Direction direction;
        private int x;
        private int y;

        public Robot(int x, int y, Direction direction) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }

        public Direction getDirection() {
            // текущее направление взгляда
            return direction;
        }

        public int getX() {
            // текущая координата X
            return x;
        }

        public int getY() {
            // текущая координата Y
            return y;
        }

        public void turnLeft() {
            // повернуться на 90 градусов против часовой стрелки
            switch (direction) {
                case UP:
                    direction = LEFT;
                    break;
                case DOWN:
                    direction = RIGHT;
                    break;
                case LEFT:
                    direction = DOWN;
                    break;
                case RIGHT:
                    direction = UP;
                    break;
            }
        }

        public void turnRight() {
            // повернуться на 90 градусов по часовой стрелке
            switch (direction) {
                case UP:
                    direction = RIGHT;
                    break;
                case DOWN:
                    direction = LEFT;
                    break;
                case LEFT:
                    direction = UP;
                    break;
                case RIGHT:
                    direction = DOWN;
                    break;
            }
        }

        public void stepForward() {
            // шаг в направлении взгляда
            // за один шаг робот изменяет одну свою координату на единицу
            switch (direction) {
                case UP:
                    y++;
                    break;
                case DOWN:
                    y--;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
