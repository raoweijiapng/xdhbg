package cn.xdh.util;

import org.springframework.ui.Model;

public class GetCurrentPage {
    public static Model getcurrentPage(int pageNum,int generalPage,Model model){
        if(pageNum<1){
            model.addAttribute("currentPage",1);
        }else if(pageNum>generalPage){
            model.addAttribute("currentPage",generalPage);
        }else{
            model.addAttribute("currentPage",pageNum);
        }
        return model;
    }

}
