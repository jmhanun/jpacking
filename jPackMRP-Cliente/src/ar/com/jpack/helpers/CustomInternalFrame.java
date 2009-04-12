/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JInternalFrame;

/**
 *
 * @author jmhanun
 */
public class CustomInternalFrame<E> extends JInternalFrame implements Serializable {

    private E dto;
    private ArrayList<E> listDto;
    private CustomInternalFrame padre;

    public CustomInternalFrame() {
        super();
    }

    public CustomInternalFrame(E dto) {
        super();
        this.dto = dto;
    }

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }

    public ArrayList<E> getListDto() {
        return listDto;
    }

    public void setListDto(ArrayList<E> listDto) {
        this.listDto = listDto;
    }

    public CustomInternalFrame getPadre() {
        return padre;
    }

    public void setPadre(CustomInternalFrame padre) {
        this.padre = padre;
    }
}
