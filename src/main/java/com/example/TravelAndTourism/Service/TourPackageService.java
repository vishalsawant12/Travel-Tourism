package com.example.TravelAndTourism.Service;

import com.example.TravelAndTourism.models.TourPackage;
import com.example.TravelAndTourism.repository.TourPackageRepository;
import com.example.TravelAndTourism.responsewrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourPackageService {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    ResponseWrapper responseWrapper;


    public ResponseEntity<?> createTourPackage(TourPackage tourPackage){
        Optional<TourPackage> exisitingPackage = tourPackageRepository.findByName(tourPackage.getName());

        if (exisitingPackage.isPresent()){
            responseWrapper.setMessage("Tour with name already exist");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
        }
        TourPackage  savePackage = tourPackageRepository.save(tourPackage);
        responseWrapper.setMessage("Tour package create successfully");
        responseWrapper.setData(savePackage);
        return new ResponseEntity<>(responseWrapper,HttpStatus.CREATED);
    }

//    update

    public ResponseEntity<?> updateTourPackage(Long id, TourPackage tourPackageDetails) {
           // Check if the tour package with the given ID exists
           Optional<TourPackage> existingPackageOpt = tourPackageRepository.findById(id);
           if (!existingPackageOpt.isPresent()) {
               responseWrapper.setMessage("Tour package not found.");
               responseWrapper.setData(null);
               return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND);
           }

           // Get the existing package and update its details

           TourPackage existingPackage = existingPackageOpt.get();

           existingPackage.setName(tourPackageDetails.getName());
           existingPackage.setDescription(tourPackageDetails.getDescription());
           existingPackage.setPrice(tourPackageDetails.getPrice());
           existingPackage.setDuration(tourPackageDetails.getDuration());
           existingPackage.setStartDate(tourPackageDetails.getStartDate());
           existingPackage.setEndDate(tourPackageDetails.getEndDate());
           existingPackage.setLocations(tourPackageDetails.getLocations());
           existingPackage.setCapacity(tourPackageDetails.getCapacity());
           existingPackage.setStatus(tourPackageDetails.getStatus());
           existingPackage.setImageUrl(tourPackageDetails.getImageUrl());

//     Save the updated tour package
           TourPackage updatedPackage = tourPackageRepository.save(existingPackage);

//     Return a success response with updated package
           responseWrapper.setMessage("Tour package updated successfully.");
           responseWrapper.setData(updatedPackage);
           return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
       }

//       getall

    public  ResponseEntity<?>getAllPackges() {
        List<TourPackage> tourPackages= tourPackageRepository.findAll();
        if (tourPackages.isEmpty())
        {
            responseWrapper.setMessage("no tour package found");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper,HttpStatus.NO_CONTENT);
        }
        responseWrapper.setMessage("Tour packages retrieved sucessfully");
        responseWrapper.setData(tourPackages);
        return new ResponseEntity<>(responseWrapper,HttpStatus.OK);

    }

//    getPackageById

    public ResponseEntity<?> getPackageById(Long id) {
        Optional<TourPackage> tourPackage = tourPackageRepository.findById(id);
        if (!tourPackage.isPresent()) {
            responseWrapper.setMessage("Tour package not found.");
            responseWrapper.setData(null);
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND); // Return 404 if the package doesn't exist
        }

        responseWrapper.setMessage("Tour package retrieved successfully.");
        responseWrapper.setData(tourPackage.get());
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK); // Return 200 with the package data
    }

//    deleteById

     public ResponseEntity<?> deleteTourPackage(Long id) {
        Optional<TourPackage> existingPackageOpt = tourPackageRepository.findById(id);

    if (!existingPackageOpt.isPresent()) {
        responseWrapper.setMessage("Tour package not found.");
        responseWrapper.setData(null);
        return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND); // Return 404 if not found
    }

    // Delete the tour package
      tourPackageRepository.deleteById(id);

    // Return a success response
       responseWrapper.setMessage("Tour package deleted successfully.");
       responseWrapper.setData(null);
       return new ResponseEntity<>(responseWrapper, HttpStatus.OK); // Return 204 No Content on successful deletion
     }

}
