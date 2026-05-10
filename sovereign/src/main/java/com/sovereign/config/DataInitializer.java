package com.sovereign.config;

import com.sovereign.model.Car;
import com.sovereign.model.CarColor;
import com.sovereign.model.Mod;
import com.sovereign.repository.CarColorRepository;
import com.sovereign.repository.CarRepository;
import com.sovereign.repository.ModRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Populates the H2 in-memory database with sample Sovereign cars, colours,
 * and luxury modifications on every startup.
 *
 * Because H2 is in-memory, data resets each run — this class ensures the
 * showroom always has content to display.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepo;
    private final CarColorRepository colorRepo;
    private final ModRepository modRepo;

    public DataInitializer(CarRepository carRepo,
                           CarColorRepository colorRepo,
                           ModRepository modRepo) {
        this.carRepo = carRepo;
        this.colorRepo = colorRepo;
        this.modRepo = modRepo;
    }

    @Override
    public void run(String... args) {
        // Skip if already seeded (e.g. running with a persistent DB)
        if (carRepo.count() > 0) return;

        // ── Cars ─────────────────────────────────────────────────────────────

        Car phantom = carRepo.save(new Car(
            "Sovereign Phantom",
            "Grand Tourer",
            "The Phantom is the pinnacle of effortless grand touring. Its 6.75-litre " +
            "twin-turbocharged V12 delivers 563 bhp in near-total silence, insulating " +
            "all who travel within from the outside world.",
            495000.0,
            "/images/phantom.jpg",
            "The most silent car in the world"
        ));

        Car ghost = carRepo.save(new Car(
            "Sovereign Ghost",
            "Saloon",
            "The Ghost embodies 'post-opulent' design — a pared-back aesthetic of " +
            "immense precision. Every surface, every texture, every detail has been " +
            "considered for those who choose substance over statement.",
            365000.0,
            "/images/ghost.jpg",
            "Effortless everywhere"
        ));

        Car wraith = carRepo.save(new Car(
            "Sovereign Wraith",
            "Coupé",
            "The most powerful Sovereign ever built. The Wraith commands a dramatic " +
            "fastback silhouette and a 624 bhp V12 that surges to 60 mph in 4.4 seconds, " +
            "all without compromising on bespoke luxury.",
            345000.0,
            "/images/wraith.jpg",
            "Power in its purest form"
        ));

        Car cullinan = carRepo.save(new Car(
            "Sovereign Cullinan",
            "SUV",
            "The Cullinan redefines what an SUV can be. Named after the largest gem-quality " +
            "rough diamond ever found, it delivers the effortless experience of a Sovereign " +
            "motor car across any terrain on earth.",
            415000.0,
            "/images/cullinan.jpg",
            "Above and beyond"
        ));

        Car dawn = carRepo.save(new Car(
            "Sovereign Dawn",
            "Convertible",
            "The Dawn is a vision of open-air luxury. Its effortless hood operation " +
            "and hushed V12 make every drive a serene occasion — whether hood up or down.",
            370000.0,
            "/images/dawn.jpg",
            "The soul of open-air luxury"
        ));

        Car spectre = carRepo.save(new Car(
            "Sovereign Spectre",
            "Electric Coupé",
            "Spectre is Sovereign's first fully-electric motor car — a grand tourer " +
            "of profound beauty, intelligence, and capability. It produces 577 bhp " +
            "and 900 Nm of torque in complete, eerie silence.",
            430000.0,
            "/images/spectre.jpg",
            "The future of effortless"
        ));

        // ── Colours ──────────────────────────────────────────────────────────

        // Phantom colours
        colorRepo.save(new CarColor("Midnight Obsidian",    "#0D0D0D",   phantom));
        colorRepo.save(new CarColor("Arctic White",         "#F5F5F0",   phantom));
        colorRepo.save(new CarColor("Bespoke Navy",         "#1A2744",   phantom));
        colorRepo.save(new CarColor("Twilight Purple",      "#2D1B4E",   phantom));

        // Ghost colours
        colorRepo.save(new CarColor("Ghost Silver",         "#C0C0C8",   ghost));
        colorRepo.save(new CarColor("Lyrical Copper",       "#B87333",   ghost));
        colorRepo.save(new CarColor("Salamanca Blue",       "#0E2254",   ghost));

        // Wraith colours
        colorRepo.save(new CarColor("Gunmetal",             "#2A2A2A",   wraith));
        colorRepo.save(new CarColor("English White",        "#F0EDEA",   wraith));
        colorRepo.save(new CarColor("Cobalto Red",          "#8B1A1A",   wraith));

        // Cullinan colours
        colorRepo.save(new CarColor("Everest Grey",         "#8A8D8F",   cullinan));
        colorRepo.save(new CarColor("Bespoke Green",        "#1B3A2D",   cullinan));
        colorRepo.save(new CarColor("Mandarin",             "#C45B26",   cullinan));

        // Dawn colours
        colorRepo.save(new CarColor("Forged Carbon",        "#1C1C1E",   dawn));
        colorRepo.save(new CarColor("Rose Gold",            "#B76E79",   dawn));
        colorRepo.save(new CarColor("Crystal Silver",       "#D4D4CE",   dawn));

        // Spectre colours
        colorRepo.save(new CarColor("Umbra",                "#1A1A2E",   spectre));
        colorRepo.save(new CarColor("Selene Grey",          "#8C8C96",   spectre));
        colorRepo.save(new CarColor("Sovereign Gold",       "#C9A84C",   spectre));

        // ── Modifications ────────────────────────────────────────────────────

        // Performance
        modRepo.save(new Mod("Performance", "Bespoke Engine Tune",
            "Hand-calibrated ECU mapping by our master technicians, unlocking an additional 25 bhp.",
            12500.0));
        modRepo.save(new Mod("Performance", "Carbon Ceramic Brake System",
            "Six-piston front calipers with carbon-ceramic discs for supreme stopping power.",
            18000.0));
        modRepo.save(new Mod("Performance", "Active Air Suspension",
            "Electronically controlled air springs with variable damping for any road surface.",
            9500.0));

        // Interior
        modRepo.save(new Mod("Interior", "Starlight Headliner",
            "Over 1,340 individually placed fibre optic lights recreating your chosen constellation.",
            15000.0));
        modRepo.save(new Mod("Interior", "Bespoke Audio by Bowers & Wilkins",
            "18-speaker, 1,300-watt system tuned specifically to the acoustic properties of your cabin.",
            11000.0));
        modRepo.save(new Mod("Interior", "Champagne Cooler",
            "A refrigerated compartment in the rear console accommodating two champagne flutes.",
            4500.0));
        modRepo.save(new Mod("Interior", "Lambswool Floormats",
            "Deep-pile New Zealand lambswool mats, hand-woven with a sovereign crest motif.",
            2200.0));

        // Exterior
        modRepo.save(new Mod("Exterior", "Carbon Fibre Aero Kit",
            "Handcrafted carbon fibre front splitter, side sills, and rear diffuser.",
            22000.0));
        modRepo.save(new Mod("Exterior", "21\" Bespoke Alloy Wheels",
            "Forged aluminium wheels with a diamond-turned finish and a chosen accent colour.",
            8500.0));
        modRepo.save(new Mod("Exterior", "Bespoke Two-Tone Paint",
            "A contrasting roof, A-pillars, or coach lines in any colour from our bespoke palette.",
            6000.0));

        System.out.println("✔ Sovereign showroom data initialised — " + carRepo.count() + " cars loaded.");
    }
}
