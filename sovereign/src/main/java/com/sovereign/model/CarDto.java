package com.sovereign.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarDto — plain Java object used to pass car data to Thymeleaf views.
 *
 * Why a DTO?
 * Thymeleaf 3.1 enforces strict-mode security and blocks calling Java methods
 * directly on JPA entities (Hibernate proxies). Converting to a DTO before
 * returning the view completely avoids those runtime exceptions.
 */
public class CarDto {

    private Long   id;
    private String name;
    private String model;
    private int    year;
    private double price;
    private String description;
    private String imageUrl;
    private List<ColorDto> colors;
    private List<ModDto>   mods;

    // ── Nested: Colour ────────────────────────────────────────────────────────

    public static class ColorDto {
        private Long   id;
        private String name;
        private String hexCode;

        public ColorDto(Long id, String name, String hexCode) {
            this.id      = id;
            this.name    = name;
            this.hexCode = hexCode;
        }

        public Long   getId()      { return id; }
        public String getName()    { return name; }
        public String getHexCode() { return hexCode; }
    }

    // ── Nested: Modification ─────────────────────────────────────────────────

    public static class ModDto {
        private Long   id;
        private String name;
        private String description;
        private double price;
        private String category;

        public ModDto(Long id, String name, String description, double price, String category) {
            this.id          = id;
            this.name        = name;
            this.description = description;
            this.price       = price;
            this.category    = category;
        }

        public Long   getId()            { return id; }
        public String getName()          { return name; }
        public String getDescription()   { return description; }
        public double getPrice()         { return price; }
        public String getCategory()      { return category; }

        /** Formatted price for display: "8,500" */
        public String getFormattedPrice() {
            return String.format("%,.0f", price);
        }
    }

    // ── Factory ───────────────────────────────────────────────────────────────

    /** Convert a JPA Car entity into a safe, flat CarDto. */
    public static CarDto from(Car car) {
        CarDto dto = new CarDto();
        dto.id          = car.getId();
        dto.name        = car.getName();
        dto.model       = car.getModel();
        dto.year        = car.getYear();
        dto.price       = car.getPrice();
        dto.description = car.getDescription();
        dto.imageUrl    = car.getImageUrl();

        dto.colors = car.getColors().stream()
                .map(c -> new ColorDto(c.getId(), c.getName(), c.getHexCode()))
                .collect(Collectors.toList());

        dto.mods = car.getMods().stream()
                .map(m -> new ModDto(m.getId(), m.getName(), m.getDescription(),
                                     m.getPrice(), m.getCategory()))
                .collect(Collectors.toList());

        return dto;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public Long   getId()          { return id; }
    public String getName()        { return name; }
    public String getModel()       { return model; }
    public int    getYear()        { return year; }
    public double getPrice()       { return price; }
    public String getDescription() { return description; }
    public String getImageUrl()    { return imageUrl; }
    public List<ColorDto> getColors() { return colors; }
    public List<ModDto>   getMods()   { return mods; }

    /** Formatted base price for display: "250,000" */
    public String getFormattedPrice() {
        return String.format("%,.0f", price);
    }
}
