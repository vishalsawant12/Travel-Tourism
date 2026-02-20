package com.example.TravelAndTourism.controller;

import com.example.TravelAndTourism.Service.TourPackageService;
import com.example.TravelAndTourism.models.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TourPackageController {

    @Autowired
    private TourPackageService tourPackageService;

    @PostMapping("/tourpackages")
    public ResponseEntity<?> createTourPackage(@RequestBody TourPackage tourPackage){
        return  tourPackageService.createTourPackage(tourPackage);
    }

//    update
    @PutMapping("/tourpackages/{id}")
    public ResponseEntity<?> updateTourPackage(@PathVariable("id") Long id, @RequestBody TourPackage tourPackageDetails) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid tour package ID.");
        }
        return tourPackageService.updateTourPackage(id, tourPackageDetails);
    }

//    update
//@PutMapping("/tourpackages/{id}")
//public ResponseEntity<?> updateTourPackage(@PathVariable Long id, @RequestBody TourPackage tourPackage) {
//    try {
//        // Attempt to update the package
//        ResponseEntity<?> updatedPackage = tourPackageService.updateTourPackage(id, tourPackage);
//        return ResponseEntity.ok(updatedPackage);
//    } catch (DataIntegrityViolationException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data for the package: " + e.getMessage());
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating tour package: " + e.getMessage());
//    }
//}


    //    getAll
    @GetMapping("/tourpackages")
    public  ResponseEntity<?>getAllPackges(){
        return tourPackageService.getAllPackges();
    }

//    getById
    @GetMapping("/tourpackages/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable Long id) {
        return tourPackageService.getPackageById(id);
    }

//    delete by id
     @DeleteMapping("/tourpackages/{id}")
     public ResponseEntity<?> deleteTourPackage(@PathVariable Long id) {
       return tourPackageService.deleteTourPackage(id);
    }


}