package com.koreait.springbootboard;

import com.koreait.springbootboard.board.model.BoardCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyMenus {

    @Autowired private HomeService service;

    public List<BoardCategoryEntity> menuList;

    //어디서든 불러 쓸수있게(호출) 하기 위해서, 타임리프쪽에서 사용함
    @Bean("menus")
    public List<BoardCategoryEntity> getMenus() {
        if (menuList == null) {
            menuList = service.selMenuCategoryList();
        }
        return menuList;
    }

}
