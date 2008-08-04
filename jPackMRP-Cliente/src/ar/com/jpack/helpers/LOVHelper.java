/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import java.util.Iterator;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;

/**
 *
 * @author jmhanun
 */
public class LOVHelper {

    public static Binding findBinding(BindingGroup bindingGroup, Object source, Object target) {
        for (Iterator<Binding> i = bindingGroup.getBindings().iterator(); i.hasNext();) {
            Binding b = i.next();
            boolean found =
                    (source == null || b.getSourceObject() == source) &&
                    (target == null || b.getTargetObject() == target);
            if (found) {
                return b;
            }

        }
        return null;
    }
}
