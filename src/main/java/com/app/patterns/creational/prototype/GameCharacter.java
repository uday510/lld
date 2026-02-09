package com.app.patterns.creational.prototype;

public class GameCharacter implements Prototype<GameCharacter> {

    private String name;
    private int level;
    private int power;

    public GameCharacter(String name, int level, int power) {
        this.name = name;
        this.level = level;
        this.power = power;

        loadResources();
    }

    private void loadResources() {
        System.out.println("Loading resources...");
    }

    @Override
    public GameCharacter clone() {

        return new GameCharacter(
                this.name,
                this.level,
                this.power
        );
    }
}
