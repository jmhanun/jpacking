/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.jpack.helpers;

import ar.com.jpack.desktop.DesktopApp;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author jmhanun
 */
public class CustomInternalFrame<E> extends JInternalFrame implements Serializable {

    private E dto;
    private ArrayList<E> listDto;
    private CustomInternalFrame padre;
    private boolean modificado;
    private boolean nuevo;
    private ResourceMap resourceMap;
    private E oldDto;

    public CustomInternalFrame() {
        super();
        resourceMap = Application.getInstance(DesktopApp.class).getContext().getResourceMap(CustomInternalFrame.class);
        this.setFrameIcon(resourceMap.getImageIcon("icono"));
    }

    public CustomInternalFrame(E dto) {
        super();
        this.dto = dto;
        resourceMap = Application.getInstance(DesktopApp.class).getContext().getResourceMap(CustomInternalFrame.class);
        this.setFrameIcon(resourceMap.getImageIcon("icono"));
    }

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        setOldDto(getDto());
        this.dto = dto;
        firePropertyChange("dto", getOldDto(), getDto());
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

    public boolean isModificado() {
        return modificado;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setModificado(boolean modificado) {
        boolean old = isModificado();
        this.modificado = modificado;
        firePropertyChange("modificado", old, isModificado());
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public E getOldDto() {
        return oldDto;
    }

    public void setOldDto(E oldDto) {
        this.oldDto = oldDto;
    }

    public ResourceMap getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(ResourceMap resourceMap) {
        this.resourceMap = resourceMap;
    }
}
