package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.BloodPost;
import org.itglance.docsea.repository.BloodPostRepository;
import org.itglance.docsea.service.BloodPostService;
import org.itglance.docsea.service.dto.BloodPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping
    public ResponseEntity<?> postBlood(@RequestBody BloodPostDTO bloodPostDTO) throws ParseException {

        System.out.println("----------------this is blood post********");
        System.out.println(bloodPostDTO.toString());
        System.out.println("----------------this is blood post********");

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date d = dateFormatter.parse(dateFormatter.format(new Date() ));
        if(bloodPostDTO.getDeadline().before(d)){
            return new ResponseEntity<String>("Deadline is not a valid date",HttpStatus.OK);
        }
        bloodPostService.postBlood(bloodPostDTO);
        return new ResponseEntity<String>("Inserted sucessfully",HttpStatus.OK);
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
    public ResponseEntity<?> getBloodPost() {
        List<BloodPost> bloodPostDTOS=bloodPostService.getAllBlood();
//        if(bloodPostDTOS == null){
//            return new ResponseEntity<String>("their are no blood posts",HttpStatus.NO_CONTENT);
//        }
        System.out.println(bloodPostDTOS);
        return new ResponseEntity<List<BloodPost>>(bloodPostDTOS,HttpStatus.OK) ;

    }
}
