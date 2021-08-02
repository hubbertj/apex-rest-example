package com.apex.apex.dto;

import java.util.List;

public class CustomerResponse {

	private List<Customer> pageItems;

	private Integer pageNumber;
	private Integer itemsPerPage;
	private Long totalElements;
	private Integer totalPages;
	private Long totalCounts;

	public List<Customer> getPageItems() {
		return pageItems;
	}

	public void setPageItems(List<Customer> pageItems) {
		this.pageItems = pageItems;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	
	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(Long totalCounts) {
		this.totalCounts = totalCounts;
	}

}
