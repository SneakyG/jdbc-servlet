package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface IPageble {
	int getPage();
	int getOffSet();
	int getLimit();
	Sorter getSorter();
}
