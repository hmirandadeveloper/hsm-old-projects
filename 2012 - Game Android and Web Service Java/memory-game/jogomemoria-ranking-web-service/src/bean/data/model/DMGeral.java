package bean.data.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import basicas.Ranking;

public class DMGeral<T> extends ListDataModel<T> 
implements SelectableDataModel<T>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DMGeral()
	{
		
	}
	
	public DMGeral(List<T> data)
	{
		super(data);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getRowData(String rowKey) {
		List<Ranking> lista = (List<Ranking>) getWrappedData();
		
		for(Ranking obj : lista)
		{
			if(obj.getId() == Long.valueOf(rowKey))
			{
				return (T)obj;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(T obj) {
		return ((Ranking)obj).getId();
	}

}
