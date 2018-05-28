package com.foxtail.common;

import com.foxtail.common.page.Pagination;
import com.foxtail.common.web.DataGrid;
<<<<<<< HEAD
=======
import com.ladmin.util.PageUtils;
>>>>>>> branch 'html版本' of https://github.com/lxr-bzd/ladmin.git

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
