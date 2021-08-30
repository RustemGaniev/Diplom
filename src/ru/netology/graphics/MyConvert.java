package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;

public class MyConvert implements TextColorSchema {

    char a;

    @Override
    public char convert(int color) {

        if (color < 25) {
            a = '\u25A0';
        }
        if (color < 50 && color > 24) {
            a = '\u25CF';
        }
        if (color < 75 && color > 49) {
            a = '\u25C9';
        }
        if (color < 100 && color > 74) {
            a = '\u25CD';
        }
        if (color < 125 && color > 99) {
            a = '\u25CE';
        }
        if (color < 150 && color > 124) {
            a = '\u25EF';
        }
        if (color < 175 && color > 149) {
            a = '\u25CB';
        }
        if (color < 200 && color > 174) {
            a = '\u25CC';
        }
        if (color <= 255 && color > 199) {
            a = '\u25E6';
        }
        return a;
    }
}
