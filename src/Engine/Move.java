package Engine;

public class Move {
    void moveCharacter(Character character){
        if(character.getSpeed().get(0) >= 0){
            moveRight(character);
        }else moveLeft(character);

        if(character.getSpeed().get(1) >= 0){
            moveDown(character);
        }else moveUp(character);
    }
    void moveUp(Character character){}

    void moveDown(Character character){}

    void moveLeft(Character character){}

    void moveRight(Character character){}
}
