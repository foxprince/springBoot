package cn.anthony.boot.util;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public class ControllerUtil {
    public static <T> void setPageVariables(Model mav, Page<T> page) {
	mav.addAttribute("itemList", page.getContent());
	int current = page.getNumber() + 1;
	int begin = Math.max(1, current - 5);
	int end = Math.min(begin + 10, page.getTotalPages());
	mav.addAttribute("beginIndex", begin);
	mav.addAttribute("endIndex", end);
	mav.addAttribute("currentIndex", current);
	mav.addAttribute("pageSize", page.getSize());
	mav.addAttribute("total", page.getTotalElements());
	mav.addAttribute("totalPages", page.getTotalPages());
	mav.addAttribute("isFirst", page.isFirst());
	mav.addAttribute("hasPrevious", page.hasPrevious());
	mav.addAttribute("hasNext", page.hasNext());
	mav.addAttribute("isLast", page.isLast());
    }
}
