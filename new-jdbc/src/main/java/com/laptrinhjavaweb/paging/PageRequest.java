package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements IPageble {

	private int page;
	private int maxPageItem;
	private Sorter sorter;

	public PageRequest(int page, int maxPageItem, Sorter sorter) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public int getPage() {
		return this.page;
	}

	@Override
	public int getOffSet() {
		if (this.page != 0 && this.maxPageItem != 0) {
			return (this.page - 1) * this.maxPageItem;
		}
		return 0;
	}

	@Override
	public int getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if(this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

}
