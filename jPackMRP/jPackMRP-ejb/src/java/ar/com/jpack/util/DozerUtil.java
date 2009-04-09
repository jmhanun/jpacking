/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.jpack.util;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

/**
 *
 * @author jmhanun
 */
public class DozerUtil {
    private static MapperIF mapper;
    static{
        mapper=new DozerBeanMapper();
    }
  public static MapperIF getDozerMapper(boolean loadLazyButNotLoaded){
      if(mapper==null){
          mapper=new DozerBeanMapper();
          
      }
      mapper.setLoadLazyButNotLoaded(loadLazyButNotLoaded);
      return mapper;
  }
}
