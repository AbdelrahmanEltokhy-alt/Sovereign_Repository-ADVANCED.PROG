package com.sovereign.config;

import com.sovereign.model.*;
import com.sovereign.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;
    private final ModRepository modRepository;
    private final CarColorRepository colorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CarRepository carRepository,
            ModRepository modRepository,
            CarColorRepository colorRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.carRepository = carRepository;
        this.modRepository = modRepository;
        this.colorRepository = colorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (carRepository.count() > 0)
            return;
        seedCars();
        seedUsers();
        System.out.println("✓ Sovereign DB seeded: 10 cars | 100 mods | 40 colors | 106 users");
    }

    // ── Image URLs: Unsplash free-to-use luxury/car photos ───────────────
    // These are direct Unsplash CDN links — no API key needed for display.
    private static final String[] CAR_IMAGES = {
            "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1544636331-e26879cd4d9b?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1552519507-da3b142c5e3e?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1568605117036-5f3e7f6f0c38?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1549317661-bd32c8ce0db5?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1597007030739-6d2d64dcf3b0?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1583121274602-3e2820c69888?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=900&q=80&auto=format&fit=crop",
            "https://images.unsplash.com/photo-1610647752706-3bb12232b0a9?w=900&q=80&auto=format&fit=crop"
    };

    private void seedCars() {

        Car phantom = save(new Car("Sovereign Phantom", "Phantom", 2025, 250000,
                "The flagship grand tourer. Absolute silence at 200 km/h.", CAR_IMAGES[0]));
        addColors(phantom, "Midnight Black", "#0D0D0D", "Glacier White", "#F5F5F5", "Royal Cobalt", "#1B3A6B",
                "Champagne Gold", "#C9A84C");
        addMods(phantom, new String[][] {
                { "Carbon Fibre Hood", "Full carbon weave replacing the factory bonnet", "8500", "Exterior" },
                { "Sport Exhaust System", "Titanium tips, deeper note, +15 HP", "12000", "Performance" },
                { "21\" Forged Wheels", "Lightweight forged aluminium sport wheels", "9500", "Wheels" },
                { "Lowered Suspension", "25mm drop, stiffer springs, sharper handling", "7000", "Performance" },
                { "Bespoke Interior Kit", "Hand-stitched leather throughout the cabin", "18000", "Interior" },
                { "Night Vision Camera", "Infrared front camera with HUD display", "5500", "Technology" },
                { "Heads-Up Display", "Speed, nav, and performance data on windscreen", "3800", "Technology" },
                { "Rear Carbon Diffuser", "Carbon rear diffuser for improved downforce", "4200", "Aerodynamics" },
                { "64-Colour Ambient Kit", "64-colour ambient lighting throughout cabin", "2800", "Interior" },
                { "Launch Control Unlock", "Software unlock for optimised 0-100 km/h runs", "6000", "Performance" },
        });

        Car wraith = save(new Car("Sovereign Wraith", "Wraith", 2025, 190000,
                "A coupe that bends physics. 630 horsepower of controlled fury.", CAR_IMAGES[1]));
        addColors(wraith, "Obsidian Black", "#1A1A1A", "Arctic Silver", "#C0C0C0", "Bordeaux Red", "#5C0A14",
                "British Racing Green", "#004225");
        addMods(wraith, new String[][] {
                { "Wide-Body Aero Kit", "Flared arches, splitter, and rear wing", "22000", "Aerodynamics" },
                { "Track Suspension", "Full coilover setup, adjustable damping", "14500", "Performance" },
                { "Brembo Ceramic Brakes", "6-piston calipers, cross-drilled ceramic discs", "18000", "Performance" },
                { "Carbon Fibre Roof", "Full carbon panoramic roof delete, saves 22 kg", "11000", "Exterior" },
                { "Roll Cage (Road Legal)", "FIA-spec road-legal roll cage with padding", "9500", "Safety" },
                { "Sport Bucket Seats", "Carbon shell, Alcantara trim, 4-point harness", "8800", "Interior" },
                { "Titanium Exhaust", "Full titanium system, 120dB track note", "16000", "Performance" },
                { "ECU Stage 2 Remap", "680 HP from factory 630, optimised torque curve", "4500", "Performance" },
                { "Forged Monoblock 20\"", "One-piece forged alloy, centre-lock fitment", "13500", "Wheels" },
                { "Data Logger System", "In-car telemetry: lap times, G-force, sector data", "6200", "Technology" },
        });

        Car ghost = save(new Car("Sovereign Ghost", "Ghost", 2025, 170000,
                "Effortless luxury sedan. Where comfort meets performance.", CAR_IMAGES[2]));
        addColors(ghost, "Pearl White", "#F8F6F0", "Gunmetal Grey", "#4A4A4A", "Sapphire Blue", "#0F3460",
                "Crimson Red", "#8B0000");
        addMods(ghost, new String[][] {
                { "Executive Rear Suite", "Fold-out tables, champagne fridge, privacy blinds", "24000", "Interior" },
                { "Starlight Headliner", "1,340 hand-placed fibre optic stars in the roof lining", "14000",
                        "Interior" },
                { "Bespoke Audio System", "18-speaker 1800W bespoke sound system", "11000", "Interior" },
                { "Massage Seat Package", "8-programme rear massage seats with heating", "6500", "Interior" },
                { "Air Suspension Upgrade", "Self-levelling, cross-linked air suspension", "9000", "Performance" },
                { "Extended Wheelbase", "+280mm stretched rear compartment conversion", "28000", "Exterior" },
                { "Rear Entertainment Pack", "Dual 12\" OLED screens embedded in headrests", "8500", "Technology" },
                { "Panoramic Sunroof", "Full-length electrically retracting glass roof", "4800", "Exterior" },
                { "21\" Polished Alloys", "5-spoke polished alloys with chrome caps", "7500", "Wheels" },
                { "Privacy Glass Package", "Factory-tint upgrade to full privacy spec", "1800", "Exterior" },
        });

        Car dawn = save(new Car("Sovereign Dawn", "Dawn", 2025, 210000,
                "The open-top masterpiece. Built for those who live without ceilings.", CAR_IMAGES[3]));
        addColors(dawn, "Desert Sand", "#C2956C", "Midnight Purple", "#2E0854", "Ivory White", "#FFFFF0",
                "Burnt Copper", "#7D4427");
        addMods(dawn, new String[][] {
                { "Soft-Top Carbon Frame", "Carbon-reinforced convertible roof frame, saves 18 kg", "12000",
                        "Exterior" },
                { "Wind Deflector", "Acoustic wind deflector for open-top cruising", "2400", "Exterior" },
                { "Sport Steering Wheel", "Flat-bottom carbon wheel, paddle shifters", "3200", "Interior" },
                { "Active Exhaust Valve", "App-controlled active exhaust valve switch", "3800", "Performance" },
                { "Summer Performance Tyres", "Pirelli P-Zero Corsa, staggered fitment", "4500", "Wheels" },
                { "UV-Protective Glass", "High-spec UV filter glass on all panels", "2100", "Exterior" },
                { "Ambient Scent System", "Bespoke cabin fragrance diffuser with 3 scents", "1600", "Interior" },
                { "Heated Armrests", "Heated and cooled front/rear armrests", "2200", "Interior" },
                { "Sport Chrono Package", "Stopwatch, launch control, sport response mode", "5500", "Technology" },
                { "Bi-Colour Exterior", "Two-tone paint — roof and body in contrast colours", "8500", "Exterior" },
        });

        Car cullinan = save(new Car("Sovereign Cullinan", "Cullinan", 2025, 330000,
                "The world's most commanding luxury SUV. Every terrain, effortlessly.", CAR_IMAGES[4]));
        addColors(cullinan, "Onyx Black", "#353839", "Alpine White", "#FAFAFA", "Forest Green", "#2D5016",
                "Tundra Grey", "#8C8C8C");
        addMods(cullinan, new String[][] {
                { "Off-Road Package", "Raised suspension, skid plates, all-terrain tyres", "14500", "Performance" },
                { "Roof Rack System", "Load-rated roof rack with LED light bar", "4200", "Exterior" },
                { "Rear Picnic Suite", "Fold-out tailgate leather-wrapped picnic suite", "8800", "Interior" },
                { "22\" Gloss Black Rims", "Gloss black forged rims, contrast polish lip", "11000", "Wheels" },
                { "Night Vision System", "Infrared thermal imaging displayed on dash", "6500", "Technology" },
                { "4-Zone Climate Control", "Individual climate zones for all 4 seats", "3200", "Interior" },
                { "Trailer Hitch Package", "Factory-rated tow bar, 3,500 kg rating", "1800", "Exterior" },
                { "Cargo Liner & Rails", "Rubberized boot liner with load rails", "900", "Interior" },
                { "Armoured Glass Upgrade", "B4-rated blast and ballistic glass package", "45000", "Safety" },
                { "Panoramic Split Roof", "Split panoramic sunroof — driver and rear sections", "5500", "Exterior" },
        });

        Car spectre = save(new Car("Sovereign Spectre", "Spectre", 2025, 280000,
                "The first fully electric Sovereign. Silent power, redefined.", CAR_IMAGES[5]));
        addColors(spectre, "Void Black", "#080808", "Stardust Silver", "#D4D4D4", "Electric Blue", "#0080FF",
                "Rose Gold", "#B76E79");
        addMods(spectre, new String[][] {
                { "Extended Battery Pack", "+120 km additional range via second battery module", "18000",
                        "Performance" },
                { "Wireless Charging Pad", "15W dual wireless charging in centre console", "1200", "Technology" },
                { "Solar Glass Roof", "Photovoltaic panoramic roof, adds ~30 km/day", "9500", "Technology" },
                { "Performance Inverter", "Sport inverter tune: 0-100 in 2.7s", "8000", "Performance" },
                { "Regen Brake Boost", "Enhanced regen braking: 4 levels of recovery", "2200", "Performance" },
                { "V2L Adapter", "Vehicle-to-load: power external devices at 3.3 kW", "800", "Technology" },
                { "Active Aero Spoiler", "Electronically deployed rear spoiler above 120 km/h", "6500",
                        "Aerodynamics" },
                { "Acoustic Cabin Package", "Extra sound deadening, silence below 18 dB cabin", "4800", "Interior" },
                { "Digital Exterior Mirrors", "Camera-based mirrors with wide-angle and rain modes", "3500",
                        "Technology" },
                { "High-Power Charger", "22 kW onboard AC charger upgrade (default 11 kW)", "3200", "Technology" },
        });

        Car silver = save(new Car("Sovereign Silver Shadow", "Silver Shadow", 2025, 145000,
                "A heritage icon reimagined. Classic lines, modern soul.", CAR_IMAGES[6]));
        addColors(silver, "Classic Silver", "#A8A9AD", "Cream White", "#FFFDD0", "Navy Blue", "#001F5B", "Walnut Brown",
                "#5C3317");
        addMods(silver, new String[][] {
                { "Heritage Grille Package", "Hand-polished stainless vertical slat grille", "4500", "Exterior" },
                { "Wood & Leather Dash", "Burr walnut veneer dashboard with hand-stitching", "9800", "Interior" },
                { "Chrome Trim Pack", "Full chrome exterior trim: window, sills, mirrors", "3200", "Exterior" },
                { "Modern Infotainment", "12\" touchscreen retro-fitted behind original fascia", "4800", "Technology" },
                { "Adaptive LED Headlights", "Modern LED adaptive headlights in period housing", "3600", "Exterior" },
                { "Air Conditioning Upgrade", "Modern climate control, period-correct switchgear", "5500", "Interior" },
                { "Power Steering Retrofit", "Modern hydraulic power assist, retains steering feel", "4200",
                        "Performance" },
                { "Disc Brake Conversion", "4-wheel disc brake conversion from original drums", "3800", "Safety" },
                { "Period Radio/DAB", "Vintage-look DAB digital radio with aux input", "1400", "Technology" },
                { "Stainless Exhaust", "Custom stainless dual exhaust, period tip styling", "2800", "Exterior" },
        });

        Car drophead = save(new Car("Sovereign Drophead", "Drophead", 2025, 235000,
                "Convertible opulence. Every drive is a statement.", CAR_IMAGES[7]));
        addColors(drophead, "Champagne Beige", "#D4B896", "Carbon Black", "#1C1C1C", "Sky Blue", "#87CEEB",
                "Garnet Red", "#6D0F1B");
        addMods(drophead, new String[][] {
                { "Teak Wood Deck", "Hand-laid teak wood exterior deck — yacht inspired", "14000", "Exterior" },
                { "Cashmere Hood Lining", "Hand-stitched cashmere soft-top interior lining", "8500", "Interior" },
                { "Bespoke Luggage Set", "3-piece Sovereign-branded leather luggage set", "6800", "Interior" },
                { "Lounge Package", "Rear centre console with drinks cabinet and cool box", "12000", "Interior" },
                { "Folding Wind Screen", "Electrically deployed wind screen behind front seats", "3800", "Exterior" },
                { "Venetian Blinds", "Hand-woven venetian blinds for side windows", "2200", "Interior" },
                { "Power Running Boards", "Electrically deployed chrome running boards", "4500", "Exterior" },
                { "Soft Close Doors", "Hydraulic soft-close door mechanism on all 4 doors", "2800", "Exterior" },
                { "Coach Lines", "Hand-painted coachline by specialist — any colour", "3500", "Exterior" },
                { "Monogram Package", "Embroidered headrests, engraved sill plates", "4200", "Interior" },
        });

        Car blackBadge = save(new Car("Sovereign Black Badge", "Black Badge", 2025, 310000,
                "The dark side of luxury. For those who reject compromise.", CAR_IMAGES[8]));
        addColors(blackBadge, "Stealth Black", "#0A0A0A", "Dark Carbon", "#1F1F1F", "Blood Red", "#660000",
                "Dark Chrome", "#3D3D3D");
        addMods(blackBadge, new String[][] {
                { "Black Pack Exterior", "Gloss black all exterior chrome elements", "8500", "Exterior" },
                { "Carbon Everywhere Kit", "Carbon fibre: bonnet, roof, diffuser, mirrors", "22000", "Exterior" },
                { "Darkened Taillights", "Smoked lens taillight conversion", "2400", "Exterior" },
                { "Blacked-Out 22\" Rims", "Gloss black 22\" 5-spoke forged wheels", "13500", "Wheels" },
                { "Black Interior Pack", "Piano black headliner, black chrome trim", "6500", "Interior" },
                { "Performance Exhaust", "Valved stainless exhaust, 680 HP black badge tune", "16500", "Performance" },
                { "Spirit of Ecstasy Dark", "Darkened stainless Spirit figurine", "1800", "Exterior" },
                { "Stealth Badging", "All badges deleted, replaced with debossed logos", "1200", "Exterior" },
                { "Sport Calibration", "Suspension, steering, throttle: sport default mode", "4500", "Performance" },
                { "Black Seatbelt Webbing", "Black seatbelt webbing replacing standard silver", "800", "Interior" },
        });

        Car bespoke = save(new Car("Sovereign Bespoke", "Bespoke", 2025, 450000,
                "Entirely handcrafted. No two are alike. The pinnacle of Sovereign.", CAR_IMAGES[9]));
        addColors(bespoke, "24K Gold Matte", "#D4AF37", "Titanium White", "#F4F4F4", "Imperial Purple", "#4B0082",
                "Carbon Fibre", "#2B2B2B");
        addMods(bespoke, new String[][] {
                { "Bespoke Paint Consult", "In-person custom colour development with our artists", "15000",
                        "Exterior" },
                { "1-of-1 Interior Design", "Fully bespoke interior: any material, any colour", "55000", "Interior" },
                { "Hand-Engraved Fascia", "Master engraver works any design into metal fascia", "12000", "Interior" },
                { "Commissioned Artwork", "Original artwork embedded in headrests or dashboard", "20000", "Interior" },
                { "Bespoke Engine Tune", "Worked engine, bespoke power output and character", "35000", "Performance" },
                { "Name Plates & Plaques", "Personalised owner plaques, engine bay and interior", "3500", "Interior" },
                { "Custom Wheel Design", "1-of-1 wheel design machined to specification", "25000", "Wheels" },
                { "Private Delivery Event", "Exclusive delivery ceremony at the Sovereign Atelier", "8000",
                        "Experience" },
                { "Bespoke Luggage Set", "8-piece matched luggage set in chosen interior leather", "18000",
                        "Interior" },
                { "Concierge Year Package", "12 months dedicated Sovereign personal concierge", "22000", "Experience" },
        });
    }

    private Car save(Car car) {
        return carRepository.save(car);
    }

    private void addColors(Car car,
            String n1, String h1, String n2, String h2,
            String n3, String h3, String n4, String h4) {
        colorRepository.save(new CarColor(n1, h1, car));
        colorRepository.save(new CarColor(n2, h2, car));
        colorRepository.save(new CarColor(n3, h3, car));
        colorRepository.save(new CarColor(n4, h4, car));
    }

    private void addMods(Car car, String[][] mods) {
        for (String[] m : mods)
            modRepository.save(new Mod(m[0], m[1], Double.parseDouble(m[2]), m[3], car));
    }

    private void seedUsers() {
        createUser("admin_tokhy", "tokhy@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_omar", "omar@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_mazen", "mazen@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_ali", "ali@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_youssef", "youssef@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_mayada", "mayada@sovereign.com", "Admin@1234", "ADMIN");
        createUser("admin_fo2sh", "fo2sh@sovereign.com", "Admin@1234", "ADMIN");

        String[][] users = {
                { "james_h", "james.hartley@gmail.com", "User@1234" },
                { "elena_v", "elena.vasquez@outlook.com", "User@1234" },
                { "kai_tanaka", "kai.tanaka@yahoo.com", "User@1234" },
                { "priya_m", "priya.menon@gmail.com", "User@1234" },
                { "luca_bianchi", "luca.bianchi@gmail.com", "User@1234" },
                { "sasha_petrov", "sasha.petrov@mail.ru", "User@1234" },
                { "amara_o", "amara.okafor@gmail.com", "User@1234" }, { "felix_w", "felix.weber@gmx.de", "User@1234" },
                { "nina_larsson", "nina.larsson@gmail.com", "User@1234" },
                { "carlos_r", "carlos.reyes@hotmail.com", "User@1234" },
                { "yuki_endo", "yuki.endo@yahoo.co.jp", "User@1234" },
                { "diana_k", "diana.kovacs@gmail.com", "User@1234" },
                { "rashid_a", "rashid.ali@gmail.com", "User@1234" },
                { "sophie_m", "sophie.martin@free.fr", "User@1234" },
                { "ethan_b", "ethan.brooks@gmail.com", "User@1234" },
                { "fatima_z", "fatima.zahra@gmail.com", "User@1234" },
                { "max_schneider", "max.schneider@web.de", "User@1234" },
                { "zara_k", "zara.khan@gmail.com", "User@1234" },
                { "pedro_s", "pedro.souza@gmail.com", "User@1234" },
                { "alice_dubois", "alice.dubois@gmail.com", "User@1234" },
                { "tariq_n", "tariq.nasser@gmail.com", "User@1234" },
                { "ingrid_h", "ingrid.hansen@gmail.com", "User@1234" },
                { "ryan_o", "ryan.oconnor@gmail.com", "User@1234" }, { "mei_lin", "mei.lin@gmail.com", "User@1234" },
                { "andrei_p", "andrei.popescu@gmail.com", "User@1234" },
                { "grace_a", "grace.adeyemi@gmail.com", "User@1234" },
                { "tom_walsh", "tom.walsh@gmail.com", "User@1234" },
                { "leila_r", "leila.rahimi@gmail.com", "User@1234" },
                { "marco_f", "marco.ferrari@gmail.com", "User@1234" }, { "aya_s", "aya.suzuki@gmail.com", "User@1234" },
                { "noah_c", "noah.carter@gmail.com", "User@1234" },
                { "chiara_r", "chiara.russo@gmail.com", "User@1234" },
                { "sam_williams", "sam.williams@gmail.com", "User@1234" },
                { "hana_kim", "hana.kim@gmail.com", "User@1234" },
                { "victor_m", "victor.moreau@gmail.com", "User@1234" },
                { "amina_d", "amina.diallo@gmail.com", "User@1234" },
                { "oliver_h", "oliver.hughes@gmail.com", "User@1234" },
                { "mia_johnson", "mia.johnson@gmail.com", "User@1234" },
                { "juan_garcia", "juan.garcia@gmail.com", "User@1234" },
                { "chloe_b", "chloe.bernard@gmail.com", "User@1234" },
                { "alex_brown", "alex.brown@gmail.com", "User@1234" },
                { "yuna_park", "yuna.park@gmail.com", "User@1234" },
                { "ibrahim_s", "ibrahim.said@gmail.com", "User@1234" },
                { "emma_l", "emma.laurent@gmail.com", "User@1234" },
                { "david_m", "david.morgan@gmail.com", "User@1234" },
                { "nadia_k", "nadia.karimova@gmail.com", "User@1234" },
                { "jake_t", "jake.taylor@gmail.com", "User@1234" },
                { "yasmine_b", "yasmine.benali@gmail.com", "User@1234" },
                { "liam_w", "liam.wilson@gmail.com", "User@1234" },
                { "sofia_e", "sofia.esposito@gmail.com", "User@1234" },
                { "adam_f", "adam.foster@gmail.com", "User@1234" },
                { "layla_h", "layla.hassan@gmail.com", "User@1234" },
                { "ben_clark", "ben.clark@gmail.com", "User@1234" },
                { "ana_silva", "ana.silva@gmail.com", "User@1234" },
                { "charlie_d", "charlie.davies@gmail.com", "User@1234" },
                { "rania_m", "rania.mahmoud@gmail.com", "User@1234" },
                { "harry_p", "harry.patel@gmail.com", "User@1234" },
                { "elle_thomas", "elle.thomas@gmail.com", "User@1234" },
                { "bilal_r", "bilal.raza@gmail.com", "User@1234" }, { "julia_c", "julia.costa@gmail.com", "User@1234" },
                { "mason_l", "mason.lee@gmail.com", "User@1234" }, { "nora_b", "nora.berg@gmail.com", "User@1234" },
                { "elias_k", "elias.kadic@gmail.com", "User@1234" },
                { "lily_chen", "lily.chen@gmail.com", "User@1234" },
                { "omar_f", "omar.farouk@gmail.com", "User@1234" },
                { "gemma_r", "gemma.roberts@gmail.com", "User@1234" },
                { "ivan_s", "ivan.smirnov@gmail.com", "User@1234" },
                { "aaliya_i", "aaliya.iqbal@gmail.com", "User@1234" },
                { "finn_o", "finn.oleary@gmail.com", "User@1234" },
                { "camille_d", "camille.dupont@gmail.com", "User@1234" },
                { "hassan_m", "hassan.malik@gmail.com", "User@1234" },
                { "anna_w", "anna.wagner@gmail.com", "User@1234" },
                { "leo_martin", "leo.martin@gmail.com", "User@1234" },
                { "sara_j", "sara.jovanovic@gmail.com", "User@1234" },
                { "caleb_a", "caleb.anderson@gmail.com", "User@1234" },
                { "maya_t", "maya.tanaka@gmail.com", "User@1234" },
                { "rami_g", "rami.ghazi@gmail.com", "User@1234" },
                { "freya_h", "freya.hansson@gmail.com", "User@1234" },
                { "tyler_j", "tyler.jones@gmail.com", "User@1234" },
                { "zeynep_a", "zeynep.aydin@gmail.com", "User@1234" },
                { "joel_b", "joel.baker@gmail.com", "User@1234" }, { "lena_v", "lena.vogt@gmail.com", "User@1234" },
                { "amir_r", "amir.rahman@gmail.com", "User@1234" }, { "eva_n", "eva.novak@gmail.com", "User@1234" },
                { "cole_s", "cole.scott@gmail.com", "User@1234" }, { "dina_a", "dina.aziz@gmail.com", "User@1234" },
                { "gabriel_f", "gabriel.ferreira@gmail.com", "User@1234" },
                { "isabel_l", "isabel.lopez@gmail.com", "User@1234" },
                { "mo_hussain", "mo.hussain@gmail.com", "User@1234" },
                { "tara_c", "tara.campbell@gmail.com", "User@1234" },
                { "nico_d", "nico.delacroix@gmail.com", "User@1234" }, { "muna_o", "muna.omar@gmail.com", "User@1234" },
                { "rob_hill", "rob.hill@gmail.com", "User@1234" }, { "yara_e", "yara.elshamy@gmail.com", "User@1234" },
                { "joel_p", "joel.phillips@gmail.com", "User@1234" }, { "hira_b", "hira.baig@gmail.com", "User@1234" },
                { "jack_miller", "jack.miller@gmail.com", "User@1234" },
                { "farida_m", "farida.mansour@gmail.com", "User@1234" },
        };
        for (String[] u : users)
            createUser(u[0], u[1], u[2], "USER");
    }

    private void createUser(String username, String email, String rawPassword, String role) {
        if (userRepository.findByUsername(username).isPresent())
            return;
        userRepository.save(new User(username, email, passwordEncoder.encode(rawPassword), role));
    }
}
