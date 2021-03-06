package org.openstreetmap.atlas.checks.atlas.predicates;

import static org.openstreetmap.atlas.checks.constants.CommonConstants.CLOSED_PARENTHESES_CHAR;
import static org.openstreetmap.atlas.checks.constants.CommonConstants.OPEN_PARENTHESES_CHAR;

import java.util.Stack;
import java.util.function.Predicate;

/**
 * Collection of value based predicates
 *
 * @author brian_l_davis
 */
public interface ValuePredicates
{
    /**
     * Tests if the {@link String} contains '(' and ')' that are balanced and closed. Balanced
     * meaning there are the same number of open parentheses as closed. Closed meaning that every
     * open parentheses is followed by a closed parentheses.
     */
    Predicate<String> ARE_PARENTHESES_BALANCED_AND_CLOSED = value ->
    {
        final Stack<Character> characterStack = new Stack<>();

        for (final char character : value.toCharArray())
        {
            if (character == OPEN_PARENTHESES_CHAR)
            {
                if (characterStack.isEmpty() || characterStack.peek() != CLOSED_PARENTHESES_CHAR)
                {
                    characterStack.push(OPEN_PARENTHESES_CHAR);
                }
                else
                {
                    characterStack.pop();
                }
            }
            if (character == CLOSED_PARENTHESES_CHAR)
            {
                if (characterStack.isEmpty())
                {
                    return false;
                }
                if (characterStack.peek() != OPEN_PARENTHESES_CHAR)
                {
                    characterStack.push(CLOSED_PARENTHESES_CHAR);
                }
                else
                {
                    characterStack.pop();
                }
            }
        }
        return characterStack.isEmpty();
    };
}
