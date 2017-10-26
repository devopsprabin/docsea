package org.itglance.docsea.web.rest;

import org.itglance.docsea.domain.HospitalUser;
import org.itglance.docsea.service.HospitalService;
import org.itglance.docsea.service.dto.HospitalDTO;
import org.itglance.docsea.service.dto.HospitalUserDTO;
import org.itglance.docsea.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by sanj__000 on 5/10/2017.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class HospitalController {


    @Autowired
    HospitalService hospitalService;

    public static final Logger logger = LoggerFactory.getLogger(HospitalController.class);


    //------------------------hospital resitration-------//
    @RequestMapping(value = "/hospital", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody HospitalUserDTO hospitalUser)
    {

        HospitalDTO hospitalDTO = new HospitalDTO(hospitalUser.getHospital());
        UserDTO userDTO = new UserDTO(hospitalUser.getUser());
        logger.info("Creating Hospital : {}", hospitalDTO);


        if(hospitalDTO.getAddress().getCity().getName() == null){
        	 logger.error(" City name should not be null");
             return new ResponseEntity(("Unable to register Hospital, City name should,nt be null"), HttpStatus.CONFLICT);

        }
        else if(hospitalService.isHospitalExist(hospitalDTO, userDTO)){
            logger.error(" Hospital already exist");
            return new ResponseEntity(("Unable to register Hospital. A hospital with registration no. or hospital name" +
                    " or lisence no. or usernme is already exist."), HttpStatus.CONFLICT);
        }
        hospitalService.registerHospital(hospitalDTO, userDTO);
        return new ResponseEntity<HospitalDTO> (hospitalDTO, HttpStatus.OK);
    }

    //-----------------Updating hospital------------------
   @PutMapping(value = "/hospital")
    public ResponseEntity<?> updateHospitalUser(@RequestBody HospitalUserDTO hospitalUserDTO){

        logger.info("Updating Hospital !!!"+hospitalUserDTO);
        System.out.println(hospitalUserDTO);

        if(!hospitalService.isHospitalExist(hospitalUserDTO.getId())){
            logger.error("Can't find hospital in the database");
            return new ResponseEntity<String>(("Cannot find hospital in database. "), HttpStatus.CONFLICT);
        }else if(!hospitalService.validateUsernameForUpdate(hospitalUserDTO.getUser())){
            logger.error("Hospital with the Username "+hospitalUserDTO.getUser().getUsername()+" already exist");
            return new ResponseEntity<String>(("Hospital with the Username "+hospitalUserDTO.getUser().getUsername()+" already exist"), HttpStatus.CONFLICT);
        }else if(!hospitalService.validateHospitalNameForUpdate(hospitalUserDTO.getHospital())){
            logger.error("Hospital with the name "+hospitalUserDTO.getHospital().getName()+" already exist");
            return new ResponseEntity<String>(("Hospital with the name already "+hospitalUserDTO.getHospital().getName()+" exist"), HttpStatus.CONFLICT);
        }else if(!hospitalService.validateLisenceForUpdate(hospitalUserDTO.getHospital())){
            logger.error("Hospital with the Lisence number "+hospitalUserDTO.getHospital().getLisenceNo()+" already exist");
            return new ResponseEntity<String>(("Hospital with the Lisence number "+hospitalUserDTO.getHospital().getLisenceNo()+" already exist"), HttpStatus.CONFLICT);
        }else if(!hospitalService.validateRegForUpdate(hospitalUserDTO.getHospital())){
            logger.error("Hospital with the Registration number "+hospitalUserDTO.getHospital().getRegistrationNo()+" already exist");
            return new ResponseEntity<String>(("Hospital with the Registration number "+hospitalUserDTO.getHospital().getRegistrationNo()+" already exist"), HttpStatus.CONFLICT);
        }

        hospitalService.updateHospital(hospitalUserDTO);

        logger.info("Hospital updated "+hospitalUserDTO);
        return new ResponseEntity<String>("Hospital ("+hospitalUserDTO.getHospital().getName()+") has been updated sucessfull",HttpStatus.OK);

    }

    //-------------- display testing----//
   @RequestMapping(value = "/hospital", method = RequestMethod.GET)

    public ResponseEntity<List<HospitalUserDTO>> listAllUsers() {
         List<HospitalUserDTO> list = hospitalService.getAllHospitalUser();
        if(list.isEmpty()){
            return new ResponseEntity<List<HospitalUserDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //----------- display hospital by username-------
    @GetMapping(value = "/hospital/{username}")
    public ResponseEntity<?> getHospitalUserByU(@PathVariable("username") String username){
        HospitalUserDTO hospitalUserDTOs = hospitalService.getHospitalByUsername(username);
        return new ResponseEntity<HospitalUserDTO>(hospitalUserDTOs ,HttpStatus.OK);
    }

    //----------- display hospital by hospital id-------
    @GetMapping(value = "/hospitalId/{id}")
    public ResponseEntity<?> getHospitalUserById(@PathVariable("id") Long id){
        HospitalUserDTO hospitalUserDTOS = hospitalService.getHospitalById(id);
        return new ResponseEntity<HospitalUserDTO>(hospitalUserDTOS ,HttpStatus.OK);
    }
}
