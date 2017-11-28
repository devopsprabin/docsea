package org.itglance.docsea.web.rest;

import io.swagger.annotations.ApiParam;
import org.itglance.docsea.domain.BloodPost;
import org.itglance.docsea.repository.BloodPostRepository;
import org.itglance.docsea.restutil.PaginationUtil;
import org.itglance.docsea.service.BloodPostService;
import org.itglance.docsea.service.dto.BloodPostDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sanjib on 6/12/17.
 */
@CrossOrigin
@RestController
@RequestMapping(value="/api/bloodPost")
public class BloodPostController {
    @Autowired
    BloodPostService bloodPostService;

    BloodPostRepository bloodPostRepository;

    public BloodPostController(BloodPostRepository bloodPostRepository) {
        this.bloodPostRepository = bloodPostRepository;
    }

    public static final Logger logger = LoggerFactory.getLogger(BloodPostController.class);


    @GetMapping
    public ResponseEntity<List<BloodPost>> getBloodPost(@ApiParam Pageable pageable) {
        logger.info("GET bloodPost api called  ");
        Page<BloodPost> bloodPostList = bloodPostService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(bloodPostList, "/api/bloodPost");
        return new ResponseEntity<>(bloodPostList.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postBlood(@RequestBody BloodPostDTO bloodPostDTO) throws ParseException {
        logger.info("POST bloodpost api called  ");
        logger.info(bloodPostDTO.toString());

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date d = dateFormatter.parse(dateFormatter.format(new Date() ));
        if(bloodPostDTO.getDeadline().before(d)){
            return new ResponseEntity<>("Deadline is not a valid date",HttpStatus.OK);
        }
        bloodPostService.postBlood(bloodPostDTO);
        return new ResponseEntity<>("Inserted sucessfully",HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> getBloodPost(Pageable page) throws ParseException {
//        Page<BloodPost> bloodPost = bloodPostService.getAllBlood(page);
//        if(bloodPost == null){
//            return new ResponseEntity<String>("their is no bloodPost",HttpStatus.NO_CONTENT);
//        }
//        System.out.println(bloodPost);
//        return new ResponseEntity<Page<BloodPost>>(bloodPost,HttpStatus.OK) ;
//
//    }

    @GetMapping
    public ResponseEntity<List<BloodPost>> getBloodPost() {
        List<BloodPost> bloodPostDTOS=bloodPostService.getAllBlood();
//        if(bloodPostDTOS == null){
//            return new ResponseEntity<String>("their are no blood posts",HttpStatus.NO_CONTENT);
//        }
        System.out.println(bloodPostDTOS);
        return new ResponseEntity<>(bloodPostDTOS,HttpStatus.OK) ;

    }

}
