package cn.anthony.boot.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.BaseEntity;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BaseEntityService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/base")
@SessionAttributes(value = "pageRequest")
public class BaseEntityController {

    @Autowired
    private BaseEntityService baseEntityService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newBaseEntityPage() {
	ModelAndView mav = new ModelAndView("/base/new", "baseEntity", new BaseEntity());
	return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewBaseEntity(@ModelAttribute BaseEntity baseEntity, final RedirectAttributes redirectAttributes) {

	ModelAndView mav = new ModelAndView();
	String message = "New baseEntity " + baseEntity.getTitle() + " was successfully created.";

	baseEntityService.create(baseEntity);
	mav.setViewName("redirect:/base/index");

	redirectAttributes.addFlashAttribute("message", message);
	return mav;
    }

    // public String list(@RequestParam(defaultValue='1', value='page.page',
    // required=false) Integer page,
    // @RequestParam(defaultValue='10', value='page.size', required=false)
    // Integer size, Model model){

    @RequestMapping(value = { "/", "index", "/list" })
    public String listPage(@Valid PageRequest pageRequest, BindingResult result, Model m) {
	if (result.hasErrors()) {
	    return "/base/list";
	}
	Page<BaseEntity> drPage = baseEntityService.find(pageRequest.page, pageRequest.size);
	ControllerUtil.setPageVariables(m, drPage);
	return "/base/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editBaseEntityPage(@PathVariable Integer id) {
	ModelAndView mav = new ModelAndView("/base/edit");
	BaseEntity baseEntity = baseEntityService.findById(id);
	mav.addObject("baseEntity", baseEntity);
	return mav;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editBaseEntity(@ModelAttribute BaseEntity baseEntity, @PathVariable Integer id, final RedirectAttributes redirectAttributes)
	    throws EntityNotFound {
	ModelAndView mav = new ModelAndView("redirect:/base/index");
	String message = "BaseEntity was successfully updated.";
	baseEntityService.update(baseEntity);
	redirectAttributes.addFlashAttribute("message", message);
	return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBaseEntity(@PathVariable Integer id, final RedirectAttributes redirectAttributes) throws EntityNotFound {
	ModelAndView mav = new ModelAndView("redirect:/base/index");
	BaseEntity baseEntity = baseEntityService.delete(id);
	String message = "The baseEntity " + baseEntity.getTitle() + " was successfully deleted.";
	redirectAttributes.addFlashAttribute("message", message);
	return mav;
    }
}
