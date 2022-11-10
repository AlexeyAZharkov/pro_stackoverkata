package com.javamentor.qa.platform.pagination.abstracts;

import com.javamentor.qa.platform.models.dto.PageDto;

import java.util.List;

public interface Pagination<T> {

    List<T> getItems();

    int getTotalResultCount();

    default PageDto<T> getItemsOnPage(int itemsOnPage, int currentPage){
        PageDto<T> result = new PageDto<>();
        result.setItemsOnPage(itemsOnPage);
        result.setCurrentPageNumber(currentPage);
        result.setTotalResultCount(getTotalResultCount());
        List<T> items = getItems();
        result.setItems(items.subList(currentPage * itemsOnPage,
                (currentPage * itemsOnPage + itemsOnPage >= items.size()) ? items.size() - 1 : currentPage * itemsOnPage + itemsOnPage));
        result.setTotalPageCount(result.getTotalResultCount() / result.getItemsOnPage());
        return result;
    }
}
