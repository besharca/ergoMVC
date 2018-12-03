package com.lil.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.youtube.Search;


@Controller
@RequestMapping("/new")
public class YoutubeController {
	
	@Autowired
	private Search ytSearch;

	@GetMapping("/video")
	public ModelAndView getSearchPage() {
		ModelAndView mv = new ModelAndView("home/youtubePage");
		
		return mv;
	}
	
	@RequestMapping(value="video", method=RequestMethod.GET, params="searchInputQuery")
	public ModelAndView searchVideos(@RequestParam String searchInputQuery) {
		ModelAndView mv = new ModelAndView("home/youtubePage"); 
		
		List<String> list = ytSearch.search(searchInputQuery);
		
		mv.addObject("searchList", list);
		
		return mv;
	}
	
	
}
