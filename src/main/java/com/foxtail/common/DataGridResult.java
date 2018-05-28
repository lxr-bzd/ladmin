package com.foxtail.common;

import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;

public class DataGridResult {

	public static DataGrid getResult(Pagination pagination) {
		
		int pageNo= pagination.getPageNo();
		int pageSize= pagination.getPageSize();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(pagination.getTotalCount());
		dataGrid.setRows(pagination.getList());
		
		return dataGrid;

	}
	
}
