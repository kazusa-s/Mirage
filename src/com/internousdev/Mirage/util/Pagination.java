package com.internousdev.Mirage.util;

import java.util.ArrayList;
import java.util.List;

import com.internousdev.Mirage.dto.PaginationDTO;
import com.internousdev.Mirage.dto.ProductInfoDTO;

public class Pagination {

	public PaginationDTO initialize(List<ProductInfoDTO> list, int pageSize) {

		PaginationDTO paginationDTO = new PaginationDTO();

		// 全ページ数 (ceilで切り上げ処理ができなかったのでif文を使用)

		int totalPageSize = list.size() % pageSize;
        //ページ数が割れない場合は　totalPageSize +1
		if(!(totalPageSize == 0)){
			paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)+1));
		//割り切れる場合、totalPageSizeはそのまま
		}else{
			paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)));
		}

		// 現在のページ番号
		paginationDTO.setCurrentPageNo(1);

		// 全レコード数
		paginationDTO.setTotalRecordSize(list.size() - 1);

		// 現在のページ番号に対する開始レコード番号（オフセット）
		paginationDTO.setStartRecordNo(pageSize * (paginationDTO.getCurrentPageNo() -1));

		// 現在のページ番号に対する終了レコード番号
		paginationDTO.setEndRecordNo(paginationDTO.getStartRecordNo() + (pageSize - 1));

		//総ページをリストに格納
		List<Integer> pageNumberList = new ArrayList<Integer>();
		for(int pageNumber = 1;pageNumber <= paginationDTO.getTotalPageSize(); pageNumber++) {
			pageNumberList.add(pageNumber);
		}
		List<ProductInfoDTO> productInfoPages = new ArrayList<ProductInfoDTO>();


        //終了レコードが総レコード数を超えた場合
		if(paginationDTO.getEndRecordNo() > paginationDTO.getTotalRecordSize()){
			for(int pageNumberOffset=paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getTotalRecordSize(); pageNumberOffset++) {
				productInfoPages.add(list.get(pageNumberOffset));
			}
		}else{
			for(int pageNumberOffset=paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getEndRecordNo(); pageNumberOffset++) {
				productInfoPages.add(list.get(pageNumberOffset));
			}
		}
		paginationDTO.setCurrentProductInfoPage(productInfoPages);


		if((paginationDTO.getStartRecordNo() - 1) <= 0) {
			paginationDTO.setPreviousPage(false);
		}else {
			paginationDTO.setPreviousPage(true);
			paginationDTO.setPreviousPageNo(paginationDTO.getCurrentPageNo() - 1);
		}

		if(paginationDTO.getEndRecordNo() + pageSize > paginationDTO.getTotalRecordSize()) {
			paginationDTO.setNextPage(false);
		}else {
			paginationDTO.setNextPage(true);
			paginationDTO.setNextPageNo(paginationDTO.getCurrentPageNo() + 1);
		}
		return paginationDTO;
	}


	public PaginationDTO getPage(List<ProductInfoDTO> list, int pageSize, String pageNo) {

		PaginationDTO paginationDTO = new PaginationDTO();

		// 全ページ数 (ceilで切り上げ処理ができなかったのでif文を使用)

		int totalPageSize = list.size() % pageSize;
        //ページ数が割れない場合は　totalPageSize +1
		if(!(totalPageSize == 0)){
			paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)+1));
		//割り切れる場合、totalPageSizeはそのまま
		}else{
			paginationDTO.setTotalPageSize((int)(Math.ceil(list.size() / pageSize)));
		}

		// 現在のページ番号
		paginationDTO.setCurrentPageNo(Integer.parseInt(pageNo));

		// 全レコード数
		paginationDTO.setTotalRecordSize(list.size() - 1);

		// 現在のページ番号に対する開始レコード番号（オフセット）
		paginationDTO.setStartRecordNo(pageSize * (paginationDTO.getCurrentPageNo() -1));

		// 現在のページ番号に対する終了レコード番号
		paginationDTO.setEndRecordNo(paginationDTO.getStartRecordNo() + (pageSize - 1));

		List<Integer> pageNumberList = new ArrayList<Integer>();
		for(int pageNumber = 1;pageNumber <= paginationDTO.getTotalPageSize(); pageNumber++) {
			pageNumberList.add(pageNumber);
		}

		List<ProductInfoDTO> productInfoPages = new ArrayList<ProductInfoDTO>();
		if(paginationDTO.getEndRecordNo() > paginationDTO.getTotalRecordSize()){

			for(int pageNumberOffset=paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getTotalRecordSize(); pageNumberOffset++) {
				productInfoPages.add(list.get(pageNumberOffset));
			}

		}else{

			for(int pageNumberOffset=paginationDTO.getStartRecordNo(); pageNumberOffset <= paginationDTO.getEndRecordNo(); pageNumberOffset++) {
				productInfoPages.add(list.get(pageNumberOffset));
			}

		}
		paginationDTO.setCurrentProductInfoPage(productInfoPages);


		if((paginationDTO.getStartRecordNo() - 1) <= 0) {
			paginationDTO.setPreviousPage(false);
		}else {
			paginationDTO.setPreviousPage(true);
			paginationDTO.setPreviousPageNo(paginationDTO.getCurrentPageNo() - 1);
		}

		if(paginationDTO.getEndRecordNo() + pageSize > paginationDTO.getTotalRecordSize()) {
			paginationDTO.setNextPage(false);
		}else {
			paginationDTO.setNextPage(true);
			paginationDTO.setNextPageNo(paginationDTO.getCurrentPageNo() + 1);
		}

		return paginationDTO;

	}

}
