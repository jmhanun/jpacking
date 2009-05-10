/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.helpers;

import java.awt.Component;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author jmhanun
 */
public class ModalAdapter extends InternalFrameAdapter {

        Component glass;

        public ModalAdapter(Component glass) {
            this.glass = glass;

            // Associate dummy mouse listeners
            // Otherwise mouse events pass through
            MouseInputAdapter adapter = new MouseInputAdapter() {
            };
            glass.addMouseListener(adapter);
            glass.addMouseMotionListener(adapter);
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
            glass.setVisible(false);
        }
    }
