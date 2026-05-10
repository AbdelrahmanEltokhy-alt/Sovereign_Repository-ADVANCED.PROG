package com.sovereign.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Plain data transfer object — holds car data for the Thymeleaf detail view.
 * Using a DTO avoids Hibernate proxy / Thymeleaf 3.1 strict-mode access issues.
 */
public class CarDto {

    private Long id;
    private String name;
    private String type;
    private String description;
    private Double basePrice;
    private String imageUrl;
    private String tagline;
    private List<ColorDto> colors;

    // ── Nested DTO for colours ─────────────────────────────────────────────
    public static class ColorDto {
        private Long id;
        private String name;
        private String hexCode;

        public ColorDto(Long id, String name, String hexCode) {
            this.id = id;
            this.name = name;
            this.hexCode = hexCode;
        }

        public Long getId()      { return id; }
        public String getName()  { return name; }
        public String getHexCode() { return hexCode; }
    }

    // ── Nested DTO for mods ────────────────────────────────────────────────
    public static class ModDto {
        private Long id;
        private String category;
        private String name;
        private String description;
        private Double price;

        public ModDto(Long id, String category, String name, String description, Double price) {
            this.id = id;
            this.category = category;
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public Long getId()           { return id; }
        public String getCategory()   { return category; }
        public String getName()       { return name; }
        public String getDescription(){ return description; }
        public Double getPrice()      { return price; }
        public String getFormattedPrice() {
            return price != null ? String.format("%,.0f", price) : "0";
        }
    }

    // ── Factory ───────────────────────────────────────────────────────────
    public static CarDto from(Car car) {
        CarDto dto = new CarDto();
        dto.id          = car.getId();
        dto.name        = car.getName();
        dto.type        = car.getType();
        dto.description = car.getDescription();
        dto.basePrice   = car.getBasePrice();
        dto.imageUrl    = car.getImageUrl();
        dto.tagline     = car.getTagline();
        dto.colors      = car.getColors().stream()
                            .map(c -> new ColorDto(c.getId(), c.getName(), c.getHexCode()))
                            .collect(Collectors.toList());
        return dto;
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public Long getId()           { return id; }
    public String getName()       { return name; }
    public String getType()       { return type; }
    public String getDescription(){ return description; }
    public Double getBasePrice()  { return basePrice; }
    public String getImageUrl()   { return imageUrl; }
    public String getTagline()    { return tagline; }
    public List<ColorDto> getColors() { return colors; }

    public String getFormattedPrice() {
        return basePrice != null ? String.format("%,.0f", basePrice) : "0";
    }
}
