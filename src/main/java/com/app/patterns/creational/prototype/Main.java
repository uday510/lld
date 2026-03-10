package com.app.patterns.creational.prototype;

public class Main {

    GameCharacter hero =
            new GameCharacter("Hero", 1, 100);

    GameCharacter copy1 = hero.clone();
    GameCharacter copy2 = hero.clone();


}
