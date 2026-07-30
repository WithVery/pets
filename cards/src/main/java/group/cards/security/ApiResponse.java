package group.cards.security;

public record ApiResponse(int status,
                          String error,
                          String message
){}
