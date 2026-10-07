package collision;

import spriteData.behavior.boxes.*;

import java.util.Map;
import java.util.Stack;

public class ColChecker {

    /**
     *
     * @param checkFor Box to check for.
     * @param checkAgainst All boxes needing to check against.
     * @return A Stack of all Sprite/ColBox IDs who collide with given checkForID.
     * @see Movable
     */
    public static Stack<Integer> isColliding(CollisionBox checkFor, Map<Integer, CollisionBox> checkAgainst) {
        Stack<Integer> collidingWith = new Stack<>();
        checkAgainst.forEach((id, box) -> {
            if (checkFor.getID() != id) {
                if (checkFor.getBounds().intersects(box.getBounds())) {
                    collidingWith.push(id);
                }
            }
        });
        return collidingWith;
    }

    /**
     * @param checkFor Box to check for.
     * @param checkAgainst All sprites needing to check against.
     * @param avoidID An ID different from the given checkFor's ID that should not be checked.
     * @return A Stack of all Sprite/ColBox IDs who collide with given checkForID (not including avoidID).
     * @see Movable
     */
    public static Stack<Integer> isColliding(
            CollisionBox checkFor,
            Map<Integer, CollisionBox> checkAgainst,
            int avoidID
    ) {
        Stack<Integer> collidingWith = new Stack<>();
        checkAgainst.forEach((id, box) -> {
            if (checkFor.getID() != id && avoidID != id) {
                if (checkFor.getBounds().intersects(box.getBounds())) {
                    collidingWith.push(id);
                }
            }
        });
        return collidingWith;
    }
}
