package cn.anthony.boot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.BaseEntity;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BaseEntityService;

@Controller
@RequestMapping(value="/base")
public class BaseEntityController {
     
    @Autowired
    private BaseEntityService baseEntityService;
 
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public void index() {
    }
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView newBaseEntityPage() {
        ModelAndView mav = new ModelAndView("/base/new", "baseEntity", new BaseEntity());
        return mav;
    }
     
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewBaseEntity(@ModelAttribute BaseEntity baseEntity, 
            final RedirectAttributes redirectAttributes) {
         
        ModelAndView mav = new ModelAndView();
        String message = "New baseEntity "+baseEntity.getTitle()+" was successfully created.";
         
        baseEntityService.create(baseEntity);
        mav.setViewName("redirect:/base/index");
                 
        redirectAttributes.addFlashAttribute("message", message);   
        return mav;     
    }
     
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView baseEntityListPage() {
        ModelAndView mav = new ModelAndView("/base/list");
        List<BaseEntity> baseEntityList = baseEntityService.findAll();
        mav.addObject("baseEntityList", baseEntityList);
        return mav;
    }
     
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editBaseEntityPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("/base/edit");
        BaseEntity baseEntity = baseEntityService.findById(id);
        mav.addObject("baseEntity", baseEntity);
        return mav;
    }
     
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editBaseEntity(@ModelAttribute BaseEntity baseEntity,
            @PathVariable Integer id,
            final RedirectAttributes redirectAttributes) throws EntityNotFound {
         
        ModelAndView mav = new ModelAndView("redirect:/base/index");
        String message = "BaseEntity was successfully updated.";
 
        baseEntityService.update(baseEntity);
         
        redirectAttributes.addFlashAttribute("message", message);   
        return mav;
    }
     
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteBaseEntity(@PathVariable Integer id,
            final RedirectAttributes redirectAttributes) throws EntityNotFound {
         
        ModelAndView mav = new ModelAndView("redirect:/base/index");        
         
        BaseEntity baseEntity = baseEntityService.delete(id);
        String message = "The baseEntity "+baseEntity.getTitle()+" was successfully deleted.";
         
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
     
}
