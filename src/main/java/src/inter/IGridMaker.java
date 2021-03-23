/**
 * 
 */
package src.inter;

import java.util.List;

import src.transferobject.OfertaViewTO;

/**
 * @author fabo_
 *
 */
public interface IGridMaker {
	
	public void setCols(Integer cols);
	public Integer getCols();
	public List<OfertaViewTO> getList();
	public void setList(List<OfertaViewTO> list);

}
