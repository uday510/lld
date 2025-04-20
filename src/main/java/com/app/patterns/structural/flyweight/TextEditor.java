package com.app.patterns.structural.flyweight;

// Flyweight is a structural design pattern that lets you share objects to support a large number of fine-grained objects efficiently.

import java.util.HashMap;
import java.util.Map;

interface Glyph {
    void draw(String font, int size, int x, int y);
}
class CharacterGlyph implements Glyph {
    private char symbol;

    public CharacterGlyph(char symbol) {
        this.symbol = symbol;
    }

    public void draw(String font, int size, int x, int y) {
        System.out.println("Drawing '" + symbol + "' in font " + font + ", size " + size + " at (" + x + "," + y + ")");
    }
}

class GlyphFactory {
    private Map<Character, Glyph> pool = new HashMap<>();

    public Glyph getGlyph(char symbol) {
        if (!pool.containsKey(symbol)) {
            pool.put(symbol, new CharacterGlyph(symbol));
        }
        return pool.get(symbol);
    }
}

public class TextEditor {

    public static void main(String[] args) {
        GlyphFactory factory = new GlyphFactory();

        String text = "Hello World!";
        int x = 0;

        for (char ch : text.toCharArray()) {
            Glyph glyph = factory.getGlyph(ch);
            glyph.draw("Arial", 12, x, 0);
            x += 10;
        }
    }
}
