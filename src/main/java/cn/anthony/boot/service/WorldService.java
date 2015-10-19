package cn.anthony.boot.service;

import java.util.Collection;

import cn.anthony.boot.doman.Country;

public interface WorldService {
	 
public Collection<Country> getAllCountries();
 
public Country getCountryById(int countryId);
 
public Country getCountryByName(String countryName);
 
public void saveCountry(Country country);
 
public void deleteCountry(Country country);
 
}
