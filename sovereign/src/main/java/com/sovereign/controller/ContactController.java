package com.sovereign.controller;

import com.sovereign.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ContactController — handles the contact and booking form.
 *
 * All @RequestParam are required=false with empty defaults.
 * This means the controller gracefully handles:
 *   - The /contact page form (fields: name, email, message)
 *   - The homepage booking form (fields: firstName/fullName, email, phone, model)
 * Both send to POST /contact. Without required=false, any missing field = 400.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String showContactForm() {
        return "contact";
    }

    @PostMapping
    public String submitContact(
            // Standard contact form fields
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String message,
            // Homepage booking form may use these instead
            @RequestParam(required = false, defaultValue = "") String fullName,
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String phone,
            @RequestParam(required = false, defaultValue = "") String model) {

        // Resolve name from whichever field was actually submitted
        String finalName = !name.isEmpty() ? name
                : !fullName.isEmpty() ? fullName
                : (firstName + " " + lastName).trim();

        // Build message from whichever fields came in
        String finalMessage = !message.isEmpty() ? message
                : buildMessage(model, phone);

        // Only save if we have at least a name and email
        if (!finalName.isEmpty() && !email.isEmpty()) {
            contactService.saveMessage(finalName, email, finalMessage);
        }

        return "redirect:/contact?success=true";
    }

    private String buildMessage(String model, String phone) {
        StringBuilder sb = new StringBuilder();
        if (!model.isEmpty()) sb.append("Interested in: ").append(model).append(". ");
        if (!phone.isEmpty()) sb.append("Phone: ").append(phone);
        return sb.length() > 0 ? sb.toString() : "Inquiry submitted via homepage form.";
    }
}
