package com.example.TravelAndTourism.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "tour_packages")
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }



    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotBlank(message = "Package name is required.")
    @Size(min = 3, max = 100, message = "Package name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "Description is required.")
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;




    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
    @Digits(integer = 8, fraction = 2, message = "Price format is invalid (max 8 digits before decimal and 2 digits after).")
    private BigDecimal price;

    @NotBlank(message = "Duration is required.")
    @Pattern(regexp = "^\\d+ days, \\d+ nights$", message = "Duration format must be 'X days, Y nights'.")
    private String duration;

    @NotNull(message = "Start date is required.")
    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;

    @NotNull(message = "End date is required.")
    @Future(message = "End date must be in the future.")
    private LocalDate endDate;

    @NotNull(message = "Locations are required.")
    @Size(min = 1, message = "At least one location must be specified.")
    @ElementCollection
    private List<@NotBlank(message = "Location name cannot be blank.") String> locations;

    @NotNull(message = "Capacity is required.")
    @Min(value = 1, message = "Capacity must be at least 1.")
    @Max(value = 500, message = "Capacity cannot exceed 500.")
    private Integer capacity;

    @NotBlank(message = "Status is required.")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be either ACTIVE or INACTIVE.")
    private String status;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;
}
