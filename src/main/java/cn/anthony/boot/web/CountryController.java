package cn.anthony.boot.web;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.anthony.boot.doman.Country;
import cn.anthony.boot.service.MockWorldService;
import cn.anthony.boot.service.WorldService;

@Controller
public class CountryController {

    private WorldService worldService = new MockWorldService();

    @RequestMapping("/countryList.html")
    @ModelAttribute("countries")
    public Collection<Country> getCountries() {
	return worldService.getAllCountries();
    }

    @RequestMapping("/countryDetails.html")
    public Country getCountry(@RequestParam(value = "id", required = true) int countryId) {
	return worldService.getCountryById(countryId);
    }
}
