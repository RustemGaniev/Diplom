package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;

public class MyConvert implements TextColorSchema {

    char pattern[] = {'\u25A0', '\u25CF', '\u25C9', '\u25CD', '\u25CE', '\u25EF', '\u25EF', '\u25EF', '\u25EF'};

    @Override
    public char convert(int color) {

        int i = color / (255 / (pattern.length + 1));
        return pattern[i];
    }
}
