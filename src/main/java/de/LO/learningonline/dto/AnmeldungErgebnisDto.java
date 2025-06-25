package de.LO.learningonline.dto;

public class AnmeldungErgebnisDto {
    private final boolean success;
    private final String message;

    public AnmeldungErgebnisDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
