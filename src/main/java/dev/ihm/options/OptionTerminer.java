package dev.ihm.options;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import dev.exception.PlatException;

/** Interface des options du menu
 *
 * @author KOMINIARZ Anaïs
 *
 */
@Order(3)
@Controller
public class OptionTerminer implements IOptionMenu {
    @Override
    public String getTitre() {
        return "Terminer";
    }

    @Override
    public void executer() {
        throw new PlatException("Aurevoir");
    }
}
