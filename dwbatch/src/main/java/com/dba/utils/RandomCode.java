package com.dba.utils;

/**
 * Author:huangjb
 * Date:2018/11/6
 * Description:
 */
public class RandomCode {
    public static int getRandNum(int min, int max) { int randNum = min + (int)(Math.random() * ((max - min) + 1)); return randNum; }
}
