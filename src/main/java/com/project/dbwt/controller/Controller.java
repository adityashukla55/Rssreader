package com.project.dbwt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dbwt.model.HomeUrl;
import com.project.dbwt.model.NewsAggregatorModel;
import com.project.dbwt.repository.HomeUrlRepository;
import com.project.dbwt.repository.NewsAggregatorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Controller {

	public static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private NewsAggregatorRepository newsAggregatorRepository;
	
	@Autowired
	private HomeUrlRepository homeUrlRepository;
	
	

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/rssdata", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE
			})
	 public ResponseEntity<List<NewsAggregatorModel>> listAllUsers() {
		List<NewsAggregatorModel> newsdata = newsAggregatorRepository.findAll();
		if (newsdata.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<NewsAggregatorModel>>(newsdata, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hi", method = RequestMethod.GET,produces = {
			MediaType.APPLICATION_JSON_VALUE
			})
	 public ResponseEntity<List<HomeUrl>> Home() {
	
		
		
		List<HomeUrl> url = homeUrlRepository.findAll();
		if (url.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<HomeUrl>>(url, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/rss", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE
			
			})
	public ResponseEntity<List<NewsAggregatorModel>> listAllUsers1() {
		List<NewsAggregatorModel> newsdata = newsAggregatorRepository.findAll();
		if (newsdata.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<NewsAggregatorModel>>(newsdata, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

		@RequestMapping(value = "/rssdata/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
			logger.info("Fetching & Deleting User with id {}", id);

			 newsAggregatorRepository.delete(id);
//			if (news == null) {
//				logger.error("Unable to delete. User with id {} not found.", id);
//				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
//						HttpStatus.NOT_FOUND);
//			}
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		@RequestMapping(value = "/rssdatadel", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser() {
			logger.info("Fetching & Deleting User with id {}");

			 newsAggregatorRepository.deleteAll();
//			if (news == null) {
//				logger.error("Unable to delete. User with id {} not found.", id);
//				return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
//						HttpStatus.NOT_FOUND);
//			}
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		// -------------------Create a User-------------------------------------------

		@RequestMapping(value = "/rssdata/create", method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody NewsAggregatorModel user) {
			logger.info("Creating User : {}", user);

//			if (userService.isUserExist(user)) {
//				logger.error("Unable to create. A User with name {} already exist", user.getName());
//				return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
//				user.getName() + " already exist."),HttpStatus.CONFLICT);
//			}
			
		System.out.println("77770000000"+user.getTitle());
			newsAggregatorRepository.save(user);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}
		
		
		@RequestMapping(value = "/hi/create", method = RequestMethod.POST)
		public ResponseEntity<?> homeUrl(@RequestBody HomeUrl user) {
			logger.info("Creating User : {}", user);
			
		System.out.println("77770000000"+user.getTitle());
			homeUrlRepository.save(user);
			return new ResponseEntity(HttpStatus.CREATED);
		}

		// ------------------- Update a User ------------------------------------------------
		
		@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody NewsAggregatorModel user) {
			logger.info("Updating User with id {}", id);

//			User currentUser = userService.findById(id);
//
//			if (currentUser == null) {
//				logger.error("Unable to update. User with id {} not found.", id);
//				return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
//						HttpStatus.NOT_FOUND);
//			}

			NewsAggregatorModel user1= newsAggregatorRepository.findOne(id);
			user1.setAuthor(user.getAuthor());
			user1.setId(user.getId());
			user1.setLink(user.getLink());
			user1.setPublishedDate(user.getPublishedDate());
			user1.setTitle(user.getTitle());
			user1.setUpdatedDate(user.getUpdatedDate());
			
			newsAggregatorRepository.save(user1);
			 return new ResponseEntity<NewsAggregatorModel>(user1, HttpStatus.OK);
		}



   @RequestMapping("/search")
   @ResponseBody
   public ResponseEntity<List<NewsAggregatorModel>> findBy(@RequestParam(name = "title") String title,
										  @RequestParam(name="link") String link) {
	logger.info("-------> find order by id : {}", title,link);

	System.out.println("---------------client created---------------");
	List<NewsAggregatorModel> fetchDataByUrl =  newsAggregatorRepository.findByTitleAndLink(title,link);


	return new ResponseEntity<>(fetchDataByUrl, HttpStatus.OK);
   }


   @Scheduled(cron = "0/200000 50 10 30 * ?2019")			//Fires at 10:15 AM every day in the year 2019:
	@Transactional                                        //for every 20 sec. 10hours 50 mins 30 days
	@RequestMapping(value = "/deleteByID/{url}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("url") String link) {
		logger.info("Fetching & Deleting User with url {}", link);

			newsAggregatorRepository.deleteByLink(link);


		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

    }


